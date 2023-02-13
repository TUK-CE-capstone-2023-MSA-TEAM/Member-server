package com.barbel.memberserver.domain.role.repository;

import com.barbel.memberserver.domain.role.document.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
}
