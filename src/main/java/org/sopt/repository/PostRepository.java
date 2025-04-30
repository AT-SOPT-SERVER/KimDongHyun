package org.sopt.repository;

import org.sopt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 기본 CRUD 기능은 JpaRepository가 모두 제공
//    Post findPostById(int id);
//
//    boolean deleteById(int id);

}