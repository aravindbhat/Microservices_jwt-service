package com.sorcerer.jwt_service.dto;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class JwtTokenRequestDto {
    private String account;
    private String username;
}
