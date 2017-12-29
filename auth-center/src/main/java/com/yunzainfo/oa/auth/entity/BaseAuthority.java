package com.yunzainfo.oa.auth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/20
 * Email:old_camel@126.com
 */

@Entity
@Table(name="BASE_AUTHORITY")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@GenericGenerator(name="uuid",strategy = "uuid")
@ApiModel(value = "Action权限",description = "Action权限")
public  class BaseAuthority {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid")

    @ApiModelProperty(value = "主键",name = "id",example = "402836e2609c7c2101609c7d791c0000")
    private String id;
    //@Column
    @ApiModelProperty(value = "Action名称",name = "name",example = "查询所有角色")
    private String name;
    //@Column
    @ApiModelProperty(value = "Action值",name = "value",example = "/baseRole/findAllRoles")
    private String value;



}
