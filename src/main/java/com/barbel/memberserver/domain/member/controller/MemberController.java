package com.barbel.memberserver.domain.member.controller;


import static com.barbel.memberserver.domain.member.controller.MemberController.MEMBER_API_URI;

import com.barbel.memberserver.domain.member.dto.MemberLoginRequest;
import com.barbel.memberserver.domain.member.dto.MemberRegistrationRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping(MEMBER_API_URI)
@Slf4j
public class MemberController {
  public static final String MEMBER_API_URI = "/api/member";

  @PostMapping("/signup")
  public String registration(@RequestBody MemberRegistrationRequest memberRegistrationRequest) {
    log.info(memberRegistrationRequest.toString());
    return "가입 완료";
  }

  @PostMapping("/signin")
  public String Login(@RequestBody MemberLoginRequest memberLoginRequest) {
    log.info(memberLoginRequest.toString());
    return "로그인 완료";
  }

  @GetMapping("/logout")
  public String Logout() {
    return "로그아웃 완료";
  }

  @GetMapping("/info")
  public String getMemberInfo() {
    return "회원 정보 조회";
  }

  @GetMapping("/duplicated/{email}")
  public String isDuplicatedEmail(@PathVariable String email) {
    return "이메일 중복 조회";
  }
}
