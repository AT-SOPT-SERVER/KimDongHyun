package org.sopt.dto.response;

public class UserResponse {
    private final Long userId;
    private final String userName;

    public UserResponse(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
