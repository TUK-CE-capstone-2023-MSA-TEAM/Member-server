package com.barbel.memberserver.global.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseDocument {
  @CreatedDate private LocalDateTime createdDate;
  @LastModifiedDate private LocalDateTime lastModifiedDate;

  private boolean isDeleted;

  public void delete() {
    this.isDeleted = true;
  }
}
