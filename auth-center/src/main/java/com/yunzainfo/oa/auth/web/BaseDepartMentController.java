package com.yunzainfo.oa.auth.web;

import com.yunzainfo.oa.auth.entity.BaseDepartMent;
import com.yunzainfo.oa.auth.service.BaseDepartMentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/12/27.
 */
@RestController
@RefreshScope
public class BaseDepartMentController {
    @Autowired
    private BaseDepartMentService baseDepartMentService;
/*bearer c7b25aab-1802-47a5-bc90-3c6940939eee*/
    @ApiOperation(value = "查询部门",notes = "查询部门信息")
    @GetMapping("/baseDepartMent/queryListForPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptName", value = "部门名称", required = false,paramType = "query",dataType = "String"),
    })
    public Page queryListForPage(String deptName, Pageable pageable){
        Sort sort=new Sort(Sort.Direction.ASC,"displayIndex");
        Pageable pageable1=new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),sort);
        return baseDepartMentService.queryListForPage(new Specification<BaseDepartMent>() {
            public Predicate toPredicate(Root<BaseDepartMent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if(StringUtils.isNotBlank(deptName)){
                    Path<String> dName=root.get("deptName");
                    query.where(cb.like(dName,"%"+deptName+"%"));
                }
                return null;
            }
        }, pageable1);

    }

    @ApiOperation(value="添加部门", notes="添加一个部门")
    @PostMapping("/baseDepartMent/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pId", value = "父部门id", required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "deptName", value = "部门名称", required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "deptType", value = "部门类型(1普通部门2系部）", required = true,paramType = "query",dataType = "Long"),
            @ApiImplicitParam(name = "displayIndex", value = "显示顺序", required = true,paramType = "query",dataType = "Long")
    })
    public Object insert(BaseDepartMent baseDept,String pId){
        try {
            if(StringUtils.isNotBlank(pId)) {
                BaseDepartMent departMent = new BaseDepartMent();
                departMent.setId(pId);
                baseDept.setBaseDept(departMent);
            }
            baseDepartMentService.insert(baseDept);
            return "保存成功";
        }catch (Exception e){
            e.printStackTrace();
            return "程序出现问题";
        }
    }
    @ApiOperation(value="修改部门", notes="修改一个部门")
    @PutMapping("/baseDepartMent/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pId", value = "父部门id", required = false,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "deptName", value = "部门名称", required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "deptType", value = "部门类型(1普通部门2系部）", required = true,paramType = "query",dataType = "Long"),
            @ApiImplicitParam(name = "displayIndex", value = "显示顺序", required = true,paramType = "query",dataType = "Long")
    })
    public Object update(BaseDepartMent baseDept,String pId){
        try {
            if(StringUtils.isNotBlank(pId)) {
                BaseDepartMent departMent = new BaseDepartMent();
                departMent.setId(pId);
                baseDept.setBaseDept(departMent);
            }
            baseDepartMentService.update(baseDept);
            return "修改成功";
        }catch (Exception e){
            e.printStackTrace();
            return "程序出现问题";
        }
    }
    @ApiOperation(value="删除部门", notes="删除一个部门")
    @DeleteMapping("/baseDepartMent/delete/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部门id", required = true,paramType = "query",dataType = "String")
    })
    public Object delete(String id){
        try {
            BaseDepartMent departMent = baseDepartMentService.queryById(id);
            if(departMent.getBaseDepartMents()==null){
                baseDepartMentService.delete(id);
                return "删除成功";
            }else {
                return "删除失败，请先删除该部门下的子部门";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "程序出现问题";
        }
    }
}
