package com.brittodev.todoapi.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Todo {

    @Id
    @GeneratedValue
    Long id;

    String title;
    String description;
    Boolean isCompleted = false;

    @CreatedDate
    @Column(updatable = false,nullable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime modifiedAt;
}
