package com.example.dolphin.domain.repository;

import com.example.dolphin.domain.entity.Comment;

import java.util.List;

/**
 * @author 王景阳
 * @date 2022/11/10 15:58
 */
public interface CommentRepository extends BaseRepository<Comment> {


    Integer deleteByUserName(String userName);

}
