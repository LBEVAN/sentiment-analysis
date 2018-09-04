package io.github.lbevan.sentiment.service.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Base class for database entities.
 */
public abstract class BaseEntity implements Serializable {

    @Id
    protected BigInteger id;

    public BigInteger getId() {
        return id;
    }
}
