package com.barbel.memberserver.domain.role.document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "roles")
public class Role {
  @Id
  private String name;
  private String description;

  @Builder
  public Role(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
