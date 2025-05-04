package org.sopt.domain;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User(Long userId, String userName) {
        validateName(userName);
        this.id = userId;
        this.name = userName;
    }

    private void validateName(String name) {
        if (name.length() > 10) {
            throw new IllegalArgumentException("이름을 10자 이내로 입력해주세요!");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("이름을 입력하지 않았습니다. 입력해주세요^^");
        }
    }

    public void updateUser(String newName) {
        validateName(newName);
        this.name = newName;
    }

    public void setUsername(String userName) {
        this.name = userName;
    }
}
