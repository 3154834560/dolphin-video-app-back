package com.example.dolphin.infrastructure.consts;

import java.io.File;

/**
 * @author 王景阳
 * @date 2022/10/27 20:31
 */
public class StringPool {

    public static final String DATA_PATH = System.getProperty("user.dir") + File.separator + "data" + File.separator;

    public static final String VIDEO_SLASH = "videos/";

    public static final String VIDEO_RESOURCE_PATH = DATA_PATH + VIDEO_SLASH;

    public static final String IMAGE_SLASH = "images/";

    public static final String IMAGE_RESOURCE_PATH = DATA_PATH + IMAGE_SLASH;

    public static final String DEFAULT_IMAGE_NAME = "default.jpg";

    public static final String DEFAULT_IMAGE_PATH = IMAGE_RESOURCE_PATH + DEFAULT_IMAGE_NAME;

    public static final String URL = "http://192.168.1.5:8090/dolphin/";

    public static final String VIDEO_URL = URL + "video/";

    public static final String IMAGE_URL = URL + "image/";

    public static final String DEFAULT_IMAGE_URL = IMAGE_URL + DEFAULT_IMAGE_NAME;

    public static final String DOT = ".";

    public static final String USER_NAME = "userName";

    public static final String ID = "id";

    public static final int PAGE_SIZE = 10;

    public final static String EQUATION = "=";

    public final static String ONE = "1";

    public final static String C = "C";

    public final static String CE = "CE";

    public final static String EMPTY = "";

    public final static String DOT_STR = ".";

    public final static String MULTIPLICATION_SIGN = "x";

    public final static String MINUS_SIGN_STR = "-";

    public final static String PLUS_SIGN_STR = "+";

    public final static String DIVISION_SIGN_STR = "÷";

    public final static String PERCENT_SIGN_STR = "%";

    public final static String EQUAL_SIGN_STR = "=";

    public final static char MULTIPLICATION = 'x';

    public final static char MINUS_SIGN = '-';

    public final static char PLUS_SIGN = '+';

    public final static char DIVISION_SIGN = '÷';

    public final static char PERCENT_SIGN = '%';

}
