package com.barbel.memberserver.domain.role.controller;

import com.barbel.memberserver.domain.role.document.Role;
import com.barbel.memberserver.domain.role.dto.RoleDeleteRequest;
import com.barbel.memberserver.domain.role.dto.RoleRegistrationRequest;
import com.barbel.memberserver.domain.role.dto.RoleUpdateRequest;
import com.barbel.memberserver.domain.role.service.RoleService;
import com.barbel.memberserver.global.result.ResultCode;
import com.barbel.memberserver.global.result.ResultResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
  // TODO: 어드민 권한이 있는 사람만 역할을 추가할 수 있도록 수정

  public ResponseEntity<ResultResponse> addRole(@RequestBody RoleRegistrationRequest roleRegistrationRequest) {
    roleService.saveRole(roleRegistrationRequest);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.ROLE_REGISTRATION_SUCCESS));
  }

  @PostMapping("/delete")
  public ResponseEntity<ResultResponse> deleteRole(@RequestBody RoleDeleteRequest roleDeleteRequest) {
    // TODO: 어드민 권한이 있는 사람만 역할을 삭제할 수 있도록 수정
    roleService.deleteRole(roleDeleteRequest);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.ROLE_DELETE_SUCCESS));
  }

  @GetMapping("/list")
  public ResponseEntity<ResultResponse> getRoleList() {
    // TODO: 로그인된 회원만 역할 목록을 볼 수 있도록 수정
    return ResponseEntity.ok(ResultResponse.of(ResultCode.ROLE_LIST_REQUEST_SUCCESS, roleService.findAll()));
  }

  @PostMapping("/update")
  public ResponseEntity<ResultResponse> updateRole(@RequestBody RoleUpdateRequest roleUpdateRequest) {
    roleService.updateRole(roleUpdateRequest);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.ROLE_UPDATE_SUCCESS));
  }
}
