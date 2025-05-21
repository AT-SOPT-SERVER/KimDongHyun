package org.sopt.controller;

import org.sopt.dto.request.CommentRequest;
import org.sopt.dto.request.UpdateCommentRequest;
import org.sopt.dto.response.ApiResponse;
import org.sopt.dto.response.CommentResponse;
import org.sopt.entity.Comment;
import org.sopt.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<CommentResponse>> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest request
    ) {
        Comment comment = commentService.createComment(postId, request.userId(), request.content());
        return ResponseEntity.ok(
                new ApiResponse<>(true, new CommentResponse(comment), null)
        );
    }

    // 댓글 조회
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(
            @PathVariable Long postId
    ) {
        List<CommentResponse> list = commentService.getCommentsByPost(postId).stream().map(CommentResponse::new).toList();
        return ResponseEntity.ok(list);
    }

    // 댓글 수정
    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long id,
            @RequestBody UpdateCommentRequest request
    ) {
        Comment updated = commentService.updateComment(id, request.content());
        return ResponseEntity.ok(new CommentResponse(updated));
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
