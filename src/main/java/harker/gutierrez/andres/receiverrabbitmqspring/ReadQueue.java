/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harker.gutierrez.andres.receiverrabbitmqspring;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author aharker
 */
@Component
public class ReadQueue {

    private static final Logger log = LoggerFactory.getLogger(ReadQueue.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    MyBeanSingleton myBeanSingleton;
    
    @Value("${ip.cola.mensajes}")
    String ipColaMensajes;
    
    @Value("${nombre.cola.mensajes}")
    String nombreColaMensajes;
    
    @Scheduled(fixedRate = 7000)
    public void reportCurrentTime() throws IOException, TimeoutException {
        log.info("Lee la cola de mensajes ahora: ", dateFormat.format(new Date()));

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ipColaMensajes);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(nombreColaMensajes, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope,
                    AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" Recibido '" + message + "'");
                
                myBeanSingleton.addMessage(message);
                
            }
        };
        channel.basicConsume(nombreColaMensajes, true, consumer);

    }
}
