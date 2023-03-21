package com.example.dolphin.api;

import com.example.dolphin.application.service.DownLoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 下载视频或图片
 *
 * @author 王景阳
 * @date 2022/11/27 15:11
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/dolphin/down")
public class DownloadApi {
    private final DownLoadService service;

    /**
     * 下载视频或图片
     */
    @GetMapping
    public void download(@RequestParam("type") String type, @RequestParam("name") String name, HttpServletResponse response) {
        service.downFile(type, name, response);
    }
}
