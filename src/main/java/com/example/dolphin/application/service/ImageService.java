package com.example.dolphin.application.service;

import com.example.dolphin.config.VideoAndAudioHandler;
import com.example.dolphin.domain.entity.User;
import com.example.dolphin.domain.repository.UserRepository;
import com.example.dolphin.infrastructure.consts.StringPool;
import com.example.dolphin.infrastructure.tool.FileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @author 王景阳
 * @date 2022/10/30 11:14
 */
@Service
public class ImageService {

    @Autowired
    private VideoAndAudioHandler handler;

    @Autowired
    private UserRepository userRepository;

    public void downImage(String name, HttpServletRequest request, HttpServletResponse response) {
        String filePath = StringPool.IMAGE_RESOURCE_PATH + name;
        FileTool.downFile(filePath, handler, request, response);
    }

    @Transactional(rollbackFor = Exception.class)
    public String uploadImage(String userName, Part image) {
        String oldImageName = image.getSubmittedFileName();
        String newImageName = System.currentTimeMillis() + FileTool.getType(oldImageName);
        try {
            image.write(StringPool.IMAGE_SLASH + newImageName);
            saveAndDelete(userName, newImageName);
        } catch (Exception e) {
            FileTool.deleteFile(StringPool.IMAGE_RESOURCE_PATH + newImageName);
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    private void saveAndDelete(String userName, String imageName) {
        User user = userRepository.findByUserName(userName);
        FileTool.deleteHeadPortrait(user.getHeadPortraitName());
        user.setHeadPortraitUrl(StringPool.IMAGE_URL + imageName);
        user.setHeadPortraitName(imageName);
        userRepository.save(user);
    }

}
