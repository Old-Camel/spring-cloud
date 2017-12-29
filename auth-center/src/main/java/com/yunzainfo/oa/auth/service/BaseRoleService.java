package com.yunzainfo.oa.auth.service;

import com.yunzainfo.oa.auth.entity.BaseAuthority;
import com.yunzainfo.oa.auth.entity.BaseRole;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface BaseRoleService {

    //查询所有角色
    List<BaseRole> findAllRoles();

    //添加角色
    boolean addRole(String roleName,String roleDesc);

    //修改角色
    boolean updateRole(String roleId,String roleName,String roleDesc);

    //修改角色权限
    boolean updateRole(String roleId,List<String> AuthrityIds);

    //删除角色
    boolean deleteRole(String roleId);

    //查询单角色权限
    Set<BaseAuthority> findAllAuthorityByRoleId(String roleId);

    //查询单个角色
    BaseRole findOneRole(String roleId);

    //根据名称查询角色
    BaseRole findOneRoleByName(String rolename);

    //分页查询所有角色
    Page<BaseRole> findAllRolesByPage(Integer page,Integer size);

}
