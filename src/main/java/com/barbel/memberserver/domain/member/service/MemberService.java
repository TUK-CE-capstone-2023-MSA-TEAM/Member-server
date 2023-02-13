package com.barbel.memberserver.domain.member.service;

import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberService {
  private final MemberRepository memberRepository;

  public void saveMember(Member member) {
    memberRepository.save(member);
  }

  public Member findMemberByEmail(String email) {
    return memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
  }

  public List<Member> findAll() {
    return memberRepository.findAll();
  }

  public void deleteMemberByEmail(String email) {
    memberRepository.deleteByEmail(email);
  }

  public Boolean isDuplicatedEmail(String email) {
    try {
      findMemberByEmail(email);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}
