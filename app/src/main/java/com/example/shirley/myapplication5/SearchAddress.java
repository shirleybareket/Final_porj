package com.example.shirley.myapplication5;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.example.shirley.myapplication5.MainActivity.staitonsGushDenList;
import static com.example.shirley.myapplication5.MainActivity.stationArielList;
import static com.example.shirley.myapplication5.MainActivity.stationArray;
import static com.example.shirley.myapplication5.MainActivity.stationEilatList;
import static com.example.shirley.myapplication5.MainActivity.stationsHaifaAndKrayotList;
import static com.example.shirley.myapplication5.MainActivity.stationsInnerSheflaList;
import static com.example.shirley.myapplication5.MainActivity.stationsJerusalemList;
import static com.example.shirley.myapplication5.MainActivity.stationsJezreelVallyList;
import static com.example.shirley.myapplication5.MainActivity.stationsMobiltyList;
import static com.example.shirley.myapplication5.MainActivity.stationsNorthernNegevList;
import static com.example.shirley.myapplication5.MainActivity.stationsShronAndCarmelsList;
import static com.example.shirley.myapplication5.MainActivity.stationsSouthernCoastalPlainList;
import static com.example.shirley.myapplication5.MainActivity.stationsTzfonList;
import static com.example.shirley.myapplication5.MainActivity.stationsYehudaList;

