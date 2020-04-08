package com.ly.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.entity.background.Account;
import com.ly.entity.background.Group;
import com.ly.mapper.GroupMapper;
import com.ly.pulgin.mybatis.plugin.PageView;
import com.ly.service.GroupService;
import com.ly.service.vo.TreeVo;

/**
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-06 11:05
 */
@Transactional
@Service("groupService")
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public PageView query(PageView pageView, Group t) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", t);
        List<Group> list = groupMapper.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public List<Group> queryAll(Group t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String id) throws Exception {
        groupMapper.delete(id);

    }

    @Override
    public void update(Group t) throws Exception {
        Group g = groupMapper.getById(String.valueOf(t.getParentId()));
        t.setLevel(g.getLevel() + 1);
        groupMapper.update(t);

    }

    @Override
    public Group getById(String id) {
        // TODO Auto-generated method stub
        return groupMapper.getById(id);
    }

    @Override
    public void add(Group t) throws Exception {
        Group g = groupMapper.getById(String.valueOf(t.getParentId()));
        t.setLevel(g.getLevel() + 1);
        groupMapper.add(t);

    }

    @Override
    public List<Group> queryAll(Account user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TreeVo> getTreeGroup(int groupId) {
        Group g = groupMapper.getById(String.valueOf(groupId));
        TreeVo root = new TreeVo();
        root.id = Integer.parseInt(g.getId());
        root.text = g.getName();

        List<TreeVo> list = new ArrayList<TreeVo>(1);
        list.add(root);

        addTreeGroup(root);

        return list;
    }

    /**
     * 获取子节点
     *
     * @param root
     */
    private void addTreeGroup(TreeVo root) {
        List<Group> groups = groupMapper.getGroupByParentId(root.id);
        if (root.children == null)
            root.children = new ArrayList<TreeVo>();

        for (Group group : groups) {
            TreeVo t = new TreeVo(Integer.parseInt(group.getId()), group.getName());
            root.children.add(t);
            addTreeGroup(t);
        }
    }

    private void addTreeGroup(TreeVo root, int groupId) {
        List<Group> groups = groupMapper.getGroupByParentId(root.id);
        if (root.children == null)
            root.children = new ArrayList<TreeVo>();

        for (Group group : groups) {
            TreeVo t = new TreeVo(Integer.parseInt(group.getId()), group.getName());
            if (t.id == groupId)
                t.checked = true;

            root.children.add(t);
            addTreeGroup(t, groupId);
        }
    }

    @Override
    public List<TreeVo> getTreeGroup(int groupId, int userGroupid) {
        Group g = groupMapper.getById(String.valueOf(userGroupid));
        TreeVo root = new TreeVo();
        root.id = Integer.parseInt(g.getId());
        root.text = g.getName();
        if (root.id == groupId)
            root.checked = true;

        addTreeGroup(root, groupId);

        List<TreeVo> list = new ArrayList<TreeVo>(1);
        list.add(root);
        return list;
    }

    @Override
    public PageView queryGroup(PageView pageView, Group group, int groupId) {

        Group g = groupMapper.getById(String.valueOf(groupId));

        List<Group> res = new ArrayList<Group>();
        res.add(g);

        addGroupByParent(g, res);

        // pageView.setRecords(res);
        pageView.setRowCount(res.size());

        if (pageView.getStartPage() > res.size())
            ;
        else {
            if (pageView.getPageSize() > res.size() - pageView.getStartPage())
                pageView.setRecords(res.subList(pageView.getStartPage(), res.size()));
            else

                pageView.setRecords(
                        res.subList(pageView.getStartPage(), pageView.getStartPage() + pageView.getPageSize()));
        }

        // pageView.setRecords(res.subList(pageView.getStartPage(),
        // res.size()));
        return pageView;
    }

    private void addGroupByParent(Group g, List<Group> groups) {
        List<Group> group = groupMapper.getGroupByParentId(Integer.parseInt(g.getId()));
        for (Group group2 : group) {
            groups.add(group2);
            addGroupByParent(group2, groups);
        }
    }

    @Override
    public List<Group> getGroupsById(String id) {

        List<Group> list = this.groupMapper.getGroupsById(id);
        return list;
    }

    @Override
    public List<Integer> getAllGroupsId(int groupId) {
        List<Group> g = this.groupMapper.getGroupByParentId(groupId);
        List<Integer> gs = new ArrayList<Integer>();
        gs.add(groupId);
        for (Group group : g) {
            gs.add(Integer.parseInt(group.getId()));
            getGroupIds(gs, Integer.parseInt(group.getId()));
        }

        return gs;
    }

    private void getGroupIds(List<Integer> gs, Integer g) {
        List<Group> result = this.groupMapper.getGroupByParentId(g);
        if (result != null && result.size() > 0) {
            for (Group group : result) {
                gs.add(Integer.parseInt(group.getId()));
                getGroupIds(gs, Integer.parseInt(group.getId()));
            }
        }

    }


    @Override
    public List<Long> getAllGroupId(Long rootid) {
        List<Long> s = this.groupMapper.getChildId(rootid);
        if (s == null) {
            return null;
        }
        return s;
    }

    /**
     * 根据组织机构名称获取信息
     */
    @Override
    public List<Group> getNameToInfo(String name) {
        // TODO Auto-generated method stub
        return groupMapper.getNameToInfo(name);
    }

    @Override
    public List<Long> getChildId(Long parentId) {
        // TODO Auto-generated method stub
        return groupMapper.getChildId(parentId);
    }


}
