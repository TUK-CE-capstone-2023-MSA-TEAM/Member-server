package com.barbel.memberserver.domain.keyword.controller;

import com.barbel.memberserver.domain.keyword.document.Keyword;
import com.barbel.memberserver.domain.keyword.dto.KeywordDeleteRequest;
import com.barbel.memberserver.domain.keyword.service.KeywordService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.barbel.memberserver.domain.keyword.controller.KeywordController.MEMBER_API_URI;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping(MEMBER_API_URI)
@Slf4j
public class KeywordController {
  public static final String MEMBER_API_URI = "/api/keyword";
  private final KeywordService keywordService;

  @GetMapping("/list")
  public List<Keyword> getKeywordList() {
    List<Keyword> keywordList = keywordService.findAll();
    log.info(keywordList.toString());
    return keywordList;
  }

  @PostMapping("/add")
  public String addKeyword(@RequestBody Keyword keyword) {
    if(keywordService.saveKeyword(keyword)) {
      return "키워드 추가 완료";
    } else {
      return "키워드 추가 실패";
    }
  }

  @PostMapping("/delete")
  public String deleteKeyword(@RequestBody KeywordDeleteRequest keywordDeleteRequest) {
    if(keywordService.deleteKeyword(keywordDeleteRequest)) {
      return "키워드 삭제 완료";
    } else {
      return "키워드 삭제 실패";
    }
  }
}
