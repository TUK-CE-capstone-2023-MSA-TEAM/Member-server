package com.barbel.memberserver.domain.keyword.service;

import com.barbel.memberserver.domain.keyword.document.Keyword;
import com.barbel.memberserver.domain.keyword.dto.KeywordDeleteRequest;
import com.barbel.memberserver.domain.keyword.dto.KeywordRegistrationRequest;
import com.barbel.memberserver.domain.keyword.dto.KeywordUpdateRequest;
import com.barbel.memberserver.domain.keyword.exception.KeywordDeleteFailedException;
import com.barbel.memberserver.domain.keyword.exception.KeywordListRequsetFailedException;
import com.barbel.memberserver.domain.keyword.exception.KeywordUpdateFailedException;
import com.barbel.memberserver.domain.keyword.repository.KeywordRepository;
import com.barbel.memberserver.global.error.exception.BusinessException;
import com.barbel.memberserver.global.utill.KeywordUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordService {
  private final KeywordRepository keywordRepository;

  public boolean saveKeyword(KeywordRegistrationRequest keywordRegistrationRequest) {
    Keyword keyword = KeywordUtil.keywordRegistrationRequestToKeyword(keywordRegistrationRequest);

    try{
      keywordRepository.save(keyword);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean deleteKeyword(KeywordDeleteRequest keywordDeleteRequest) {
    // TODO: 소프트 딜리트 적용시켜서 이미 지워진 상태이거나 삭제할 키워드가 존재하지 않는 경우 예외 발생시켜야함.

    try{
      keywordRepository.deleteById(keywordDeleteRequest.getKeyword());
      return true;
    } catch (Exception e) {
      throw new KeywordDeleteFailedException();
    }
  }

  public List<Keyword> findAll() {
    try{
      return keywordRepository.findAll();
    } catch (Exception e) {
      throw new KeywordListRequsetFailedException();
    }
  }

  public boolean isDuplicatedKeyword(String keyword) {
    return keywordRepository.existsById(keyword);
  }

  public void updateKeyword(KeywordUpdateRequest keywordUpdateRequest) {
    //키워드 String 자체를 id로 사용하므로 키워드 자체를 수정하는 것이 아니라 키워드를 삭제하고 새로운 키워드를 등록하는 방식으로 구현함.

    try {
      deleteKeyword(KeywordDeleteRequest.builder()
              .keyword(keywordUpdateRequest.getKeywordPre())
              .build());
      saveKeyword(KeywordRegistrationRequest.builder()
              .keyword(keywordUpdateRequest.getKeywordPost())
              .build());
    } catch (BusinessException e) {
      throw new KeywordUpdateFailedException();
    }

  }
}
