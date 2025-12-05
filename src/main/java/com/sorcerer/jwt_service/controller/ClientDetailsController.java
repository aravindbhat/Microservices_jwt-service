package com.sorcerer.jwt_service.controller;

import com.sorcerer.jwt_service.dto.ClientDetailsDto;
import com.sorcerer.jwt_service.dto.ClientDetailsResponseDto;
import com.sorcerer.jwt_service.entity.ClientDetails;
import com.sorcerer.jwt_service.service.ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/clientdetails")
public class ClientDetailsController {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @PostMapping("/newclient")
    public ResponseEntity<String> addclient(@RequestBody ClientDetailsDto clientDetailsDto){
        System.out.println("newclient");
        return ResponseEntity.ok(clientDetailsService.addclient(clientDetailsDto)+ " for "+clientDetailsDto.getAccount());
    }

    @DeleteMapping("/removeclient/{account}")
    public ResponseEntity<String> deleteclient(@PathVariable("account") String account){

        return ResponseEntity.ok( clientDetailsService.deleteclient(account)+ account);
    }

    @PutMapping("/renewsecret")
    public ResponseEntity<String> renewsecret(@RequestParam("account") String account, @RequestParam("secretKey") String secretKey){

        ClientDetails updated=clientDetailsService.renewsecret(account,secretKey);
        return ResponseEntity.ok("Client secret renewed successfully for account: "+account+". It will expire at "+ updated.getExpiresAt());
    }

    @GetMapping("/clients")
    public ResponseEntity<Iterable<ClientDetails>> clients(){
        System.out.println("clients");
        return ResponseEntity.ok(clientDetailsService.clients());
    }

    @GetMapping("/client/{account}")
    public  ResponseEntity<ClientDetails> client(@PathVariable("account") String account){
        System.out.println("client");
        return ResponseEntity.ok(clientDetailsService.client(account));
    }
}
