package com.study.controller;

import com.github.pagehelper.PageInfo;
import com.study.model.Resources;
import com.study.model.Role;
import com.study.model.RoleResources;
import com.study.model.User;
import com.study.service.RoleResourcesService;
import com.study.service.RoleService;
import com.study.util.DataGridResultInfo;
import com.study.util.PageBean;
import com.study.util.ResultUtil;

import org.apache.shiro.SecurityUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private RoleResourcesService roleResourcesService;

    @RequestMapping(value = "/getData")
    public DataGridResultInfo getData(HttpServletRequest request, HttpServletResponse response,PageBean page){
      List<Role> queryByType = roleService.selectByPage(page);
      PageInfo<Role> pageInfo=new PageInfo<Role>(queryByType);
      return ResultUtil.createDataGridResult(pageInfo.getTotal(), pageInfo.getList());
    }
    @RequestMapping("/rolesWithSelected")
    public List<Role> rolesWithSelected(Integer uid){
        return roleService.queryRoleListWithSelected(uid);
    }

    //分配角色
    @RequestMapping("/saveRoleResources")
    public String saveRoleResources(RoleResources roleResources){
        if(StringUtils.isEmpty(roleResources.getRoleid()))
            return "error";
        try {
            roleResourcesService.addRoleResources(roleResources);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/add")
    public String add(Role role) {
        try {
            roleService.save(role);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id){
        try{
            roleService.delRole(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }



}