public class SearchAddress extends AppCompatActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    GoogleMap mGoogleMap;
    GoogleApiClient mGoogleApiClient;
    Marker markersearch;
    MarkerOptions markerOptions;
    Double Latitude;
    Double Longitude;

    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    private boolean LocationChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (googleServicesAvailable()) {

            setContentView(R.layout.activity_search_address);
            initMap();


        } else {
            // No Google Maps Layout
        }
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment1);
        mapFragment.getMapAsync(this);

    }

    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Cant connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void goToLocation(double lat, double lng) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        markerOptions.position(ll);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        markersearch = mGoogleMap.addMarker(markerOptions);
        mGoogleMap.moveCamera(update);
    }

    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                googleMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }

    }

    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (location == null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            } else {
                goToLocationZoom(location.getLatitude(), location.getLongitude(), 15);
            }
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());


        //to save the location as double:
        Latitude = mLastLocation.getLatitude();//current latitude
        Longitude = mLastLocation.getLongitude();//current longitude
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("My_Position: <" + Latitude + "," + Longitude + ">");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates if LocationChange==false
        if (!LocationChange) {
            if (mGoogleApiClient != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (LocationListener) this);
            }
        }

    }


    public void onSearch(View view) throws IOException {
        EditText et = (EditText) findViewById(R.id.etfindaddress);
        List<Address> listaddress = null;
        String location = et.getText().toString();
        Geocoder gc = new Geocoder(this);
        if (location == null || location == "") {
            Toast.makeText(this, "error! Location not found!", Toast.LENGTH_SHORT).show();
            et.setText("");
            return;
        }
        listaddress = gc.getFromLocationName(location, 1);
        if (listaddress == null || listaddress.size() == 0) {
            Toast.makeText(this, "No Location found", Toast.LENGTH_SHORT).show();
            return;
        }
        Address address = listaddress.get(0);
        String locality = address.getLocality();
        Toast.makeText(this, locality, Toast.LENGTH_LONG).show();
        double lat = address.getLatitude();
        double lng = address.getLongitude();
        //goToLocation(lat,lng);
        goToLocationZoom(lat, lng, 15);
        // LatLng latLng = new LatLng(lat , lng);
        // mGoogleMap.addMarker(markerOptions.title("Marker"));
        //mGoogleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        //mGoogleMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        et.setText("");
        LOCATION2D loc = new LOCATION2D(lat, lng);
        String nearestStatitanName = loc.nearestStatitan();
        String nearestOfNearestStation = loc.nearestOfNearestStation(nearestStatitanName);
        Toast.makeText(this, "The nearest station is :" + nearestStatitanName, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "The  nearest of the nearest station is :" + nearestOfNearestStation, Toast.LENGTH_LONG).show();
        calculationOfPollutionMainFunction(nearestStatitanName);

    }


    public void calculationOfPollutionMainFunction(String neareststation) throws IOException {
        // the min of allPollutans is the ans
        // allPollutans[0] = So2; allPollutans[1] =nox; allPollutans[2] = no2; allPollutans[3] = os;
        // allPollutans[4] = Co; allPollutans[5] = Pm10; allPollutans[6] = Pm2.5;
        boolean allDataIsPresent = false;
        double[] allPollutans = new double[7];
        String colorGraph = "", pollutionLevel = "";
        double minPollutanLevel = 0;
        Log.d("clac", "00000");
        allPollutans = findTheNearstStationOnDatabase(neareststation);
        Log.d("clac", "11111");
        Toast.makeText(this, "complements Data from nearest station!" , Toast.LENGTH_LONG).show();
        /*while (!(allDataIsPresent)){
            if ( allPollutans[0] !=200 && allPollutans[1] !=200 &&
                    allPollutans[2] !=200 && allPollutans[3] !=200 &&
                    allPollutans[5] !=200 && allPollutans[6] !=200){
                allDataIsPresent =true;
            }
            allPollutans = complementsData(allPollutans, neareststation);
            Log.d("clac!!!!!", "allP" + Arrays.toString(allPollutans));
            neareststation = nearestOfNearestStation(neareststation);
        }*/
        if ( allPollutans[0] ==200 || allPollutans[1] ==200 ||
                allPollutans[2] ==200 || allPollutans[3] ==200 ||
                allPollutans[5] ==200 || allPollutans[6] ==200){

                allPollutans = complementsData(allPollutans, neareststation);
        }
        Log.d("clac!!!!!", "allP" + Arrays.toString(allPollutans));
        minPollutanLevel = findMin(allPollutans);

        // -400 <= PollutionLevel <= 200
        if (51 <= minPollutanLevel && minPollutanLevel <= 100) {
            colorGraph = "Green";
            pollutionLevel = " Good! :)";
        }
        if (0 <= minPollutanLevel && minPollutanLevel <= 50) {
            colorGraph = "Yellow";
            pollutionLevel = " Medium";
        }
        if (-200 <= minPollutanLevel && minPollutanLevel <= -1) {
            colorGraph = "red";
            pollutionLevel = " high! :(";
        }
        if (-400 <= minPollutanLevel && minPollutanLevel <= -201) {
            colorGraph = "brown";
            pollutionLevel = " very high!!! :O";
        }
        Log.d("clacCo", "44444!!!!!!!");
        Toast.makeText(this, "PollutionLevel Co = " + minPollutanLevel +
                "/n colorGraph =" + colorGraph +
                "/n  pollutionLevel" + pollutionLevel, Toast.LENGTH_LONG).show();
    }

    private double[] complementsData(double[] allPollutans, String neareststation) throws IOException {
        Log.d("clac", "22222");
        // allPollutans[0] = So2; allPollutans[1] =nox; allPollutans[2] = no2; allPollutans[3] = os;
        // allPollutans[4] = Co; allPollutans[5] = Pm10; allPollutans[6] = Pm2.5;
       double[] temp = new double[7];
        String nearestOfNearestStation = nearestOfNearestStation(neareststation);
        temp = findTheNearstStationOnDatabase(nearestOfNearestStation);
        if (temp[0]==200 && temp[1]==200 &&temp[2]==200 && temp[3]==200 &&temp[4]==200 && temp[5]==200 && temp[6] ==200) // cant find on database the nearestof neareat station
        {
            Toast.makeText(this, "nearest station not found in Database!" , Toast.LENGTH_LONG).show();
            Toast.makeText(this, "complements Data from the second nearest station!" , Toast.LENGTH_LONG).show();
            String nearestOfNearest2Station = NearestOfnearest2Station(nearestOfNearestStation);
            temp = findTheNearstStationOnDatabase(nearestOfNearest2Station);
        }
        for (int i=0; i<allPollutans.length; i++) {
            if (allPollutans[i] == 200 && temp[i] != 200 && i !=4) {
                allPollutans[i] = temp[i];
            }
        } Log.d("clac", "33333");

              if ( allPollutans[0] ==200 || allPollutans[1] ==200 ||
                        allPollutans[2] ==200 || allPollutans[3] ==200 ||
                        allPollutans[5] ==200 || allPollutans[6] ==200){
                  Log.d("clac", "444444");
                  Toast.makeText(this, "Data is still missing!", Toast.LENGTH_LONG).show();
            }
            return allPollutans;
    }

    private double findMin(double[] allPollutans) {
        double min = allPollutans[0];
        for (int i = 1; i<allPollutans.length; i++){
            if (min>allPollutans[i])
                min = allPollutans[i];
        }
        return min;
    }


    private double[] findTheNearstStationOnDatabase(String stationName) {
        double[] allPollutants = new double[7];
        for (int i=0; i<allPollutants.length; i++){
            allPollutants[i]=200;
        }

        for (int i = 1; i < staitonsGushDenList.size(); i++) {
            if (stationName.equals(staitonsGushDenList.get(i).getStation())) {
                Toast.makeText(this, "Found the nearest station on database - GushDan!", Toast.LENGTH_LONG).show();
                allPollutants = claculationOfAirPollutionLevelGushDen(staitonsGushDenList.get(i));

                return allPollutants;
            }
        }
        Toast.makeText(this, "Not Found in stationsGushDen!", Toast.LENGTH_SHORT).show();

        for (int i = 1; i < stationArielList.size(); i++) {
            if (stationName.equals(stationArielList.get(i).getStation())) {
                Toast.makeText(this, "Found the nearest station on database - Ariel!", Toast.LENGTH_LONG).show();
                allPollutants = claculationOfAirPollutionLevelAriel(stationArielList.get(i));

                return allPollutants;
            }
        }
        Toast.makeText(this, "Not Found in stationsAriel!", Toast.LENGTH_SHORT).show();
        for (int i = 1; i < stationEilatList.size(); i++) {
            if (stationName.equals(stationEilatList.get(i).getStation())) {
                Toast.makeText(this, "Found the nearest station on database - Eilat!", Toast.LENGTH_LONG).show();
                allPollutants = claculationOfAirPollutionLevelEilat(stationEilatList.get(i));

                return allPollutants;
            }
        }
        for (int i = 1; i < stationsHaifaAndKrayotList.size(); i++) {
            if (stationName.equals(stationsHaifaAndKrayotList.get(i).getStation())) {
                Toast.makeText(this, "Found the nearest station on database- HaifaAndKrayot!", Toast.LENGTH_LONG).show();
                allPollutants = claculationOfAirPollutionLevelHaifaAndKrayot(stationsHaifaAndKrayotList.get(i));

                return allPollutants;
            }
        }
        Toast.makeText(this, "Not Found in stationsHaifaAndKrayot!", Toast.LENGTH_SHORT).show();
        for (int i = 1; i < stationsInnerSheflaList.size(); i++) {
            if (stationName.equals(stationsInnerSheflaList.get(i).getStation())) {
                Toast.makeText(this, "Found the nearest station on database -InnerShefla!", Toast.LENGTH_LONG).show();
                allPollutants = claculationOfAirPollutionLevelInnerShefla(stationsInnerSheflaList.get(i));

                return allPollutants;
            }
        }
        Toast.makeText(this, "Not Found in stationsInnerShefla!", Toast.LENGTH_SHORT).show();

        for (int i = 1; i < stationsJerusalemList.size(); i++) {
            if (stationName.equals(stationsJerusalemList.get(i).getStation())) {
                Toast.makeText(this, "Found the nearest station on database -Jerusalem!", Toast.LENGTH_LONG).show();
                allPollutants = claculationOfAirPollutionLevelJerusalem(stationsJerusalemList.get(i));

                return allPollutants;
            }
        }
        Toast.makeText(this, "Not Found in stationsJerusalem!", Toast.LENGTH_SHORT).show();
        for (int i = 1; i < stationsJezreelVallyList.size(); i++) {
            if (stationName.equals(stationsJezreelVallyList.get(i).getStation())) {
                Toast.makeText(this, "Found the nearest station on database -JezreelVally!", Toast.LENGTH_LONG).show();
                allPollutants = claculationOfAirPollutionLevelJezreelVally(stationsJezreelVallyList.get(i));

                return allPollutants;
            }
        }
        Toast.makeText(this, "Not Found in stationsJezreelVally!", Toast.LENGTH_SHORT).show();
        for (int i = 1; i < stationsMobiltyList.size(); i++) {
            if (stationName.equals(stationsMobiltyList.get(i).getStation())) {
                Toast.makeText(this, "Found the nearest station on database - JezreelVally! ", Toast.LENGTH_LONG).show();
                allPollutants = claculationOfAirPollutionLevelMobilty(stationsMobiltyList.get(i));

                return allPollutants;
            }
        }
        Toast.makeText(this, "Not Found in stationsMobility!", Toast.LENGTH_SHORT).show();
        for (int i = 1; i < stationsNorthernNegevList.size(); i++) {
            if (stationName.equals(stationsNorthernNegevList.get(i).getStation())) {
                Toast.makeText(this, "Found the nearest station on database -Mobility!", Toast.LENGTH_LONG).show();
                allPollutants = claculationOfAirPollutionLevelNorthernNegev(stationsNorthernNegevList.get(i));

                return allPollutants;
            }
        }
        Toast.makeText(this, "Not Found in stationsNorthernNegev!", Toast.LENGTH_SHORT).show();
        for (int i = 1; i < stationsShronAndCarmelsList.size(); i++) {
            if (stationName.equals(stationsShronAndCarmelsList.get(i).getStation())) {
                Toast.makeText(this, "Found the nearest station on database- NorthernNegev!", Toast.LENGTH_LONG).show();
                allPollutants = claculationOfAirPollutionLevelShronAndCarmel(stationsShronAndCarmelsList.get(i));

                return allPollutants;
            }
        }
        Toast.makeText(this, "Not Found in stationsShronAndCarmel!", Toast.LENGTH_SHORT).show();
        for (int i = 1; i < stationsSouthernCoastalPlainList.size(); i++) {
            if (stationName.equals(stationsSouthernCoastalPlainList.get(i).getStation())) {
                Toast.makeText(this, "Found the nearest station on database - SouthernCoastalPlain!", Toast.LENGTH_LONG).show();
                allPollutants = claculationOfAirPollutionLevelSouthernCoastalPlain(stationsSouthernCoastalPlainList.get(i));

                return allPollutants;
            }
        }
        Toast.makeText(this, "Not Found in stationsSouthernCoastalPlain!", Toast.LENGTH_SHORT).show();
        for (int i = 1; i < stationsTzfonList.size(); i++) {
            if (stationName.equals(stationsTzfonList.get(i).getStation())) {
                Toast.makeText(this, "Found the nearest station on database - stationsTzfon!", Toast.LENGTH_LONG).show();
                allPollutants = claculationOfAirPollutionLevelTzfon(stationsTzfonList.get(i));
                return allPollutants;
            }
        }
        Toast.makeText(this, "Not Found in stationTzfon!", Toast.LENGTH_SHORT).show();
        for (int i = 1; i < stationsYehudaList.size(); i++) {
            if (stationName.equals(stationsYehudaList.get(i).getStation())) {
                Toast.makeText(this, "Found the nearest station on database - stationYehuda!", Toast.LENGTH_LONG).show();
                allPollutants = claculationOfAirPollutionLevelYehuda(stationsYehudaList.get(i));

                return allPollutants;
            }
        }

        Toast.makeText(this, "Not Found nearsat station on database!", Toast.LENGTH_SHORT).show();
        return allPollutants;
    }


    //clac Pollution
    //Eilats pollutans : so2,nox,no2,o3,pm10
    private double[] claculationOfAirPollutionLevelEilat(stationEilat stationEilat) {
        Log.d("clacEilat", "!!!!!!!!");
        // allPollutans[0] = So2; allPollutans[1] =nox; allPollutans[2] = no2; allPollutans[3] = os;
        // allPollutans[4] = Co; allPollutans[5] = Pm10; allPollutans[6] = Pm2.5;
        double[] allPollutans = new double[7];
        for (int i =0; i<allPollutans.length; i++)
            allPollutans[i] =200; // cant be 200

        if (stationEilat.getSo2().length() > 0) {
            double clacSo2 = clacPollutionLevel_so2(stationEilat.getSo2());
            allPollutans[0] = clacSo2;
        }
        if (stationEilat.getNox().length() > 0) {
            double clacNox = clacPollutionLevel_nox(stationEilat.getNox());
            allPollutans[1] = clacNox;
        }

        if (stationEilat.getNo2().length() > 0) {
            double clacNo2 = clacPollutionLevel_no2(stationEilat.getNo2());
           allPollutans[2]= clacNo2;
        }
        if (stationEilat.getO3().length() > 0) {
            double clacO3 = clacPollutionLevel_o3(stationEilat.getO3());
            allPollutans[3] =clacO3;
        }
        if (stationEilat.getPm10().length() > 0) {
            double clacPm10 = clacPollutionLevel_pm10(stationEilat.getPm10());
            allPollutans[5] =clacPm10;
        }
        return allPollutans;
    }

    //HaifaAndKrayots pollutans : so2,nox,no2,o3,pm10,co,pm2.5
    private double[] claculationOfAirPollutionLevelHaifaAndKrayot(stationsHaifaAndKrayot stationsHaifaAndKrayot) {
        Log.d("clacHaifaAndKrayot", "!!!!!!!!");

        double[] allPollutans = new double[7];
        for (int i =0; i<allPollutans.length; i++)
            allPollutans[i] =200; // cant be 200

        if (stationsHaifaAndKrayot.getSo2().length() > 0) {
            double clacSo2 = clacPollutionLevel_so2(stationsHaifaAndKrayot.getSo2());
            allPollutans[0]= clacSo2;
        }
        if (stationsHaifaAndKrayot.getNox().length() > 0) {
            double clacNox = clacPollutionLevel_nox(stationsHaifaAndKrayot.getNox());
            allPollutans[1] = clacNox;
        }

        if (stationsHaifaAndKrayot.getNo2().length() > 0) {
            double clacNo2 = clacPollutionLevel_no2(stationsHaifaAndKrayot.getNo2());
            allPollutans[2]= clacNo2;
        }
        if (stationsHaifaAndKrayot.getO3().length() > 0) {
            double clacO3 = clacPollutionLevel_o3(stationsHaifaAndKrayot.getO3());
            allPollutans[3] = clacO3;
        }
        if (stationsHaifaAndKrayot.getPm10().length() > 0) {
            double clacPm10 = clacPollutionLevel_pm10(stationsHaifaAndKrayot.getPm10());
            allPollutans[5] = clacPm10;
        }
        if (stationsHaifaAndKrayot.getCo().length() > 0) {
            double clacCo = clacPollutionLevel_co(stationsHaifaAndKrayot.getCo());
            allPollutans[4]= clacCo;
        }
        if (stationsHaifaAndKrayot.getPm25().length() > 0) {
            double clacPm25 = clacPollutionLevel_pm10(stationsHaifaAndKrayot.getPm25());
            allPollutans[6]= clacPm25;
        }
        return allPollutans;
    }

    //InnerShefla pollutans : so2,nox,no2,o3,pm10,pm2.5
    private double[] claculationOfAirPollutionLevelInnerShefla(stationsInnerShefla stationsInnerShefla) {
        Log.d("clacInnerShefla", "!!!!!!!!");

        double[] allPollutans = new double[7];
        for (int i =0; i<allPollutans.length; i++)
            allPollutans[i] =200; // cant be 200

        if (stationsInnerShefla.getSo2().length() > 0) {
            double clacSo2 = clacPollutionLevel_so2(stationsInnerShefla.getSo2());
            allPollutans[0]= clacSo2;
        }
        if (stationsInnerShefla.getNox().length() > 0) {
            double clacNox = clacPollutionLevel_nox(stationsInnerShefla.getNox());
            allPollutans[1] = clacNox;
        }

        if (stationsInnerShefla.getNo2().length() > 0) {
            double clacNo2 = clacPollutionLevel_no2(stationsInnerShefla.getNo2());
            allPollutans[2] = clacNo2;
        }
        if (stationsInnerShefla.getO3().length() > 0) {
            double clacO3 = clacPollutionLevel_o3(stationsInnerShefla.getO3());
           allPollutans[3]= clacO3;
        }
        if (stationsInnerShefla.getPm10().length() > 0) {
            double clacPm10 = clacPollutionLevel_pm10(stationsInnerShefla.getPm10());
            allPollutans[5] = clacPm10;
        }

        if (stationsInnerShefla.getPm25().length() > 0) {
            double clacPm25 = clacPollutionLevel_pm10(stationsInnerShefla.getPm25());
            allPollutans[6] = clacPm25;
        }

        return allPollutans;
    }



    //Jerusalem pollutans : so2,nox,no2,o3,pm10,pm2.5,co
    private double[] claculationOfAirPollutionLevelJerusalem(stationsJerusalem stationsJerusalem) {
        Log.d("clacJerusalem", "!!!!!!!!");
        double[] allPollutans = new double[7];
        for (int i =0; i<allPollutans.length; i++)
            allPollutans[i] =200; // cant be 200

        if (stationsJerusalem.getSo2().length() > 0) {
            double clacSo2 = clacPollutionLevel_so2(stationsJerusalem.getSo2());
            allPollutans[0]= clacSo2;
        }
        if (stationsJerusalem.getNox().length() > 0) {
            double clacNox = clacPollutionLevel_nox(stationsJerusalem.getNox());
            allPollutans[1]= clacNox;
        }

        if (stationsJerusalem.getNo2().length() > 0) {
            double clacNo2 = clacPollutionLevel_no2(stationsJerusalem.getNo2());
            allPollutans[2] = clacNo2;
        }
        if (stationsJerusalem.getO3().length() > 0) {
            double clacO3 = clacPollutionLevel_o3(stationsJerusalem.getO3());
            allPollutans[3]= clacO3;
        }
        if (stationsJerusalem.getPm10().length() > 0) {
            double clacPm10 = clacPollutionLevel_pm10(stationsJerusalem.getPm10());
            allPollutans[5] = clacPm10;
        }
        if (stationsJerusalem.getCo().length() > 0) {
            double clacCo = clacPollutionLevel_co(stationsJerusalem.getCo());
            allPollutans[4]= clacCo;
        }
        return allPollutans;

    }

    //Jezreel Vally pollutans : so2,nox,no2,o3,pm10,pm2.5
    private double[] claculationOfAirPollutionLevelJezreelVally(stationsJezreelVally stationsJezreelVally) {
        Log.d("clacJezreelVally", "!!!!!!!!");
        double[] allPollutans = new double[7];
        for (int i =0; i<allPollutans.length; i++)
            allPollutans[i] =200; // cant be 200

        if (stationsJezreelVally.getSo2().length() > 0) {
            double clacSo2 = clacPollutionLevel_so2(stationsJezreelVally.getSo2());
            allPollutans[0] = clacSo2;
        }
        if (stationsJezreelVally.getNox().length() > 0) {
            double clacNox = clacPollutionLevel_nox(stationsJezreelVally.getNox());
            allPollutans[1]= clacNox;
        }

        if (stationsJezreelVally.getNo2().length() > 0) {
            double clacNo2 = clacPollutionLevel_no2(stationsJezreelVally.getNo2());
            allPollutans[2]= clacNo2;
        }
        if (stationsJezreelVally.getO3().length() > 0) {
            double clacO3 = clacPollutionLevel_o3(stationsJezreelVally.getO3());
            allPollutans[3] = clacO3;
        }
        if (stationsJezreelVally.getPm10().length() > 0) {
            double clacPm10 = clacPollutionLevel_pm10(stationsJezreelVally.getPm10());
            allPollutans[5] = clacPm10;
        }
        if (stationsJezreelVally.getPm25().length() > 0) {
            double clacPm25 = clacPollutionLevel_pm25(stationsJezreelVally.getPm25());
            allPollutans[6] = clacPm25;
        }
       return allPollutans;

    }

    //Mobilty pollutans : so2,nox,no2,o3,pm10,pm2.5,co
    private double[] claculationOfAirPollutionLevelMobilty(stationsMobilty stationsMobilty) {
        Log.d("clacMobilty", "!!!!!!!!");
        double[] allPollutans = new double[7];
        for (int i =0; i<allPollutans.length; i++)
            allPollutans[i] =200; // cant be 200

        if (stationsMobilty.getSo2().length()>0) {
            double clacSo2 = clacPollutionLevel_so2(stationsMobilty.getSo2());
            allPollutans[0] = clacSo2;
        }
        if (stationsMobilty.getNox().length()>0) {
            double clacNox = clacPollutionLevel_nox(stationsMobilty.getNox());
            allPollutans[1] = clacNox;
        }
        if (stationsMobilty.getNo2().length()>0) {
            double clacNo2 = clacPollutionLevel_no2(stationsMobilty.getNo2());
            allPollutans[2] = clacNo2;
        }
        if (stationsMobilty.getO3().length()>0) {
           double clacO3 = clacPollutionLevel_o3(stationsMobilty.getO3());
            allPollutans[3]= clacO3 ;
        }
        if (stationsMobilty.getPm10().length()>0) {
            double clacPm10 = clacPollutionLevel_pm10(stationsMobilty.getPm10());
            allPollutans[5] = clacPm10;
        }
        if (stationsMobilty.getPm25().length()>0) {
            double clacPm25 = clacPollutionLevel_pm25(stationsMobilty.getPm25());
            allPollutans[6] = clacPm25;
        }
        if (stationsMobilty.getCo().length()>0) {
            double clacCo = clacPollutionLevel_co(stationsMobilty.getCo());
            allPollutans[4] = clacCo;
        }
        return allPollutans;
    }

    //Northern Negev pollutans : so2,nox,no2,o3,pm10,pm2.5
    private double[] claculationOfAirPollutionLevelNorthernNegev(stationsNorthernNegev stationsNorthernNegev) {
        Log.d("clacNorthernNegev", "!!!!!!!!");
        double[] allPollutans = new double[7];
        for (int i = 0; i < allPollutans.length; i++)
            allPollutans[i] = 200; // cant be 200

        if (stationsNorthernNegev.getSo2().length() > 0) {
            double clacSo2 = clacPollutionLevel_so2(stationsNorthernNegev.getSo2());
            allPollutans[0] = clacSo2;
        }
        if (stationsNorthernNegev.getNox().length() > 0) {
            double clacNox = clacPollutionLevel_nox(stationsNorthernNegev.getNox());
            allPollutans[1] = clacNox;
        }
        if (stationsNorthernNegev.getNo2().length() > 0) {
            double clacNo2 = clacPollutionLevel_no2(stationsNorthernNegev.getNo2());
            allPollutans[2] = clacNo2;
        }
        if (stationsNorthernNegev.getO3().length() > 0) {
            double clacO3 = clacPollutionLevel_o3(stationsNorthernNegev.getO3());
            allPollutans[3] = clacO3;
        }
        if (stationsNorthernNegev.getPm10().length() > 0) {
            double clacPm10 = clacPollutionLevel_pm10(stationsNorthernNegev.getPm10());
            allPollutans[5] = clacPm10;
        }
        if (stationsNorthernNegev.getPm25().length() > 0) {
            double clacPm25 = clacPollutionLevel_pm25(stationsNorthernNegev.getPm25());
            allPollutans[6] = clacPm25;
        }
        return allPollutans;
    }

    //Shron And Carmel pollutans : so2,nox,no2,o3,pm10,pm2.5,co
    private double[] claculationOfAirPollutionLevelShronAndCarmel(stationsShronAndCarmel stationsShronAndCarmel) {
        Log.d("clacShronAndCarmel", "!!!!!!!!");
        double[] allPollutans = new double[7];
        for (int i =0; i<allPollutans.length; i++)
            allPollutans[i] =200; // cant be 200

        if (stationsShronAndCarmel.getSo2().length()>0) {
            double clacSo2 = clacPollutionLevel_so2(stationsShronAndCarmel.getSo2());
            allPollutans[0] = clacSo2;
        }
        if (stationsShronAndCarmel.getNox().length()>0) {
            double clacNox = clacPollutionLevel_nox(stationsShronAndCarmel.getNox());
            allPollutans[1] = clacNox;
        }
        if (stationsShronAndCarmel.getNo2().length()>0) {
            double clacNo2 = clacPollutionLevel_no2(stationsShronAndCarmel.getNo2());
            allPollutans[2] = clacNo2;
        }
        if (stationsShronAndCarmel.getO3().length()>0) {
            double clacO3 = clacPollutionLevel_o3(stationsShronAndCarmel.getO3());
            allPollutans[3]= clacO3 ;
        }
        if (stationsShronAndCarmel.getPm10().length()>0) {
            double clacPm10 = clacPollutionLevel_pm10(stationsShronAndCarmel.getPm10());
            allPollutans[5] = clacPm10;
        }
        if (stationsShronAndCarmel.getPm25().length()>0) {
            double clacPm25 = clacPollutionLevel_pm25(stationsShronAndCarmel.getPm25());
            allPollutans[6] = clacPm25;
        }
        if (stationsShronAndCarmel.getCo().length()>0) {
            double clacCo = clacPollutionLevel_co(stationsShronAndCarmel.getCo());
            allPollutans[4] = clacCo;
        }
        return allPollutans;
    }



    //Southern Coastal Plain pollutans : so2,nox,no2,o3,pm10,pm2.5
    private double[] claculationOfAirPollutionLevelSouthernCoastalPlain(stationsSouthernCoastalPlain stationsSouthernCoastalPlain) {
        Log.d("clacSouthernCoastal", "!!!!!!!");
        double[] allPollutans = new double[7];
        for (int i =0; i<allPollutans.length; i++)
            allPollutans[i] =200; // cant be 200

        if (stationsSouthernCoastalPlain.getSo2().length()>0) {
            double clacSo2 = clacPollutionLevel_so2(stationsSouthernCoastalPlain.getSo2());
            allPollutans[0] = clacSo2;
        }
        if (stationsSouthernCoastalPlain.getNox().length()>0) {
            double clacNox = clacPollutionLevel_nox(stationsSouthernCoastalPlain.getNox());
            allPollutans[1] = clacNox;
        }
        if (stationsSouthernCoastalPlain.getNo2().length()>0) {
            double clacNo2 = clacPollutionLevel_no2(stationsSouthernCoastalPlain.getNo2());
            allPollutans[2] = clacNo2;
        }
        if (stationsSouthernCoastalPlain.getO3().length()>0) {
            double clacO3 = clacPollutionLevel_o3(stationsSouthernCoastalPlain.getO3());
            allPollutans[3]= clacO3 ;
        }
        if (stationsSouthernCoastalPlain.getPm10().length()>0) {
            double clacPm10 = clacPollutionLevel_pm10(stationsSouthernCoastalPlain.getPm10());
            allPollutans[5] = clacPm10;
        }
        if (stationsSouthernCoastalPlain.getPm25().length()>0) {
            double clacPm25 = clacPollutionLevel_pm25(stationsSouthernCoastalPlain.getPm25());
            allPollutans[6] = clacPm25;
        }

        return allPollutans;
    }

    //Tzfon pollutans : so2,nox,no2,o3,pm10,pm2.5
    private double[] claculationOfAirPollutionLevelTzfon(stationsTzfon stationsTzfon) {
        Log.d("clacTzfon", "!!!!!!!");
        double[] allPollutans = new double[7];
        for (int i =0; i<allPollutans.length; i++)
            allPollutans[i] =200; // cant be 200

        if (stationsTzfon.getSo2().length()>0) {
            double clacSo2 = clacPollutionLevel_so2(stationsTzfon.getSo2());
            allPollutans[0] = clacSo2;
        }
        if (stationsTzfon.getNox().length()>0) {
            double clacNox = clacPollutionLevel_nox(stationsTzfon.getNox());
            allPollutans[1] = clacNox;
        }
        if (stationsTzfon.getNo2().length()>0) {
            double clacNo2 = clacPollutionLevel_no2(stationsTzfon.getNo2());
            allPollutans[2] = clacNo2;
        }
        if (stationsTzfon.getO3().length()>0) {
            double clacO3 = clacPollutionLevel_o3(stationsTzfon.getO3());
            allPollutans[3]= clacO3 ;
        }
        if (stationsTzfon.getPm10().length()>0) {
            double clacPm10 = clacPollutionLevel_pm10(stationsTzfon.getPm10());
            allPollutans[5] = clacPm10;
        }
        if (stationsTzfon.getPm25().length()>0) {
            double clacPm25 = clacPollutionLevel_pm25(stationsTzfon.getPm25());
            allPollutans[6] = clacPm25;
        }

        return allPollutans;
    }

    //Yehuda pollutans : so2,nox,no2,o3,pm10,pm2.5
    private double[] claculationOfAirPollutionLevelYehuda(stationsYehuda stationsYehuda) {
        Log.d("clacYehuda", "!!!!!!!");
        double[] allPollutans = new double[7];
        for (int i =0; i<allPollutans.length; i++)
            allPollutans[i] =200; // cant be 200

        if (stationsYehuda.getSo2().length()>0) {
            double clacSo2 = clacPollutionLevel_so2(stationsYehuda.getSo2());
            allPollutans[0] = clacSo2;
        }
        if (stationsYehuda.getNox().length()>0) {
            double clacNox = clacPollutionLevel_nox(stationsYehuda.getNox());
            allPollutans[1] = clacNox;
        }
        if (stationsYehuda.getNo2().length()>0) {
            double clacNo2 = clacPollutionLevel_no2(stationsYehuda.getNo2());
            allPollutans[2] = clacNo2;
        }
        if (stationsYehuda.getO3().length()>0) {
            double clacO3 = clacPollutionLevel_o3(stationsYehuda.getO3());
            allPollutans[3]= clacO3 ;
        }
        if (stationsYehuda.getPm10().length()>0) {
            double clacPm10 = clacPollutionLevel_pm10(stationsYehuda.getPm10());
            allPollutans[5] = clacPm10;
        }
        if (stationsYehuda.getPm25().length()>0) {
            double clacPm25 = clacPollutionLevel_pm25(stationsYehuda.getPm25());
            allPollutans[6] = clacPm25;
        }

        return allPollutans;
    }

    //Ariel pollutans : nox,no2,o3,pm10
    private double[] claculationOfAirPollutionLevelAriel(stationAriel stationAriel) {
        Log.d("clacAriel", "!!!!!!!");
        double[] allPollutans = new double[7];
        for (int i =0; i<allPollutans.length; i++)
            allPollutans[i] =200; // cant be 200


        if (stationAriel.getNox().length()>0) {
            double clacNox = clacPollutionLevel_nox(stationAriel.getNox());
            allPollutans[1] = clacNox;
        }
        if (stationAriel.getNo2().length()>0) {
            double clacNo2 = clacPollutionLevel_no2(stationAriel.getNo2());
            allPollutans[2] = clacNo2;
        }
        if (stationAriel.getO3().length()>0) {
            double clacO3 = clacPollutionLevel_o3(stationAriel.getO3());
            allPollutans[3]= clacO3 ;
        }

        if (stationAriel.getPm10().length()>0) {
            double clacPm10 = clacPollutionLevel_co(stationAriel.getPm10());
            allPollutans[4] = clacPm10;
        }
        return allPollutans;
    }
    //Gush dan pollutans : nox,no2,o3,co,so2,pm10,pm2.5
    private double[] claculationOfAirPollutionLevelGushDen(staitonsGushDan staitonsGushDan) {
        Log.d("clacGushDan", "!!!!!!!");
        double[] allPollutans = new double[7];
        for (int i =0; i<allPollutans.length; i++)
            allPollutans[i] =200; // cant be 200

        if (staitonsGushDan.getSo2().length()>0) {
            double clacSo2 = clacPollutionLevel_so2(staitonsGushDan.getSo2());
            allPollutans[0] = clacSo2;
        }
        if (staitonsGushDan.getNox().length()>0) {
            double clacNox = clacPollutionLevel_nox(staitonsGushDan.getNox());
            allPollutans[1] = clacNox;
        }
        if (staitonsGushDan.getNo2().length()>0) {
            double clacNo2 = clacPollutionLevel_no2(staitonsGushDan.getNo2());
            allPollutans[2] = clacNo2;
        }
        if (staitonsGushDan.getO3().length()>0) {
            double clacO3 = clacPollutionLevel_o3(staitonsGushDan.getO3());
            allPollutans[3]= clacO3 ;
        }
        if (staitonsGushDan.getPm10().length()>0) {
            double clacPm10 = clacPollutionLevel_pm10(staitonsGushDan.getPm10());
            allPollutans[5] = clacPm10;
        }
        if (staitonsGushDan.getPm25().length()>0) {
            double clacPm25 = clacPollutionLevel_pm25(staitonsGushDan.getPm25());
            allPollutans[6] = clacPm25;
            Log.d("pm2", "VVVV");
        }
        if (staitonsGushDan.getCo().length()>0) {
            double clacCo = clacPollutionLevel_co(staitonsGushDan.getCo());
            allPollutans[4] = clacCo;
        }
        Log.d("gushdan", "VVVVV");
        return allPollutans;
    }

    private double clacPollutionLevel_co(String co) {
        Log.d("clacCo", "!!!!!!!");
        /*{cp = Concentrating pollutant ;
        BPhi = The high value of AQI domains (Suitable for pollutant concentration)
        BPlow =The low value of AQI domains (Suitable for cp)
        IChi = The high value of concentrations domains (Suitable for cp)
        IClow =The high value of concentrations domains (Suitable for cp)
         AGI = Air Quality Index*/

        Log.d("clacCo", "!!!!!!!" + co);

           double cp = Double.parseDouble(co);
            Log.d("clac co", "cp = " + cp);
            //Toast.makeText(this, "cp = " + cp + " co", Toast.LENGTH_LONG).show();
        int BPhi = 0, BPlow = 0, IChi = 0, IClow = 0;
        double AQI = 0, ansPollutionLevel_co = 0;
       if (cp<0) cp =0;

        if (0 <= cp && cp <= 26) {
            BPhi = 49;
            BPlow = 0;
            IChi = 26;
            IClow = 0;
        } else if (27 <= cp && cp <= 51) {
            BPhi = 50;
            BPlow = 100;
            IChi = 51;
            IClow = 27;
        } else if (52 <= cp && cp <= 78) {
            BPhi = 101;
            BPlow = 200;
            IChi = 78;
            IClow = 52;
        } else if (79 <= cp && cp <= 104) {
            BPhi = 201;
            BPlow = 300;
            IChi = 104;
            IClow = 79;
        } else if (105 <= cp && cp <= 130) {
            BPhi = 301;
            BPlow = 400;
            IChi = 130;
            IClow = 105;
        } else if (131 <= cp && cp <= 156) {
            BPhi = 500;
            BPlow = 401;
            IChi = 156;
            IClow = 131;
        } else {
            Toast.makeText(this, "Co Not good", Toast.LENGTH_LONG).show();

        }
        Log.d("clacCo", "2222!!!!!!!");

        if (BPhi ==0 || IChi ==0)
            ansPollutionLevel_co = 200;
        else {
            AQI = (BPhi - BPlow) * ((cp - IClow) / (IChi - IClow)) + BPlow;
            ansPollutionLevel_co = 100 - AQI;
            Toast.makeText(this, "Co PollutionLevel" + ansPollutionLevel_co, Toast.LENGTH_LONG).show();
        }
            return ansPollutionLevel_co;

    }


    private double clacPollutionLevel_pm25(String pm25) {
        Log.d("clacPm2.5", "!!!!!!!");
                /*{cp = Concentrating pollutant ;
        BPhi = The high value of AQI domains (Suitable for pollutant concentration)
        BPlow =The low value of AQI domains (Suitable for cp)
        IChi = The high value of concentrations domains (Suitable for cp)
        IClow =The high value of concentrations domains (Suitable for cp)
         AGI = Air Quality Index*/
        //double cp = 0;
       // try {
          double  cp = Double.parseDouble(pm25);
        //} catch (NumberFormatException e) {
            //cp did not contain a vaild double;
        //}
               //double cp = Double.parseDouble(pm25);
                Log.d("clac pm2.5", "cp = " + cp);
                Toast.makeText(this, "cp = " + cp + "pm25", Toast.LENGTH_LONG).show();

          //  } catch (NumberFormatException e) {
                //cp did not contain a vaild double;

        if (cp<0) cp =0;
        double BPhi =0, BPlow =0,IChi=0,IClow=0,AQI =0,ansPollutionLevel_pm25=0;

        if (0<=cp && cp<=18.5){
            BPhi =49;
            BPlow =0;
            IChi =18.5;
            IClow =0;
        }
        else if(18.6<=cp && cp<=37){
            BPhi =50;
            BPlow =100;
            IChi =37;
            IClow =18.5;
        }
        else if (37.1<=cp && cp<=84){
            BPhi =101;
            BPlow =200;
            IChi =84;
            IClow =37.1;
        }
        else if (84.1<=cp && cp<=130){
            BPhi =201;
            BPlow =300;
            IChi =130;
            IClow =84.1;
        }
        else if (130.1<=cp && cp<=165){
            BPhi =301;
            BPlow =400;
            IChi =165;
            IClow =130.1;
        }
        else if (165.1<=cp && cp<=200){
            BPhi =500;
            BPlow =401;
            IChi =200;
            IClow =165.1;
        }
        else {
            Toast.makeText(this,"pm2.5 Not good",Toast.LENGTH_LONG).show();
        }Log.d("clacPm2.5", "22222!!!!!!!" );

        if (BPhi ==0 || IChi ==0)
            ansPollutionLevel_pm25 = 200;
        else {
            AQI = (BPhi - BPlow) * ((cp - IClow) / (IChi - IClow)) + BPlow;
            ansPollutionLevel_pm25 = 100 - AQI;
            Toast.makeText(this, "Pm2.5 PollutionLevel" + ansPollutionLevel_pm25, Toast.LENGTH_LONG).show();
        }Log.d("clacPm2.5", "3333!!!!!!!");
       return ansPollutionLevel_pm25;
        }


    private double clacPollutionLevel_pm10(String pm10) {
        Log.d("clacPm10", "!!!!!!!");
                  /*{cp = Concentrating pollutant ;
        BPhi = The high value of AQI domains (Suitable for pollutant concentration)
        BPlow =The low value of AQI domains (Suitable for cp)
        IChi = The high value of concentrations domains (Suitable for cp)
        IClow =The high value of concentrations domains (Suitable for cp)
         AGI = Air Quality Index*/
        //double cp = 0;

       // try {
         double   cp = Double.parseDouble(pm10);
        //} catch (NumberFormatException e) {
            //cp did not contain a vaild double;
        //}
        // double cp = Double.parseDouble(pm10);
       // Log.d("clac pm10", "cp = " + cp);
        Toast.makeText(this, "cp = " + cp + " pm10", Toast.LENGTH_LONG).show();
        int BPhi =0, BPlow =0,IChi=0,IClow=0;
            double AQI =0,ansPollutionLevel_pm10=0;
        if (cp<0) cp =0;
        if (0<=cp && cp<=65){
            BPhi =49;
            BPlow =0;
            IChi =65;
            IClow =0;
        }
        else if(66<=cp && cp<=129){
            BPhi =50;
            BPlow =100;
            IChi =129;
            IClow =66;
        }
        else if (130<=cp && cp<=215){
            BPhi =101;
            BPlow =200;
            IChi =215;
            IClow =130;
        }
        else if (216<=cp && cp<=300){
            BPhi =201;
            BPlow =300;
            IChi =300;
            IClow =216;
        }
        else if (301<=cp && cp<=355){
            BPhi =301;
            BPlow =400;
            IChi =355;
            IClow =301;
        }
        else if (356<=cp && cp<=400){
            BPhi =500;
            BPlow =401;
            IChi =400;
            IClow =356;
        }
        else {
            Toast.makeText(this,"pm10 Not good",Toast.LENGTH_LONG).show();
        }Log.d("clacPm10", "22222!!!!!!!" );

        if (BPhi ==0 || IChi ==0)
            ansPollutionLevel_pm10 = 200;
        else {
            AQI = (BPhi - BPlow) * ((cp - IClow) / (IChi - IClow)) + BPlow;
            ansPollutionLevel_pm10 = 100 - AQI;
            Toast.makeText(this, "Pm10 PollutionLevel" + ansPollutionLevel_pm10, Toast.LENGTH_LONG).show();
        }
        return ansPollutionLevel_pm10;
    }

    private double clacPollutionLevel_o3(String o3) {
        Log.d("clacO3", "!!!!!!!");
                        /*{cp = Concentrating pollutant ;
        BPhi = The high value of AQI domains (Suitable for pollutant concentration)
        BPlow =The low value of AQI domains (Suitable for cp)
        IChi = The high value of concentrations domains (Suitable for cp)
        IClow =The high value of concentrations domains (Suitable for cp)
         AGI = Air Quality Index*/
        //double cp = 0;

        //try {
          double  cp = Double.parseDouble(o3);
        ///} catch (NumberFormatException e) {
            //cp did not contain a vaild double;
        //}
        if (cp<0) cp =0;
            Toast.makeText(this, "cp = " + cp + " o3", Toast.LENGTH_LONG).show();
            Log.d("clac co", "cp = " + cp);
        int BPhi = 0, BPlow = 0, IChi = 0, IClow = 0;
                double AQI = 0, ansPollutionLevel_o3 = 0;

            if (0 <= cp && cp <= 35) {
                BPhi = 49;
                BPlow = 0;
                IChi = 35;
                IClow = 0;
            } else if (36 <= cp && cp <= 70) {
                BPhi = 50;
                BPlow = 100;
                IChi = 70;
                IClow = 36;
            } else if (71 <= cp && cp <= 97) {
                BPhi = 101;
                BPlow = 200;
                IChi = 97;
                IClow = 71;
            } else if (98 <= cp && cp <= 117) {
                BPhi = 201;
                BPlow = 300;
                IChi = 117;
                IClow = 98;
            } else if (118 <= cp && cp <= 155) {
                BPhi = 301;
                BPlow = 400;
                IChi = 155;
                IClow = 118;
            } else if (156 <= cp && cp <= 188) {
                BPhi = 500;
                BPlow = 401;
                IChi = 188;
                IClow = 156;
            } else {
                Toast.makeText(this, "O3 Not good", Toast.LENGTH_LONG).show();
            }
            Log.d("clacO3", "22222!!!!!!!");

        if (BPhi ==0 || IChi ==0)
            ansPollutionLevel_o3 = 200;
        else {
            AQI = (BPhi - BPlow) * ((cp - IClow) / (IChi - IClow)) + BPlow;
            ansPollutionLevel_o3 = 100 - AQI;
            Toast.makeText(this, "O3 PollutionLevel" + ansPollutionLevel_o3, Toast.LENGTH_LONG).show();
        }
            return ansPollutionLevel_o3;
            }


    private double clacPollutionLevel_no2(String no2) {
        Log.d("clacNo2", "!!!!!!!");
                         /*{cp = Concentrating pollutant ;
        BPhi = The high value of AQI domains (Suitable for pollutant concentration)
        BPlow =The low value of AQI domains (Suitable for cp)
        IChi = The high value of concentrations domains (Suitable for cp)
        IClow =The high value of concentrations domains (Suitable for cp)
         AGI = Air Quality Index}*/
        //if(!(no2.equals("")) ) {
       // double cp = 0;

        //try {
          double  cp = Double.parseDouble(no2);
        //} catch (NumberFormatException e) {
            //cp did not contain a vaild double;
       // }
            Toast.makeText(this, "cp = " + cp + " no2", Toast.LENGTH_LONG).show();
            Log.d("clac no2", "cp = " + cp);

    int BPhi = 0, BPlow = 0, IChi = 0, IClow = 0;
            double AQI = 0, ansPollutionLevel_no2 = 0;
        if (cp<0) cp =0;

            if (0 <= cp && cp <= 53) {
                BPhi = 49;
                BPlow = 0;
                IChi = 53;
                IClow = 0;
            } else if (54 <= cp && cp <= 105) {
                BPhi = 50;
                BPlow = 100;
                IChi = 105;
                IClow = 54;
            } else if (106 <= cp && cp <= 160) {
                BPhi = 101;
                BPlow = 200;
                IChi = 160;
                IClow = 106;
            } else if (161 <= cp && cp <= 213) {
                BPhi = 201;
                BPlow = 300;
                IChi = 213;
                IClow = 161;
            } else if (214 <= cp && cp <= 260) {
                BPhi = 301;
                BPlow = 400;
                IChi = 260;
                IClow = 214;
            } else if (261 <= cp && cp <= 316) {
                BPhi = 500;
                BPlow = 401;
                IChi = 316;
                IClow = 261;
            } else {
                Toast.makeText(this, "No2 Not good", Toast.LENGTH_LONG).show();
            }
        Log.d("clacNo2", "222222!!!!!!!");

        if (BPhi ==0 || IChi ==0)
            ansPollutionLevel_no2 = 200;
        else {
            AQI = (BPhi - BPlow) * ((cp - IClow) / (IChi - IClow)) + BPlow;
            ansPollutionLevel_no2 = 100 - AQI;
            Log.d("clacNo2", "3333!!!!!!!");
            Toast.makeText(this, "NO2 PollutionLevel" + ansPollutionLevel_no2, Toast.LENGTH_LONG).show();
        }
            return ansPollutionLevel_no2;
        }


    private double clacPollutionLevel_nox(String nox) {
        Log.d("clacNox", "!!!!!!!");
                               /*{cp = Concentrating pollutant ;
        BPhi = The high value of AQI domains (Suitable for pollutant concentration)
        BPlow =The low value of AQI domains (Suitable for cp)
        IChi = The high value of concentrations domains (Suitable for cp)
        IClow =The high value of concentrations domains (Suitable for cp)
         AGI = Air Quality Index}*/

       // double cp = 0;

       // try {
         double   cp = Double.parseDouble(nox);
        //} catch (NumberFormatException e) {
            //cp did not contain a vaild double;
        //}
            // double cp = Double.parseDouble(nox);
            Toast.makeText(this, "cp = " + cp + " nox", Toast.LENGTH_LONG).show();
            Log.d("clac nox", "cp = " + cp);

            int BPhi =0, BPlow =0,IChi=0,IClow=0;
            double AQI =0,ansPollutionLevel_nox=0;
        if (cp<0) cp =0;
        if (0<=cp && cp<=250){
            BPhi =49;
            BPlow =0;
            IChi =250;
            IClow =0;
        }
        else if(251<=cp && cp<=499){
            BPhi =50;
            BPlow =100;
            IChi =499;
            IClow =251;
        }
        else if (500<=cp && cp<=750){
            BPhi =101;
            BPlow =200;
            IChi =750;
            IClow =500;
        }
        else if (751<=cp && cp<=1000){
            BPhi =201;
            IChi =1000;
            IClow =751;
        }
        else if (1001 <=cp && cp<=1200){
            BPhi =301;
            BPlow =400;
            IChi =1200;
            IClow =1001;
        }
        else if (1201 <=cp && cp<=1400){
            BPhi =500;
            BPlow =401;
            IChi =1400;
            IClow =1201;
        }
        else {
            Toast.makeText(this,"Nox Not good",Toast.LENGTH_LONG).show();
        } Log.d("clacNox", "2222!!!!!!!" );

        if (BPhi ==0 || IChi ==0)
            ansPollutionLevel_nox = 200;
        else {
            AQI = (BPhi - BPlow) * ((cp - IClow) / (IChi - IClow)) + BPlow;
            ansPollutionLevel_nox = 100 - AQI;
            Toast.makeText(this, "Nox PollutionLevel" + ansPollutionLevel_nox, Toast.LENGTH_LONG).show();
        }
        return ansPollutionLevel_nox;
        }


    private double clacPollutionLevel_so2(String so2) {
        Log.d("clacSo2", "!!!!!!!");
                                 /*{cp = Concentrating pollutant ;
        BPhi = The high value of AQI domains (Suitable for pollutant concentration)
        BPlow =The low value of AQI domains (Suitable for cp)
        IChi = The high value of concentrations domains (Suitable for cp)
        IClow =The high value of concentrations domains (Suitable for cp)
         AGI = Air Quality Index}*/
        //double cp = 0;

        //try {
          double  cp = Double.parseDouble(so2);
        //} catch (NumberFormatException e) {
            //cp did not contain a vaild double;
        //}
            //double cp = Double.parseDouble(so2);
            Toast.makeText(this, "cp = " + cp + " so2", Toast.LENGTH_LONG).show();
            Log.d("clac so2", "cp = " + cp);


        int BPhi =0, BPlow =0,IChi=0,IClow=0;
            double AQI =0,ansPollutionLevel_so2=0;
        if (cp<0) cp =0;
        if (0<=cp && cp<=67){
            BPhi =49;
            BPlow =0;
            IChi =67;
            IClow =0;
        }
        else if(68<=cp && cp<=133){
            BPhi =50;
            BPlow =100;
            IChi =133;
            IClow =68;
        }
        else if (134<=cp && cp<=163){
            BPhi =101;
            BPlow =200;
            IChi =163;
            IClow =134;
        }
        else if (164<=cp && cp<=191){
            BPhi =201;
            BPlow =300;
            IChi =191;
            IClow =161;
        }
        else if (192<=cp && cp<=253){
            BPhi =301;
            BPlow =400;
            IChi =253;
            IClow =192;
        }
        else if (254 <=cp && cp<=303){
            BPhi =500;
            BPlow =401;
            IChi =303;
            IClow =254;
        }
        else {
            Toast.makeText(this,"No2 Not good",Toast.LENGTH_LONG).show();
        }Log.d("clacSo2", "!2222!!!!!!" );

        if (BPhi ==0 || IChi ==0)
            ansPollutionLevel_so2 = 200;
        else {
            AQI = (BPhi - BPlow) * ((cp - IClow) / (IChi - IClow)) + BPlow;
            ansPollutionLevel_so2 = 100 - AQI;
            Toast.makeText(this, "Nox PollutionLevel" + ansPollutionLevel_so2, Toast.LENGTH_LONG).show();
        }
        Log.d("clacSo2", "33333!!!!!!!" );
        return ansPollutionLevel_so2;
        }

