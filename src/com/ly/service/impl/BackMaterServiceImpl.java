package com.ly.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.entity.background.Account;
import com.ly.mapper.BackMaterialMapper;
import com.ly.service.BackMaterialService;

/**
 * 门店的后台处理实现
 */
@Transactional
@Service("backmaterialService")
public class BackMaterServiceImpl implements BackMaterialService {
    @Autowired
    private BackMaterialMapper backMaterialMapper;

    @Override
    public Integer getMaterialCount() {
        return backMaterialMapper.getMaterialCount();
    }

    @Override
    public List<Map<String, Object>> list(Map m) {
        return backMaterialMapper.list(m);
    }

    @Override
    public Map<String, Object> getMaterialInfo(String id) {

        return backMaterialMapper.getMaterialInfo(id);
    }

    @Override
    public boolean modify(Map m) {
        boolean flag = false;
        //	if(!this.checkOne(m)){
        if (backMaterialMapper.modify(m) > 0) {
            flag = true;
        } else {
            flag = false;
        }
		/*} else {
			flag = false;
		}*/

        return flag;
    }

    @Override
    public boolean deleteMaterial(String id) {
        boolean flag = false;
        int num = 0;
        String[] split = id.split(",");
        for (int i = 0; i < split.length; i++) {
            num = backMaterialMapper.deleteMaterial(split[i]);
        }
        if (num > 0) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 物资信息
     */
    @Override
    public boolean add(Map m, HttpServletRequest request) {
        boolean flag = false;
        //获取当前登录的信息
        Account ac = (Account) request.getSession().getAttribute("userSession");
        //获取当前登录的用户账号
        String userName = ac.getAccountName();
        m.put("create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        m.put("create_phone", userName);
        //验证物资的唯一性
        if (!this.checkOne(m)) {
            //新增物资信息
            if (backMaterialMapper.add(m) > 0) {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;

    }

    /**
     * 验证唯一性
     *
     * @param m
     * @return
     */
    public boolean checkOne(Map m) {
        boolean flag = false;
        //验证唯一性
        if (backMaterialMapper.checkOne(m) > 0) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }
}
