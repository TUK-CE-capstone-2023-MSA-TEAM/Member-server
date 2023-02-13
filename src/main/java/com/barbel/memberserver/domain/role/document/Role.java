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
  private String role;
  private String description;

  @Builder
  public Role(String role, String description) {
    this.role = role;
    this.description = description;
  }
}
