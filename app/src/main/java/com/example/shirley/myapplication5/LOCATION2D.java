package com.example.shirley.myapplication5;

import android.location.Location;
import android.util.Log;

import java.io.IOException;

import static com.example.shirley.myapplication5.MainActivity.stationArray;

/**
 * Created by Shirley on 31/03/2017.
 */

public class LOCATION2D {
    private double latitude;
    private double longitude;

    public LOCATION2D() {
    }

    public LOCATION2D(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LOCATION2D{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
    public String nearestStatitan() throws IOException {
        Log.d("find nearst station","V");
        double tempShortDistance=0;
        double currentShortDistance=0;
        String stationname ="x";
        double lat1 = this.getLatitude();
        double long1 = this.getLongitude();
        double long2, lat2;
        //Location loc1 = new Location("Loc1");
       // loc1.setLatitude(lat1);
       // loc1.setLongitude(long1);
        for (int i =1; i<stationArray.size(); i++) {
            long2 = Double.parseDouble(stationArray.get(i).getlongitude());
            lat2 = Double.parseDouble(stationArray.get(i).getlatitude());

            tempShortDistance = getDistance3(lat1, long1,lat2, long2);
            //Log.d("LOC", "6!!!!!!!!!!!!:" + stationArray.get(i) );

            if(i == 1) currentShortDistance = tempShortDistance;
            if (tempShortDistance < currentShortDistance) {
                currentShortDistance = tempShortDistance;
                stationname = stationArray.get(i).getStation();
            }
        }
        return stationname;

    }

    public String nearestOfNearestStation (String stationNearetsName) throws  IOException {
        double tempShortDistance=0;
        double currentShortDistance=0;
        double lat1 =0,long1 = 0;
        String nearestStatitan = stationNearetsName;
        String nearestOfNearestStatitan="Y";
        for (int i =1; i<stationArray.size(); i++) {
            if (nearestStatitan.equals(stationArray.get(i).getStation())) {
                lat1 = Double.parseDouble(stationArray.get(i).getlatitude());
                long1 = Double.parseDouble(stationArray.get(i).getlongitude());
            }
        }
        double long2, lat2;
        if (lat1 != 0 && long1 != 0)
            for (int j = 1; j < stationArray.size(); j++) {
                    long2 = Double.parseDouble(stationArray.get(j).getlongitude());
                    lat2 = Double.parseDouble(stationArray.get(j).getlatitude());

                tempShortDistance = getDistance3(lat1, long1, lat2, long2);
                if (j == 1) currentShortDistance = tempShortDistance;
                Log.d("LOC2", "current = " + currentShortDistance + " tenp = " + tempShortDistance + "name : " + stationArray.get(j).getStation());
                if (tempShortDistance < currentShortDistance && !(stationArray.get(j).getStation().equals(nearestStatitan ))) {
                    currentShortDistance = tempShortDistance;
                    nearestOfNearestStatitan = stationArray.get(j).getStation();
                        }
                    }

                return nearestOfNearestStatitan;
            }



    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }


    /*
    private static double distance4(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;

        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    public float getDistance(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        Log.d("LOC", "5!!!!!!!!!!!!:" );
        float[] results = new float[1];
        Location.distanceBetween(startLatitude, startLongitude,
                endLatitude, endLongitude, results);
       // float distance = ;
        return  results[0];
    }



    private double getDistance3(double lat1, double long1, double lat2, double long2) {
        double theta = long1 - long2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }/*
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

/*
    private double getDistance2(double lat1, double long1, double lat2, double long2) {

        LOCATION2D startPoint=new LOCATION2D(lat1, long1);
        LOCATION2D endPoint=new LOCATION2D(lat2, long2);

        double distance=startPoint.distanceTo(endPoint);
        return distance;
    }



   private double getDistance1(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }*/

}
