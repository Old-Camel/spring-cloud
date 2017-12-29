package com.yunzainfo.oa.auth.repository;

import com.yunzainfo.oa.auth.entity.BaseRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/21
 * Email:old_camel@126.com
 */
public interface BaseRoleRepository extends JpaRepository<BaseRole,String> {
    BaseRole findBaseRoleByRoleName(String roleName);
}
