package com.wpf.controller;

import com.wpf.entity.Group;
import com.wpf.entity.Result;
import com.wpf.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("group")
public class GroupController {

    @Autowired
    private GroupService groupService;
    /**
     * 修改部门信息方法
     */
    @PostMapping("update")
    public Result update(@RequestBody Group group) {
        Result result = new Result();
        try {
            groupService.update(group);
            result.setMsg("修改部门信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 查询一个部门信息
     */
    @GetMapping("findOne")
    public Group findOne(String id) {
        return groupService.findOne(id);
    }


    /**
     * 删除部门信息
     *
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result delete(String id) {
        Result result = new Result();
        try {
            groupService.delete(id);
            result.setMsg("删除部门信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("删除部门信息失败!!!");
        }
        return result;
    }


    /**
     * 保存部门信息
     *
     * @param group
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody Group group) {
        Result result = new Result();
        try {
            groupService.save(group);
            result.setMsg("保存部门信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("保存部门信息失败!!!");
        }
        return result;
    }

    /**
     * 查询所有
     *
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("findByPage")
    public Map<String, Object> findByPage(Integer page, Integer rows) {
        page = page == null ? 1 : page;
        rows = rows == null ? 4 : rows;
        HashMap<String, Object> map = new HashMap<>();
        //分页处理
        List<Group> groups = groupService.findByPage(page, rows);
        //计算总页数
        Integer totals = groupService.findTotals();
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;
        map.put("groups", groups);
        map.put("totals", totals);
        map.put("totalPage", totalPage);
        map.put("page", page);
        return map;
    }
}
