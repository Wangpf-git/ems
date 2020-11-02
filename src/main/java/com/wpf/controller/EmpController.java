package com.wpf.controller;

import com.wpf.entity.Emp;
import com.wpf.entity.Result;
import com.wpf.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("emp")
@CrossOrigin
@Slf4j
public class EmpController {


    @Autowired
    private EmpService empService;

    @Value("${upload.dir}")
    private String realPath;



    /**
     * 修改员工信息
     */
    @PostMapping("update")
    public Result update(MultipartFile pic, Emp emp) throws IOException {
        Result result = new Result();

        try{
            //对接收文件做base64
            String picpath = Base64Utils.encodeToString(pic.getBytes());
            emp.setPath(picpath);
            //处理文件上传
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+extension;
            pic.transferTo(new File(realPath,newFileName));

            //修改景点信息
            empService.update(emp);
            result.setMsg("修改员工信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 查询员工信息
     */
    @GetMapping("findOne")
    public Emp findOne(String id){
        return empService.findOne(id);
    }

    /**
     * 删除员工信息
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result delete(String id){
        Result result = new Result();
        try{
            empService.delete(id);
            result.setMsg("删除员工信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 保存景点信息
     *
     * @param pic
     * @return
     */
    @PostMapping("save")
    public Result save(MultipartFile pic, Emp emp) throws IOException {
        Result result = new Result();
        try {
            //文件上传
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
            //base64编码处理
            emp.setPath(Base64Utils.encodeToString(pic.getBytes()));
            //文件上传
            File file = new File(realPath);
            pic.transferTo(new File(file,newFileName));
            //保存place对象
            empService.save(emp);
            result.setMsg("保存员工信息成功!!!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }

        return result;
    }


    /**
     * 根据省份id查询景点的方法
     */
    @GetMapping("findAllEmp")
    public Map<String, Object> findAllEmp(Integer page, Integer rows, String groupId) {
        HashMap<String, Object> result = new HashMap<>();
        page = page == null ? 1 : page;
        rows = rows == null ? 3 : rows;
        //景点集合
        List<Emp> emps = empService.findByGroupIdPage(page, rows, groupId);
        //处理分页
        Integer counts = empService.findByGroupIdCounts(groupId);
        //总页数
        Integer totalPage = counts % rows == 0 ? counts / rows : counts / rows + 1;
        result.put("emps", emps);
        result.put("page", page);
        result.put("counts", counts);
        result.put("totalPage", totalPage);
        return result;
    }
}
