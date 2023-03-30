package com.example.dolphin.application.dto.input;

import com.example.dolphin.domain.model.Comment;
import com.example.dolphin.domain.repository.UserRepository;
import com.example.dolphin.domain.repository.VideoRepository;
import com.example.dolphin.domain.specs.UserSpec;
import com.example.dolphin.infrastructure.utils.IocUtil;
import lombok.Data;

/**
 * @author 王景阳
 * @date 2023-03-28 21:02
 */
@Data
public class CommentInput {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 视频id
     */
    private String videoId;
    /**
     * 评论内容
     */
    private String content;

    public Comment to() {
        UserRepository userRepository = IocUtil.get(UserRepository.class);
        VideoRepository videoRepository = IocUtil.get(VideoRepository.class);
        return new Comment(userRepository.getBy(UserSpec.userName(userName))
                , videoRepository.getById(videoId), content);
    }
}
