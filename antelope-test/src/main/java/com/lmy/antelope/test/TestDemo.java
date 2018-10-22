package com.lmy.antelope.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yangmeiliang
 * @date 2018/4/18
 */
public class TestDemo {

    public static void main(String[] args) {
        TestDemo demo = new TestDemo();
        demo.test02();
    }

    public void test01() {
        List<String> collect = IntStream.range(0, 20).mapToObj(String::valueOf).collect(Collectors.toList());
        ArrayList<String> list = new ArrayList<>(collect);
        for (String i : list) {
            if (Integer.valueOf(i) < 10) {
                list.remove(i);
            }
        }
        collect.forEach(System.out::println);
    }

    private void test02() {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("bcd");
        list.add("123");
        list.add("123");
        list.add("456");

        list.removeIf("123"::equals);

        list.forEach(System.out::print);
    }
}
