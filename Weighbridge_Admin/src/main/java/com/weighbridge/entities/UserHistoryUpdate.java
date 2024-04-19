package com.weighbridge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserHistoryUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roles;

    private LocalDateTime modifiedDate;
    private String modifiedBy;

    private String site;

    private LocalDateTime createdDate;
    private String createdBy;
}
