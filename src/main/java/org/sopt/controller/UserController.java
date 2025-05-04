package org.sopt.controller;


import org.sopt.domain.User;
import org.sopt.dto.request.UserRequest;
import org.sopt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
