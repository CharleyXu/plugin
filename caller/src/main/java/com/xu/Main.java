package com.xu;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.ServiceLoader;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Iterator<Plugin> it = serviceLoader().iterator();
        while (it.hasNext()) {
            Plugin plugin = it.next();
            log.info("new demo plugin found: " + plugin.getClass().getName());
            plugin.execute("test");
        }
    }

    private static ServiceLoader<Plugin> serviceLoader() {
        return ServiceLoader.load(Plugin.class, PluginManager.getInstance().getLoader());
    }

}
