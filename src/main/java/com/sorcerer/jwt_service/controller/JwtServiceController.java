package com.sorcerer.jwt_service.controller;

import com.sorcerer.jwt_service.dto.JwtResponseDto;
import com.sorcerer.jwt_service.dto.JwtTokenRequestDto;
import com.sorcerer.jwt_service.dto.JwtTokenValidateRequestDTO;
import com.sorcerer.jwt_service.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tokenservice")
public class JwtServiceController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/generatetoken")
    public ResponseEntity<JwtResponseDto> generatetoken(@RequestBody JwtTokenRequestDto jwtTokenRequestDto){
        return ResponseEntity.ok(jwtService.generateToken(jwtTokenRequestDto.getAccount(),jwtTokenRequestDto.getUsername()));
    }
    @PostMapping("/validatetoken")
    public ResponseEntity<JwtResponseDto> validatetoken(@RequestBody JwtTokenValidateRequestDTO jwtTokenValidateRequestDTO){
        return ResponseEntity.ok(jwtService.validateToken(jwtTokenValidateRequestDTO.getAccount(),jwtTokenValidateRequestDTO.getToken()));
    }
}
