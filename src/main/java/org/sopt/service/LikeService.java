package org.sopt.service;

import org.sopt.domain.Like;
import org.sopt.domain.Post;
import org.sopt.repository.LikeRepository;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public LikeService(LikeRepository likeRepository,
                       PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    // 게시글 좋아요 추가
    @Transactional
    public Like createPostLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 없습니다. id=" + postId));

        if (likeRepository.existsByPostAndUserId(post, userId)) {
            throw new IllegalStateException("이미 좋아요를 누른 게시글입니다.");
        }

        Like like = new Like();
        like.setPost(post);
        like.setUserId(userId);
        return likeRepository.save(like);
    }


    // 게시글 좋아요 삭제x
    public void deletePostLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 없습니다. id=" + postId));

        Like like = likeRepository.findByPostAndUserId(post, userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좋아요입니다."));
        likeRepository.delete(like);
    }
}
