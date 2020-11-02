package com.wpf.service;

import com.wpf.entity.Group;

import java.util.List;

public interface GroupService {

    //参数1:当前页 //参数2:每页显示记录数
    List<Group> findByPage(Integer page, Integer rows);


    //查询总跳数
    Integer findTotals();

    //保存省份方法
    void save(Group group);

    //删除省份的方法
    void delete(String id);

    //查询省份信息
    Group findOne(String id);

    //修改省份信息
    void update(Group group);
}



