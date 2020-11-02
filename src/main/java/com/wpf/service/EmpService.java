package com.wpf.service;

import com.wpf.entity.Emp;

import java.util.List;

public interface EmpService {

    List<Emp> findByGroupIdPage(Integer page,Integer rows,String groupId);


    Integer findByGroupIdCounts(String groupId);


    void save(Emp emp);

    void delete(String id);

    Emp findOne(String id);

    void update(Emp emp);

}
