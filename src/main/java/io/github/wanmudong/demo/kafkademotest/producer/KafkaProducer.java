package io.github.wanmudong.demo.kafkademotest.producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class KafkaProducer {
    private final Producer<String,String> producer;
    public final static String TOPIC = "TEST-TOPIC";

    private KafkaProducer(){
        Properties props = new Properties();
        //配置kafka的端口
        props.put("metadata.broker.list","localhost:9092");
        //配置value的序列化类
        props.put("serializer.class","kafka.serializer.StringEncoder");
        //配置key的序列化类
        props.put("key.serializer.class","kafka.serializer.StringEncoder");

        props.put("request.required.acks", "-1");

        producer = new Producer<String, String>(new ProducerConfig(props));
    }

    void produce(){
        int messageNo = 1000;
        final int COUNT = 10000;

        while (messageNo < COUNT) {
            String key = String.valueOf(messageNo);
            String data = "hello kafka message " + key;
            producer.send(new KeyedMessage<String, String>(TOPIC, key, data));
            System.out.println(data);
            messageNo++;
        }

    }

    public static void main(String[] args) {
        new KafkaProducer().produce();
    }
}
