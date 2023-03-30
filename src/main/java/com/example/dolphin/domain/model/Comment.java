package com.example.dolphin.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * @author 王景阳
 * @date 2022/11/10 15:55
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    /**
     * 视频
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Video video;
    /**
     * 评论内容
     */
    @Setter
    private String content;

    public Comment(User user, Video video, String content) {
        this.user = user;
        this.video = video;
        this.content = content;
    }
}
