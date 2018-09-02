package io.github.lbevan.sentiment.service.domain.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Base class for database entities.
 */
public abstract class BaseEntity implements Serializable {

    @Id
    protected BigInteger id;

    @CreatedDate
    protected Date createdAt;


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
