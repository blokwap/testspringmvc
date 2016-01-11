package com.bj.test.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by baojun on 2016/1/11.
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Role extends BasePojo{
    private String name;
    private List<Permission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(name="t_role_permission",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="permission_id")})
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
