package com.wpf.dao;

import com.wpf.entity.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface EmpDAO extends BaseDAO<Emp,String>{

    List<Emp>  findByGroupIdPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("groupId") String groupId);


    Integer findByGroupIdCounts(String groupId);

}


