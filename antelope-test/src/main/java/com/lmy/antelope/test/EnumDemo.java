package com.lmy.antelope.test;

/**
 * @author yangmeiliang
 * @date 2018/4/18
 */
public class EnumDemo {

    public static void main(String[] args) {
        switch (TestEnum01.TEST01) {
            case TEST01:
                System.out.println("test01");
                break;
            case TEST02:
                System.out.println("test02");
                break;
            default:
        }
    }
}
