package com.example.dolphin.application.service;


import com.example.dolphin.application.dto.output.VideoOutput;
import com.example.dolphin.domain.entity.Collection;
import com.example.dolphin.domain.entity.Video;
import com.example.dolphin.domain.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王景阳
 * @date 2022/11/10 18:50
 */
@Service
public class CollectionService {

    @Autowired
    CollectionRepository collectionRepository;

    @Autowired
    UserService userService;

    @Autowired
    VideoService videoService;

    @Transactional(rollbackFor = Exception.class)
    public List<VideoOutput> getAllCollection(String userName) {
        List<Collection> collections = collectionRepository.findAllByUserName(userName);
        List<VideoOutput> outputs = new ArrayList<>();
        List<Video> videos = videoService.getBy(collections.stream().map(Collection::getVideoId).collect(Collectors.toList()));
        for (Video video : videos) {
            outputs.add(VideoOutput.of(video));
        }
        return outputs;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean collection(String userName, String videoId) {
        Collection collection = new Collection(userName, videoId);
        collectionRepository.save(collection);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean unCollection(String userName, String videoId) {
        collectionRepository.deleteByUserNameAndVideoId(userName, videoId);
        return true;
    }
}
