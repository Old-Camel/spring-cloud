package com.yunzainfo.oa.auth.service.impl;

import com.yunzainfo.oa.auth.entity.BaseDepartMent;
import com.yunzainfo.oa.auth.repository.BaseDepartMentRepository;
import com.yunzainfo.oa.auth.service.BaseDepartMentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;

/**
 * Created by admin on 2017/12/27.
 */
@Service
public class BaseDepartMentServiceImpl implements BaseDepartMentService {
    @Autowired
    private BaseDepartMentRepository baseDeptRepository;
    @Override
    @Transactional
    public Page<BaseDepartMent> queryListForPage(Specification<BaseDepartMent> var1,Pageable pageable) {
       return baseDeptRepository.findAll(var1,pageable);
    }

    @Override
    public BaseDepartMent queryById(String id) {
        return baseDeptRepository.findOne(id);
    }

    @Override
    public long getCount() {
        return baseDeptRepository.count();
    }

    @Override
    public void insert(BaseDepartMent baseDept) {
       baseDeptRepository.saveAndFlush(baseDept);
    }

    @Override
    public void update(BaseDepartMent baseDept) {
        /*BaseDepartMent baseDepartMent = baseDeptRepository.findOne(baseDept.getId());
        if(baseDept.getBaseDept()==null){
            if(baseDepartMent.getBaseDept()!=null){
                baseDept.setBaseDept(baseDepartMent.getBaseDept());
            }
        }*/
        baseDeptRepository.saveAndFlush(baseDept);
    }

    @Override
    public void delete(BaseDepartMent baseDept) {
        baseDeptRepository.delete(baseDept);
    }

    @Override
    public void delete(String id) {
        baseDeptRepository.delete(id);
    }
}
