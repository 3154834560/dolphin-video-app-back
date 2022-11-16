package com.example.dolphin.application.service;

import com.example.dolphin.application.dto.output.CommentOutput;
import com.example.dolphin.application.dto.output.UserOutput;
import com.example.dolphin.domain.entity.Comment;
import com.example.dolphin.domain.entity.User;
import com.example.dolphin.domain.entity.Video;
import com.example.dolphin.domain.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王景阳
 * @date 2022/11/11 19:28
 */
@Service
public class CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional(rollbackFor = Exception.class)
    public List<CommentOutput> getAllComment(String videoId) {
        List<Comment> comments = videoService.getBy(videoId).getComments();
        List<CommentOutput> outputs = new ArrayList<>();
        for (int i = comments.size() - 1; i >= 0; i--) {
            Comment comment = comments.get(i);
            UserOutput user = userService.getBy(comment.getUserName());
            outputs.add(CommentOutput.of(user, comment));
        }
        return outputs;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean comment(String userName, String videoId, String content) {
        Video video = videoService.getBy(videoId);
        Comment comment = new Comment(userName, content);
        commentRepository.save(comment);
        video.addComment(comment);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean unComment(String id) {
        commentRepository.delById(id);
        return true;
    }
}
