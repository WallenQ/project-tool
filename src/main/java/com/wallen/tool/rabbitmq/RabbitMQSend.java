package com.wallen.tool.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq发送方法
 *
 * @author wallen
 * @date 20210719
 */
public class RabbitMQSend {
    /**
     * 定义一个队列
     */
    private final static String QUEUE_NAME = "QUEUE_NAME";

    public static void main(String[] args) {
        Map<String, Object> sendData = new HashMap<>(5);
        sendData.put("batteryVoltage", 430);
        sendData.put("deviceId", 6142);
        sendData.put("deviceType", 3);
        sendData.put("batterManufacturerNumber", 2);
        sendData.put("maxChargingCurrent", 280);
        try {
            send(QUEUE_NAME, sendData);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送方法
     *
     * @param queue
     * @param sendData 消息内容为byte array, so可以自己随意编码
     * @throws IOException
     * @throws TimeoutException
     */
    public static void send (String queue,Object sendData) throws IOException, TimeoutException {
        //创建一个连接
        ConnectionFactory factory = new ConnectionFactory();
        //连接本地，如果需要指定到服务，需在这里指定IP
        factory.setHost("192.168.0.11");
        factory.setPort(5672);
        factory.setUsername("user1");
        factory.setPassword("123456");
        Connection connection = factory.newConnection();
        String exchange = "section6";

        //创建一个通道
        Channel channel = connection.createChannel();
        Map<String ,Object> map = new HashMap<>(1);
        //申明通道发送消息的队列，把消息发送至消息队列 - 如果队列不存在则会创建一个队列
        channel.queueDeclare(queue, true, false, false, map);
        channel.basicPublish(exchange, queue, null, SerializationUtils.serialize((Serializable)sendData));
        //消息发送完成后，关闭通道和连接
        channel.close();

        connection.close();
    }
}
