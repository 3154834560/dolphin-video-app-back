package com.example.dolphin.application.service;

import cn.hutool.core.util.RandomUtil;
import com.example.dolphin.application.dto.output.VideoOutput;
import com.example.dolphin.config.VideoAndAudioHandler;
import com.example.dolphin.domain.entity.Support;
import com.example.dolphin.domain.entity.User;
import com.example.dolphin.domain.entity.Video;
import com.example.dolphin.domain.repository.CollectionRepository;
import com.example.dolphin.domain.repository.SupportRepository;
import com.example.dolphin.domain.repository.UserRepository;
import com.example.dolphin.domain.repository.VideoRepository;
import com.example.dolphin.domain.specs.SupportSpec;
import com.example.dolphin.infrastructure.consts.StringPool;
import com.example.dolphin.infrastructure.tool.FileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 王景阳
 * @date 2022/10/29 19:09
 */
@Service
public class VideoService {

    @Autowired
    private VideoAndAudioHandler handler;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private SupportRepository supportRepository;

    @Transactional(rollbackFor = Exception.class)
    public List<VideoOutput> getAllBy(String userName) {
        return userRepository.findByUserName(userName).getVideos().stream().map(VideoOutput::of).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public List<VideoOutput> randomGet(int index) {
        List<Video> content = new ArrayList<>(videoRepository.findAll(PageRequest.of(index, StringPool.PAGE_SIZE)).getContent());
        content.add(null);
        List<Video> videos = RandomUtil.randomEleList(content, content.size() - 1);
        return videos.stream().filter(Objects::nonNull).map(VideoOutput::of).collect(Collectors.toList());
    }

    public void downVideo(String name, HttpServletRequest request, HttpServletResponse response) {
        String filePath = StringPool.VIDEO_RESOURCE_PATH + name;
        FileTool.downFile(filePath, handler, request, response);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean uploadVideo(String userName, String introduction, Part video, Part cover) {
        String oldVideoName = video.getSubmittedFileName();
        String oldCoverName = cover == null ? oldVideoName : cover.getSubmittedFileName();
        String newVideoName = System.currentTimeMillis() + FileTool.getType(oldVideoName);
        String newCoverName = cover == null ? newVideoName : System.currentTimeMillis() + FileTool.getType(oldCoverName);
        try {
            video.write(StringPool.VIDEO_SLASH + newVideoName);
            if (cover != null) {
                cover.write(StringPool.IMAGE_SLASH + newCoverName);
            }
            save(userName, introduction, newVideoName, newCoverName);
        } catch (Exception e) {
            FileTool.deleteFile(StringPool.VIDEO_RESOURCE_PATH + newVideoName);
            FileTool.deleteFile(StringPool.IMAGE_RESOURCE_PATH + newCoverName);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBy(String videoId) {
        Video video = videoRepository.getById(videoId);
        String videoPath = StringPool.VIDEO_RESOURCE_PATH + video.getVideoName();
        String coverPath = StringPool.IMAGE_RESOURCE_PATH + video.getCoverName();
        FileTool.deleteFile(videoPath);
        FileTool.deleteFile(coverPath);
        collectionRepository.deleteByVideoId(videoId);
        supportRepository.deleteByVideoId(videoId);
        videoRepository.delById(videoId);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public VideoOutput support(String userName, String videoId, int n) {
        boolean exists = supportRepository.exists(SupportSpec.exists(userName, videoId));
        Video video = videoRepository.getById(videoId);
        if (exists) {
            if (n < 0) {
                video.setNumbers(video.getNumbers() + n);
                supportRepository.deleteByUserNameAndVideoId(userName, videoId);
            }
        } else {
            video.setNumbers(video.getNumbers() + n);
            Support support = new Support(userName, videoId);
            supportRepository.save(support);
        }
        return VideoOutput.of(video);
    }

    @Transactional(rollbackFor = Exception.class)
    public VideoOutput update(String videoId, String introduction) {
        Video video = videoRepository.getById(videoId);
        video.setIntroduction(introduction);
        return VideoOutput.of(video);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Video> getBy(List<String> ids) {
        List<Video> videos = new ArrayList<>();
        ids.forEach(id -> videos.add(getBy(id)));
        return videos.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public Video getBy(String id) {
        return videoRepository.findVideoById(id);
    }

    private void save(String userName, String introduction, String videoName, String coverName) {
        User user = userRepository.findByUserName(userName);
        Video video = new Video();
        video.setAuthor(user.getUserName());
        video.setIntroduction(introduction);
        video.setVideoName(videoName);
        video.setCoverName(coverName);
        video.setAuthorNick(user.getNick());
        video.setUrl(StringPool.VIDEO_URL + videoName);
        video.setCoverUrl(StringPool.IMAGE_URL + coverName);
        videoRepository.save(video);
        user.addVideos(video);
        userRepository.save(user);
    }


}
