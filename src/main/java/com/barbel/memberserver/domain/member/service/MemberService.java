package com.barbel.memberserver.domain.member.service;

import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.dto.MemberDetailResponse;
import com.barbel.memberserver.domain.member.dto.MemberListResponse;
import com.barbel.memberserver.domain.member.dto.MenteeDetailResponse;
import com.barbel.memberserver.domain.member.dto.MentorDetailResponse;
import com.barbel.memberserver.domain.member.exception.MemberListRequestFailedException;
import com.barbel.memberserver.domain.member.exception.MemberNotFoundException;
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

  public MemberDetailResponse findMemberByEmail(String email) {
    Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
    return new MemberDetailResponse(
        member.getEmail(),
        member.getNickname(),
        member.getAge(),
        member.getSex(),
        member.getIntroduce(),
        member.getProfileImageURL(),
        member.getRole(),
        member.getMajors(),
        member.getInterests()
    );
  }

  public List<MemberListResponse> findAll() {
    try {
      return memberRepository.findAll().stream().map(it ->
          new MemberListResponse(
              it.getEmail(),
              it.getNickname(),
              it.getProfileImageURL(),
              it.getRole()
          )).collect(java.util.stream.Collectors.toList());
    } catch (Exception e) {
      throw new MemberListRequestFailedException();
    }
  }

  public void deleteMemberByEmail(String email) {
    if(!isDuplicatedEmail(email)) {
      throw new MemberNotFoundException();
    }
    memberRepository.deleteByEmail(email);
  }

  public List<MemberListResponse> findMembersByMajor(
      String major
  ) {
    List<Member> members = memberRepository.findAllByRole("MENTOR");
    members = members.stream().filter(it -> it.getMajors().contains(major)).collect(java.util.stream.Collectors.toList());
    return members.stream().map(it ->
        new MemberListResponse(
            it.getEmail(),
            it.getNickname(),
            it.getProfileImageURL(),
            it.getRole()
        )).collect(java.util.stream.Collectors.toList());
//    return memberRepository.findAllByRole("MENTOR").stream().filter(it -> it.getMajors().contains(major))
//        .map(it ->
//            new MemberListResponse(
//                it.getEmail(),
//                it.getNickname(),
//                it.getProfileImageURL(),
//                it.getRole()
//            )).collect(java.util.stream.Collectors.toList());
  }

  public List<MemberListResponse> findMembersByInterest(
      String interest
  ) {
    List<Member> members = memberRepository.findAllByRole("MENTEE");
    members = members.stream().filter(it -> it.getInterests().contains(interest)).collect(java.util.stream.Collectors.toList());
    return members.stream().map(it ->
        new MemberListResponse(
            it.getEmail(),
            it.getNickname(),
            it.getProfileImageURL(),
            it.getRole()
        )).collect(java.util.stream.Collectors.toList());
//    return memberRepository.findAllByRole("MENTEE").stream().filter(it -> it.getInterests().contains(interest))
//        .map(it ->
//            new MemberListResponse(
//                it.getEmail(),
//                it.getNickname(),
//                it.getProfileImageURL(),
//                it.getRole()
//            )).collect(java.util.stream.Collectors.toList());
  }

//  public MentorDetailResponse findMentorDetailByEmail(
//      String email
//  ) {
//    Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
//    return MemberUtil.MemberToMentorDetailResponse(member);
//  }
//
//  public MenteeDetailResponse findMenteeDetailByEmail(
//      String email
//  ) {
//    Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
//    return MemberUtil.MemberToMenteeDetailResponse(member);
//  }

  private Boolean isDuplicatedEmail(String email) {
    return memberRepository.existsById(email);
  }
}
