package com.example.shirley.myapplication5;

/**
 * Created by Shirley on 17/05/2017.
 */

public class stationEilat {
    private String station;
    private String time;
    private String so2;
    private String nox;
    private String no2;
    private String no;
    private String o3;
    private String pm10;
    private String tsp;
    private String ws;
    private String wd;
    private String temp;
    private String rh;
    private String gsr;
    private String rain;


    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }



    public  String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }


    public String getWps() {
        return ws;
    }

    public void setWps(String wps) {
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

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }



    @Override
    public String toString() {
        return "stationEilat{" +
                "station='" + station + '\'' +
                ", time='" + time + '\'' +
                ", so2='" + so2 + '\'' +
                ", nox='" + nox + '\'' +
                ", no2='" + no2 + '\'' +
                ", no='" + no + '\'' +
                ", o3='" + o3 + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", tsp='" + tsp + '\'' +
                ", ws='" + ws + '\'' +
                ", wd='" + wd + '\'' +
                ", temp='" + temp + '\'' +
                ", rh='" + rh + '\'' +
                ", gsr='" + gsr + '\'' +
                ", rain='" + rain + '\'' +
                '}';
    }

    public  String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public  String getNox() {
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

    public String getTsp() {
        return tsp;
    }

    public void setTsp(String tsp) {
        this.tsp = tsp;
    }

    public String getGsr() {

        return gsr;
    }

    public void setGsr(String gsr) {
        this.gsr = gsr;
    }
}
