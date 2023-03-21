package com.example.dolphin.application.service;


import com.example.dolphin.application.dto.output.VideoOutput;
import com.example.dolphin.domain.entity.Collection;
import com.example.dolphin.domain.repository.CollectionRepository;
import com.example.dolphin.domain.repository.UserRepository;
import com.example.dolphin.domain.repository.VideoRepository;
import com.example.dolphin.domain.specs.CollectionSpec;
import com.example.dolphin.domain.specs.UserSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王景阳
 * @date 2022/11/10 18:50
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollectionService {

    private final CollectionRepository repository;

    private final UserRepository userRepository;

    private final VideoRepository videoRepository;

    public List<VideoOutput> getAllCollection(String userName) {
        List<Collection> collections = repository.findAll(CollectionSpec.userName(userName));
        return collections.stream().map(c -> VideoOutput.of(c.getVideo())).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean collection(String userName, String videoId) {
        Collection collection = new Collection(userRepository.getBy(UserSpec.userName(userName)), videoRepository.getById(videoId));
        repository.save(collection);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean unCollection(String userName, String videoId) {
        Collection collection = repository.getBy(CollectionSpec.userName(userName).and(CollectionSpec.videoId(videoId)));
        repository.delete(collection);
        return true;
    }
}
