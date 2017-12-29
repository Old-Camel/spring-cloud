package com.yunzainfo.oa.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/12/27.
 */
@Entity
@Table(name="BASE_DEPARTMENT")
@GenericGenerator(name = "uuid", strategy = "uuid")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseDepartMent extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    private String id;
    //部门名称
    @Size(max = 100)
    @Column(name="DEPT_NAME",nullable = false)
    private String deptName;
    //部门类型(1普通部门2系部）
    @Column(name="DEPT_TYPE")
    private Integer deptType;
    //部门描述
    @Size(max = 500)
    @Column(name="DEPT_COMMENT")
    private String deptComment;
    //是否为子部门
    @Column(name="LEAF")
    private Integer leaf;
    //显示顺序
    @Column(name="DISPLAY_INDEX")
    private Integer displayIndex;
    //部门编码
    @Size(max = 32)
    @Column(name="DEPT_CODE")
    private String deptCode;
    @Column(name="STATUS")
    private Integer status;
    //部门的子集
    @OneToMany(mappedBy = "baseDept",fetch = FetchType.LAZY)
    @BatchSize(size = 20)
    private List<BaseDepartMent> baseDepartMents;
    //父部门
    @JsonIgnore
    @ManyToOne(optional = true,fetch = FetchType.LAZY,targetEntity = BaseDepartMent.class)
    @JoinColumn(name = "department_pid")
    private BaseDepartMent baseDept;


}
