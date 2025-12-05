package com.sorcerer.jwt_service.dto;

import lombok.*;

import java.sql.Timestamp;
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ClientDetailsResponseDto {

    private String account;
    private String secretKey;
    private Timestamp createdAt;
    private Timestamp lastUpdatedAt;
    private Timestamp expiresAt;
}
