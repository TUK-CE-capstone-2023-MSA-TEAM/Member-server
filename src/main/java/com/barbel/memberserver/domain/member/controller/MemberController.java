package com.barbel.memberserver.domain.member.controller;


import static com.barbel.memberserver.domain.member.controller.MemberController.MEMBER_API_URI;

import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.service.MemberService;
import com.barbel.memberserver.global.result.ResultCode;
import com.barbel.memberserver.global.result.ResultResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping(MEMBER_API_URI)
@Slf4j
public class MemberController {
  public static final String MEMBER_API_URI = "/api/member";
  private final MemberService memberService;

  @GetMapping("/info/{email}")
  public ResponseEntity<ResultResponse> getMemberInfo(@PathVariable String email) {
    Member member = memberService.findMemberByEmail(email);
    log.info(member.toString());
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_FIND_SUCCESS, member));
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
