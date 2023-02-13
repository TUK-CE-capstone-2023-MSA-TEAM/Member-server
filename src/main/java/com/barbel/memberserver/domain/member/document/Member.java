package com.barbel.memberserver.domain.member.document;

import com.barbel.memberserver.global.document.BaseDocument;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "members")
public class Member extends BaseDocument {
  @Id
  private String email;
  private String password;
  private String name;
  private String nickname;
  private int age;
  private String address;
  private String addressDetail;
  private String sex;
  private String phone;
  private String introduce;
  private List<String> interests;
  private List<String> majors;
  private String role;
  @Builder
  public Member(
      String email,
      String password,
      String name,
      String nickname,
      int age,
      String address,
      String addressDetail,
      String sex,
      String phone,
      String introduce,
      List<String> interests,
      List<String> majors,
      String role) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.nickname = nickname;
    this.age = age;
    this.address = address;
    this.addressDetail = addressDetail;
    this.sex = sex;
    this.phone = phone;
    this.introduce = introduce;
    this.interests = interests;
    this.majors = majors;
    this.role = role;
  }
}
