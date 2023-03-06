package com.barbel.memberserver.domain.keyword.controller;

import com.barbel.memberserver.domain.keyword.document.Keyword;
import com.barbel.memberserver.domain.keyword.dto.KeywordDeleteRequest;
import com.barbel.memberserver.domain.keyword.dto.KeywordRegistrationRequest;
import com.barbel.memberserver.domain.keyword.dto.KeywordUpdateRequest;
import com.barbel.memberserver.domain.keyword.exception.KeywordDeleteFailedException;
import com.barbel.memberserver.domain.keyword.exception.KeywordDuplicatedException;
import com.barbel.memberserver.domain.keyword.service.KeywordService;
import com.barbel.memberserver.global.result.ResultResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.barbel.memberserver.domain.keyword.controller.KeywordController.MEMBER_API_URI;
import static com.barbel.memberserver.global.result.ResultCode.KEYWORD_DELETE_SUCCESS;
import static com.barbel.memberserver.global.result.ResultCode.KEYWORD_LIST_REQUEST_SUCCESS;
import static com.barbel.memberserver.global.result.ResultCode.KEYWORD_REGISTRATION_SUCCESS;
import static com.barbel.memberserver.global.result.ResultCode.KEYWORD_UPDATE_SUCCESS;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping(MEMBER_API_URI)
@Slf4j
public class KeywordController {
  public static final String MEMBER_API_URI = "/api/keyword";
  private final KeywordService keywordService;

  @GetMapping("/list")
  public ResponseEntity<ResultResponse> getKeywordList() {
    //TODO: 로그인 된 회원만 키워드 리스트를 볼 수 있도록 구현해야함.

    List<Keyword> keywordList = keywordService.findAll();
    return ResponseEntity.ok(ResultResponse.of(KEYWORD_LIST_REQUEST_SUCCESS, keywordList));
  }

  @PostMapping("/add")
  public ResponseEntity<ResultResponse> addKeyword(@RequestBody KeywordRegistrationRequest keywordRegistrationRequest) {
    //TODO: 키워드 등록 어드민 권한 있는 회원만 가능하도록 구현해야함.

    if(keywordService.isDuplicatedKeyword(keywordRegistrationRequest.getKeyword())) {
      throw new KeywordDuplicatedException();
    }
    keywordService.saveKeyword(keywordRegistrationRequest);
    return ResponseEntity.ok(ResultResponse.of(KEYWORD_REGISTRATION_SUCCESS));
  }

  @PostMapping("/delete")
  public ResponseEntity<ResultResponse> deleteKeyword(@RequestBody KeywordDeleteRequest keywordDeleteRequest) {
    //TODO: 키워드 삭제 어드민 권한 있는 회원만 가능하도록 구현해야함.

    if (keywordService.deleteKeyword(keywordDeleteRequest)) {
      return ResponseEntity.ok(ResultResponse.of(KEYWORD_DELETE_SUCCESS));
    } else {
      throw new KeywordDeleteFailedException();
    }
  }

  @PostMapping("/update")
  public ResponseEntity<ResultResponse> updateKeyword(@RequestBody KeywordUpdateRequest keywordUpdateRequest) {
    keywordService.updateKeyword(keywordUpdateRequest);
    return ResponseEntity.ok(ResultResponse.of(KEYWORD_UPDATE_SUCCESS));
  }
}
