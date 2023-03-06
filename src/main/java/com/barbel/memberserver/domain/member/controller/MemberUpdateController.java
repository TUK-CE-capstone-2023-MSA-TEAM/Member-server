package com.barbel.memberserver.domain.member.controller;

import static com.barbel.memberserver.domain.member.controller.MemberController.MEMBER_API_URI;

import com.barbel.memberserver.domain.member.dto.MemberPasswordUpdateRequest;
import com.barbel.memberserver.domain.member.dto.MemberUpdateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping(MEMBER_API_URI)
@Slf4j
public class MemberUpdateController {

  //TODO: 회원 정보 수정 기능 구현.
  @PostMapping("/update/common")
  public String update(@RequestBody MemberUpdateRequest memberUpdateRequest) {
    log.info(memberUpdateRequest.toString());
    return "업데이트 완료";
  }
  @PostMapping("/update/password")
  public String updatePassword(@RequestBody MemberPasswordUpdateRequest memberPasswordUpdateRequest) {
    log.info(memberPasswordUpdateRequest.toString());
    return "비밀번호 업데이트 완료";
  }
}
