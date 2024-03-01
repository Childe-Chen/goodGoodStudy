package com.cxd.mq;
import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientConfigurationBuilder;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProducerExample {
    private static final Logger logger = LoggerFactory.getLogger(ProducerExample.class);

    private static final long DAY_4_MS = 4 * 24 * 60 * 60 * 1000;
    private static final long S_10 = 20 * 1000;

    public static void main(String[] args) throws Exception {
        // 接入点地址，需要设置成Proxy的地址和端口列表，一般是xxx:8081;xxx:8081。
        String endpoint = "localhost:8081";
        // 消息发送的目标Topic名称，需要提前创建。
        String topic = "TestDelayTopic";
        ClientServiceProvider provider = ClientServiceProvider.loadService();
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder().setEndpoints(endpoint);
        ClientConfiguration configuration = builder.build();
        // 初始化Producer时需要设置通信配置以及预绑定的Topic。
        Producer producer = provider.newProducerBuilder()
                .setTopics(topic)
                .setClientConfiguration(configuration)
                .build();

        long deliveryTimestamp = System.currentTimeMillis() + DAY_4_MS;
//        long deliveryTimestamp = System.currentTimeMillis() + S_10;
        String body = dateToStr(new Date(deliveryTimestamp));
        // 普通消息发送。
        Message message = provider.newMessageBuilder()
                .setTopic(topic)
                .setDeliveryTimestamp(deliveryTimestamp)
                // 消息体。
                .setBody(body.getBytes())
                .build();
        try {
            // 发送消息，需要关注发送结果，并捕获失败等异常。
            SendReceipt sendReceipt = producer.send(message);
            logger.info("Send message successfully, messageId={} {}", sendReceipt.getMessageId(), body);
        } catch (ClientException e) {
            logger.error("Failed to send message", e);
        }

        producer.close();
    }

    public static String dateToStr(Date date) {
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return longSdf.format(date);
    }
}