package com.wallen.tool.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq发送方法
 *
 * @author wallen
 * @date 20210719
 */
public class Send {
    /**
     * 定义一个队列
     */
    private final static String QUEUE_NAME = "QUEUE_NAME";

    public static void main(String[] argv) throws IOException, TimeoutException {

        List<String> queues = new ArrayList<>();
        queues.add(QUEUE_NAME);

        //创建一个连接
        ConnectionFactory factory = new ConnectionFactory();
        //连接本地，如果需要指定到服务，需在这里指定IP
        factory.setHost("192.168.0.11");
        factory.setPort(5672);
        factory.setUsername("user1");
        factory.setPassword("123456");
        Connection connection = factory.newConnection();
        String exchange = "section6";

        //Declaring a queue is idempotent - 如果队列不存在则会创建一个队列
        //消息内容为byte array, so可以自己随意编码
        for (String queue : queues) {
            //创建一个通道
            Channel channel = connection.createChannel();
            //申明通道发送消息的队列，把消息发送至消息队列‘hello’
            Map<String, Object> map = new HashMap<>(5);
            channel.queueDeclare(queue, true, false, false, map);
            map.put("batteryVoltage", 430);
            map.put("deviceId", 6142);
            map.put("deviceType", 3);
            map.put("batterManufacturerNumber", 2);
            map.put("maxChargingCurrent", 280);

            channel.basicPublish(exchange, queue, null, SerializationUtils.serialize((Serializable) map));
            //消息发送完成后，关闭通道和连接
            channel.close();
        }

        connection.close();
    }
}
