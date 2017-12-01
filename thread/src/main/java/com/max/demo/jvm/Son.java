package com.max.demo.jvm;

/**
 * Created by max on 2017/12/1.
 */
public class Son extends Father {
    public static final String SStr1 = "Son1";
    public static String fStr1 = "Son1";
    protected String SStr2 = "Son2";
    private String SStr3 = "Son3";

    public Son() {
        System.out.println("Son constructor be called");
        System.out.println(this.fStr1);
    }
    public static String getName() {
        return "son";
    }
    
    public String getName1() {
        return "son";
    }

    {
        System.out.println("Son common block be called");
    }

    static {
        System.out.println("Son static block be called");
        System.out.println(Son.SStr1);
    }

    public static void main(String[] args) {
        Father son = new Son();
        System.out.println();
        new Son();

        System.out.println();
        Father father = new Father();
        System.out.println(father.getName1());
        System.out.println(son.getName1());
        System.out.println();
        System.out.println(father.getName());
        System.out.println(son.getName());
    }

}
