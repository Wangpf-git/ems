package com.wpf.service;

import com.wpf.dao.EmpDAO;
import com.wpf.entity.Emp;
import com.wpf.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpServiceImpl implements EmpService{

    @Autowired
    private EmpDAO empDAO;
    @Autowired
    private GroupService groupService;

    @Override
    public void update(Emp emp) {
        empDAO.update(emp);

        Group group = groupService.findOne(emp.getGroupid());
        //更新省份信息的景点个数
        group.setEmpcounts(group.getEmpcounts()+1);
        groupService.update(group);
    }

    @Override
    public Emp findOne(String id) {
        return empDAO.findOne(id);
    }

    @Override
    public void delete(String id) {
        //直接删除景点  更新省份景点个数 删除景点
        Emp emp = empDAO.findOne(id);
        Group group = groupService.findOne(emp.getGroupid());
        group.setEmpcounts(group.getEmpcounts()-1);
        groupService.update(group);
        //删除景点信息
        empDAO.delete(id);
    }

    @Override
    public void save(Emp emp) {
        //保存景点
        empDAO.save(emp);
        //查询原始省份信息
        Group group = groupService.findOne(emp.getGroupid());
        //更新省份信息的景点个数
        group.setEmpcounts(group.getEmpcounts()+1);
        groupService.update(group);

    }

    @Override
    public List<Emp> findByGroupIdPage(Integer page, Integer rows, String groupId) {
        int start = (page-1)*rows;
        return empDAO.findByGroupIdPage(start,rows,groupId);
    }

    @Override
    public Integer findByGroupIdCounts(String groupId) {
        return empDAO.findByGroupIdCounts(groupId);
    }
}
