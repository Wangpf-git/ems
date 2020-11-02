package com.wpf.groups.test;

import com.wpf.EmsVueApplication;
import com.wpf.entity.Group;
import com.wpf.service.GroupService;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = EmsVueApplication.class)
@RunWith(SpringRunner.class)
public class TestGroupService {

    @Autowired
    private GroupService groupService;

    @Test
    public void testFindByPage(){
        List<Group> list = groupService.findByPage(1,5);
        list.forEach(gr->{
            System.out.println(gr);
        });
    }

}
