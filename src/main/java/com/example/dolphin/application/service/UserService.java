package com.example.dolphin.application.service;

import com.example.dolphin.application.dto.input.UserInput;
import com.example.dolphin.application.dto.output.UserOutput;
import com.example.dolphin.domain.model.Concern;
import com.example.dolphin.domain.model.User;
import com.example.dolphin.domain.model.Video;
import com.example.dolphin.domain.repository.*;
import com.example.dolphin.domain.specs.*;
import com.example.dolphin.infrastructure.consts.StringPool;
import com.example.dolphin.infrastructure.exception.DuplicateException;
import com.example.dolphin.infrastructure.tool.FileTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public String updateHeadPortraitBy(String userName, Part part) {
        String headPortraitName = System.currentTimeMillis() + FileTool.getType(part.getSubmittedFileName());
        String headPortraitPath = StringPool.IMAGE_RESOURCE_PATH + headPortraitName;
        try {
            FileOutputStream headPortraitStream = new FileOutputStream(new File(headPortraitPath));
            FileTool.downFile(part.getInputStream(), headPortraitStream);
        } catch (IOException e) {
            e.printStackTrace();
            FileTool.deleteImage(headPortraitName);
            return StringPool.UPLOAD_FAIL;
        }
        User user = userRepository.getBy(UserSpec.userName(userName));
        FileTool.deleteImage(user.getHeadPortraitName());
        user.setHeadPortraitName(headPortraitName);
        return StringPool.UPLOAD_SUCCESS;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBy(String userName) {
        User user = userRepository.getBy(UserSpec.userName(userName));
        deleteData(user);
        userRepository.delete(user);
        return true;
    }

    /**
     * 返回0表示当前用户不存在，返回1表示密码错误，返回2表示密码正确
     */
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
        videos.forEach(this::deleteVideo);
        FileTool.deleteImage(user.getHeadPortraitName());
    }

    private void deleteVideo(Video video) {
        videoRepository.delete(video);
        FileTool.deleteVideo(video.getVideoName());
        FileTool.deleteImage(video.getCoverName());
    }

    private void deleteSupport(String userId) {
        supportRepository.deleteAll(supportRepository.findAll(SupportSpec.userId(userId)));
    }

    private void deleteCollection(String userId) {
        collectionRepository.deleteAll(collectionRepository.findAll(CollectionSpec.userId(userId)));
    }

    private void deleteConcern(String userId) {
        List<Concern> all = concernRepository.findAll(ConcernSpec.userId(userId));
        all.addAll(concernRepository.findAll(ConcernSpec.concernedUseId(userId)));
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
