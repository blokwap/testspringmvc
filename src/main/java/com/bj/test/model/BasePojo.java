package com.bj.test.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by baojun on 2016/1/11.
 */
@DynamicInsert
@DynamicUpdate
@MappedSuperclass
public class BasePojo implements Serializable {
    public static final int NOT_USED_HASHCODE = Integer.MIN_VALUE;

    private int hashCode = NOT_USED_HASHCODE;

    private String id;
    private Date createTime;

    private Date updateTime;

    private User createUser;

    private User updateUser;
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column
    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    @Column
    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public int hashCode() {
        if (NOT_USED_HASHCODE == this.hashCode) {
            if (null == this.getId())
                return super.hashCode();

            String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
            this.hashCode = hashStr.hashCode();

        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!obj.getClass().equals(this.getClass()))
            return false;

        BasePojo mObj = (BasePojo) obj;
        if (null == this.getId() || null == mObj.getId())
            return false;
        return (this.getId().equals(mObj.getId()));

    }

    public String toString() {
        String str = getClass().getName() + "[id=" + getId() + "]";
        return str;
    }
}
