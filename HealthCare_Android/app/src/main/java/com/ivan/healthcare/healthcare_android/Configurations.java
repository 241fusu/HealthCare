package com.ivan.healthcare.healthcare_android;

import java.util.UUID;

/**
 * 应用的基本常量配置
 * Created by Ivan on 16/2/8.
 */
public class Configurations {
    /**
     * 是否进行debug输出日志信息
     */
    public static final boolean DEBUG = true;
    /**
     * 数据库名
     */
    public final static String DATABASE_NAME = "healthcare_android.db";
    /**
     * 网络请求基址
     */
    public static final String REQUEST_URL = "http://192.168.88.100/User.php";
    /**
     * 蓝牙UUID
     */
    public static final UUID btUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    /**
     * 蓝牙服务名
     */
    public static final String btServerName = "HealthCare_Android_Client";
    /**
     * shared preference的名字
     */
    public static final String PREFERENCE_NAME = "com.ivan.healthcare.healthcare_android.preference";
    /**
     * 用户头像本地文件路径
     */
    public static final String AVATAR_FILE_PATH = "/user/avatar.png";
    /**
     * 本地存储用户数据的文件夹名字
     */
    public static final String USER_DIR = "/user";
}