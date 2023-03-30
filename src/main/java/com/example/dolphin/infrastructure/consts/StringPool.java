package com.example.dolphin.infrastructure.consts;

import java.io.File;

/**
 * @author 王景阳
 * @date 2022/10/27 20:31
 */
public class StringPool {

    public static final String DATA_PATH = System.getProperty("user.dir") + File.separator + "data" + File.separator;

    public static final String VIDEO_SLASH = "videos/";

    public static final String SLASH = "/";

    public static final String VIDEO_RESOURCE_PATH = DATA_PATH + VIDEO_SLASH;

    public static final String IMAGE_SLASH = "images/";

    public static final String IMAGE_RESOURCE_PATH = DATA_PATH + IMAGE_SLASH;

    public static final String DEFAULT_IMAGE_NAME = "default.jpg";

    public static final String DOT = ".";

    public static final String UPLOAD_SUCCESS = "上传成功！";

    public static final String UPLOAD_FAIL = "上传失败！";

    public static final int PAGE_SIZE = 10;
}
