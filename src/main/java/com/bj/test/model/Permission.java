package com.bj.test.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by baojun on 2016/1/11.
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Permission {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
