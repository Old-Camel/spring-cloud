package com.yunzainfo.oa.auth.service.impl;

import com.yunzainfo.oa.auth.entity.BaseAuthority;
import com.yunzainfo.oa.auth.repository.BaseAuthorityRepository;
import com.yunzainfo.oa.auth.service.BaseAuthrityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseAuthrityServiceImpl implements BaseAuthrityService{

    @Autowired
    BaseAuthorityRepository authorityRepository;

    @Override
    public List<BaseAuthority> findAllBaseAuthority() {
        return authorityRepository.findAll();
    }

    @Override
    public boolean addBaseAuthority(String name, String value) {
        try{
            if(name != null && value!=null){
                BaseAuthority authority = new BaseAuthority();
                authority.setName(name);
                authority.setValue(value);
                authorityRepository.saveAndFlush(authority);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBaseAuthorityValue(String authorityId, String newName, String newValue) {
        try{
            BaseAuthority authority = authorityRepository.findOne(authorityId);
            if(newName != null){
                authority.setName(newName);
            }
            if(newValue != null){
                authority.setValue(newValue);
            }
            authorityRepository.saveAndFlush(authority);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBaseAuthority(String authorityId) {
        try{
            if (authorityId !=null){
                authorityRepository.delete(authorityId);
                authorityRepository.flush();
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
    public BaseAuthority findOneAuthority(String authorityId) {
        if(authorityId != null){
            return authorityRepository.findOne(authorityId);
        }
        else{
            return null;
        }
    }
}
