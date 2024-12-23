package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.model.Post;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 특정 사용자가 작성한 게시글 조회
    List<Post> findByUser_UserId(Long userId);

    // 게시글 내용으로 검색
    List<Post> findByContentContaining(String keyword);
}

