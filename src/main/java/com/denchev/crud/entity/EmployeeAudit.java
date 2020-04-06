package com.denchev.crud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class EmployeeAudit {

    @Column(name = "date_created", nullable = false)
    // @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "date_updated", nullable = false)
    // @UpdateTimestamp
    private LocalDateTime dateUpdated;

    @PrePersist
    private void createTimestamp() {
        dateCreated = LocalDateTime.now();
        dateUpdated = LocalDateTime.now();
    }

    @PreUpdate
    private void updateTimestamp() {
        dateUpdated = LocalDateTime.now();
    }
}
