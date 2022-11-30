package com.example.dolphin.infrastructure.tool;

import cn.hutool.core.io.FileUtil;
import com.example.dolphin.config.VideoAndAudioHandler;
import com.example.dolphin.infrastructure.consts.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author 王景阳
 * @date 2022/10/30 11:15
 */
@Slf4j
public class FileTool {

    public static String getType(String path) {
        return path.substring(path.lastIndexOf(StringPool.DOT));
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (!filePath.endsWith(StringPool.DEFAULT_IMAGE_NAME) && file.exists()) {
            FileUtil.del(file);
        }
    }

    public static void downFile(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) > 0) {
            outputStream.write(bytes, 0, len);
        }
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    public static void downFile(String filePath, VideoAndAudioHandler handler, HttpServletRequest request, HttpServletResponse response) {
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                String mimeType = Files.probeContentType(path);
                if (!StringUtils.hasText(mimeType)) {
                    response.setContentType(mimeType);
                }
                request.setAttribute(VideoAndAudioHandler.ATTR_FILE, path);
                handler.handleRequest(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public static void deleteHeadPortrait(String imageName) {
        if (!imageName.equals(StringPool.DEFAULT_IMAGE_NAME)) {
            String imagePath = StringPool.IMAGE_RESOURCE_PATH + imageName;
            FileTool.deleteFile(imagePath);
        }
    }
}
