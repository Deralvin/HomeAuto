package id.co.comapny.pptik.homeauto;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class ConsumerRMQ {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    public  void  ConsumerDataRMQ() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://homeauto:homeauto12345!@167.205.7.226");
        factory.setVirtualHost("/homeauto");
        //factory.setUri("amqp://guest:guest@localhost");
        factory.setConnectionTimeout(3000000);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        QueueingConsumer consumer =  new QueueingConsumer(channel);

        channel.basicConsume("homeautomation",false,consumer);


        QueueingConsumer.Delivery delivery = consumer.nextDelivery();

        if (delivery != null){
            try{

                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("Message Consumed" + message);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

            }catch (Exception e){
                channel.basicReject(delivery.getEnvelope().getDeliveryTag(),true);
            }


        }


    }
}
