package com.barbel.memberserver.domain.member.service;

import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.dto.MemberLoginRequest;
import com.barbel.memberserver.domain.member.exception.EmailDuplicatedException;
import com.barbel.memberserver.domain.member.exception.MemberNotFountException;
import com.barbel.memberserver.domain.member.repository.MemberRepository;
import com.barbel.memberserver.global.jwt.Provider.JwtTokenProvider;
import com.barbel.memberserver.global.jwt.RefreshTokenRepository;
import com.barbel.memberserver.global.jwt.document.RefreshToken;
import com.barbel.memberserver.global.jwt.dto.TokenInfo;
import com.barbel.memberserver.global.jwt.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {
  private final MemberRepository memberRepository;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final JwtTokenProvider jwtTokenProvider;
  private final RefreshTokenRepository refreshTokenRepository;

  public void saveMember(Member member) {
    if (isDuplicatedEmail(member.getEmail())) {
      throw new EmailDuplicatedException();
    }
    memberRepository.save(member);
  }
  @Transactional
  public TokenInfo login(MemberLoginRequest memberLoginRequest) {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(memberLoginRequest.getEmail(), memberLoginRequest.getPassword());

    Authentication authentication =
        authenticationManagerBuilder.getObject().authenticate(authenticationToken);

    TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

    refreshTokenRepository.save(new RefreshToken(memberLoginRequest.getEmail(), tokenInfo.getRefreshToken()));
    return tokenInfo;
  }

  public boolean validateToken(String token) {
    return jwtTokenProvider.validateToken(token);
  }

  public TokenInfo refreshToken(String accessToken, String refreshToken) {
    Authentication authentication;

    if(!jwtTokenProvider.validateToken(accessToken) || !jwtTokenProvider.validateToken(refreshToken)) {
      throw new InvalidTokenException();
    }

    try {
      authentication = jwtTokenProvider.getAuthentication(accessToken);
    } catch (Exception e) {
      throw new InvalidTokenException();
    }

    String userId = authentication.getName();
    if(getRefreshToken(userId).getTokenValue() != refreshToken) {
      throw new InvalidTokenException();
    }

    TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
    refreshTokenRepository.save(new RefreshToken(userId, tokenInfo.getRefreshToken()));
    return tokenInfo;
  }

  private RefreshToken getRefreshToken(String userId) {
    return refreshTokenRepository.findById(userId).orElseThrow(MemberNotFountException::new);
  }

  private Boolean isDuplicatedEmail(String email) {
    return memberRepository.existsById(email);
  }
}
