package com.example.dolphin.domain.specs;

import com.example.dolphin.domain.entity.Comment;
import com.example.dolphin.domain.entity.Comment_;
import com.example.dolphin.domain.entity.User_;
import com.example.dolphin.domain.entity.Video_;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author 王景阳
 * @date 2023-03-20 21:58
 */
public class CommentSpec {
    public static Specification<Comment> videoId(String videoId) {
        return (root, query, cb) -> {
            if (videoId == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(Comment_.video).get(Video_.id), videoId);
        };
    }

    public static Specification<Comment> userId(String userId) {
        return (root, query, cb) -> {
            if (userId == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(Comment_.user).get(User_.id), userId);
        };
    }
}
