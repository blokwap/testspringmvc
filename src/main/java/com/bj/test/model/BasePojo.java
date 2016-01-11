package com.bj.test.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by baojun on 2016/1/11.
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class BasePojo implements Serializable {
    public static final int NOT_USED_HASHCODE = Integer.MIN_VALUE;

    private int hashCode = NOT_USED_HASHCODE;

    private String id;

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid.hex")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
