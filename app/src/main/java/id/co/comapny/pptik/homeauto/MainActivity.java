package id.co.comapny.pptik.homeauto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

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
                }else{
                    tv.setBackgroundColor(Color.RED);
                    tv.setText("LAMP is OFF");
                }
            }
        });
    }
}
