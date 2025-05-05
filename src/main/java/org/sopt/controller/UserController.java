package org.sopt.controller;


import org.sopt.domain.User;
import org.sopt.dto.request.UserRequest;
import org.sopt.dto.response.UserResponse;
import org.sopt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        User user = userService.createUser(request.userName());
        UserResponse response = new UserResponse(user.getId(), user.getName());
        return ResponseEntity.ok(response);
    }

    // 이름 변경 시
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserName(
            @PathVariable Long id,
            @RequestBody UserRequest request) {
        User updateUser = userService.updateUsername(id, request.userName());
        UserResponse response = new UserResponse(updateUser.getId(), updateUser.getName());
        return ResponseEntity.ok(response);
    }

    // 사용자 삭제 시
    @DeleteMapping("/{id}")
    private ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 전체회원 조회
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> response = userService.getAllUsers().stream()
                .map(user -> new UserResponse(user.getId(), user.getName()))
                .toList();
        return ResponseEntity.ok(response);
    }
}
