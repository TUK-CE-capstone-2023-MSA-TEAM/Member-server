package com.barbel.memberserver.domain.member.controller;


import static com.barbel.memberserver.domain.member.controller.MemberController.MEMBER_API_URI;

import com.barbel.memberserver.domain.member.dto.MemberLoginRequest;
import com.barbel.memberserver.domain.member.dto.MemberRegistrationRequest;
import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.service.MemberService;
import com.barbel.memberserver.global.utill.MemberRegistrationDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping(MEMBER_API_URI)
@Slf4j
public class MemberController {
  public static final String MEMBER_API_URI = "/api/member";
  private final MemberService memberService;

  @PostMapping("/signup")
  public String registration(@RequestBody MemberRegistrationRequest memberRegistrationRequest) {
    log.info(memberRegistrationRequest.toString());
    Member member = MemberRegistrationDTOMapper.toMember(memberRegistrationRequest);
    memberService.saveMember(member);
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
  public List<Member> getMemberInfo() {
    List<Member> memberList = memberService.findAll();
    log.info(memberList.toString());
    return memberList;
  }

  @GetMapping("/duplicated/{email}")
  public Boolean isDuplicatedEmail(@PathVariable String email) {
    if(memberService.isDuplicatedEmail(email)) {
      return false;
    }
    else {
      return true;
    }
  }

  @GetMapping("/delete/{email}")
  public String deleteMember(@PathVariable String email) {
    memberService.deleteMemberByEmail(email);
    return "삭제 완료";
  }
}
