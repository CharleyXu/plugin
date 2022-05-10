package com.xu;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;

@Slf4j
public class FileUtil {

    /**
     * 获取当前服务路径
     */
    public static String getJarPath() {
        return getJarPath(getJarFullPath(FileUtil.class));
    }

    private static String getJarPath(String path) {
        if (Objects.isNull(path)) {
            return "";
        }
        int bootInf = path.lastIndexOf("/BOOT-INF");
        if (bootInf > 0) {
            path = path.substring(0, bootInf);
        }
        int jarIndex = path.lastIndexOf(".jar");
        //  jar运行和单元测试区分
        if (jarIndex > 0) {
            path = path.substring(0, jarIndex);
            int lastSlashIndex = path.lastIndexOf("/");
            path = path.substring(0, lastSlashIndex + 1);
        } else if (path.endsWith("test-classes/")) {
            int pos = path.lastIndexOf("target");
            path = path.substring(0, pos);
            path = path + "src/test/resources/";
        } else {
            int pos = path.lastIndexOf("target");
            path = path.substring(0, pos);
            path = path + "src/main/resources/";
        }
        return path;
    }

    private static String getJarFullPath(Class<?> clazz) {
        String path = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            path = URLDecoder.decode(path, "UTF-8");
            path = path.replace("\\", "/");
            // 删去 file:
            if (path.startsWith("file:")) {
                path = path.substring(5);
            }
            return path;
        } catch (UnsupportedEncodingException e) {
            log.error("[com.xu.FileUtil] get JarFullPath, error message: {}", e.getMessage(), e);
        }
        return "";
    }

}
