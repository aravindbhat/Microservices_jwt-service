package com.sorcerer.jwt_service.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorDto {
    private String errorMessage;
    private LocalDateTime time;
    private HttpStatus status;
}
