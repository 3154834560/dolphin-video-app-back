package com.example.dolphin.application.service;

import com.example.dolphin.application.dto.input.UserInput;
import com.example.dolphin.application.dto.output.UserOutput;
import com.example.dolphin.domain.entity.User;
import com.example.dolphin.domain.entity.Video;
import com.example.dolphin.domain.repository.CollectionRepository;
import com.example.dolphin.domain.repository.ConcernRepository;
import com.example.dolphin.domain.repository.SupportRepository;
import com.example.dolphin.domain.repository.UserRepository;
import com.example.dolphin.domain.specs.UserSpec;
import com.example.dolphin.infrastructure.consts.StringPool;
import com.example.dolphin.infrastructure.exception.DuplicateException;
import com.example.dolphin.infrastructure.tool.FileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王景阳
 * @date 2022/10/29 20:59
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ConcernRepository concernRepository;

    @Autowired
    CollectionRepository collectionRepository;

    @Autowired
    SupportRepository supportRepository;

    @Autowired
    VideoService videoService;

    @Transactional(rollbackFor = Exception.class)
    public List<UserOutput> getAll() {
        return userRepository.findAll().stream().map(UserOutput::of).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public UserOutput getBy(String userName) {
        return UserOutput.of(userRepository.findByUserName(userName));
    }

    @Transactional(rollbackFor = Exception.class)
    public UserOutput createBy(UserInput input) {
        checkingDuplicate(StringPool.USER_NAME, input.getUserName());
        User user = new User();
        input.copy(user);
        user.setHeadPortraitUrl(StringPool.DEFAULT_IMAGE_URL);
        user.setHeadPortraitName(StringPool.DEFAULT_IMAGE_NAME);
        userRepository.save(user);
        return UserOutput.of(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserOutput updateBy(UserInput input) {
        User user = userRepository.findByUserName(input.getUserName());
        input.copy(user);
        userRepository.save(user);
        return UserOutput.of(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBy(String userName) {
        User user = userRepository.findByUserName(userName);
        deleteData(user);
        userRepository.deleteByUserName(userName);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public int verify(String userName, String password) {
        int verifyResult = 2;
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            verifyResult = 0;
        } else if (!user.getPassword().equals(password)) {
            verifyResult = 1;
        }
        return verifyResult;
    }

    public List<User> getBy(List<String> userNames) {
        List<User> users = new ArrayList<>();
        userNames.forEach(userName -> users.add(userRepository.findByUserName(userName)));
        return users;
    }


    private void deleteData(User user) {
        deleteSupport(user.getUserName());
        FileTool.deleteHeadPortrait(user.getHeadPortraitName());
        user.getVideos().forEach(this::deleteVideo);
        deleteConcern(user.getUserName());
        deleteCollection(user.getUserName());
    }

    private void deleteSupport(String userName) {
        supportRepository.deleteByUserName(userName);
    }

    private void deleteVideo(Video video) {
        String videoPath = StringPool.VIDEO_RESOURCE_PATH + video.getVideoName();
        String coverPath = StringPool.IMAGE_RESOURCE_PATH + video.getCoverName();
        FileTool.deleteFile(videoPath);
        FileTool.deleteFile(coverPath);
        supportRepository.deleteByVideoId(video.getId());
        collectionRepository.deleteByVideoId(video.getId());
    }

    private void deleteCollection(String userName) {
        collectionRepository.deleteByUserName(userName);
    }

    private void deleteConcern(String userName) {
        concernRepository.deleteByUserName(userName);
        concernRepository.deleteByConcernedUserName(userName);
    }

    public void checkingDuplicate(String filedName, String val) {
        if (userRepository.exists(UserSpec.exists(filedName, val))) {
            throw new DuplicateException("当前 " + filedName + ": [ " + val + " ] 已存在！");
        }
    }

}
