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
import java.util.Set;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/20
 * Email:old_camel@126.com
 */
@Entity
@Table(name="BASE_USER")
@GenericGenerator(name = "uuid", strategy = "uuid")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseUser extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    private String id;
    @Size(max = 20)
    @Column(name="USER_NAME",length = 50,unique = true,nullable = false)
    private String username;
    @Size(max = 100)
    @Column(nullable = false)
    private String password;
    @Email
    @Size(min = 5, max = 100)
    @Column(name="USER_EMAIL",length = 100, unique = true)
    private String email;
    @JsonIgnore
    @ManyToMany(targetEntity = BaseRole.class,fetch = FetchType.EAGER)
    @BatchSize(size = 20)
    private Set<BaseRole> roles = new HashSet<>();
    @Transient
    private Set<GrantedAuthority> authorities = new HashSet<>();
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> userAuthotities = new HashSet<>();
        for(BaseRole role : this.roles){
            for(BaseAuthority authority : role.getAuthorities()){
                userAuthotities.add(new SimpleGrantedAuthority(authority.getValue()));
                userAuthotities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }
        return userAuthotities;
    }
    @JsonIgnore
    @ManyToOne(optional = true,fetch = FetchType.LAZY,targetEntity = BaseDepartMent.class)   //外键是否可以为空
    @JoinColumn(name = "department_id")
    private BaseDepartMent departMent;
}
