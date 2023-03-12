package com.barbel.memberserver.global.jwt;

import com.barbel.memberserver.global.jwt.document.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
}
