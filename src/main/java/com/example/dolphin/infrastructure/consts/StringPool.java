package com.example.dolphin.infrastructure.consts;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 王景阳
 * @date 2022/10/27 20:31
 */
public class StringPool {

    public static String LOCAL_IP;

    static {
        try {
            LOCAL_IP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static String LOCAL_PORT = "8090";

    public static final String URL = "http://" + LOCAL_IP + ":" + LOCAL_PORT + "/static/";

    public static final String DATA_PATH = System.getProperty("user.dir") + File.separator + "data" + File.separator;

    public static final String VIDEO_SLASH = "videos/";

    public static final String SLASH = "/";

    public static final String VIDEO_RESOURCE_PATH = DATA_PATH + VIDEO_SLASH;

    public static final String IMAGE_SLASH = "images/";

    public static final String IMAGE_RESOURCE_PATH = DATA_PATH + IMAGE_SLASH;

    public static final String DEFAULT_IMAGE_NAME = "default.jpg";

    public static final String DEFAULT_IMAGE_PATH = IMAGE_RESOURCE_PATH + DEFAULT_IMAGE_NAME;

    public static final String VIDEO_URL = URL + "videos/";

    public static final String IMAGE_URL = URL + "images/";

    public static final String DEFAULT_IMAGE_URL = IMAGE_URL + DEFAULT_IMAGE_NAME;

    public static final String DOT = ".";

    public static final int PAGE_SIZE = 10;
}