////// find the nearest of any station
    public String nearestOfNearestStation (String stationName) throws  IOException {
        Log.d("find nearstOfnearest ","V");
        double tempShortDistance=0;
        double currentShortDistance=0;
        double lat1 =0,long1 = 0;
        String nearestStatitan = stationName;
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

                if (tempShortDistance < currentShortDistance && !(stationArray.get(j).getStation().equals(nearestStatitan ))) {
                    currentShortDistance = tempShortDistance;
                    nearestOfNearestStatitan = stationArray.get(j).getStation();
                }
            }

        return nearestOfNearestStatitan;
    }
    public String NearestOfnearest2Station (String stationName) throws  IOException {
        Log.d("find nearstOfnearest ","V");
        double tempShortDistance=0;
        double currentShortDistance=0;
        double lat1 =0,long1 = 0;
        String nearestStatitan = stationName;
        String nearestOfNearest = nearestOfNearestStation(stationName);
        String nearestOfNearest2Statitan="Y";
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

                if (tempShortDistance < currentShortDistance && !(stationArray.get(j).getStation().equals(nearestStatitan)
                                                            && !stationArray.get(j).getStation().equals(nearestOfNearest) )) {
                    currentShortDistance = tempShortDistance;
                    nearestOfNearest2Statitan = stationArray.get(j).getStation();
                }
            }

        return nearestOfNearest2Statitan;
    }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
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
    }


    ////////////////

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    public void gotoScreen1(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void gotoCurrentLoction(View view) {
        Intent intent = new Intent(this, CurrentLocation.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}