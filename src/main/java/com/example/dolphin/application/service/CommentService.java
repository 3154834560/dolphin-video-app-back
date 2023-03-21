package com.example.dolphin.application.service;

import com.example.dolphin.application.dto.output.CommentOutput;
import com.example.dolphin.domain.entity.Comment;
import com.example.dolphin.domain.entity.User;
import com.example.dolphin.domain.entity.Video;
import com.example.dolphin.domain.repository.CommentRepository;
import com.example.dolphin.domain.repository.UserRepository;
import com.example.dolphin.domain.repository.VideoRepository;
import com.example.dolphin.domain.specs.CommentSpec;
import com.example.dolphin.domain.specs.UserSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王景阳
 * @date 2022/11/11 19:28
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository repository;

    private final UserRepository userRepository;

    private final VideoRepository videoRepository;

    public List<CommentOutput> getAllComment(String videoId) {
        List<Comment> comments = repository.findAll(CommentSpec.videoId(videoId));
        return comments.stream().map(CommentOutput::of).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean comment(String userName, String videoId, String content) {
        User user = userRepository.getBy(UserSpec.userName(userName));
        Video video = videoRepository.getById(videoId);
        Comment comment = new Comment(user, video, content);
        repository.save(comment);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean unComment(String id) {
        repository.delById(id);
        return true;
    }
}
