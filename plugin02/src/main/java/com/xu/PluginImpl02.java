package com.xu;

public class PluginImpl02 implements Plugin {

    private int callCount = 0;

    public int execute(String string) {
        callCount += 2;
        System.out.println("Hi " + string);
        return callCount;
    }
}
