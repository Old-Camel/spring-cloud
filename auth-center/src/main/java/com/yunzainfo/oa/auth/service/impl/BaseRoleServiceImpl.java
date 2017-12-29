package com.yunzainfo.oa.auth.service.impl;

import com.yunzainfo.oa.auth.entity.BaseAuthority;
import com.yunzainfo.oa.auth.entity.BaseRole;
import com.yunzainfo.oa.auth.repository.BaseAuthorityRepository;
import com.yunzainfo.oa.auth.repository.BaseRoleRepository;
import com.yunzainfo.oa.auth.service.BaseRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BaseRoleServiceImpl implements BaseRoleService {

    @Autowired
    BaseRoleRepository repository;

    @Autowired
    BaseAuthorityRepository authorityRepository;

    @Override
    public List<BaseRole> findAllRoles() {
        return repository.findAll();
    }

    @Override
    public boolean addRole(String roleName, String roleDesc) {
        try{
            BaseRole role = new BaseRole();
            if(roleDesc != null && roleName != null){
                role.setRoleName(roleName);
                role.setRoleDesc(roleDesc);
                repository.saveAndFlush(role);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRole(String roleId,String roleName, String roleDesc) {
        try {
            BaseRole role = repository.findOne(roleId);
            if(roleName!= null && roleDesc!=null){
                role.setRoleDesc(roleDesc);
                role.setRoleName(roleName);
                repository.saveAndFlush(role);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRole(String roleId,List<String> AuthrityIds) {
        try{
            Set<BaseAuthority> baseAuthorities = new HashSet<>();
            BaseRole role = repository.findOne(roleId);
            for(String auid:AuthrityIds){
                BaseAuthority authority = authorityRepository.findOne(auid);
                if (authority !=null) {
                    baseAuthorities.add(authority);
                }
            }
            if(!baseAuthorities.isEmpty()){
                role.setAuthorities(baseAuthorities);
                repository.saveAndFlush(role);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRole(String roleId) {
        if(roleId != null){
            repository.delete(roleId);
            repository.flush();
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Set<BaseAuthority> findAllAuthorityByRoleId(String roleId) {
        if(roleId !=null){
            return repository.findOne(roleId).getAuthorities();
        }
        else{
            return null;
        }
    }

    @Override
    public BaseRole findOneRole(String roleId) {
        if (roleId!=null){
            return repository.findOne(roleId);
        }else{
            return null;
        }
    }

    @Override
    public BaseRole findOneRoleByName(String rolename) {
        if(rolename != null){
            return repository.findBaseRoleByRoleName(rolename);
        }else{
            return null;
        }
    }

    @Override
    public Page<BaseRole> findAllRolesByPage(Integer page, Integer size) {
        if(page!=null && size!=null) {
            Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
            return repository.findAll(pageable);
        }else{
            return null;
        }
    }
}
