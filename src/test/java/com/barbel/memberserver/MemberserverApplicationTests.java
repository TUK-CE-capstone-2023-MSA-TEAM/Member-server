package com.barbel.memberserver;

import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MemberserverApplicationTests {

  @Autowired
  private MemberService memberService;
  @Test
  void contextLoads() {
  }

  @Test
  void dbConnectionTest() {
    Member member = Member.builder().email("rlandwnd555@naver.com")
        .age(25).name("김우중").password("password").sex("남자").nickname("로니콜먼")
        .address("서울특별시").addressDetail("강남구").role("고수").interests(List.of("운동", "독서")).majors(List.of("컴퓨터공학")).build();
    try {
      memberService.saveMember(member);
    } catch (Exception e) {
      e.printStackTrace();
    }
    Member member1 = memberService.findMemberByEmail("rlandwnd555@naver.com");
    System.out.println(member1);
    assertThrows(IllegalArgumentException.class, () -> {
      memberService.findMemberByEmail("asdfasdf@gmail.com");
    });
  }

}
