package com.example.mapro1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.baidu.mapapi.SDKInitializer;

public class MainActivity extends AppCompatActivity {

    private ImageView image_gps = null;
    private ImageView image_glonass = null;
    private ImageView image_beidou = null;
    private ImageButton imagebtn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        image_gps = (ImageView) findViewById(R.id.image_gps);
        image_glonass = (ImageView) findViewById(R.id.image_glonass);
        image_beidou = (ImageView) findViewById(R.id.image_beidou);
        imagebtn = (ImageButton) findViewById(R.id.imagebutton_main);
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Bmap.class));
            }
        });
        //image_gps.setImageResource(R.drawable.gps_lost);//重置图片

    }
}
