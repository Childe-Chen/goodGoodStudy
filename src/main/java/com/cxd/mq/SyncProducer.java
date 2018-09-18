package com.cxd.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc
 *
 * @author childe
 * @date 2018/9/14 15:01
 **/
public class SyncProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyncProducer.class);
    public static void main(String[] args) {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("S_Test");
        // Specify name server addresses.
        producer.setNamesrvAddr("mq101.2dfire-daily.com:9876;mq102.2dfire-daily.com:9876");
        try {
            //Launch the instance.
            producer.start();
            for (int i = 0; i < 100000; i++) {
                //Create a message instance, specifying topic, tag and message body.
                Message msg = new Message("dmall" /* Topic */,
                        "testS" /* Tag */,
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
                );
                //Call send message to deliver message to one of brokers.
                SendResult sendResult = producer.send(msg);


                msg = new Message("dmall" /* Topic */,
                        "test1S" /* Tag */,
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
                );
                //Call send message to deliver message to one of brokers.
                sendResult = producer.send(msg);

                if (i%100 == 0) {
                    LOGGER.info(i + "");
                    Thread.sleep(10 * 1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
