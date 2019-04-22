package com.lmy.antelope;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @author yangmeiliang
 */
public class Consumer2 {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer1 = new DefaultMQPushConsumer("Consumer");
        consumer1.setMessageModel(MessageModel.CLUSTERING);
        consumer1.setNamesrvAddr("111.231.85.42:9876");
        consumer1.subscribe("TopicTest", "*");
//        consumer1.registerMessageListener(MessageFactory.createMessageListenerConcurrently());
        consumer1.registerMessageListener(MessageFactory.createMessageListenerOrderly());
        consumer1.setInstanceName("Consumer2");
        consumer1.start();
        System.out.println("Consumer2 Started.");
    }
}
