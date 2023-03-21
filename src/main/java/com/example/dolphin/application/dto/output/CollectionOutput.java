package com.example.dolphin.application.dto.output;

import com.example.dolphin.domain.entity.Video;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 王景阳
 * @date 2022/11/10 21:08
 */
@Data
@Accessors(chain = true)
public class CollectionOutput {

    private String videoId;

    /**
     * 视频作者，对应用户名
     */
    private String author;

    private String headPortraitUrl;

    private String nick;

    /**
     * 视频封面
     */
    private String coverUrl;

    private String videoUrl;

    /**
     * 视频简介
     */
    private String introduction;

    public static CollectionOutput of(Video video, String headPortraitUrl, String nick) {
        return new CollectionOutput()
                .setVideoId(video.getId())
                .setAuthor(video.getUser().getUserName())
                .setHeadPortraitUrl(headPortraitUrl)
                .setNick(nick)
                .setCoverUrl(video.getCoverUrl())
                .setIntroduction(video.getIntroduction())
                .setVideoUrl(video.getUrl());
    }

}
