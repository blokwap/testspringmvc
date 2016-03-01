package com.bj.test.controller;

/**
 * Created by baojun on 2016/3/1.
 */
public class Test {
    static {
        a = 1;
    }

    private static int a = 2;

    {
        a = 3;
    }

    public static void main(String[] args) {
        Test t = new Test();
        System.out.println(a);
    }
}
