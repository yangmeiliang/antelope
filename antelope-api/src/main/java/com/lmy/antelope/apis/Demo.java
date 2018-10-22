package com.lmy.antelope.apis;

/**
 * @author yangmeiliang
 * @date 2018/4/3
 */
public class Demo {

    public void demo1() throws InterruptedException {
        synchronized (this){
this.wait();
        }
    }
}
