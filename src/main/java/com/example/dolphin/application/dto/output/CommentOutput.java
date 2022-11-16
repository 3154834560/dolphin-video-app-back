package com.example.dolphin.application.dto.output;

import com.example.dolphin.domain.entity.Comment;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author 王景阳
 * @date 2022/11/11 19:24
 */
@Data
@Accessors(chain = true)
public class CommentOutput {

    private String commentId;

    private String userName;

    private String nick;

    private String headPortraitUrl;

    private String content;

    private LocalDateTime creatAt;

    public static CommentOutput of(UserOutput user, Comment comment) {
        return new CommentOutput()
                .setCommentId(comment.getId())
                .setUserName(user.getUserName())
                .setNick(user.getNick())
                .setHeadPortraitUrl(user.getHeadPortraitUrl())
                .setContent(comment.getContent())
                .setCreatAt(comment.getCreateAt());
    }

}
