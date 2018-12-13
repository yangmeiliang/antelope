package com.lmy.antelope;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yangmeiliang
 * @date 2018/1/22
 */
public class StreamTest {

    @Test
    public void test01() {
        StopWatch stopWatch = new StopWatch();
        List<MyTask> tasks = IntStream.range(0, 10).mapToObj(i -> new MyTask(1)).collect(Collectors.toList());

        stopWatch.start("普通循环处理：");
        tasks.forEach(MyTask::calculate);
        stopWatch.stop();
        System.out.println("============================================");
        stopWatch.start("CompletableFuture线程处理：");
        tasks.stream().map(task -> CompletableFuture.runAsync(task::calculate)).forEach(CompletableFuture::join);
        stopWatch.stop();
        System.out.println("============================================");
        stopWatch.start("并发流处理：");
        tasks.parallelStream().forEach(MyTask::calculate);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());


    }

}

@Data
class MyTask {
    private final int num;

    public MyTask(int num) {
        this.num = num;
    }

    public int calculate() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(num * 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }
}
