package com.yunzainfo.oa.auth.service;

import com.yunzainfo.oa.auth.entity.BaseAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseAuthrityService {

    //查询所有Action路径
    List<BaseAuthority> findAllBaseAuthority();

    //添加Action路径
    boolean addBaseAuthority(String name,String value);

    //修改Action名称路径
    boolean updateBaseAuthorityValue(String authorityId,String newName,String newValue);

    //删除Action
    boolean deleteBaseAuthority(String authorityId);

    //查询单个Action
    BaseAuthority findOneAuthority(String authorityId);

}
