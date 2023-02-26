package com.barbel.memberserver.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

  // Member
  MEMBER_REGISTRATION_SUCCESS("M001", "사용자 등록 성공"),
  MEMBER_ACCOUNT_DUPLICATED("M002", "회원 아이디 중복"),
  MEMBER_ACCOUNT_NOT_DUPLICATED("M003", "회원 아이디 중복되지않음"),
  MEMBER_LOGIN_SUCCESS("M004", "회원 로그인 성공"),
  MEMBER_LOGOUT_SUCCESS("M005", "회원 로그아웃 성공"),
  MEMBER_UPDATE_SUCCESS("M006", "회원 프로필 업데이트 성공"),

  // Role
  ROLE_REGISTRATION_SUCCESS("R001", "권한 등록 성공"),
  ROLE_UPDATE_SUCCESS("R002", "권한 수정 성공"),
  ROLE_DELETE_SUCCESS("R003", "권한 삭제 성공"),
  ROLE_FIND_SUCCESS("R004", "권한 조회 성공"),

  // keyword
  KEYWORD_REGISTRATION_SUCCESS("K001", "키워드 등록 성공"),
  KEYWORD_UPDATE_SUCCESS("K002", "키워드 수정 성공"),
  KEYWORD_DELETE_SUCCESS("K003", "키워드 삭제 성공"),
  KEYWORD_FIND_SUCCESS("K004", "키워드 조회 성공");

  private final String code;
  private final String message;

}
