package id.co.comapny.pptik.homeauto;

import id.co.comapny.pptik.homeauto.ConsumerRMQ;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
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
    ConsumerRMQ consumer = new ConsumerRMQ();
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = (TextView) findViewById(R.id.tv);
        final  Switch sb = (Switch)findViewById(R.id.lamp);

        final TextView txtKa1=(TextView)findViewById(R.id.txtKai1);
        final  Switch ka1 = (Switch)findViewById(R.id.kai1);

        final TextView txtKa2=(TextView)findViewById(R.id.txtKai2);
        final  Switch ka2 = (Switch)findViewById(R.id.kai2);

        final TextView txtKa3=(TextView)findViewById(R.id.txtKai3);
        final  Switch ka3 = (Switch)findViewById(R.id.kai3);

        final TextView txtKa4=(TextView)findViewById(R.id.txtKai4);
        final  Switch ka4 = (Switch)findViewById(R.id.kai4);

        tv.setBackgroundColor(Color.RED);
        tv.setText("LAMP is OFF");

        txtKa1.setBackgroundColor(Color.RED);
        txtKa1.setText("FAN 1 is OFF");

        txtKa2.setBackgroundColor(Color.RED);
        txtKa2.setText("FAN 2 is OFF");

        txtKa3.setBackgroundColor(Color.RED);
        txtKa3.setText("FAN 3 is OFF");

        txtKa4.setBackgroundColor(Color.RED);
        txtKa4.setText("FAN 4 is OFF");

        Switch all= (Switch)findViewById(R.id.all);
        all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tv.setBackgroundColor(Color.GREEN);
                    tv.setText("LAMP is ON");
                    sb.setChecked(true);
                    sb.setClickable(false);

                    txtKa1.setBackgroundColor(Color.GREEN);
                    txtKa1.setText("FAN 1 is ON");
                    ka1.setChecked(true);
                    ka1.setClickable(false);

                    txtKa2.setBackgroundColor(Color.GREEN);
                    txtKa2.setText("FAN 2 is ON");
                    ka2.setChecked(true);
                    ka2.setClickable(false);

                    txtKa3.setBackgroundColor(Color.GREEN);
                    txtKa3.setText("FAN 3 is ON");
                    ka3.setChecked(true);
                    ka3.setClickable(false);

                    txtKa4.setBackgroundColor(Color.GREEN);
                    txtKa4.setText("FAN 4 is ON");
                    ka4.setChecked(true);
                    ka4.setClickable(false);

                }else{
                    tv.setBackgroundColor(Color.RED);
                    tv.setText("LAMP is OFF");
                    sb.setChecked(false);
                    sb.setClickable(true);

                    txtKa1.setBackgroundColor(Color.RED);
                    txtKa1.setText("FAN 1 is OFF");
                    ka1.setChecked(false);
                    ka1.setClickable(true);

                    txtKa2.setBackgroundColor(Color.RED);
                    txtKa2.setText("FAN 2 is OFF");
                    ka2.setChecked(false);
                    ka2.setClickable(true);

                    txtKa3.setBackgroundColor(Color.RED);
                    txtKa3.setText("FAN 3 is OFF");
                    ka3.setChecked(false);
                    ka3.setClickable(true);

                    txtKa4.setBackgroundColor(Color.RED);
                    txtKa4.setText("FAN 4 is OFF");
                    ka4.setChecked(false);
                    ka4.setClickable(true);
                }
            }
        });

        ka1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtKa1.setBackgroundColor(Color.GREEN);
                    txtKa1.setText("FAN 1 is ON");

                }else{
                    txtKa1.setBackgroundColor(Color.RED);
                    txtKa1.setText("FAN 1 is OFF");
                }
            }
        });

        ka2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtKa2.setBackgroundColor(Color.GREEN);
                    txtKa2.setText("FAN 2 is ON");
                }else{
                    txtKa2.setBackgroundColor(Color.RED);
                    txtKa2.setText("FAN 2 is OFF");
                }
            }
        });

        ka3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtKa3.setBackgroundColor(Color.GREEN);
                    txtKa3.setText("FAN 3 is ON");
                }else{
                    txtKa3.setBackgroundColor(Color.RED);
                    txtKa3.setText("FAN 3 is OFF");
                }
            }
        });

        ka4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtKa4.setBackgroundColor(Color.GREEN);
                    txtKa4.setText("FAN 4 is ON");
                }else{
                    txtKa4.setBackgroundColor(Color.RED);
                    txtKa4.setText("FAN 4 is OFF");
                }
            }
        });
        sb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    tv.setBackgroundColor(Color.GREEN);
                    tv.setText("LAMP is ON");
                    try {
                        String dataSendType = "on_lamp";
                        AndroidToRMQLamp(dataSendType);
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

                }else{
                    tv.setBackgroundColor(Color.RED);
                    tv.setText("LAMP is OFF");
                    checkOnOff = false;
                   // pmg.dataOff();
                    try {
                        String dataSendType = "off_lamp";
                        AndroidToRMQLamp(dataSendType);
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
//                    Toast.makeText(context, "Lamp OFF", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public  void  AndroidToRMQLamp(String dataSendType) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://homeauto:homeauto12345!@167.205.7.226");
        factory.setVirtualHost("/homeauto");
        //factory.setUri("amqp://guest:guest@localhost");
        factory.setConnectionTimeout(3000000);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        if (dataSendType == "off_lamp"){
            String messageOn = "off" ;
            channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());

            System.out.println("published mesasge"  + messageOn);
        }else if (dataSendType == "on_lamp"){
            String messageOn = "on" ;
            channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());

            System.out.println("published mesasge"  + messageOn);
        }

        // channel.basicPublish("amq.topic","homeauto",null,messageOn.getBytes());

        // System.out.println("published mesasge"  + messageOn);
        //channel.queueDeclare("homeauto",true,false,false,null);


    }
}
