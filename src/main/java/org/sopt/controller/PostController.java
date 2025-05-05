package org.sopt.controller;

import org.sopt.domain.User;
import org.sopt.dto.request.PostRequest;
import org.sopt.dto.response.ApiResponse;
import org.sopt.service.PostService;
import org.sopt.domain.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시물 생성 -> 게시물 생성시 userId를 반환
    @PostMapping
    public ResponseEntity<ApiResponse<Post>> createPost(
            @RequestHeader Long userId,
            @RequestBody PostRequest request) {
        Post post = postService.createPost(userId, request.title(), request.content());
        return ResponseEntity.ok(new ApiResponse<>(true, post, null));
    }

    // 모든 게시물 조회
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 게시물 ID로 조회
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    // 게시물 제목 수정
    @PatchMapping("/{id}")
    public ResponseEntity<String> updatePostTitle(@PathVariable Long id, @RequestBody PostRequest request) {
        postService.updatePostTitle(id, request.title());
        return ResponseEntity.ok().build();
    }

    // 게시물 내용 수정
    @PatchMapping("/{id}/content")
    public ResponseEntity<String> updatePostContent(@PathVariable Long id, @RequestBody PostRequest request) {
        postService.updatePostContent(id, request.content());
        return ResponseEntity.ok().build();
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }
}
