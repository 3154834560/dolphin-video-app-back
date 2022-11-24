package com.example.dolphin.infrastructure.tool;

import com.example.dolphin.infrastructure.consts.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * org.bytedeco.javacv 0.8 版本
 * 使用此方法获取视频第一帧方法
 * 此方法只需要org.bytedeco.javacv 0.8 jar较小
 * 但是速度吗
 *
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
     * @param angele    生成的图片旋转角度
     */
    public static void getVideoFirstImg(String videoPath, String imagePath, String imageType, int angele) throws Exception {
        String videoName = videoPath.substring(videoPath.lastIndexOf(StringPool.SLASH) + 1);
        log.info("开始获取[ " + videoName + " ]视频第一帧");
        //构造器支持InputStream，可以直接传MultipartFile.getInputStream()
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath);
        //开始播放
        fFmpegFrameGrabber.start();
        //获取视频总帧数
        int lengthInFrames = fFmpegFrameGrabber.getLengthInFrames();
        //指定第几帧
        int frameNum = 0;
        fFmpegFrameGrabber.setFrameNumber(frameNum);
        //获取指定第几帧的图片
        Frame f = fFmpegFrameGrabber.grabFrame();
        Frame frame = f;
        while (frameNum < lengthInFrames) {
            // 过滤前10帧，避免出现全黑的图片，依自己情况而定
            f = fFmpegFrameGrabber.grabFrame();
            if (f != null && f.image != null) {
                frame = f;
            }
            if (frameNum >= 10) {
                break;
            }
            frameNum++;
            fFmpegFrameGrabber.setFrameNumber(frameNum);
        }
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
     * @param angele    生成的图片旋转角度
     */
    public static void getVideoLastImg(String videoPath, String imagePath, String imageType, int angele) throws Exception {
        String videoName = videoPath.substring(videoPath.lastIndexOf(StringPool.SLASH) + 1);
        log.info("开始获取视频[ " + videoName + " ]最后一帧");
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(new File(videoPath));
        //开始播放
        fFmpegFrameGrabber.start();
        //获取视频总帧数
        int lengthInFrames = fFmpegFrameGrabber.getLengthInFrames();
        //指定第几帧
        int frameNum = lengthInFrames - 1;
        fFmpegFrameGrabber.setFrameNumber(frameNum);
        //获取指定第几帧的图片
        Frame f = fFmpegFrameGrabber.grabFrame();
        Frame frame = f;
        while (frameNum >= 0) {
            // 过滤后20帧，避免出现全黑的图片，依自己情况而定
            f = fFmpegFrameGrabber.grabFrame();
            if (f != null && f.image != null) {
                frame = f;
            }
            if (lengthInFrames - frameNum >= 20) {
                break;
            }
            frameNum--;
            fFmpegFrameGrabber.setFrameNumber(frameNum);
        }
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
        opencv_core.IplImage img = frame.image;
        int width = img.width();
        int height = img.height();
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bi.getGraphics().drawImage(frame.image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH),
                0, 0, null);
        return rotatePhonePhoto(bi, angele);
    }

}