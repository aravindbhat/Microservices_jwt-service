package com.sorcerer.jwt_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NonNull
    private String account;
    @NonNull
    private String secretKey;
    private Timestamp createdAt;
    private Timestamp lastUpdatedAt;
    private Timestamp expiresAt;

}
