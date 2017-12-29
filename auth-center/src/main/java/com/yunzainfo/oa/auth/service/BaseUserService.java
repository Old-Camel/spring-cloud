package com.yunzainfo.oa.auth.service;

import com.yunzainfo.oa.auth.entity.BaseUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by admin on 2017/12/27.
 */
public interface BaseUserService {
    Page<BaseUser> queryListForPage(Specification<BaseUser> var1,Pageable pageable);
    BaseUser querybyId(String id);
    void insert(BaseUser baseUser);
    void update(BaseUser baseUser);
    void delete(BaseUser baseUser);
    void delete(String id);
}
