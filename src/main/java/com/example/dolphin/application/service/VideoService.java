package com.example.dolphin.application.service;

import com.example.dolphin.application.dto.input.VideoInput;
import com.example.dolphin.application.dto.output.VideoListOutput;
import com.example.dolphin.application.dto.output.VideoOutput;
import com.example.dolphin.domain.model.User;
import com.example.dolphin.domain.model.Video;
import com.example.dolphin.domain.repository.*;
import com.example.dolphin.domain.specs.*;
import com.example.dolphin.infrastructure.consts.StringPool;
import com.example.dolphin.infrastructure.tool.FileTool;
import com.example.dolphin.infrastructure.tool.VideoTool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王景阳
 * @date 2022/10/29 19:09
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoService {

    /**
     * 获取视频第一帧作为视频封面，第一帧图片类型，默认为 jpg
     */
    @Value("${image.type:jpg}")
    private String imageType;

    /**
     * 获取视频第一帧作为视频封面，第一帧图片旋转角度
     */
    @Value("${image.rotate.angle:0}")
    private int rotateAngle;

    private final UserRepository userRepository;

    private final VideoRepository videoRepository;

    private final CollectionRepository collectionRepository;

    private final SupportRepository supportRepository;

    private final CommentRepository commentRepository;

    public List<VideoListOutput> getAllBy(String userName) {
        return videoRepository.findAll(VideoSpec.userName(userName)).stream().map(VideoListOutput::of).collect(Collectors.toList());
    }

    public List<VideoOutput> randomGet(int index) {
        List<Video> content = new ArrayList<>(videoRepository.findAll(PageRequest.of(index, StringPool.PAGE_SIZE)).getContent());
        Collections.shuffle(content);
        return content.stream().map(VideoOutput::of).collect(Collectors.toList());
    }

    /**
     * 分块上传视频
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadVideo(String videoInputStr, Part video, Part cover) {
        ObjectMapper objectMapper = new ObjectMapper();
        VideoInput videoInput = null;
        try {
            videoInput = objectMapper.readValue(videoInputStr, VideoInput.class);
        } catch (JsonProcessingException e) {
            return false;
        }
        if (video == null) {
            return true;
        }
        return saveVideoAndCover(videoInput, video, cover);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBy(String videoId) {
        Video video = videoRepository.getById(videoId);
        deleteVideo(video);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public VideoOutput update(String videoId, String introduction) {
        Video video = videoRepository.getById(videoId);
        video.setIntroduction(introduction);
        return VideoOutput.of(video);
    }

    public VideoOutput getById(String id) {
        return VideoOutput.of(videoRepository.getById(id));
    }

    private boolean saveVideoAndCover(VideoInput videoInput, Part video, Part cover) {
        String videoPath = videoInput.getVideoPath();
        String coverPath = videoInput.getCoverPath(imageType);
        try {
            InputStream videoInputStream = video.getInputStream();
            FileOutputStream videoOutputStream = new FileOutputStream(new File(videoPath), true);
            FileTool.downFile(videoInputStream, videoOutputStream);
            if (cover != null && videoInput.isHasCover()) {
                InputStream coverInputStream = cover.getInputStream();
                FileOutputStream coverOutputStream = new FileOutputStream(new File(coverPath));
                FileTool.downFile(coverInputStream, coverOutputStream);
            } else if (cover == null && !videoInput.isHasCover() && videoInput.isEnd()) {
                VideoTool.getVideoFirstImg(videoPath, coverPath, imageType, rotateAngle);
            }
            if (videoInput.isEnd()) {
                save(videoInput.getUserName(), videoInput.getVideoIntroduction(), videoInput.getVideoName(), videoInput.getCoverName(imageType));
            }
        } catch (Exception e) {
            FileTool.deleteFile(videoPath);
            FileTool.deleteFile(coverPath);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void save(String userName, String introduction, String videoName, String coverName) {
        User user = userRepository.getBy(UserSpec.userName(userName));
        Video video = new Video(user, videoName, coverName, introduction);
        videoRepository.save(video);
    }

    public void deleteVideo(Video video) {
        collectionRepository.delete(collectionRepository.getBy(CollectionSpec.videoId(video.getId())));
        commentRepository.delete(commentRepository.getBy(CommentSpec.videoId(video.getId())));
        supportRepository.delete(supportRepository.getBy(SupportSpec.videoId(video.getId())));
        videoRepository.delete(video);
        FileTool.deleteVideo(video.getVideoName());
        FileTool.deleteImage(video.getCoverName());
    }
}
