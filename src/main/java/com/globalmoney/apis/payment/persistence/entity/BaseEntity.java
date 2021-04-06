package com.globalmoney.apis.payment.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1275447607325557567L;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    /**
     * CreatedAt and ModifiedAt will be set to current timeStamp on persisting an entity
     */
    @PrePersist
    public void prePersist()
    {
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    /**
     * ModifiedAt will be set to current timeStamp on updating an entity
     */
    @PreUpdate
    public void preUpdate()
    {
        this.modifiedAt = LocalDateTime.now();
    }

}
