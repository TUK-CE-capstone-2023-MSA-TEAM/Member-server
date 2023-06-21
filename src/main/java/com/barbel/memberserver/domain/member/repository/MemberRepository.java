package com.barbel.memberserver.domain.member.repository;

import com.barbel.memberserver.domain.member.document.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
  List<Member> findAll();
  List<Member> findAllByRoleAndMajorsContaining(String role, String major);
  List<Member> findAllByRoleAndInterestsContaining(String role, String interest);
  Optional<Member> findByEmail(String email);

  List<Member> findAllByRole(String role);
  List<Member> findAllByMajorsContaining(String major);
  void deleteByEmail(String email);
}
