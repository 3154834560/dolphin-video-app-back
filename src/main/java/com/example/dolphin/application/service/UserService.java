package com.example.dolphin.application.service;

import com.example.dolphin.application.dto.input.UserInput;
import com.example.dolphin.application.dto.output.UserOutput;
import com.example.dolphin.domain.entity.Concern;
import com.example.dolphin.domain.entity.User;
import com.example.dolphin.domain.entity.Video;
import com.example.dolphin.domain.repository.*;
import com.example.dolphin.domain.specs.*;
import com.example.dolphin.infrastructure.consts.StringPool;
import com.example.dolphin.infrastructure.exception.DuplicateException;
import com.example.dolphin.infrastructure.tool.FileTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王景阳
 * @date 2022/10/29 20:59
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final ConcernRepository concernRepository;

    private final CommentRepository commentRepository;

    private final CollectionRepository collectionRepository;

    private final SupportRepository supportRepository;

    private final VideoRepository videoRepository;

    private final VideoService videoService;

    public List<UserOutput> getAll() {
        return userRepository.findAll().stream().map(UserOutput::of).collect(Collectors.toList());
    }

    public UserOutput getBy(String userName) {
        return UserOutput.of(userRepository.getBy(UserSpec.userName(userName)));
    }

    @Transactional(rollbackFor = Exception.class)
    public UserOutput createBy(UserInput input) {
        checkingDuplicate(input.getUserName());
        User user = input.to();
        user.setHeadPortraitUrl(StringPool.DEFAULT_IMAGE_URL);
        user.setHeadPortraitName(StringPool.DEFAULT_IMAGE_NAME);
        userRepository.save(user);
        return UserOutput.of(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserOutput updateBy(UserInput input) {
        User user = userRepository.getBy(UserSpec.userName(input.getUserName()));
        input.copy(user);
        return UserOutput.of(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBy(String userName) {
        User user = userRepository.getBy(UserSpec.userName(userName));
        deleteData(user);
        userRepository.delete(user);
        return true;
    }

    public int verify(String userName, String password) {
        int verifyResult = 2;
        User user = userRepository.getBy(UserSpec.userName(userName));
        if (user == null) {
            verifyResult = 0;
        } else if (!user.getPassword().equals(password)) {
            verifyResult = 1;
        }
        return verifyResult;
    }

    private void deleteData(User user) {
        deleteConcern(user.getId());
        deleteCollection(user.getId());
        deleteComment(user.getId());
        deleteSupport(user.getId());
        List<Video> videos = videoRepository.findAll(VideoSpec.userId(user.getId()));
        videos.forEach(videoService::deleteVideo);
        FileTool.deleteHeadPortrait(user.getHeadPortraitName());
    }

    private void deleteSupport(String userId) {
        supportRepository.findAll(SupportSpec.userId(userId));
    }

    private void deleteCollection(String userId) {
        collectionRepository.findAll(CollectionSpec.userId(userId));
    }

    private void deleteConcern(String userId) {
        List<Concern> all = concernRepository.findAll(ConcernSpec.userName(userId));
        all.addAll(concernRepository.findAll(ConcernSpec.concernedUserName(userId)));
        concernRepository.deleteAll(all);
    }

    private void deleteComment(String userId) {
        commentRepository.deleteAll(commentRepository.findAll(CommentSpec.userId(userId)));
    }

    public void checkingDuplicate(String val) {
        if (userRepository.exists(UserSpec.userName(val))) {
            throw new DuplicateException("当前用户名[ " + val + " ] 已存在！");
        }
    }

}
