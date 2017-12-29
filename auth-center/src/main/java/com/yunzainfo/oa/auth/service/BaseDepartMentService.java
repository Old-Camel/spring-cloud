package com.yunzainfo.oa.auth.service;

import com.yunzainfo.oa.auth.entity.BaseDepartMent;
import com.yunzainfo.oa.auth.entity.BaseUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by admin on 2017/12/27.
 */
public interface BaseDepartMentService {
    Page<BaseDepartMent> queryListForPage(Specification<BaseDepartMent> var1, Pageable pageable);
    BaseDepartMent queryById(String id);
    long getCount();
    void insert(BaseDepartMent baseDept);
    void update(BaseDepartMent baseDept);
    void delete(BaseDepartMent baseDept);
    void delete(String id);
}
