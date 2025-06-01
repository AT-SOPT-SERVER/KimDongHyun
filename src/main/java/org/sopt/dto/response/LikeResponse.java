package org.sopt.dto.response;

import org.sopt.domain.Like;

public record LikeResponse(
        Long likeId,
        Long postId,
        Long userId
) {
    public LikeResponse(Like like) {
        this(
                like.getId(),
                like.getPost().getId(),
                like.getUserId()
        );
    }
}