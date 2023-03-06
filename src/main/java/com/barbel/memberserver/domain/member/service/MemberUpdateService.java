package com.barbel.memberserver.domain.member.service;

import com.barbel.memberserver.domain.member.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateService {
  private final MemberRepository memberRepository;

  // TODO: MemberUpdateService 구현
}
