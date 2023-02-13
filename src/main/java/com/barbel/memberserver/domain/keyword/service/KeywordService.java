package com.barbel.memberserver.domain.keyword.service;

import com.barbel.memberserver.domain.keyword.document.Keyword;
import com.barbel.memberserver.domain.keyword.repository.KeywordRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordService {
  private final KeywordRepository keywordRepository;

  public boolean saveKeyword(Keyword keyword) {
    try{
      keywordRepository.save(keyword);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean deleteKeyword(Keyword keyword) {
    try{
      keywordRepository.delete(keyword);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public List<Keyword> findAll() {
    return keywordRepository.findAll();
  }
}
