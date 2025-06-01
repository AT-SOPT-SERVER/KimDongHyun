package org.sopt.dto.response;

import org.sopt.entity.Comment;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        Long userId,
        String userName,
        String content,
        LocalDateTime createdAt
) {
    public CommentResponse(Comment c) {
        this(
                c.getId(),
                c.getUser().getId(),
                c.getUser().getName(),
                c.getContent(),
                c.getCreatedAt()
        );
    }
}
