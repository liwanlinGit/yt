package com.study.service;

import com.study.model.Role;
import com.study.util.PageBean;

import java.util.List;

public interface RoleService extends IService<Role> {

    public List<Role> queryRoleListWithSelected(Integer uid);

    List<Role> selectByPage(PageBean bean);
    public List<Role> queryAll(PageBean bean);

    /**
     * 删除角色 同时删除角色资源表中的数据
     * @param roleid
     */
    public void delRole(Integer roleid);
}
