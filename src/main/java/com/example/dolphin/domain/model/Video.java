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
 * @date 2022/10/29 20:36
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Video extends BaseEntity {
    public Video(User user, String videoName, String coverName, String introduction) {
        this.user = user;
        this.videoName = videoName;
        this.coverName = coverName;
        this.introduction = introduction;
        this.numbers = 0;
    }

    /**
     * 视频名
     */
    private String videoName;
    /**
     * 视频封面名
     */
    private String coverName;
    /**
     * 视频简介
     */
    @Setter
    private String introduction;
    /**
     * 点赞数
     */
    @Setter
    private long numbers;
    /**
     * 所属用户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
