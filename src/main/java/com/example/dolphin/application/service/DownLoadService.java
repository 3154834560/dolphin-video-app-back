package com.example.dolphin.application.service;

import com.example.dolphin.infrastructure.consts.StringPool;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author 王景阳
 * @date 2022/11/27 15:46
 */
@Service
public class DownLoadService {

    public void downFile(String type, String name, HttpServletResponse response) {
        String filePath = StringPool.DATA_PATH + type + File.separator + name;
        File file = new File(filePath);
        if (!file.exists()) {
            response.reset();
            response.setHeader("msg", changeISO88591(type + "不存在！"));
            return;
        }
        try {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + changeISO88591(name));
            response.setHeader("msg", changeISO88591("下载成功！"));
            FileInputStream inputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.setHeader("msg", changeISO88591("下载失败！"));
        }
    }

    private String changeISO88591(String content) {
        return new String(content.getBytes(), StandardCharsets.ISO_8859_1);
    }

}
