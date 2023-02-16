package com.barbel.memberserver.domain.role.service;

import com.barbel.memberserver.domain.role.document.Role;
import com.barbel.memberserver.domain.role.dto.RoleDeleteRequest;
import com.barbel.memberserver.domain.role.dto.RoleRegistrationRequest;
import com.barbel.memberserver.domain.role.repository.RoleRepository;
import com.barbel.memberserver.global.utill.RoleUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleService {
  private final RoleRepository roleRepository;

  public boolean saveRole(RoleRegistrationRequest roleRegistrationRequest) {
    Role role = RoleUtil.roleRegistrationRequestToRole(roleRegistrationRequest);

    try{
      roleRepository.save(role);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean deleteRole(RoleDeleteRequest roleDeleteRequest) {
    String roleName = roleDeleteRequest.getRole();
    try{
      roleRepository.deleteByName(roleName);
      return true;
    } catch (Exception e) {
      return false;
    }

  }

  public List<Role> findAll() {
    return roleRepository.findAll();
  }
}
