package com.barbel.memberserver.domain.keyword.document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "keywords")
public class Keyword {
  @Id
  private String keyword;

  @Builder
  public Keyword(String keyword) {
    this.keyword = keyword;
  }
}
