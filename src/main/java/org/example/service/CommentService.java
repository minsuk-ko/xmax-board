package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.model.Comment;
import java.util.List;
import java.util.Optional;
import org.example.repository.CommentRepository;
import org.example.repository.UserRepository;
import org.example.repository.PostRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    // 댓글 생성
    public Comment createComment(Long postId, Long userId, Long parentId, Comment comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            if (userId != null) {
                userRepository.findById(userId).ifPresent(comment::setUser);
            }
            if (parentId != null) {
                commentRepository.findById(parentId).ifPresent(comment::setParent);
            }
            return commentRepository.save(comment);
        }).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    // 모든 댓글 조회
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // ID로 댓글 조회
    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    // 게시글 ID로 댓글 조회
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPost_PostId(postId);
    }

    // 사용자 ID로 댓글 조회
    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByUser_UserId(userId);
    }

    // 댓글 업데이트
    public Comment updateComment(Long commentId, Comment updatedComment) {
        return commentRepository.findById(commentId).map(comment -> {
            comment.setContent(updatedComment.getContent());
            comment.setUpdatedAt(updatedComment.getUpdatedAt());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

