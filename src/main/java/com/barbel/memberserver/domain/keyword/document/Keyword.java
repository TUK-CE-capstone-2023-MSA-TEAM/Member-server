package com.barbel.memberserver.domain.keyword.document;

import com.barbel.memberserver.global.document.BaseDocument;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "keywords")
public class Keyword extends BaseDocument {
  @Id
  private String word;

  @Builder
  public Keyword(String word) {
    this.word = word;
  }
}
