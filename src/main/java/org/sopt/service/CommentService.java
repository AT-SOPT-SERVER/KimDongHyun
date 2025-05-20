package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.entity.Comment;
import org.sopt.repository.CommentRepository;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // 작성
    public Comment createComment(Long postId, Long userId, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 포스트입니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Comment comment = new Comment(post, user, content);
        return commentRepository.save(comment);
    }

    // 수정
    public Comment updateComment(Long id, String content) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        comment.updateContent(content);
        // 변경 감지로 자동 반영
        return comment;
    }

    // 삭제
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않는 댓글입니다."));
        }
        commentRepository.deleteById(id);
    }

    // 조회
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 포스트입니다."));
        return commentRepository.findByPostOrderByCreatedAtAsc(post);
    }
}
