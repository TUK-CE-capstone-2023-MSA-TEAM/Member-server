package com.barbel.memberserver.domain.member.controller;


import static com.barbel.memberserver.domain.member.controller.MemberController.MEMBER_API_URI;

import com.barbel.memberserver.domain.member.dto.MemberLoginRequest;
import com.barbel.memberserver.domain.member.dto.MemberRegistrationRequest;
import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.service.MemberService;
import com.barbel.memberserver.global.result.ResultCode;
import com.barbel.memberserver.global.result.ResultResponse;
import com.barbel.memberserver.global.utill.MemberUtil;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
  private final MemberService memberService;

  // TODO: 회원가입, 로그인 빼고 로그인 필요.
  @PostMapping("/signup")
  public ResponseEntity<ResultResponse> registration(
      @RequestBody @Valid MemberRegistrationRequest memberRegistrationRequest) {
    log.info(memberRegistrationRequest.toString());
    Member member = MemberUtil.memberRegistrationRequestToMember(memberRegistrationRequest);
    memberService.saveMember(member);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_REGISTRATION_SUCCESS));
  }

  @PostMapping("/signin")
  public ResponseEntity<ResultResponse> Login(@RequestBody MemberLoginRequest memberLoginRequest) {
    //TODO: 로그인 처리 로직 구현
    log.info(memberLoginRequest.toString());
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_LOGIN_SUCCESS));
  }

  @GetMapping("/logout")
  public ResponseEntity<ResultResponse> Logout() {
    //TODO: 로그아웃 처리 로직 구현
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_LOGOUT_SUCCESS));
  }

  @GetMapping("/info")
  public ResponseEntity<ResultResponse> getMemberInfo() {
    String dummyEmail = "";
    Member memberList = memberService.findMemberByEmail(dummyEmail);
    log.info(memberList.toString());
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_FIND_SUCCESS, memberList));
  }

  @GetMapping("/delete/{email}")
  public ResponseEntity<ResultResponse> deleteMember(@PathVariable String email) {
    //TODO: 회원 탈퇴 DTO 정의한 다음 비밀번호 입력받도록 수정.
    memberService.deleteMemberByEmail(email);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_DELETE_SUCCESS));
  }

  @GetMapping("/list")
  public ResponseEntity<ResultResponse> getMemberList() {
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_LIST_REQUEST_SUCCESS, memberService.findAll()));
  }

  // TODO: 특정 특기나 관심사 등으로 검색하는 기능 구현. (메서드 2개 필요)
}
