package com.barbel.memberserver.domain.member.controller;

import static com.barbel.memberserver.domain.member.controller.MemberController.MEMBER_API_URI;

import com.barbel.memberserver.domain.member.dto.MemberPasswordUpdateRequest;
import com.barbel.memberserver.domain.member.dto.MemberUpdateRequest;
import com.barbel.memberserver.domain.member.service.MemberUpdateService;
import com.barbel.memberserver.global.result.ResultCode;
import com.barbel.memberserver.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping(MEMBER_API_URI)
@Slf4j
public class MemberUpdateController {
  private final MemberUpdateService memberUpdateService;

  @PostMapping("/update/common")
  @Operation(summary = "기본 회원정보 수정")
  public ResponseEntity<ResultResponse> update(@RequestBody MemberUpdateRequest memberUpdateRequest) {
    log.info(memberUpdateRequest.toString());
    memberUpdateService.updateMemberCommon(memberUpdateRequest);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_UPDATE_SUCCESS));
  }
  @PostMapping("/update/password")
  @Operation(summary = "비밀번호 수정")
  public ResponseEntity<ResultResponse> updatePassword(@RequestBody MemberPasswordUpdateRequest memberPasswordUpdateRequest) {
    log.info(memberPasswordUpdateRequest.toString());
    memberUpdateService.updateMemberPassword(memberPasswordUpdateRequest);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_PASSWORD_UPDATE_SUCCESS));
  }

  @PostMapping("/update/profileImage")
  @Operation(summary = "프로필 이미지 수정")
  public ResponseEntity<ResultResponse> updateProfileImage(
      @RequestPart String memberId,
      @RequestBody MultipartFile profileImage)
  {
    memberUpdateService.updateMemberProfileImage(memberId, profileImage);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_PROFILE_IMAGE_UPDATE_SUCCESS));
  }
}
