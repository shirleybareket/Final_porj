package com.example.shirley.myapplication5;

/**
 * Created by Shirley on 17/05/2017.
 */

public class stationsTzfon {
    private String station;
    private String time;
    private String so2;
    private String nox;
    private String no2;
    private String no;
    private String o3;
    private String pm10;
    private String pm25;
    private String ws;
    private String wd;
    private String temp;
    private String rh;



    @Override
    public String toString() {
        return "stationsTzfon{" +
                "station='" + station + '\'' +
                ", time='" + time + '\'' +
                ", so2='" + so2 + '\'' +
                ", nox='" + nox + '\'' +
                ", no2='" + no2 + '\'' +
                ", no='" + no + '\'' +
                ", o3='" + o3 + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", ws='" + ws + '\'' +
                ", wd='" + wd + '\'' +
                ", temp='" + temp + '\'' +
                ", rh='" + rh + '\'' +


                '}';
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String wps) {
        this.ws = wps;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }


    public String getPm10() {
        return pm10;
    }

    public void setPm10(String ppm10) {
        this.pm10 = ppm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getNox() {
        return nox;
    }

    public void setNox(String nox) {
        this.nox = nox;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}