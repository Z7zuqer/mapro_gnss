package com.example.mapro1;

import android.app.Activity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Duhao on 2017/6/9.
 */

public class download {
    UsernamePasswordCredentials upc;
    AuthScope as;
    BasicCredentialsProvider bcp;
    DefaultHttpClient dhc;
    HttpGet hg;
    HttpResponse hr;
    BufferedReader reader;
    StringBuilder builder;
    Data data_1;
    List<Data.data> list;
    int res=0;
    String line;
    public download(){
        upc=new UsernamePasswordCredentials("secRF","ask1314772002");
        as=new AuthScope(null,-1);
        bcp=new BasicCredentialsProvider();
        bcp.setCredentials(as,upc);
        dhc=new DefaultHttpClient();
        dhc.setCredentialsProvider(bcp);
    }
    public int Download() {
        try{
            hg=new HttpGet("http://118.89.201.90/mapro/info/gps");
            hr=dhc.execute(hg);
            line="";
            reader=new BufferedReader(new InputStreamReader(hr.getEntity().getContent()));
            builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            data_1=JsonUtils.parseStudentFromJson(builder.toString());
            list=data_1.info;
            for (Data.data s : list) {
                String name_1=s.getName(),sta_1=s.getStatus();
                int id=name_1.charAt(0)-'0',sta=sta_1.charAt(0)-'0';
                if(sta==0)sta=1;else if(sta==1)sta=2;else if(sta==2)sta=0;
                if(id==2)res+=sta;
            }
            hg=new HttpGet("http://118.89.201.90/mapro/info/glonass");
            hr=dhc.execute(hg);
            line="";
            reader=new BufferedReader(new InputStreamReader(hr.getEntity().getContent()));
            builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            data_1=JsonUtils.parseStudentFromJson(builder.toString());
            list=data_1.info;
            for (Data.data s : list) {
                String name_1=s.getName(),sta_1=s.getStatus();
                int id=name_1.charAt(0)-'0',sta=sta_1.charAt(0)-'0';
                if(sta==0)sta=1;else if(sta==1)sta=2;else if(sta==2)sta=0;
                if(id==2)res+=sta*100;
            }
            hg=new HttpGet("http://118.89.201.90/mapro/info/beidou");
            hr=dhc.execute(hg);
            line="";
            reader=new BufferedReader(new InputStreamReader(hr.getEntity().getContent()));
            builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            data_1=JsonUtils.parseStudentFromJson(builder.toString());
            list=data_1.info;
            for (Data.data s : list) {
                String name_1=s.getName(),sta_1=s.getStatus();
                int id=name_1.charAt(0)-'0',sta=sta_1.charAt(0)-'0';
                if(sta==0)sta=1;else if(sta==1)sta=2;else if(sta==2)sta=0;
                if(id==2)res+=sta*10;
            }
            return res;
        }catch(Exception e){
            return 0;
        }
    }
}
