package com.barbel.memberserver.domain.member.service;

import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.exception.EmailDuplicatedException;
import com.barbel.memberserver.domain.member.exception.MemberListRequestFailedException;
import com.barbel.memberserver.domain.member.exception.MemberNotFountException;
import com.barbel.memberserver.domain.member.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberService {
  private final MemberRepository memberRepository;

  public Member findMemberByEmail(String email) {
    return memberRepository.findByEmail(email).orElseThrow(() -> new MemberNotFountException());
  }

  public List<Member> findAll() {
    try {
      return memberRepository.findAll();
    } catch (Exception e) {
      throw new MemberListRequestFailedException();
    }
  }

  public void deleteMemberByEmail(String email) {
    if(!isDuplicatedEmail(email)) {
      throw new MemberNotFountException();
    }
    memberRepository.deleteByEmail(email);
  }

  private Boolean isDuplicatedEmail(String email) {
    return memberRepository.existsById(email);
  }
}
