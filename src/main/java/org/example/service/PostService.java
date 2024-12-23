package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.model.Post;
import java.util.List;
import java.util.Optional;
import org.example.repository.PostRepository;
import org.example.repository.UserRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    // 게시글 생성
    public Post createPost(Long userId, Post post) {
        return userRepository.findById(userId).map(user -> {
            post.setUser(user);
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 모든 게시글 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // ID로 게시글 조회
    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    // 사용자 ID로 게시글 조회
    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUser_UserId(userId);
    }

    // 게시글 업데이트
    public Post updatePost(Long postId, Post updatedPost) {
        return postRepository.findById(postId).map(post -> {
            post.setContent(updatedPost.getContent());
            post.setUpdatedAt(updatedPost.getUpdatedAt());
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    // 게시글 삭제
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
