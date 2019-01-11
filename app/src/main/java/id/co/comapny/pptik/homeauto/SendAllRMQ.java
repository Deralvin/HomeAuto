package id.co.comapny.pptik.homeauto;

import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class SendAllRMQ {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    public  void  sendAll(String allParam) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://homeauto:homeauto12345!@167.205.7.226");
        factory.setVirtualHost("/homeauto");
        //factory.setUri("amqp://guest:guest@localhost");
        factory.setConnectionTimeout(3000000);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        if (allParam == "sendAllTrue"){
            String messageOn = "AllOn" ;
            channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());
            //System.out.println("published mesasge"  + messageOn);

        }else if(allParam == "sendAllFalse"){
            String messageOn = "AllOff" ;
            channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());
        }



    }
}
