package com.example.dolphin.application.service;

import com.example.dolphin.domain.model.Support;
import com.example.dolphin.domain.model.Video;
import com.example.dolphin.domain.repository.SupportRepository;
import com.example.dolphin.domain.repository.UserRepository;
import com.example.dolphin.domain.repository.VideoRepository;
import com.example.dolphin.domain.specs.SupportSpec;
import com.example.dolphin.domain.specs.UserSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 王景阳
 * @date 2023-03-20 22:25
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SupportService {
    private final SupportRepository supportRepository;

    private final VideoRepository videoRepository;

    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public boolean support(String userName, String videoId, int n) {
        Support support = supportRepository.getBy(SupportSpec.userNameAndVideoId(userName, videoId));
        Video video = videoRepository.getById(videoId);
        video.setNumbers(video.getNumbers() + n);
        if (support != null) {
            supportRepository.del(support);
        } else {
            support = new Support(video, userRepository.getBy(UserSpec.userName(userName)));
            supportRepository.save(support);
        }
        return true;
    }

    public boolean isSupport(String userName, String videoId) {
        return supportRepository.exists(SupportSpec.userNameAndVideoId(userName, videoId));
    }
}
