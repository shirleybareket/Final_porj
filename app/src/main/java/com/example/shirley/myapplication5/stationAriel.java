package com.example.shirley.myapplication5;

/**
 * Created by Shirley on 17/05/2017.
 */

public class stationAriel {
    private String station;
    private String time;

    private String nox;
    private String no2;
    private String no;
    private String o3;
    private String pm10;

    @Override
    public String toString() {
        return "stationAriel{" +
                "station='" + station + '\'' +
                ", time='" + time + '\'' +
                ", nox='" + nox + '\'' +
                ", no2='" + no2 + '\'' +
                ", no='" + no + '\'' +
                ", o3='" + o3 + '\'' +

                ", pm10='" + pm10 + '\'' +
                '}';
    }




    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPpm10(String pm10) {
        this.pm10 = pm10;
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



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}



