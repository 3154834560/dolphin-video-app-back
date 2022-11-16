package com.example.dolphin.domain.specs;


import com.example.dolphin.domain.entity.User;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author 王景阳
 * @date 2022/10/29 20:58
 */
public class UserSpec {

    public static Specification<User> exists(String filedName, String val) {
        return (root, query, cb) -> {
            if (val == null) {
                return cb.disjunction();
            }
            return cb.equal(root.get(filedName), val);
        };
    }

}
