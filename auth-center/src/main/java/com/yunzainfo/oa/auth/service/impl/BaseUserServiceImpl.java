package com.yunzainfo.oa.auth.service.impl;

import com.yunzainfo.oa.auth.entity.BaseDepartMent;
import com.yunzainfo.oa.auth.entity.BaseUser;
import com.yunzainfo.oa.auth.repository.BaseUserRepository;
import com.yunzainfo.oa.auth.service.BaseUserService;
import com.yunzainfo.oa.auth.service.BaseUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/12/27.
 */
@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Autowired
    private BaseUserRepository baseUserRepository;


    @Override
    public Page<BaseUser> queryListForPage(Specification<BaseUser> var1, Pageable pageable) {
        return baseUserRepository.findAll(var1,pageable);
    }

    @Override
    public BaseUser querybyId(String id) {
        return baseUserRepository.findOne(id);
    }

    @Override
    public void insert(BaseUser baseUser) {
        baseUserRepository.saveAndFlush(baseUser);
    }

    @Override
    public void update(BaseUser baseUser) {
/*        BaseUser baseUser1 = baseUserRepository.findOne(baseUser.getId());
        if(baseUser.getDepartMent()==null){
            if(baseUser1.getDepartMent()!=null){
                baseUser.setDepartMent(baseUser1.getDepartMent());
            }
        }*/
        baseUserRepository.saveAndFlush(baseUser);
    }

    @Override
    public void delete(BaseUser baseUser) {
        baseUserRepository.delete(baseUser);
    }

    @Override
    public void delete(String id) {
        baseUserRepository.delete(id);
    }
}
