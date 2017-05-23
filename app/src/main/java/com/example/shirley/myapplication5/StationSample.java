package com.example.shirley.myapplication5;

/**
 * Created by Shirley on 13/05/2017.
 */

class StationSample {
    private String latitude;
    private String longitude;
    private String station;
    private String id;

    public StationSample (){}

    public String getId() {
        return id;
    }
    public String getStation() {
        return station;
    }
    public String getlongitude() {
        return longitude;
    }
    public String getlatitude() {
        return latitude;
    }

    public void  setId(String id){
        this.id = id;
    }
    public void  setStation(String station){
        this.station = station;
    }
    public void  setLongitude (String longitude){
        this.longitude = longitude;
    }
    public void  setLatitude(String latitude){
        this.latitude = latitude;
    }
    public String toString() {
        return "StationSample{" +
                "Station name'" + station + '\'' +
                "latitude'" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
