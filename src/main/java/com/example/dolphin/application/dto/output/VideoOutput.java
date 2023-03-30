package com.example.dolphin.application.dto.output;

import com.example.dolphin.domain.model.Video;
import lombok.Data;

/**
 * @author 王景阳
 * @date 2022/11/11 20:47
 */
@Data
public class VideoOutput {
    /**
     * 视频id
     */
    private String id;
    /**
     * 视频名称
     */
    private String videoName;
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
     * 视频封面名
     */
    private String coverName;
    /**
     * 点赞数
     */
    private long numbers;

    public static VideoOutput of(Video video) {
        if (video == null) {
            return null;
        }
        VideoOutput output = new VideoOutput();
        output.setId(video.getId());
        output.setAuthor(video.getUser().getUserName());
        output.setAuthorNick(video.getUser().getNick());
        output.setIntroduction(video.getIntroduction());
        output.setNumbers(video.getNumbers());
        output.setCoverName(video.getCoverName());
        output.setVideoName(video.getVideoName());
        return output;
    }
}
