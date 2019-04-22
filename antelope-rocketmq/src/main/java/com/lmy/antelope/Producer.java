package com.lmy.antelope;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangmeiliang
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("Test");
        producer.setNamesrvAddr("111.231.85.42:9876");
        producer.setRetryTimesWhenSendAsyncFailed(0);

//        sync(producer);
//        async(producer);
//        oneway(producer);
        orderly(producer);

//        transaction();
    }

    private static void sync(DefaultMQProducer producer) throws Exception {
        producer.start();
        for (int i = 0; i < 100; i++) {
            byte[] body = ("sync_test_" + i).getBytes(RemotingHelper.DEFAULT_CHARSET);
            Message msg = new Message("TopicTest", "TagA", body);
            SendResult sendResult = producer.send(msg, 5000);
            System.out.println(JSON.toJSONString(sendResult));
        }
    }

    private static void oneway(DefaultMQProducer producer) throws Exception {
        producer.start();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            byte[] body = ("oneway_test_" + i).getBytes(RemotingHelper.DEFAULT_CHARSET);
            Message msg = new Message("TopicTest", "TagA", body);
            producer.sendOneway(msg);
        }
        System.out.printf("消息发送完成, 耗时：%s秒", (System.currentTimeMillis() - start) / 1000);
    }

    private static void async(DefaultMQProducer producer) throws Exception {
        producer.start();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            byte[] body = ("async_test_" + i).getBytes(RemotingHelper.DEFAULT_CHARSET);
            Message msg = new Message("TopicTest", "TagA", body);
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        System.out.printf("消息发送完成, 耗时：%s秒", (System.currentTimeMillis() - start) / 1000);
    }


    private static void orderly(DefaultMQProducer producer) throws Exception {
        producer.start();
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 100; i++) {
            int orderId = i % 10;
            byte[] body = ("orderly_test_" + i).getBytes(RemotingHelper.DEFAULT_CHARSET);
            Message msg = new Message("TopicTest", tags[i % tags.length], "KEY" + i, body);
            SendResult sendResult = producer.send(msg, (mqs, msg1, arg) -> {
                Integer id = (Integer) arg;
                int index = id % mqs.size();
                return mqs.get(index);
            }, orderId);
            System.out.printf("%s%n", sendResult);
        }
    }

    public static void transaction() throws Exception {
        TransactionListener transactionListener = new TransactionListenerImpl();
        TransactionMQProducer producer = new TransactionMQProducer("TransactionProducer");
        ExecutorService executorService = new ThreadPoolExecutor(
                2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2000), r -> {
            Thread thread = new Thread(r);
            thread.setName("client-transaction-msg-check-thread");
            return thread;
        });
        producer.setNamesrvAddr("111.231.85.42:9876");
        producer.setRetryTimesWhenSendAsyncFailed(0);
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();

        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {
                byte[] body = ("orderly_test_" + i).getBytes(RemotingHelper.DEFAULT_CHARSET);
                Message msg = new Message("TopicTest", tags[i % tags.length], "KEY" + i, body);
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                System.out.printf("%s%n", sendResult);

                Thread.sleep(10);
            } catch (MQClientException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
