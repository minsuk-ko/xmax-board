package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.model.User;
import java.util.*;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일을 통해 사용자 찾기
    User findByEmail(String email);

    // 사용자 이름으로 사용자 찾기
    List<User> findByUsername(String username);
}

