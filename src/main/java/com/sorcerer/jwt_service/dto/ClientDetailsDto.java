package com.sorcerer.jwt_service.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ClientDetailsDto {

    private String account;
    private String secretKey;

}
