package com.example.dolphin.domain.specs;

import com.example.dolphin.domain.entity.Support;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/**
 * @author 王景阳
 * @date 2022/11/11 21:21
 */
public class SupportSpec {

    private static final String USER_NAME = "userName";

    private static final String VIDEO_ID = "videoId";

    public static Specification<Support> exists(String userName, String videoId) {
        return (root, query, cb) -> {
            Path<Object> userNames = root.get(USER_NAME);
            Path<Object> videoIds = root.get(VIDEO_ID);
            Predicate p1 = cb.equal(userNames, userName);
            Predicate p2 = cb.equal(videoIds, videoId);
            return cb.and(p1, p2);
        };
    }
}
