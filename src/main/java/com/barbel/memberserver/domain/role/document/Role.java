package com.barbel.memberserver.domain.role.document;

import com.barbel.memberserver.global.document.BaseDocument;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "roles")
public class Role extends BaseDocument {
  @Id
  private String name;
  private String description;

  @Builder
  public Role(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
