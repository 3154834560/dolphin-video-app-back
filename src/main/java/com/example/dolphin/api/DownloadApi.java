package com.example.dolphin.api;

import com.example.dolphin.application.service.DownLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 王景阳
 * @date 2022/11/27 15:11
 */
@Controller
@RequestMapping("/dolphin/down")
public class DownloadApi {

    @Autowired
    DownLoadService service;

    @GetMapping
    public void download(@RequestParam("type") String type, @RequestParam("name") String name, HttpServletResponse response) {
        service.downFile(type, name, response);
    }
}
