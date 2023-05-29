package com.barbel.memberserver.domain.member.service;

import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.dto.MemberListResponse;
import com.barbel.memberserver.domain.member.dto.MenteeDetailResponse;
import com.barbel.memberserver.domain.member.dto.MentorDetailResponse;
import com.barbel.memberserver.domain.member.exception.MemberListRequestFailedException;
import com.barbel.memberserver.domain.member.exception.MemberNotFountException;
import com.barbel.memberserver.domain.member.repository.MemberRepository;
import com.barbel.memberserver.global.utill.MemberUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberService {
  private final MemberRepository memberRepository;

  public Member findMemberByEmail(String email) {
    return memberRepository.findByEmail(email).orElseThrow(MemberNotFountException::new);
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

  public List<MemberListResponse> findMembersByMajor(
      String major
  ) {
    return memberRepository.findAllByMajorsContainsAndRole(major, "MENTOR")
        .stream().map(it ->
            new MemberListResponse(
                it.getEmail(),
                it.getNickname(),
                it.getProfileImageURL(),
                it.getRole()
            )).collect(java.util.stream.Collectors.toList());
  }

  public List<MemberListResponse> findMembersByInterest(
      String interest
  ) {
    return memberRepository.findAllByInterestsContainsAndRole(interest, "MENTEE")
        .stream().map(it ->
            new MemberListResponse(
                it.getEmail(),
                it.getNickname(),
                it.getProfileImageURL(),
                it.getRole()
            )).collect(java.util.stream.Collectors.toList());
  }

  public MentorDetailResponse findMentorDetailByEmail(
      String email
  ) {
    Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFountException::new);
    return MemberUtil.MemberToMentorDetailResponse(member);
  }

  public MenteeDetailResponse findMenteeDetailByEmail(
      String email
  ) {
    Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFountException::new);
    return MemberUtil.MemberToMenteeDetailResponse(member);
  }

  private Boolean isDuplicatedEmail(String email) {
    return memberRepository.existsById(email);
  }
}
