package com.example.dolphin.api;

import com.example.dolphin.acomm.model.rest.R;
import com.example.dolphin.application.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @author 王景阳
 * @date 2022/10/30 11:13
 */
@Controller
@RequestMapping("/dolphin/image")
public class ImageApi {

    @Autowired
    ImageService service;

    @GetMapping("/{name}")
    public void downVideo(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse response) {
        service.downImage(name, request, response);
    }

    @PostMapping
    @ResponseBody
    public R<?> uploadVideo(@RequestParam("userName") String userName, @RequestPart("image") Part image) {
        return R.data(service.uploadImage(userName, image));
    }
}
