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

    // 게시물 생성: 게시물의 제목은 중복 불가능
    public Post createPost(Long userId, String title, String content) {
        if (postRepository.existsByTitle(title)) {
            throw new IllegalArgumentException("이미 존재하는 제목입니다. 다른 제목을 입력해주세요.");
        }
        Post post = new Post(userId, title, content);
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
    public void updatePostTitle(Long id, String newTitle) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id: " + id));
        post.updateTitle(newTitle);
        postRepository.save(post);
    }

    // 게시글 내용 수정
    public void updatePostContent(Long id, String newContent) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id: " + id));
        post.updateContent(newContent);
        postRepository.save(post);
    }

    // 게시물 삭제
    public void deletePostById(Long id) {
        if (!postRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 게시물이 없습니다. id: " + id);
        }
        postRepository.deleteById(id);
    }
}
