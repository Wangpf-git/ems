package com.wpf.service;

import com.wpf.dao.GroupDAO;
import com.wpf.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {



    @Autowired
    private GroupDAO groupDAO;


    @Override
    public List<Group> findByPage(Integer page, Integer rows) {
        int start = (page-1)*rows;
        return groupDAO.findByPage(start,rows);
    }

    @Override
    public Group findOne(String id) {
        return groupDAO.findOne(id);
    }

    @Override
    public void update(Group group) {
        groupDAO.update(group);
    }

    @Override
    public void delete(String id) {
        groupDAO.delete(id);
    }

    @Override
    public void save(Group group) {
        group.setEmpcounts(0);//景点个数为零
        groupDAO.save(group);
    }

    @Override
    public Integer findTotals() {
        return groupDAO.findTotals();
    }
}


