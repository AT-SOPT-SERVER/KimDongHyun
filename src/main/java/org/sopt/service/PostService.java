package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시물 생성
    public Post createPost(String title) {
        Post post = new Post(title);
        return postRepository.save(post);
    }

    // 모든 게시물 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 게시물 ID로 조회
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // 게시글 제목 수정
    public boolean updatePostTitle(Long id, String newTitle) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return false; // 해당 ID의 게시물이 없을 경우
        }

        try {
            post.updateTitle(newTitle); // 도메인 객체의 메서드로 제목 수정
            postRepository.save(post);  // DB에 저장
            return true;
        } catch (IllegalArgumentException e) {
            return false; // 유효성 검증에서 실패한 경우
        }
    }

    // 게시글 내용 수정
    public boolean updatePostContent(Long id, String newContent) {
        Post post = postRepository.findById(id).orElse(null);
        post.updateContent(newContent);
        postRepository.save(post);

        return true;
    }

    // 게시물 삭제
    public boolean deletePostById(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
