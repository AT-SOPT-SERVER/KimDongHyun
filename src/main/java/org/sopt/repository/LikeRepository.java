package org.sopt.repository;

import org.sopt.domain.Like;
import org.sopt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPostAndUserId(Post post, Long userId);
    Optional<Like> findByPostAndUserId(Post post, Long userId);
}
