package com.yunzainfo.oa.auth.web;

import com.yunzainfo.oa.auth.entity.BaseAuthority;
import com.yunzainfo.oa.auth.service.BaseAuthrityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RefreshScope
@Api(value = "BaseAuthrityController")
public class BaseAuthrityController {

    @Autowired
    BaseAuthrityService authrityService;

    //查询所有Action路径
    //查询所有角色
    @ApiOperation(value="查询Action", notes="查询所有Action")
    @ApiImplicitParams({
    })
    @GetMapping(value = "/baseauthority")
    List<BaseAuthority> findAllBaseAuthority(){
        return authrityService.findAllBaseAuthority();
    }


    @ApiOperation(value="添加Action", notes="添加Action路径")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Action名称", required = true,paramType = "query",dataType = "String",defaultValue = "查询所有"),
            @ApiImplicitParam(name = "value", value = "Action值", required = true,paramType = "query",dataType = "String",defaultValue = "/baseauthority")
    })
    @PostMapping(value = "/baseauthority")
        //添加Action路径
    boolean addBaseAuthority(String name,String value){
        return authrityService.addBaseAuthority(name,value);
    }

    @ApiOperation(value="修改Action", notes="修改Action名称,值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorityid", value = "ACTION主键", required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "newname", value = "Action新名称", required = true,paramType = "query",dataType = "String",defaultValue = "查询所有"),
            @ApiImplicitParam(name = "newvalue", value = "Action新值", required = true,paramType = "query",dataType = "String",defaultValue = "/baseauthority")
    })
    @PutMapping(value = "/baseauthority")
        //修改Action名称路径
    boolean updateBaseAuthority(String authorityid,String newname,String newvalue){
        return authrityService.updateBaseAuthorityValue(authorityid,newname,newvalue);
    }

    @ApiOperation(value="删除Action", notes="删除Action")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorityid", value = "ACTION主键", required = true,paramType = "query",dataType = "String"),
    })
    @DeleteMapping(value = "/baseAuthority/{authorityid}")
    //删除Action
    boolean deleteBaseAuthority(String authorityid){
        return authrityService.deleteBaseAuthority(authorityid);
    }


    @ApiOperation(value="查询单个Action", notes="查询单个Action")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorityid", value = "ACTION主键", required = true,paramType = "query",dataType = "String"),
    })
    @GetMapping(value = "/baseauthority/{authorityid}")
    //查询单个Action
    BaseAuthority findOneAuthority(String authorityid){
        return authrityService.findOneAuthority(authorityid);
    }
}
