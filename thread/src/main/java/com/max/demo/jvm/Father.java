package com.max.demo.jvm;

/**
 * Created by majun on 2017/12/1.
 */
public class Father {
    public static final String SStr1 = "father1";
    public static String fStr1 = "father1";
    protected String fStr2 = "father2";
    private String fStr3 = "father3";

    public static String getName() {
        return "father";
    }

    public String getName1() {
        return "father";
    }

    {
        System.out.println("Father common block be called");
    }

    static {
        System.out.println("Father static block be called");
    }

    public Father() {
        System.out.println("Father constructor be called");
    }
}
