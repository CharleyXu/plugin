package com.xu;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

@Slf4j
public class PluginManager {

    private static final String PLUGIN_DIR = "plugins";
    private ClassLoader pluginClassLoader = null;

    private PluginManager() {
    }

    private static class InnerObject {
        private static PluginManager instance = new PluginManager();
    }

    public static PluginManager getInstance() {
        return InnerObject.instance;
    }

    private ClassLoader initLoader() {
        try {
            String jarPath = FileUtil.getJarPath() + PLUGIN_DIR;
            log.info(jarPath);
            File dir = new File(jarPath);
            File[] files = dir.listFiles();
            ArrayList<URL> urls = new ArrayList<>();
            for (File file : files) {
                if (file.getName().endsWith(".jar")) {
                    log.info("loading new plugin " + file.getAbsolutePath() + "...");
                    urls.add(file.toURI().toURL());
                }
            }
            int validSize = urls.size();
            log.info("totally load " + validSize + " plugin jars.");
            pluginClassLoader = new URLClassLoader(urls.toArray(new URL[validSize]));
            return pluginClassLoader;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }

    public ClassLoader getLoader() {
        if (pluginClassLoader == null) {
            return initLoader();
        }
        return pluginClassLoader;
    }


}
