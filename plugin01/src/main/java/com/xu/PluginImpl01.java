package com.xu;

public class PluginImpl01 implements Plugin {
    private int callCount = 0;

    public int execute(String string) {
        callCount += 1;
        System.out.println("Hello " + string);
        return callCount;
    }
}
