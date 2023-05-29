package com.barbel.memberserver.domain.member.document;

import com.barbel.memberserver.global.document.BaseDocument;
import java.util.Collection;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "members")
public class Member extends BaseDocument implements UserDetails {
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
  private String profileImageURL;
  private List<String> interests;
  private List<String> majors;
  private String role;

  //TODO: 이미지 업로드 된 스토리지 URL 주소 담을 필드 필요.

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
    this.profileImageURL = "https://identitylessimgserver.s3.ap-northeast-2.amazonaws.com/member/base_profile.png";
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
