package com.example.dolphin.application.dto.output;

import com.example.dolphin.domain.model.Comment;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 王景阳
 * @date 2022/11/11 19:24
 */
@Data
public class CommentOutput {
    /**
     *  评论id
     */
    private String commentId;
    /**
     *  用户名
     */
    private String userName;
    /**
     *  昵称
     */
    private String nick;
    /**
     *  头像名称-带后缀
     */
    private String headPortraitName;
    /**
     * 评论内容
     */
    private String content;
    /**
     *  创建时间
     */
    private LocalDateTime createAt;

    public static CommentOutput of(Comment comment) {
        CommentOutput output = new CommentOutput();
        output.setCommentId(comment.getId());
        output.setUserName(comment.getUser().getUserName());
        output.setNick(comment.getUser().getNick());
        output.setHeadPortraitName(comment.getUser().getHeadPortraitName());
        output.setContent(comment.getContent());
        output.setCreateAt(comment.getCreateAt());
        return output;
    }

}
