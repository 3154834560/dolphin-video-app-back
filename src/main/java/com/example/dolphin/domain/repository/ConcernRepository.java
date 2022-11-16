package com.example.dolphin.domain.repository;

import com.example.dolphin.domain.entity.Concern;

import java.util.List;

/**
 * @author 王景阳
 * @date 2022/11/10 18:46
 */
public interface ConcernRepository extends BaseRepository<Concern> {

    List<Concern> findAllByUserName(String UserName);

    Concern findAllByUserNameAndConcernedUserName(String UserName, String concernedUserName);

    Integer deleteByUserNameAndConcernedUserName(String UserName, String concernedUserName);

    Integer deleteByUserName(String UserName);

    Integer deleteByConcernedUserName(String concernedUserName);
}
