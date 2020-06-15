package com.imooc.web.controller;

import java.io.FileReader;

public class Test {
    public static void main(String[] args) {
        String a = "";
        System.out.println(isEmpty(a));
        System.out.println(isBlank(a));
//        try {
//            test();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
    }

    public static void test() {
        try {

            FileReader fr = new FileReader("c:/abc.txt");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("1======="+e.getMessage());
        }
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
