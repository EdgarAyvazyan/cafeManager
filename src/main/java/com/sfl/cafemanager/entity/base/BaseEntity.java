package com.sfl.cafemanager.entity.base;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @LastModifiedDate
    @Column(name = "update_date")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    public BaseEntity(Date createDate, Date updateDate) {
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public BaseEntity() {
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
