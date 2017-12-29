package com.yunzainfo.oa.auth.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.yunzainfo.oa.auth.entity.BaseDepartMent;
import com.yunzainfo.oa.auth.entity.BaseUser;
import com.yunzainfo.oa.auth.graphql.UserSchema;
import com.yunzainfo.oa.auth.service.BaseUserService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.Optional;

/**
 * Created by admin on 2017/12/27.
 */
@RestController
@RefreshScope
public class BaseUserController {
    @Autowired
    private BaseUserService baseUserService;
@Autowired UserSchema userSchema;
    @ApiOperation(value = "查询用户",notes = "查询用户信息")
    @GetMapping("/baseUser/queryListForPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户姓名", required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "deptName", value = "部门名称", required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "deptId", value = "部门id", required = false,paramType = "query",dataType = "String")
    })
    public Page queryListForPage( Pageable pageable, String userName, String deptName, String deptId){
        return baseUserService.queryListForPage(new Specification<BaseUser>() {
            public Predicate toPredicate(Root<BaseUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list=new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(userName)) {
                    Path<String> name = root.get("username");
                    Predicate nameW = criteriaBuilder.like(name, "%" + userName + "%");
                    list.add(nameW);
                }
                if(StringUtils.isNotBlank(deptName)){
                    Path<BaseDepartMent> dName=root.get("departMent");
                    Predicate dNameW = criteriaBuilder.like(dName.get("deptName"), "%" + deptName + "%");
                    list.add(dNameW);
                }
                if(StringUtils.isNotBlank(deptId)){
                    Path<BaseDepartMent> dept=root.get("departMent");
                    Predicate dIdW = criteriaBuilder.equal(dept.get("id"),deptId);
                    list.add(dIdW);
                }
                if(list.size()!=0){
                    Predicate[] pres=new Predicate[list.size()];
                    list.toArray(pres);
                    criteriaQuery.where(list.toArray(pres));
                }
                return null;
            }
        }, pageable);
    }

    @ApiOperation(value="添加用户", notes="添加一个用户")
    @PostMapping("/baseUser/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "departMent.id", value = "部门id", required = true,paramType = "query",dataType = "String")
    })
    public Object insert(BaseUser baseUser){
        try {
            baseUserService.insert(baseUser);
            return "保存成功";
        }catch (Exception e){
            e.printStackTrace();
            return "程序出现问题";
        }
    }
    @ApiOperation(value="修改用户", notes="修改一个用户")
    @PutMapping("/baseUser/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "departMent.id", value = "部门id", required = true,paramType = "query",dataType = "String")
    })
    public Object update(BaseUser baseUser){
        try {
            baseUserService.update(baseUser);
            return "修改成功";
        }catch (Exception e){
            e.printStackTrace();
            return "程序出现问题";
        }
    }
    @ApiOperation(value="删除用户", notes="删除一个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true,paramType = "query",dataType = "String"),
    })
    @DeleteMapping("/baseUser/delete/{id}")
    public Object delete(String id){
        try {
            baseUserService.delete(id);
            return "删除成功";
        }catch (Exception e){
            e.printStackTrace();
            return "程序出现问题";
        }
    }
    @ApiOperation(value = "查询用户",notes = "查询用户信息")
    @GetMapping("/baseUser/{query}")
    public String query(@PathVariable String query){
        Map<String, Object> result1 = new GraphQL(userSchema.GraphSchema()).execute(query).getData();
        Map<String, Object> result = GraphQL.newGraphQL(userSchema.GraphSchema()).build().execute(query).getData();
       return Optional.ofNullable(result).map(re->   JSONUtils.toJSONString(re)).orElse("查询条件不正确!");
    }
}
