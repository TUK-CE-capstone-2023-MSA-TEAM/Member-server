package com.barbel.memberserver.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  // Global
  INVALID_INPUT_VALUE(400, "G001", "유효하지 않은 입력값입니다."),
  INTERNAL_SERVER_ERROR(500, "G002", "서버 내부 오류입니다."),
  FILE_CONVERT_ERROR(500, "G003", "파일 변환 오류입니다."),
  UN_AUTHORIZED_ACCESS(401, "G004", "권한이 없습니다."),
  INVALID_TOKEN(401, "G005", "유효하지 않은 토큰입니다."),

  // Member
  MEMBER_NOT_FOUND(400, "M001", "사용자를 찾을 수 없습니다."),
  MEMBER_ACCOUNT_DUPLICATED(400, "M002", "회원 아이디 중복"),
  MEMBER_UPDATE_FAIL(400, "M003", "회원 프로필 업데이트 실패"),
  MEMBER_LIST_REQUEST_FAIL(500, "M004", "회원 리스트 요청 실패"),
  MEMBER_PASSWORD_WRONG(401, "M005", "비밀번호가 틀렸습니다."),
  MEMBER_AUTH_FAIL(401, "M006", "회원 인증 실패"),

  // Role
  ROLE_NOT_FOUND(400, "R001", "역할을 찾을 수 없습니다."),
  ROLE_LIST_REQUEST_FAIL(500, "R006", "역할 리스트 요청 실패"),
  ROLE_REGISTRATION_FAIL(500, "R002", "역할 등록 실패"),
  ROLE_UPDATE_FAIL(500, "R003", "역할 수정 실패"),
  ROLE_DELETE_FAIL(500, "R004", "역할 삭제 실패"),
  ROLE_DUPLICATED(400, "R005", "이미 등록되어있는 역할입니다."),

  // keyword
  KEYWORD_NOT_FOUND(400, "K001", "키워드를 찾을 수 없습니다."),
  KEYWORD_REGISTRATION_FAIL(500, "K002", "키워드 등록 실패"),
  KEYWORD_UPDATE_FAIL(500, "K003", "키워드 수정 실패"),
  KEYWORD_DELETE_FAIL(500, "K004", "키워드 삭제 실패"),
  KEYWORD_DUPLICATED(400, "K005", "이미 등록되어있는 키워드입니다."),
  KEYWORD_LIST_REQUEST_FAIL(500, "K006", "키워드 리스트 요청 실패");

  private final int status;
  private final String code;
  private final String message;

}
