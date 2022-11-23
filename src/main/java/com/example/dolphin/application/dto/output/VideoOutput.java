package com.example.dolphin.application.dto.output;

import com.example.dolphin.domain.entity.Video;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 王景阳
 * @date 2022/11/11 20:47
 */
@Data
@Accessors(chain = true)
public class VideoOutput {

    private String id;

    /**
     * 视频播放Url
     */
    private String url;

    /**
     * 视频作者，对应用户名
     */
    private String author;

    /**
     * 视频作者昵称
     */
    private String authorNick;

    /**
     * 视频简介
     */
    private String introduction;

    /**
     * 视频封面
     */
    private String coverUrl;

    /**
     * 点赞数
     */
    private long numbers;

    public static VideoOutput of(Video video) {
        return new VideoOutput()
                .setId(video.getId())
                .setUrl(video.getUrl())
                .setAuthor(video.getAuthor())
                .setAuthorNick(video.getAuthorNick())
                .setIntroduction(video.getIntroduction())
                .setCoverUrl(video.getCoverUrl())
                .setNumbers(video.getNumbers());
    }
}
