package com.barbel.memberserver.global.utill;

import com.barbel.memberserver.global.jwt.exception.NoAuthInfoException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
  public static String getCurrentMemberId() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication.getName() == null) {
      throw new NoAuthInfoException();
    }
    return authentication.getName();
  }

  public static String getLoginedMemberId(String token) {
    JwtParser jwtParser = Jwts.parserBuilder().build();
    Claims claims = jwtParser.parseClaimsJws(token).getBody();

    return claims.get("sub", String.class);
  }
}
