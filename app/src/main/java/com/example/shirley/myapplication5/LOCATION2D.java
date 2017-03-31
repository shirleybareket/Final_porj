package com.example.shirley.myapplication5;

/**
 * Created by Shirley on 31/03/2017.
 */

public class LOCATION2D {
    private String latitude;
    private String longitude;

    public LOCATION2D() {
    }

    public LOCATION2D(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
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

    @Override
    public String toString() {
        return "LOCATION2D{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
