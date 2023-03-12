package com.barbel.memberserver.domain.member.controller;

import static com.barbel.memberserver.domain.member.controller.MemberController.MEMBER_API_URI;

import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.dto.MemberLoginRequest;
import com.barbel.memberserver.domain.member.dto.MemberRegistrationRequest;
import com.barbel.memberserver.domain.member.dto.TokenRefreshRequest;
import com.barbel.memberserver.domain.member.dto.TokenValidateRequest;
import com.barbel.memberserver.domain.member.service.LoginService;
import com.barbel.memberserver.domain.member.service.MemberService;
import com.barbel.memberserver.global.jwt.dto.TokenInfo;
import com.barbel.memberserver.global.jwt.exception.InvalidTokenException;
import com.barbel.memberserver.global.result.ResultCode;
import com.barbel.memberserver.global.result.ResultResponse;
import com.barbel.memberserver.global.utill.MemberUtil;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping(MEMBER_API_URI)
@Slf4j
public class AuthController {
  public static final String MEMBER_API_URI = "/api/auth";
  private final MemberService memberService;
  private final LoginService loginService;

  @Operation(summary = "회원가입")
  @PostMapping("/signup")
  public ResponseEntity<ResultResponse> registration(
      @RequestBody @Valid MemberRegistrationRequest memberRegistrationRequest) {
    log.info(memberRegistrationRequest.toString());
    Member member = MemberUtil.memberRegistrationRequestToMember(memberRegistrationRequest);
    loginService.saveMember(member);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_REGISTRATION_SUCCESS));
  }

  @Operation(summary = "로그인")
  @PostMapping("/signin")
  public ResponseEntity<ResultResponse> Login(@RequestBody MemberLoginRequest memberLoginRequest) {
    //TODO: 로그인 처리 로직 구현
    log.info(memberLoginRequest.toString());
    TokenInfo tokenInfo = loginService.login(memberLoginRequest);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_LOGIN_SUCCESS, tokenInfo));
  }

  @Operation(summary = "로그아웃")
  @GetMapping("/logout")
  public ResponseEntity<ResultResponse> Logout() {
    //TODO: 로그아웃 처리 로직 구현
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_LOGOUT_SUCCESS));
  }

  @Operation(summary = "토큰 재발급")
  @PostMapping("/refresh")
  public ResponseEntity<ResultResponse> refreshToken(@RequestBody TokenRefreshRequest tokenRefreshRequest) {
    TokenInfo tokenInfo =
        loginService.refreshToken(tokenRefreshRequest.getAccessToken(), tokenRefreshRequest.getRefreshToken());
    return ResponseEntity.ok(ResultResponse.of(ResultCode.TOKEN_REFRESH_SUCCESS, tokenInfo));
  }

  @Operation(summary = "토큰 검증")
  @PostMapping("/validate")
  public ResponseEntity<ResultResponse> validateToken(@RequestBody TokenValidateRequest tokenValidateRequest) {
    if(loginService.validateToken(tokenValidateRequest.getToken())) {
      return ResponseEntity.ok(ResultResponse.of(ResultCode.TOKEN_VALIDATE_SUCCESS));
    }
    throw new InvalidTokenException();
  }


}
