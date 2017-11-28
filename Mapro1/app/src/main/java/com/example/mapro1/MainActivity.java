package com.example.mapro1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView image_gps = null;
    private ImageView image_glonass = null;
    private ImageView image_beidou = null;
    private ImageButton imagebtn = null;
    UpdateUIBroadcastReceiver broadcastReceiver;
    public static final String ACTION_UPDATEUI="action.updateUI";
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
        //image_gps.setImageResource(R.drawable.gps_lost);
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Bmap.class));
            }
        });
        IntentFilter filter=new IntentFilter();
        filter.addAction(ACTION_UPDATEUI);
        broadcastReceiver=new UpdateUIBroadcastReceiver();
        registerReceiver(broadcastReceiver,filter);

        startService(new Intent(this,BackSer.class));
    }
    public void onDestroy(){
        super.onDestroy();
        stopService(new Intent(this,BackSer.class));
        unregisterReceiver(broadcastReceiver);
    }
    private class UpdateUIBroadcastReceiver extends BroadcastReceiver{
        public void onReceive(Context context, Intent intent){
                int k=intent.getExtras().getInt("sta");
                int sta_1=k%10;k/=10;
                if(sta_1==0)image_gps.setImageResource(R.drawable.gps_lost);
                else if(sta_1==1)image_gps.setImageResource(R.drawable.gps_safe);
                else image_gps.setImageResource(R.drawable.gps_unsafe);
                int sta_2=k%10;k/=10;
                if(sta_2==0)image_beidou.setImageResource(R.drawable.beidou_lost);
                else if(sta_2==1)image_beidou.setImageResource(R.drawable.beidou_safe);
                else image_beidou.setImageResource(R.drawable.beidou_unsafe);
                int sta_3=k%10;k/=10;
                if(sta_3==0)image_glonass.setImageResource(R.drawable.glonass_lost);
                else if(sta_3==1)image_glonass.setImageResource(R.drawable.glonass_safe);
                else image_glonass.setImageResource(R.drawable.glonass_unsafe);
        }
    }
}
