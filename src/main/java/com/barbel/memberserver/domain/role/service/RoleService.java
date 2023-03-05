package com.barbel.memberserver.domain.role.service;

import com.barbel.memberserver.domain.role.document.Role;
import com.barbel.memberserver.domain.role.dto.RoleDeleteRequest;
import com.barbel.memberserver.domain.role.dto.RoleRegistrationRequest;
import com.barbel.memberserver.domain.role.dto.RoleUpdateRequest;
import com.barbel.memberserver.domain.role.exception.RoleDeleteFailedException;
import com.barbel.memberserver.domain.role.exception.RoleDuplicatedException;
import com.barbel.memberserver.domain.role.exception.RoleListRequestFailedException;
import com.barbel.memberserver.domain.role.exception.RoleUpdateFailedException;
import com.barbel.memberserver.domain.role.repository.RoleRepository;
import com.barbel.memberserver.global.error.exception.BusinessException;
import com.barbel.memberserver.global.utill.RoleUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleService {
  private final RoleRepository roleRepository;

  public void saveRole(RoleRegistrationRequest roleRegistrationRequest) {
    if(isDuplicateRole(roleRegistrationRequest.getName())) {
      throw new RoleDuplicatedException();
    }
    Role role = RoleUtil.roleRegistrationRequestToRole(roleRegistrationRequest);
    roleRepository.save(role);
  }

  public void deleteRole(RoleDeleteRequest roleDeleteRequest) {
    if(!isDuplicateRole(roleDeleteRequest.getRole())) {
      throw new RoleDeleteFailedException();
    }
    roleRepository.deleteById(roleDeleteRequest.getRole());
  }

  public List<Role> findAll() {
    try{
      return roleRepository.findAll();
    } catch (Exception e) {
      throw new RoleListRequestFailedException();
    }
  }

  public void updateRole(RoleUpdateRequest roleUpdateRequest) {
    if(roleUpdateRequest.getRolePre().equals(roleUpdateRequest.getRolePost())) {
      Role role = roleRepository.findById(roleUpdateRequest.getRolePre()).orElseThrow(
          () -> new RoleUpdateFailedException()
      );
      role.setDescription(roleUpdateRequest.getDescription());
      roleRepository.save(role);
    } else {
      roleRepository.deleteById(roleUpdateRequest.getRolePre());
      try{
        deleteRole(RoleDeleteRequest.builder()
            .role(roleUpdateRequest.getRolePre()).build());

        saveRole(RoleRegistrationRequest.builder()
            .name(roleUpdateRequest.getRolePost())
            .description(roleUpdateRequest.getDescription()).build());
      } catch (BusinessException e) {
        throw new RoleUpdateFailedException();
      }
    }
  }

  public boolean isDuplicateRole(String roleName) {
    return roleRepository.existsById(roleName);
  }
}
