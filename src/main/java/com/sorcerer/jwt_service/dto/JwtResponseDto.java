package com.sorcerer.jwt_service.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtResponseDto {
    private String status;
    private String userName;
    private String token;
    private Date generatedAt;
    private Date validTill;
}
