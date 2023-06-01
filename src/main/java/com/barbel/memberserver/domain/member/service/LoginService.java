package com.barbel.memberserver.domain.member.service;

import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.dto.MemberLoginRequest;
import com.barbel.memberserver.domain.member.dto.MemberRegistrationRequest;
import com.barbel.memberserver.domain.member.exception.AuthenticationFailedException;
import com.barbel.memberserver.domain.member.exception.EmailDuplicatedException;
import com.barbel.memberserver.domain.member.exception.MemberNotFountException;
import com.barbel.memberserver.domain.member.repository.MemberRepository;
import com.barbel.memberserver.global.jwt.Provider.JwtTokenProvider;
import com.barbel.memberserver.global.jwt.RefreshTokenRepository;
import com.barbel.memberserver.global.jwt.document.RefreshToken;
import com.barbel.memberserver.global.jwt.dto.TokenInfo;
import com.barbel.memberserver.global.jwt.exception.InvalidTokenException;
import com.barbel.memberserver.global.utill.MemberUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
  private final MemberRepository memberRepository;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final JwtTokenProvider jwtTokenProvider;
  private final RefreshTokenRepository refreshTokenRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void saveMember(MemberRegistrationRequest memberRegistrationRequest) {
    if (isDuplicatedEmail(memberRegistrationRequest.getEmail())) {
      throw new EmailDuplicatedException();
    }
    Member member = MemberUtil.memberRegistrationRequestToMember(memberRegistrationRequest);
//    member.setPassword(passwordEncoder.encode(member.getPassword()));
    member.setPassword(member.getPassword());
    memberRepository.save(member);
  }
  @Transactional
  public TokenInfo login(MemberLoginRequest memberLoginRequest) {
//    Member member = memberRepository.findByEmail(memberLoginRequest.getEmail())
//        .orElseThrow(MemberNotFountException::new);
//    if(!passwordEncoder.matches(memberLoginRequest.getPassword(), member.getPassword())) {
//      throw new AuthenticationFailedException();
//    }
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(memberLoginRequest.getEmail(), memberLoginRequest.getPassword());
    log.info("로그인 시도 | email : " + memberLoginRequest.getEmail() + " | password : " + memberLoginRequest.getPassword());
    log.info("인코딩 된 패스워드 : " + passwordEncoder.encode(memberLoginRequest.getPassword()));

    try {
      Authentication authentication =
              authenticationManagerBuilder.getObject().authenticate(authenticationToken);

      TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

      refreshTokenRepository.save(new RefreshToken(memberLoginRequest.getEmail(), tokenInfo.getRefreshToken()));
      return tokenInfo;
    } catch (BadCredentialsException e) {
      throw new AuthenticationFailedException();
    }
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
    if(!getRefreshToken(userId).getTokenValue().equals(refreshToken)) {
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
