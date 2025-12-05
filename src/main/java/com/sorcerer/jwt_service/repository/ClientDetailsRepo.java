package com.sorcerer.jwt_service.repository;

import com.sorcerer.jwt_service.entity.ClientDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface ClientDetailsRepo extends JpaRepository<ClientDetails, Long> {
    @Transactional
    @Modifying
    void deleteByAccount(String account);
    Optional<ClientDetails> findByAccount(String account);

    @Transactional
    @Modifying
    @Query("UPDATE ClientDetails cd SET cd.secretKey=:secretKey,cd.lastUpdatedAt=:lastUpdateAt, cd.expiresAt=:expiresAt WHERE cd.account=:account ")
    ClientDetails renewsecret(@Param("account") String account, @Param("secretKey") String encodedSecret, @Param("expiresAt") Timestamp newExpiry, @Param("lastUpdatesAt") Timestamp lastUpdated);
}
