package com.interceptionphonetool.home.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by pengjunjun on 2017/7/15.
 */
@Entity
public class Record {

    @Id
    private Long id;

    private String number;

    private Date createTime = new Date();

    @Generated(hash = 14164910)
    public Record(Long id, String number, Date createTime) {
        this.id = id;
        this.number = number;
        this.createTime = createTime;
    }

    @Generated(hash = 477726293)
    public Record() {
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
