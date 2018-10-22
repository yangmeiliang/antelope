package com.lmy.antelope.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author yangmeiliang
 * @date 2018/4/11
 */
public class QuickSortDemo {

    @Test
    public void test01() {
        List<Integer> list = new ArrayList<>(10);
        IntStream.range(0,10).forEach(list::add);
        System.out.println(list.size());
        list.forEach(System.out::println);
    }


    private void sort(int[] args) {

        int len = args.length;
        int left = 0;
        int right = len - 1;


    }

    private int quickSort(int[] args, int left, int right) {

        int point = left;
        for (int i = point + 1; i < right; i++) {
            if (args[i] < args[point]) {
                swap(args, i, point);
            }
        }


        return 0;
    }

    private void swap(int[] args, int i, int j) {
        int tmp = args[i];
        args[i] = args[j];
        args[j] = tmp;
    }

}


//    function partition(arr, left ,right) {     // 分区操作
//        var pivot = left,                      // 设定基准值（pivot）
//                index = pivot + 1;
//        for (var i = index; i <= right; i++) {
//            if (arr[i] < arr[pivot]) {
//                swap(arr, i, index);
//                index++;
//            }
//        }
//        swap(arr, pivot, index - 1);
//        return index-1;
//    }