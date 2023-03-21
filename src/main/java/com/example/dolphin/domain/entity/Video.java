package com.example.dolphin.domain.entity;

import com.example.dolphin.acomm.domain.BaseEntity;
import com.example.dolphin.infrastructure.consts.StringPool;
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
        this.url = StringPool.VIDEO_URL + videoName;
        this.videoName = videoName;
        this.coverUrl = StringPool.IMAGE_URL + coverName;
        this.coverName = coverName;
        this.introduction = introduction;
        this.numbers = 0;
    }

    /**
     * 视频播放Url
     */
    private String url;

    /**
     * 视频名
     */
    private String videoName;

    /**
     * 视频封面
     */
    private String coverUrl;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
