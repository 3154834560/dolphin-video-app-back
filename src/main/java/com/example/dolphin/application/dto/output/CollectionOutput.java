package com.example.dolphin.application.dto.output;

import com.example.dolphin.domain.model.Video;
import lombok.Data;

/**
 * @author 王景阳
 * @date 2022/11/10 21:08
 */
@Data
public class CollectionOutput {
    /**
     *  视频id
     */
    private String videoId;
    /**
     * 视频作者，对应用户名
     */
    private String author;
    /**
     *  头像名称-带后缀
     */
    private String headPortraitName;
    /**
     * 昵称
     */
    private String nick;
    /**
     * 视频封面-带后缀
     */
    private String coverName;
    /**
     *  视频名-带后缀
     */
    private String videoName;
    /**
     * 视频简介
     */
    private String introduction;

    public static CollectionOutput of(Video video, String headPortraitName, String nick) {
        CollectionOutput output = new CollectionOutput();
        output.setVideoId(video.getId());
        output.setAuthor(video.getUser().getUserName());
        output.setHeadPortraitName(headPortraitName);
        output.setNick(nick);
        output.setIntroduction(video.getIntroduction());
        output.setCoverName(video.getCoverName());
        output.setVideoName(video.getVideoName());
        return output;
    }

}
