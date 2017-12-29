package com.yunzainfo.oa.auth.web;

import com.yunzainfo.oa.auth.entity.BaseAuthority;
import com.yunzainfo.oa.auth.entity.BaseRole;
import com.yunzainfo.oa.auth.service.BaseRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RefreshScope
@Api(value = "BaseRoleController")
public class BaseRoleController {

    @Autowired
    BaseRoleService service;

    //查询所有角色
    @ApiOperation(value="查询角色", notes="查询所有角色")
    @ApiImplicitParams({
    })
    @GetMapping(value = "/baserole")
    List<BaseRole> findAllRoles(){
        return service.findAllRoles();
    }

    //添加角色
    @ApiOperation(value="添加角色", notes="添加系统角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rolename", value = "角色名称", required = true,paramType = "query",dataType = "String",defaultValue = "测试"),
            @ApiImplicitParam(name = "roledesc", value = "角色描述", required = true,paramType = "query",dataType = "String",defaultValue = "测试")
    })
    @PostMapping(value = "/baserole")
    boolean addRole(String rolename, String roledesc){
        return  service.addRole(rolename,roledesc);
    }

    //修改角色
    @ApiOperation(value="修改角色", notes="修改系统角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", value = "角色主键", required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "rolename", value = "角色名称", required = true,paramType = "query",dataType = "String",defaultValue = "测试修改"),
            @ApiImplicitParam(name = "roledesc", value = "角色描述", required = true,paramType = "query",dataType = "String",defaultValue = "测试修改")
    })
    @PutMapping(value = "/baserole")
    boolean updateRole(String roleid,String rolename,String roledesc){
        return  service.updateRole(roleid,rolename,roledesc);
    }

    //修改角色权限
    @ApiOperation(value="修改角色权限", notes="修改系统角色权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", value = "角色主键", required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "authrityids", value = "Action权限主键的集合", required = true)
    })
    @PutMapping(value = "/baserole/authority")
    boolean updateRole(String roleid,List<String> authrityids){
        return service.updateRole(roleid,authrityids);
    }

    //删除角色
    @ApiOperation(value="删除角色", notes="删除系统角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", value = "角色主键", required = true,paramType = "query",dataType = "String"),
    })
    @DeleteMapping(value = "/baserole/{roleid}")
    boolean deleteRole(String roleid){
        return service.deleteRole(roleid);
    }

    //查询单角色权限
    @ApiOperation(value="查询角色权限", notes="查询角色下所有可访问Action的集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", value = "角色主键", required = true,paramType = "query",dataType = "String"),
    })
    @GetMapping("/baserole/{roleid}/authority")
    Set<BaseAuthority> findAllAuthorityByRoleId(String roleid){
        return service.findAllAuthorityByRoleId(roleid);
    }


    @ApiOperation(value="查询单个角色", notes="查询单个角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", value = "角色主键", required = true,paramType = "query",dataType = "String"),
    })
    @GetMapping("/baserole/{roleid}")
    //查询单个角色
    BaseRole findOneRole(String roleid){
        return service.findOneRole(roleid);
    }

    @ApiOperation(value="查询单个角色", notes="根据角色名称查询单个角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rolename", value = "角色名称", required = true,paramType = "query",dataType = "String"),
    })
    @GetMapping("/baserole/{rolename}")
    //根据名称查询角色
    BaseRole findOneRoleByName(String rolename){
        return  service.findOneRoleByName(rolename);
    }


    @ApiOperation(value="查询角色分页", notes="根据角色名称查询单个角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true,paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "一页多少条", required = true,paramType = "query",dataType = "Integer")
    })
    @GetMapping("/baserole/{page}/{size}")
    //分页查询所有角色
    Page<BaseRole> findAllRolesByPage(Integer page, Integer size){
        return service.findAllRolesByPage(page,size);
    }

}
