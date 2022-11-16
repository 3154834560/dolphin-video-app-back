package com.example.dolphin.domain.repository;

import com.example.dolphin.domain.entity.Collection;
import com.example.dolphin.domain.entity.Concern;

import java.util.List;

/**
 * @author 王景阳
 * @date 2022/11/10 18:46
 */
public interface CollectionRepository extends BaseRepository<Collection> {

    List<Collection> findAllByUserName(String userName);

    Collection findAllByUserNameAndVideoId(String userName, String videoId);

    Integer deleteByUserNameAndVideoId(String userName, String videoId);

    Integer deleteByUserName(String userName);

    Integer deleteByVideoId(String videoId);
}
