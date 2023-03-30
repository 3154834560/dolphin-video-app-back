package com.example.dolphin.domain.specs;

import com.example.dolphin.domain.model.Concern;
import com.example.dolphin.domain.model.Concern_;
import com.example.dolphin.domain.model.User_;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author 王景阳
 * @date 2023-03-20 22:07
 */
public class ConcernSpec {
    public static Specification<Concern> userId(String userId) {
        return (root, query, cb) -> {
            if (userId == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(Concern_.user).get(User_.id), userId);
        };
    }

    public static Specification<Concern> concernedUseId(String userId) {
        return (root, query, cb) -> {
            if (userId == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(Concern_.concernedUser).get(User_.id), userId);
        };
    }

    public static Specification<Concern> userName(String userName) {
        return (root, query, cb) -> {
            if (userName == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(Concern_.user).get(User_.userName), userName);
        };
    }

    public static Specification<Concern> concernedUserName(String userName) {
        return (root, query, cb) -> {
            if (userName == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(Concern_.concernedUser).get(User_.userName), userName);
        };
    }
}
