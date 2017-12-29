package com.yunzainfo.oa.auth.repository;

import com.yunzainfo.oa.auth.entity.BaseDepartMent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by admin on 2017/12/27.
 */
public interface BaseDepartMentRepository extends JpaRepository<BaseDepartMent,String>,JpaSpecificationExecutor<BaseDepartMent> {
}
