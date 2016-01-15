package com.bj.test.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by baojun on 2016/1/11.
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class User extends BasePojo{
    private String username;
    private String password;
    private List<Role> roles;

    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
