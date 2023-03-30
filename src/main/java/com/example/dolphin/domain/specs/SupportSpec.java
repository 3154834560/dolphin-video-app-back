package com.example.dolphin.domain.specs;

import com.example.dolphin.domain.model.Support;
import com.example.dolphin.domain.model.Support_;
import com.example.dolphin.domain.model.User_;
import com.example.dolphin.domain.model.Video_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/**
 * @author 王景阳
 * @date 2022/11/11 21:21
 */
public class SupportSpec {
    public static Specification<Support> userNameAndVideoId(String userName, String videoId) {
        return (root, query, cb) -> {
            Path<String> userNames = root.get(Support_.user).get(User_.userName);
            Path<String> videoIds = root.get(Support_.video).get(Video_.id);
            Predicate p1 = cb.equal(userNames, userName);
            Predicate p2 = cb.equal(videoIds, videoId);
            return cb.and(p1, p2);
        };
    }

    public static Specification<Support> userId(String userId) {
        return (root, query, cb) -> {
            if (userId == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(Support_.user).get(User_.id), userId);
        };
    }

    public static Specification<Support> videoId(String videoId) {
        return (root, query, cb) -> {
            if (videoId == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(Support_.video).get(Video_.id), videoId);
        };
    }
}
