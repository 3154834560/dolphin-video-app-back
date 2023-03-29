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

    private LocalDateTime createAt;

    public static CommentOutput of(Comment comment) {
        return new CommentOutput()
                .setCommentId(comment.getId())
                .setUserName(comment.getUser().getUserName())
                .setNick(comment.getUser().getNick())
                .setHeadPortraitUrl(comment.getUser().getHeadPortraitUrl())
                .setContent(comment.getContent())
                .setCreateAt(comment.getCreateAt());
    }

}
