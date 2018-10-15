package id.co.comapny.pptik.homeauto;

import android.content.Context;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {
    public boolean checkOnOff;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = (TextView) findViewById(R.id.tv);
        Switch sb = (Switch)findViewById(R.id.lamp);
        sb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    tv.setBackgroundColor(Color.GREEN);
                    tv.setText("LAMP is ON");
                    checkOnOff = true;
                    try {
                        AndroidToRMQOn();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (KeyManagementException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                    // pmg.dataOn();
                    Toast.makeText(context, "Lamp ON", Toast.LENGTH_LONG).show();
                }else{
                    tv.setBackgroundColor(Color.RED);
                    tv.setText("LAMP is OFF");
                    checkOnOff = false;
                   // pmg.dataOff();
                    try {
                        AndroidToRMQOff();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (KeyManagementException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Lamp OFF", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public  void  AndroidToRMQOn() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://homeauto:homeauto12345!@167.205.7.226");
        factory.setVirtualHost("/homeauto");
        //factory.setUri("amqp://guest:guest@localhost");
        factory.setConnectionTimeout(3000000);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


       // channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());

       // System.out.println("published mesasge"  + messageOn);
        //channel.queueDeclare("homeauto",true,false,false,null);

        String messageOn = "ON" ;
        channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());

        System.out.println("published mesasge"  + messageOn);




    }
    public  void  AndroidToRMQOff() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://homeauto:homeauto12345!@167.205.7.226");
        factory.setVirtualHost("/homeauto");
        //factory.setUri("amqp://guest:guest@localhost");
        factory.setConnectionTimeout(3000000);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        // channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());

        // System.out.println("published mesasge"  + messageOn);
        //channel.queueDeclare("homeauto",true,false,false,null);

        String messageOn = "OFF" ;
        channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());

        System.out.println("published mesasge"  + messageOn);




    }
}
