package com.barbel.memberserver.domain.member.controller;

import static com.barbel.memberserver.domain.member.controller.MemberController.MEMBER_API_URI;

import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.dto.MemberDetailResponse;
import com.barbel.memberserver.domain.member.service.MemberService;
import com.barbel.memberserver.global.result.ResultCode;
import com.barbel.memberserver.global.result.ResultResponse;
import com.barbel.memberserver.global.utill.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping(MEMBER_API_URI)
@Slf4j
public class MemberController {
  public static final String MEMBER_API_URI = "account/api/member";
  private final MemberService memberService;

  @GetMapping("/info")
  @Operation(summary = "로그인된 회원정보 조회")
  public ResponseEntity<ResultResponse> getMemberInfo(
      @RequestHeader("Authorization") String token
  ) {
    MemberDetailResponse member = memberService.findMemberByEmail(SecurityUtil.getLoginedMemberId(token));
    log.info(member.toString());
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_FIND_SUCCESS, member));
  }

  @GetMapping("/{email}")
  @Operation(summary = "특정 회원정보 조회")
  public ResponseEntity<ResultResponse> getMemberInfoByEmail(
      @PathVariable String email
  ) {
    MemberDetailResponse member = memberService.findMemberByEmail(email);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_FIND_SUCCESS, member));
  }

  @GetMapping("/delete/{email}")
  @Operation(summary = "회원탈퇴")
  public ResponseEntity<ResultResponse> deleteMember(@PathVariable String email) {
    //TODO: 회원 탈퇴 DTO 정의한 다음 비밀번호 입력받도록 수정.
    memberService.deleteMemberByEmail(email);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_DELETE_SUCCESS));
  }

  @GetMapping("/list")
  @Operation(summary = "전체 회원 리스트 조회")
  public ResponseEntity<ResultResponse> getMemberList() {
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_LIST_REQUEST_SUCCESS, memberService.findAll()));
  }

  @GetMapping("/list/major")
  @Operation(summary = "특정 특기를 가진 멘토 리스트 조회")
  public ResponseEntity<ResultResponse> getMemberListByMajor(
      @RequestParam String major
  ) {
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_LIST_REQUEST_SUCCESS, memberService.findMembersByMajor(major)));
  }

  @GetMapping("/list/interest")
  @Operation(summary = "특정 관심사를 가진 멘티 리스트 조회")
  public ResponseEntity<ResultResponse> getMemberListByInterest(
      @RequestParam String interest
  ) {
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_LIST_REQUEST_SUCCESS, memberService.findMembersByInterest(interest)));
  }

//  @GetMapping("/detail/mentor/{email}")
//  @Operation(summary = "특정 멘토 디테일 정보 조회")
//  public ResponseEntity<ResultResponse> getMemberDetail(
//      @PathVariable String email
//  ) {
//    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_FIND_SUCCESS, memberService.findMentorDetailByEmail(email)));
//  }
//
//  @GetMapping("/detail/mentee/{email}")
//  @Operation(summary = "특정 멘티 디테일 정보 조회")
//  public ResponseEntity<ResultResponse> getMemberDetailMentee(
//      @PathVariable String email
//  ) {
//    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_FIND_SUCCESS, memberService.findMenteeDetailByEmail(email)));
//  }
}
