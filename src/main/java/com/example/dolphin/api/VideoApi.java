package com.example.dolphin.api;

import com.example.dolphin.acomm.model.rest.R;
import com.example.dolphin.application.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @author 王景阳
 * @date 2022/10/27 20:44
 */
@Controller
@RequestMapping("/dolphin/video")
public class VideoApi {

    @Autowired
    private VideoService service;

    @GetMapping("/all")
    @ResponseBody
    public R<?> getAll(@RequestParam("userName") String userName) {
        return R.data(service.getAllBy(userName));
    }

    @GetMapping
    @ResponseBody
    public R<?> getBy(@RequestParam("id") String id) {
        return R.data(service.getById(id));
    }

    @GetMapping("/random/{index}")
    @ResponseBody
    public R<?> randomGet(@PathVariable("index") int index) {
        return R.data(service.randomGet(index));
    }

    @GetMapping("/{name}")
    public void downVideo(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse response) {
        service.downVideo(name, request, response);
    }

    @PostMapping
    @ResponseBody
    public R<?> uploadVideo(@RequestParam("userName") String userName, @RequestParam("introduction") String introduction, @RequestPart("video") Part video, @RequestPart(name = "cover", required = false) Part cover) {
        return R.data(service.uploadVideo(userName, introduction, video, cover));
    }

    @PostMapping("/shard")
    @ResponseBody
    public R<?> uploadShardVideo(@RequestParam("videoInput") String videoInputStr, @RequestPart(name = "video", required = false) Part video, @RequestPart(name = "cover", required = false) Part cover) {
        return R.data(service.uploadVideo(videoInputStr, video, cover));
    }

    @DeleteMapping("/{videoId}")
    @ResponseBody
    public R<?> delete(@PathVariable("videoId") String videoId) {
        return R.data(service.deleteBy(videoId));
    }

    @PutMapping("/update/{videoId}")
    @ResponseBody
    public R<?> updateVideo(@PathVariable("videoId") String videoId, @RequestParam("introduction") String introduction) {
        return R.data(service.update(videoId, introduction));
    }


}
