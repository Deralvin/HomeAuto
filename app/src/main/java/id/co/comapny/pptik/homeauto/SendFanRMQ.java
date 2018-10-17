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

public class SendFanRMQ {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    public  void  sendRMQFan(String fanParam) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://homeauto:homeauto12345!@167.205.7.226");
        factory.setVirtualHost("/homeauto");
        //factory.setUri("amqp://guest:guest@localhost");
        factory.setConnectionTimeout(3000000);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        //send message TO RMQ
        if (fanParam == "FanOneOn"){
            String messageOn = "KAon" ;
            channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());
            //System.out.println("published mesasge"  + messageOn);

        }else if (fanParam == "FanOneOff"){
            String messageOn = "KAoff" ;
            channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());
            //System.out.println("published mesasge"  + messageOn);
        }

        //FAN 2
        else if (fanParam == "FanTwoOn"){
            String messageOn = "KBon" ;
            channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());
            //System.out.println("published mesasge"  + messageOn);
        }
        else if (fanParam == "FanTwoOff"){
            String messageOn = "KBoff" ;
            channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());
            //System.out.println("published mesasge"  + messageOn);
        }

        //FAN 3
        else if (fanParam == "FanThreeOn"){
            String messageOn = "KCon" ;
            channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());
            //System.out.println("published mesasge"  + messageOn);
        }
        else if (fanParam == "FanThreeOff"){
            String messageOn = "KCoff" ;
            channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());
            //System.out.println("published mesasge"  + messageOn);
        }


        //FAN 4
        else if (fanParam == "FanFourOn"){
            String messageOn = "KDon" ;
            channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());
            //System.out.println("published mesasge"  + messageOn);
        }
        else if (fanParam == "FanFourOff"){
            String messageOn = "Kdoff" ;
            channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());
            //System.out.println("published mesasge"  + messageOn);
        }
    }

    public void SendSpeed() throws InterruptedException {
        Thread.sleep(500); //0.5 sec
    }
}
