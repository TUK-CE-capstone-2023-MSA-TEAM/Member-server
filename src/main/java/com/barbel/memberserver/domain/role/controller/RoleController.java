package com.barbel.memberserver.domain.role.controller;

import com.barbel.memberserver.domain.role.document.Role;
import com.barbel.memberserver.domain.role.dto.RoleDeleteRequest;
import com.barbel.memberserver.domain.role.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.barbel.memberserver.domain.role.controller.RoleController.MEMBER_API_URI;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@RequestMapping(MEMBER_API_URI)
public class RoleController {
  public static final String MEMBER_API_URI = "/api/role";
  private final RoleService roleService;

  @PostMapping("/add")
  public String addRole(@RequestBody Role role) {
    if(roleService.saveRole(role)) {
      return "역할 추가 완료";
    } else {
      return "역할 추가 실패";
    }
  }

  @PostMapping("/delete")
  public String deleteRole(@RequestBody RoleDeleteRequest roleDeleteRequest) {
    if(roleService.deleteRole(roleDeleteRequest)) {
      return "역할 삭제 완료";
    } else {
      return "역할 삭제 실패";
    }
  }

  @GetMapping("/list")
  public List<Role> getRoleList() {
    return roleService.findAll();
  }
}
