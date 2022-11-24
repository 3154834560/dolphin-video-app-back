package com.example.dolphin.infrastructure.tool;


import com.example.dolphin.infrastructure.consts.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author 王景阳
 * @date 2022/11/24 9:37
 */
@Slf4j
public class VideoTool {

    /**
     * 获取视频第一帧
     *
     * @param videoPath 视频路径，可以为 "https://vt1.doubanio.com/202101120940/a3e7ae32c21341710eaceba2d2e56039/view/movie/M/402680931.mp4"
     * @param imagePath 生成的图片保存路径
     * @param imageType 生成的图片类型
     * @param angele     生成的图片旋转角度
     */
    public static void getVideoFirstImg(String videoPath, String imagePath, String imageType, int angele) throws Exception {
        String videoName = videoPath.substring(videoPath.lastIndexOf(StringPool.SLASH) + 1);
        log.info("开始获取[ " + videoName + " ]视频第一帧");
        Frame frame = null;
        //构造器支持InputStream，可以直接传MultipartFile.getInputStream()
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath);
        //开始播放
        fFmpegFrameGrabber.start();
        //获取视频总帧数
        int ftp = fFmpegFrameGrabber.getLengthInFrames();
        //指定第几帧
        fFmpegFrameGrabber.setFrameNumber(0);
        //获取指定第几帧的图片
        frame = fFmpegFrameGrabber.grabImage();
        //文件绝对路径+名字
        File outPut = new File(imagePath);
        ImageIO.write(frameToBufferedImage(frame, angele), imageType, outPut);
        fFmpegFrameGrabber.flush();
        fFmpegFrameGrabber.stop();
        log.info("获取视频[ " + videoName + " ]第一帧成功");
    }
    /**
     * 获取视频最后一帧
     *
     * @param videoPath 视频路径，可以为 "https://vt1.doubanio.com/202101120940/a3e7ae32c21341710eaceba2d2e56039/view/movie/M/402680931.mp4"
     * @param imagePath 生成的图片保存路径
     * @param imageType 生成的图片类型
     * @param angele     生成的图片旋转角度
     */
    public static void getVideoLastImg(String videoPath, String imagePath, String imageType, int angele) throws Exception {
        String videoName = videoPath.substring(videoPath.lastIndexOf(StringPool.SLASH) + 1);
        log.info("开始获取视频[ " + videoName + " ]最后一帧");
        Frame frame = null;
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath);
        //开始播放
        fFmpegFrameGrabber.start();
        //获取视频总帧数
        int ftp = fFmpegFrameGrabber.getLengthInFrames();
        //指定第几帧
        fFmpegFrameGrabber.setFrameNumber(ftp - 5);
        //获取指定第几帧的图片
        frame = fFmpegFrameGrabber.grabImage();
        File outPut = new File(imagePath);
        ImageIO.write(frameToBufferedImage(frame, angele), imageType, outPut);
        fFmpegFrameGrabber.flush();
        fFmpegFrameGrabber.stop();
        log.info("获取视频[ " + videoName + " ]最后一帧");
    }

    /**
     * 旋转照片
     */
    private static BufferedImage rotatePhonePhoto(BufferedImage bufferedImage, int angele) {
        log.info("获取图片旋转 " + angele + " 度");
        BufferedImage res = null;
        try {
            int srcWidth = bufferedImage.getWidth(null);
            int srcHeight = bufferedImage.getHeight(null);

            int swidth = srcWidth;
            int sheight = srcHeight;

            if (angele == 90 || angele == 270) {
                swidth = srcHeight;
                sheight = srcWidth;
            }
            Rectangle rectDes = new Rectangle(new Dimension(swidth, sheight));
            res = new BufferedImage(rectDes.width, rectDes.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = res.createGraphics();
            g2.translate((rectDes.width - srcWidth) / 2.0, (rectDes.height - srcHeight) / 2.0);
            g2.rotate(Math.toRadians(angele), srcWidth / 2.0, srcHeight / 2.0);
            g2.drawImage(bufferedImage, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    }

    private static BufferedImage frameToBufferedImage(Frame frame, int angele) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        return rotatePhonePhoto(converter.getBufferedImage(frame), angele);
    }
}
