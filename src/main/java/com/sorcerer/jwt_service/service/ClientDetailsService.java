package com.sorcerer.jwt_service.service;

import com.sorcerer.jwt_service.dto.ClientDetailsDto;
import com.sorcerer.jwt_service.dto.ClientDetailsResponseDto;
import com.sorcerer.jwt_service.entity.ClientDetails;
import com.sorcerer.jwt_service.repository.ClientDetailsRepo;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Base64;

@Service
public class ClientDetailsService {

    @Autowired
    private ClientDetailsRepo clientDetailsRepo;

        public String addclient(ClientDetailsDto clientDetailsDto) {

        ClientDetails clientDetails=clientDetailsRepo.save(ClientDetails.builder()
                .account(clientDetailsDto.getAccount())
                .secretKey(clientDetailsDto.getSecretKey())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .lastUpdatedAt(new Timestamp(System.currentTimeMillis()))
                .expiresAt(new Timestamp(System.currentTimeMillis()+1000*60*60*24*365))
                .build());
        return "Client DEtails saved successfully";
    }

    public String deleteclient(String account) {

            ClientDetails existing=clientDetailsRepo.findByAccount(account).orElse(null);
            if(existing==null)
                throw new IllegalArgumentException("Client details does not exist for "+account);
            clientDetailsRepo.deleteByAccount(account);
            return "Client deleted successfully";

    }


    public Iterable<ClientDetails> clients() {
            return clientDetailsRepo.findAll();

    }

    public ClientDetails client(String account) {
            return clientDetailsRepo.findByAccount(account).orElse(null);
    }

    public ClientDetails renewsecret(String account, String secretKey) {
            String encodedSecret=Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
            Timestamp newExpiry=new Timestamp(System.currentTimeMillis()+1000*60*60*24*365);
            Timestamp lastUpdated=new Timestamp(System.currentTimeMillis());
            ClientDetails updated=clientDetailsRepo.renewsecret(account,encodedSecret,newExpiry,lastUpdated);
            return updated;
    }
}
