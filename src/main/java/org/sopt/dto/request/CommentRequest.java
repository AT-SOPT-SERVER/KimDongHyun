package org.sopt.dto.request;

public record CommentRequest(
        Long userId,
        String content) {
}
