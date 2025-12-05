package com.sorcerer.jwt_service.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class JwtTokenValidateRequestDTO {
    private String account;
    private String token;
}
