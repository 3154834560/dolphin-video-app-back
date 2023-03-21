package com.example.dolphin.domain.specs;

import com.example.dolphin.domain.entity.User_;
import com.example.dolphin.domain.entity.Video;
import com.example.dolphin.domain.entity.Video_;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author 王景阳
 * @date 2023-03-20 21:50
 */
public class VideoSpec {
    public static Specification<Video> userName(String userName) {
        return ((root, query, cb) -> {
            if (userName == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(Video_.user).get(User_.userName), userName);
        });
    }

    public static Specification<Video> userId(String userId) {
        return ((root, query, cb) -> {
            if (userId == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(Video_.user).get(User_.id), userId);
        });
    }
}
