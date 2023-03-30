package com.example.dolphin.application.service;

import com.example.dolphin.application.dto.input.CommentInput;
import com.example.dolphin.application.dto.output.CommentOutput;
import com.example.dolphin.domain.model.Comment;
import com.example.dolphin.domain.repository.CommentRepository;
import com.example.dolphin.domain.repository.UserRepository;
import com.example.dolphin.domain.repository.VideoRepository;
import com.example.dolphin.domain.specs.CommentSpec;
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

    public Integer getCommentCountBy(String videoId) {
        return repository.findAll(CommentSpec.videoId(videoId)).size();
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean comment(CommentInput input) {
        Comment comment = input.to();
        repository.save(comment);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean unComment(String id) {
        repository.delById(id);
        return true;
    }
}
