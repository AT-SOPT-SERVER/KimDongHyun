package org.sopt.controller;


import org.sopt.domain.User;
import org.sopt.dto.request.UserRequest;
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
    public ResponseEntity<Long> createUser(@RequestBody UserRequest request) {
        User user = userService.createUser(request.userName());
        return ResponseEntity.ok(user.getId());
    }

    // 이름 변경 시
    @PatchMapping
    public ResponseEntity<String> updateUserName(
            @PathVariable Long id,
            @RequestBody UserRequest request) {
        User updateUser = userService.updateUsername(id, request.userName());
        return ResponseEntity.ok(updateUser.getName());
    }

    // 사용자 삭제 시
    @DeleteMapping
    private ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 전체회원 조회
    @GetMapping
    private ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
