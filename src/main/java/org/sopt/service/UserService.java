package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 새로운 사용자 생성
    public User createUser(String userName) {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("이름은 필수입니다.");
        }
        User user = new User();
        user.setName(userName);
        return userRepository.save(user);
    }

    // ID로 사용자 조회
    @Transactional(readOnly = true)
    public User getUserIdById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당하는 사용자가 없습니다. id: " + id)
                );
    }

    // 전체 사용자 조회
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 사용자 이름 수정
    public User updateUsername(Long id, String newName) {
        User user = getUserIdById(id);
        user.setName(newName);
        return userRepository.save(user);
    }

    // 사용자 삭제
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("해당하는 사용자가 없습니다. id: " + id);
        }
        userRepository.deleteById(id);
    }
}
