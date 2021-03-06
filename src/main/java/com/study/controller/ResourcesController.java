package com.study.controller;

import com.github.pagehelper.PageInfo;
import com.study.model.Resources;
import com.study.service.ResourcesService;
import com.study.shiro.ShiroService;
import com.study.util.DataGridResultInfo;
import com.study.util.PageBean;
import com.study.util.ResultUtil;

import org.apache.shiro.SecurityUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resources")
public class ResourcesController {

    @Resource
    private ResourcesService resourcesService;
    @Resource
    private ShiroService shiroService;


    @RequestMapping("/resourcesWithSelected")
    public List<Resources> resourcesWithSelected(Integer rid){
        return resourcesService.queryResourcesListWithSelected(rid);
    }

    @RequestMapping("/loadMenu")
    public List<Resources> loadMenu(){
        Map<String,Object> map = new HashMap<>();
        Integer userid = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        map.put("type",1);
        map.put("userid",userid);
        List<Resources> resourcesList = resourcesService.loadUserResources(map);
        return resourcesList;
    }

    //@CacheEvict(cacheNames="resources", allEntries=true)
    @RequestMapping(value = "/add")
    public String add(Resources resources){
        try{
            resourcesService.save(resources);
            //更新权限
            shiroService.updatePermission();
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    //@CacheEvict(cacheNames="resources", allEntries=true)
    @RequestMapping(value = "/delete")
    public String delete(Integer id){
        try{
            resourcesService.delete(id);
            //更新权限
            shiroService.updatePermission();
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    
    @RequestMapping(value = "/getMenu")
    public DataGridResultInfo getMenu(HttpServletRequest request, HttpServletResponse response,PageBean page){
      Resources example=new Resources();
      example.setType(1);
      List<Resources> queryByType = resourcesService.queryByType(example,page);
      PageInfo<Resources> pageInfo=new PageInfo<Resources>(queryByType);
      return ResultUtil.createDataGridResult(pageInfo.getTotal(), pageInfo.getList());
    }
}
