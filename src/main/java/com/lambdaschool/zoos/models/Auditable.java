package com.lambdaschool.zoos.models;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.zip.DataFormatException;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable
{
    @CreatedBy
    protected String createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createddate;

    @LastModifiedBy
    protected String lastmodifiedby;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastmodifieddate;

    public String getCreatedBy()
    {
        return createdBy;
    }

    public Date getCreateddate()
    {
        return createddate;
    }

    public String getLastmodifiedby()
    {
        return lastmodifiedby;
    }

    public Date getLastmodifieddate()
    {
        return lastmodifieddate;
    }
}
