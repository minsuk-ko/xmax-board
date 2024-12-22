-- 외래 키 무결성 제약 조건 비활성화
SET FOREIGN_KEY_CHECKS = 0;

-- 기존 테이블 삭제
DROP TABLE IF EXISTS Comment;
DROP TABLE IF EXISTS Post;
DROP TABLE IF EXISTS User;

-- 외래 키 무결성 제약 조건 다시 활성화
SET FOREIGN_KEY_CHECKS = 1;

-- User 테이블 생성
CREATE TABLE User (
                      user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL,
                      email VARCHAR(100) NOT NULL UNIQUE,
                      password VARCHAR(100) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- Post 테이블 생성
CREATE TABLE Post (
                      post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      user_id BIGINT NOT NULL,
                      content TEXT NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                      likes INT NOT NULL DEFAULT 0,
                      FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Comment 테이블 생성
CREATE TABLE Comment (
                         comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         post_id BIGINT NOT NULL,
                         user_id BIGINT,
                         parent_id BIGINT,
                         content TEXT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                         likes INT DEFAULT 0 NOT NULL,
                         FOREIGN KEY (post_id) REFERENCES Post(post_id) ON DELETE CASCADE,
                         FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE SET NULL,
                         FOREIGN KEY (parent_id) REFERENCES Comment(comment_id) ON DELETE CASCADE
) ENGINE=InnoDB
