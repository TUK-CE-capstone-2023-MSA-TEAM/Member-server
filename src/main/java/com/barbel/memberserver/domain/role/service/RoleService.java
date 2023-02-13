package com.barbel.memberserver.domain.role.service;

import com.barbel.memberserver.domain.role.document.Role;
import com.barbel.memberserver.domain.role.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleService {
  private final RoleRepository roleRepository;

  public boolean saveRole(Role role) {
    try{
      roleRepository.save(role);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean deleteRole(Role role) {
    try{
      roleRepository.delete(role);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public List<Role> findAll() {
    return roleRepository.findAll();
  }
}
