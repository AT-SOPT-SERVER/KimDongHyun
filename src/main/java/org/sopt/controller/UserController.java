package org.sopt.controller;


import org.sopt.domain.User;
import org.sopt.dto.UserRequest;
import org.sopt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        try {
            User user = userService.createUser(request.userName());
            return ResponseEntity.ok(user.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
