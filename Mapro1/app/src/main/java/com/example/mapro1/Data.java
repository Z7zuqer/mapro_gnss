package com.example.mapro1;

import java.util.List;

/**
 * Created by Duhao on 2017/6/9.
 */

public class Data {
    public List<data> info;
    public static class data{
        public String name;
        public String id;
        public String status;
        public String latitude;
        public String longitude;
        public String time;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        public String getLatitude() {
            return latitude;
        }
        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
        public String getLongitude() {
            return longitude;
        }
        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }

    }
    public List<data> getInfo() {
        return info;
    }
    public void setInfo(List<data> info) {
        this.info = info;
    }
}
