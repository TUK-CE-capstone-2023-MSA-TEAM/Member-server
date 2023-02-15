package com.barbel.memberserver.domain.keyword.service;

import com.barbel.memberserver.domain.keyword.document.Keyword;
import com.barbel.memberserver.domain.keyword.dto.KeywordDeleteRequest;
import com.barbel.memberserver.domain.keyword.dto.KeywordRegistrationRequest;
import com.barbel.memberserver.domain.keyword.repository.KeywordRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordService {
  private final KeywordRepository keywordRepository;

  public boolean saveKeyword(KeywordRegistrationRequest keywordRegistrationRequest) {

    Keyword keyword = Keyword.builder()
            .keyword(keywordRegistrationRequest.getKeyword())
            .build();

    try{
      keywordRepository.save(keyword);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean deleteKeyword(KeywordDeleteRequest keywordDeleteRequest) {
    String keywordName = keywordDeleteRequest.getKeyword();
    try{
      keywordRepository.deleteByWord(keywordName);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public List<Keyword> findAll() {
    return keywordRepository.findAll();
  }
}
