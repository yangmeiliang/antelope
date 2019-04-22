package com.lmy.antelope;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author yangmeiliang
 */
public class MessageFactory {

    public static MessageListenerConcurrently createMessageListenerConcurrently() {
        return (msgs, context) -> {
            for (MessageExt msg : msgs) {
                try {
                    String info = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")
                            + " concurrently: " + StringUtils.toEncodedString(msg.getBody(), Charset.defaultCharset())
                            + ", queueId: " + msg.getQueueId()
                            + ", tags: " + msg.getTags()
                            + ", keys: " + msg.getKeys()
                            + ", messageId: " + msg.getMsgId();
                    System.out.println(info);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        };
    }

    public static MessageListenerOrderly createMessageListenerOrderly() {
        return (msgs, context) -> {
            for (MessageExt msg : msgs) {
                try {
                    String info = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")
                            + " orderly: " + StringUtils.toEncodedString(msg.getBody(), Charset.defaultCharset())
                            + ", queueId: " + msg.getQueueId()
                            + ", tags: " + msg.getTags()
                            + ", keys: " + msg.getKeys()
                            + ", messageId: " + msg.getMsgId();
                    System.out.println(info);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
            }
            return ConsumeOrderlyStatus.SUCCESS;
        };
    }
}
