package com.yunzainfo.oa.auth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/20
 * Email:old_camel@126.com
 */
@Entity
@Table(name="BASE_ROLE")
@GenericGenerator(name = "uuid", strategy = "uuid")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "角色对象",description = "角色对象")
public class BaseRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @ApiModelProperty(value = "主键",name = "id",example = "402836e2609bd92901609bda13f50000")
    private String id;

    @Column(name="ROLE_NAME",length = 50,unique = true,nullable = false)
    @ApiModelProperty(value = "角色名称",name = "roleName",example = "管理员")
    private String roleName;

    @Column(name="ROLE_DESC")
    @ApiModelProperty(value = "角色描述",name = "roleDesc",example = "我是管理员")
    private String roleDesc;

    @ManyToMany(targetEntity = BaseAuthority.class,fetch = FetchType.EAGER)
    @BatchSize(size = 20)
    @ApiModelProperty(value = "Action访问权限集合",name = "authorities",example = "/baseRole/findAllRoles")
    private Set<BaseAuthority> authorities = new HashSet<>();

}
