package com.barbel.memberserver.domain.member.repository;

import com.barbel.memberserver.domain.member.document.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
  List<Member> findAll();
  Optional<Member> findAllByMajorsContains(String interest);
  Optional<Member> findAllByInterestsContains(String major);
  Optional<Member> findByEmail(String email);
  void deleteByEmail(String email);
}
