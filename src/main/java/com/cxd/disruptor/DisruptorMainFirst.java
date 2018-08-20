package com.cxd.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

/**
 * desc
 *
 * @author childe
 * @date 2018/4/13 11:04
 **/
public class DisruptorMainFirst {

    public static void main(String[] args) throws Exception {
        // 队列中的元素
        class Element {

            private long value;

            public long get(){
                return value;
            }

            public void set(long value){
                this.value= value;
            }

        }

        // 生产者的线程工厂
        ThreadFactory productThreadFactory = (r) -> new Thread(r, "simpleThread");

        // RingBuffer生产工厂,初始化RingBuffer的时候使用
        EventFactory<Element> ringBufferFactory = Element::new;

        // 处理Event的handler
        EventHandler<Element> handler = (Element element, long sequence, boolean endOfBatch)
                -> System.out.println("Element: " + element.get() + "->" + sequence + " - " + endOfBatch);

        // 阻塞策略
        BlockingWaitStrategy strategy = new BlockingWaitStrategy();

        // 指定RingBuffer的大小
        int bufferSize = 16;

        // 创建disruptor，采用单生产者模式
        Disruptor<Element> disruptor = new Disruptor(ringBufferFactory, bufferSize, productThreadFactory, ProducerType.SINGLE, strategy);

        // 设置EventHandler
        disruptor.handleEventsWith(handler);

        // 启动disruptor的线程
        disruptor.start();

        RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();

        for (int l = 0; true; l++) {
            // 获取下一个可用位置的下标
            long sequence = ringBuffer.next();
            try {
                // 返回可用位置的元素
                Element event = ringBuffer.get(sequence);
                // 设置该位置元素的值
                event.set(l);
            } finally {
                ringBuffer.publish(sequence);
            }

            Thread.sleep(100);
        }
    }

}
