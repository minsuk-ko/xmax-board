package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.model.Comment;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 댓글 조회
    List<Comment> findByPost_PostId(Long postId);

    // 특정 사용자가 작성한 댓글 조회
    List<Comment> findByUser_UserId(Long userId);

    // 특정 부모 댓글의 대댓글 조회
    List<Comment> findByParent_CommentId(Long parentId);
}

