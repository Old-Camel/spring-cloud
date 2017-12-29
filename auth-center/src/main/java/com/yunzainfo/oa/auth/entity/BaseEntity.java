package com.yunzainfo.oa.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/20
 * Email:old_camel@126.com
 */
@MappedSuperclass
@Audited
@EntityListeners(AuditingEntityListener.class)
@Data
@ApiModel(value = "所有类的基类",description = "拥有一些共有属性")
public abstract class BaseEntity {
    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    @JsonIgnore
    @ApiModelProperty(value = "创建用户",name = "createdBy",example = "admin")
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    @JsonIgnore
    @ApiModelProperty(value = "创建日期",name = "createdDate",example = "2017-12-28")
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    @JsonIgnore
    @ApiModelProperty(value = "最后修改人",name = "lastModifiedBy",example = "admin")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    @ApiModelProperty(value = "最后修改时间",name = "lastModifiedDate",example = "2017-12-28")
    private Date lastModifiedDate;

}
