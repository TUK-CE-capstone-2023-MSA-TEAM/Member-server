package com.barbel.memberserver.domain.member.service;


import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return memberRepository.findById(username)
        .map(this::createUserDetails)
        .orElseThrow(() -> new UsernameNotFoundException(username+" not found."));
  }

  private UserDetails createUserDetails(Member member) {
    return User.builder()
        .username(member.getEmail())
        .password(passwordEncoder.encode(member.getPassword()))
        .roles(member.getRole())
        .build();
  }
}
