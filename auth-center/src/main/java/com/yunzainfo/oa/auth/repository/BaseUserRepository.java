package com.yunzainfo.oa.auth.repository;

import com.yunzainfo.oa.auth.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/20
 * Email:old_camel@126.com
 */
public interface BaseUserRepository extends JpaRepository<BaseUser,String>,JpaSpecificationExecutor<BaseUser> {
    Optional<BaseUser> findBaseUserByUsername(String userName);
}
