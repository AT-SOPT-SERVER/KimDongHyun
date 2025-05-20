package org.sopt.dto.request;

public record CommentRequest(
        Long user,
        String content) {
}
