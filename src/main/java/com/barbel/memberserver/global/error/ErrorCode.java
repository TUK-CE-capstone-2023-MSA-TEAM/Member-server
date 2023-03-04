package com.barbel.memberserver.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  // Member
  MEMBER_NOT_FOUND("M001", "사용자를 찾을 수 없습니다."),
  MEMBER_ACCOUNT_DUPLICATED("M002", "회원 아이디 중복"),
  MEMBER_ACCOUNT_NOT_DUPLICATED("M003", "회원 아이디 중복되지않음"),
  MEMBER_LOGIN_SUCCESS("M004", "회원 로그인 성공"),
  MEMBER_LOGOUT_SUCCESS("M005", "회원 로그아웃 성공"),
  MEMBER_UPDATE_SUCCESS("M006", "회원 프로필 업데이트 성공"),

  // Role
  ROLE_NOT_FOUND("R001", "권한을 찾을 수 없습니다."),
  ROLE_REGISTRATION_FAIL("R002", "권한 등록 실패"),
  ROLE_UPDATE_FAIL("R003", "권한 수정 실패"),
  ROLE_DELETE_FAIL("R004", "권한 삭제 실패"),

  // keyword
  KEYWORD_NOT_FOUND("K001", "키워드를 찾을 수 없습니다."),
  KEYWORD_REGISTRATION_FAIL("K002", "키워드 등록 실패"),
  KEYWORD_UPDATE_FAIL("K003", "키워드 수정 실패"),
  KEYWORD_DELETE_FAIL("K004", "키워드 삭제 실패");

  private final String code;
  private final String message;

}
