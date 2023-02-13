package com.barbel.memberserver.domain.keyword.repository;

import com.barbel.memberserver.domain.keyword.document.Keyword;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends MongoRepository<Keyword, String> {
  void deleteByWord(String word);
}
