package org.sopt.controller;

import org.sopt.domain.Like;
import org.sopt.domain.Post;
import org.sopt.dto.request.LikeRequest;
import org.sopt.dto.request.PostRequest;
import org.sopt.dto.response.ApiResponse;
import org.sopt.dto.response.LikeResponse;
import org.sopt.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // 좋아요 누르기
    @PostMapping("/posts/{postId}/likes")
    public ResponseEntity<ApiResponse<LikeResponse>> likeComment(
            @PathVariable Long postId,
            @RequestBody LikeRequest request
    ) {
        Like like = likeService.createCommentLike(postId, request.userId());
        LikeResponse data = new LikeResponse(like);
        return ResponseEntity
                .ok(new ApiResponse<>(true, new LikeResponse(like), null
                ));
    }

    // 좋아요 취소하기
    @DeleteMapping("/posts/{postId}/likes/{likeId}")
    public ResponseEntity<ApiResponse<Void>> unlikePost(
            @PathVariable Long postId,
            @PathVariable Long likeId
    ) {
        likeService.deleteLike(likeId);
        return ResponseEntity.ok(new ApiResponse<>(true, null, null));
    }
}
