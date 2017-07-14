package com.interceptionphonetool.home.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * Created by bykj003 on 2017/7/14.
 */
@Entity
public class Phone {
    @Id
    private Long id;

    @NotNull
    private String number;

    private Date createTime = new Date();

    @Generated(hash = 430444964)
    public Phone(Long id, @NotNull String number, Date createTime) {
        this.id = id;
        this.number = number;
        this.createTime = createTime;
    }

    @Generated(hash = 429398894)
    public Phone() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
