package com.barbel.memberserver.domain.member.service;

import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.dto.MemberPasswordUpdateRequest;
import com.barbel.memberserver.domain.member.dto.MemberUpdateRequest;
import com.barbel.memberserver.domain.member.exception.MemberNotFountException;
import com.barbel.memberserver.domain.member.exception.WrongPasswordException;
import com.barbel.memberserver.domain.member.repository.MemberRepository;
import com.barbel.memberserver.global.aws.S3Uploader;
import com.barbel.memberserver.global.utill.MemberUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateService {
  private final MemberRepository memberRepository;
  private final S3Uploader s3Uploader;
  private final PasswordEncoder passwordEncoder;
  private final String S3_DIR_NAME = "/member/profileImage";

  @Transactional
  public void updateMemberCommon(
      MemberUpdateRequest memberUpdateRequest
  ) {
    Member member = memberRepository.findById(memberUpdateRequest.getMemberId())
        .orElseThrow(MemberNotFountException::new);
    MemberUtil.updateMember(memberUpdateRequest, member);
    memberRepository.save(member);
  }

  @Transactional
  public void updateMemberPassword(
      MemberPasswordUpdateRequest memberPasswordUpdateRequest
  ) {
    Member member = memberRepository.findById(memberPasswordUpdateRequest.getMemberId())
        .orElseThrow(MemberNotFountException::new);
    if(!passwordEncoder.encode(member.getPassword()).equals(memberPasswordUpdateRequest.getPassword())) {
      throw new WrongPasswordException();
    }
    member.setPassword(memberPasswordUpdateRequest.getNewPassword());
    memberRepository.save(member);
  }

  @Transactional
  public void updateMemberProfileImage(
      String memberId,
      MultipartFile profileImage
  ) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(MemberNotFountException::new);
    member.setProfileImageURL(uploadProfileImage(profileImage));
    memberRepository.save(member);
  }

  private String uploadProfileImage(
      MultipartFile profileImage
  ) {
    return s3Uploader.uploadImage(profileImage, S3_DIR_NAME);
  }
}
