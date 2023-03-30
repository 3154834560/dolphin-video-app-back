package com.example.dolphin.domain.specs;

import com.example.dolphin.domain.model.Collection;
import com.example.dolphin.domain.model.Collection_;
import com.example.dolphin.domain.model.User_;
import com.example.dolphin.domain.model.Video_;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author 王景阳
 * @date 2023-03-20 21:28
 */
public class CollectionSpec {
    public static Specification<Collection> userId(String userId) {
        return (root, query, cb) -> {
            if (userId == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(Collection_.user).get(User_.id), userId);
        };
    }

    public static Specification<Collection> userName(String userName) {
        return (root, query, cb) -> {
            if (userName == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(Collection_.user).get(User_.userName), userName);
        };
    }

    public static Specification<Collection> videoId(String videoId) {
        return (root, query, cb) -> {
            if (videoId == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(Collection_.video).get(Video_.id), videoId);
        };
    }
}
