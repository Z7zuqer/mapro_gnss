package com.example.mapro1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.MainThread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Duhao on 2017/6/9.
 */

public class BackSer extends Service {
    private TimerTask task;
    private Timer timer;
    private int sta;
    private Thread thread;
    download k=new download();
   public IBinder onBind(Intent intent){
       return null;
   }
   public void onCreate(){
      super.onCreate();
       final Intent intent=new Intent();
       intent.setAction(MainActivity.ACTION_UPDATEUI);
       timer=new Timer();
       task=new TimerTask(){
           public void run(){
               intent.putExtra("sta",k.Download());
               sendBroadcast(intent);
           }
       };
       timer.schedule(task,1000,1000);
   }
   public int onStartCommand(Intent intent, int flags, int startId){
       return START_STICKY;
   }
   public void onStart(Intent intent,int startId){
       timer.schedule(task,1000,1000);
   }
   public void onDestroy(){
       super.onDestroy();
       timer.cancel();
   }
}
