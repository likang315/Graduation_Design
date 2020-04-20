package com.ly.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ly.entity.background.Account;
import com.ly.mapper.ActivityMapper;
import com.ly.mapper.BackExpressMapper;
import com.ly.mapper.BackStoreMapper;
import com.ly.service.BackExpressService;
import com.ly.service.BackStoreService;
import com.ly.util.FTPLinuxUtils;

/**
 * 快递公司的后台处理 实现
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-13 16:49
 */
@Transactional
@Service("backExpressService")
public class BackExpressServiceImpl implements BackExpressService {

    @Autowired
    private BackExpressMapper backExpressMapper;

    /**
     * 获取到快递公司的清单
     */
    @Override
    public List<Map<String, Object>> getCompanyList(Map param) {
        return backExpressMapper.getCompanyList(param);
    }

    /**
     * 新增快递公司
     */

    @Override
    public boolean addExpress(Map<String, Object> expressInfo, HttpSession session) {
        boolean flag = false;
        String uuid = UUID.randomUUID().toString();
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Account account = (Account) session.getAttribute("userSession");
        String createPhone = account.getAccountName();
        uuid = uuid.replace("-", "");
        // 查询是否存在
        if (!this.checkExist(expressInfo)) {
            expressInfo.put("id", uuid);
            expressInfo.put("create_time", createTime);
            expressInfo.put("create_phone", createPhone);
            if (backExpressMapper.addExpress(expressInfo) > 0) {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 查询当前快递公司信息在库中是否存在
     *
     * @param expressInfo
     * @return
     */
    public boolean checkExist(Map<String, Object> expressInfo) {
        boolean flag = false;
        //查询是否存在
        if (backExpressMapper.checkExise(expressInfo) > 0) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 删除快递信息
     */
    @Override
    public boolean deleteStore(String id) {
        boolean flag = false;
        int num = 0;
        String[] split = id.split(",");
        for (int i = 0; i < split.length; i++) {
            num = backExpressMapper.deleteExpress(split[i]);
        }
        if (num > 0) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 获取快递公司的总数量
     */
    @Override
    public Integer getCompanycount() {
        return backExpressMapper.getCompanycount();

    }

    /**
     * 获取快递员的数量
     */
    @Override
    public Integer getCourierCount() {
        return backExpressMapper.getCourierCount();
    }

    /**
     * 获取快递员清单
     */
    @Override
    public List<Map<String, Object>> getCourierList(Map param) {

        return backExpressMapper.getCourierList(param);

    }

    /**
     * 获取到快递公司的信息
     */
    @Override
    public List<Map<String, Object>> getCompany() {
        return backExpressMapper.getCompany();
    }

    /**
     * 新增快递员信息,分为单个、批量导入处理
     */
    @Override
    public String addCourier(Map map, MultipartFile file, HttpSession session) {
        String info = "";
        // 获取当前维护数据的用户
        Account account = (Account) session.getAttribute("userSession");
        String createPhone = account.getAccountName();
        //如果文件是空的，执行单个店员信息维护的方法
        if (file == null) {
            if (!this.cheackCourier(map)) {
                //添加单个用户信息
                map.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                if (backExpressMapper.addCourier(map) > 0) {
                    info = "successful";
                } else {
                    info = "failture";
                }
            } else {
                info = "failture";
            }

        } else {
            //执行批量信息维护的方法
            // 获取文件的名称
            String fileName = file.getOriginalFilename();
            //获取文件流
            InputStream localInputStream = null;
            try {
                localInputStream = file.getInputStream();
            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }

            // 判断文件名是否是03Excel
            boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");
            // 1、读取工作簿
            Workbook workbook = null;
            try {
                workbook = is03Excel ? new HSSFWorkbook(localInputStream) : new XSSFWorkbook(localInputStream);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            // 2、读取工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 3、读取行
            int sumRow = sheet.getPhysicalNumberOfRows();
            int failPhone = 0;
            // 如果行数大于1
            if (sumRow > 1) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                // 逐行读取excel数据
                for (int k = 1; k < sumRow; k++) {
                    Map<String, Object> m = new HashMap<String, Object>();
                    // 4.读取单元格的数据
                    Row row = sheet.getRow(k);

                    // 解析第一列（快递员姓名）
                    String real_name = "";
                    try {
                        real_name = row.getCell(0).getStringCellValue();
                    } catch (Exception e) {
                        double dreal_name = row.getCell(0).getNumericCellValue();
                        real_name = BigDecimal.valueOf(dreal_name).toString();
                    }
                    m.put("real_name", real_name);

                    // 解析第二列（快递员电话）
                    String accountName = "";
                    try {
                        accountName = row.getCell(1).getStringCellValue();
                    } catch (Exception e) {
                        double daccountName = row.getCell(1).getNumericCellValue();
                        accountName = BigDecimal.valueOf(daccountName).toPlainString();
                    }
                    //验证电话号码是否符合规范，通过时，执行添加操作
                    if (accountName.matches("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$")) {
                        m.put("accountName", accountName);
                    } else {
                        //由于电话号码不符合规范的电话数量
                        failPhone++;
                        //结束本次循环
                        continue;
                    }


                    //设置账号的登录密码(默认密码为123456)
                    m.put("password", "4QrcOUm6Wau+VuBX8g+IPg==");
                    //设置账号类型2为
                    m.put("accountType", "2");
                    //设置账号的默认state
                    m.put("state", "1");
                    //设置账号维护入库时间
                    m.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    //设置账号的默认groupid
                    m.put("groupId", "112");
                    //设置默认的aut_flag(2)
                    m.put("auth_flag", "2");
                    m.put("createUser", createPhone);
                    m.put("company", map.get("company"));
                    //将循环完成的一条快递员的信息添加到list
                    list.add(m);
                }
                //将循环遍历完成的店员数据添加到临时表中
                if (backExpressMapper.addCourierTemp(list) > 0) {
                    //当添加到临时表成功后,查询出临时表的数据根据电话号码进行去重，
                    List<Map<String, Object>> ls = backExpressMapper.getCourierGrpAcc(createPhone);
                    //得到去过自重的数据，清除表中本次导入的数据
                    if (backExpressMapper.deleteCourierTemp(createPhone) > 0) {
                        //当删除完成后，将去重后的数据导入到临时表中
                        if (backExpressMapper.addCourierTemp(ls) > 0) {
                            //将去重后的数据导入到临时表成功后,比对获得最终可以入主表的数据
                            List<Map<String, Object>> lsFinal = backExpressMapper.getFinalCourier(createPhone);
                            //有数据
                            if (lsFinal.size() > 0) {
                                //以list形式插入用户信息
                                backExpressMapper.addCourierList(lsFinal);
                                //插入数据后，清除临时表的数据
                                backExpressMapper.deleteCourierTemp(createPhone);
                                //数据维护成功
                                info = "快递员信息维护成功,其中有" + failPhone + "条数据由于快递员电话号码不符合规范，未进行维护。";
                            } else {
                                //无符合规范的数据
                                info = "快递员信息维护失败。";
                                //删除临时表中的数据
                                backExpressMapper.deleteCourierTemp(createPhone);
                            }
                        }
                    }


                }

            }
        }
        return info;
    }

    /**
     * 检查Courier是否存在
     *
     * @param map
     * @return
     */
    public boolean cheackCourier(Map map) {
        boolean flag = false;
        // 验证当前快递员信息是否在库
        if (backExpressMapper.cheackCourier(map) > 0) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;

    }

    /**
     * 根据id删除快递员信息
     */

    @Override
    public boolean deleteCourier(String id) {

        boolean flag = false;
        int num = 0;
        String[] split = id.split(",");
        for (int i = 0; i < split.length; i++) {
            num = backExpressMapper.deleteCourier(split[i]);
        }
        if (num > 0) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 根据快递员的id查询出信息
     */
    @Override
    public Map<String, Object> getCourierInfo(String id) {
        return backExpressMapper.getCourierInfo(id);

    }

    @Override
    public boolean modifyCourier(Map courierInfo) {
        boolean flag;
        if (backExpressMapper.modifyCourier(courierInfo) > 0) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }
}
	
	
	
	
	
		
		
	

	
	
