package com.example.shirley.myapplication5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static List<StationSample> stationArray = new ArrayList<>();
    public static List<staitonsGushDan> staitonsGushDenList =new ArrayList<>();
    public static List<stationEilat> stationEilatList = new ArrayList<>();
    public static List<stationsNorthernNegev> stationsNorthernNegevList = new ArrayList<>();
    public static List<stationsSouthernCoastalPlain> stationsSouthernCoastalPlainList = new ArrayList<>();
    public static List<stationsTzfon> stationsTzfonList = new ArrayList<>();
    public static List<stationsYehuda> stationsYehudaList = new ArrayList<>();
    public static List<stationsHaifaAndKrayot> stationsHaifaAndKrayotList = new ArrayList<>();
    public static List<stationAriel> stationArielList = new ArrayList<>();
    public static List<stationsInnerShefla> stationsInnerSheflaList = new ArrayList<>();
    public static List<stationsMobilty> stationsMobiltyList = new ArrayList<>();
    public static List<stationsJezreelVally> stationsJezreelVallyList = new ArrayList<>();
    public static List<stationsShronAndCarmel> stationsShronAndCarmelsList = new ArrayList<>();
    public static List<stationsJerusalem> stationsJerusalemList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        readStationLocData();
        readStaitonsGushDanData();
        readStaitonsArielData();
        readStaitonsEilatData();
        readStaitonsHaifaAndKrayotData();
        readStaitonsInnerSheflaData();
        readStaitonsJerusalemData();
        readStaitonsJezreelVallyData();
        readStaitonsMobiltyData();
        readStaitonsNorthernNegevData();
        readStaitonsShronAndCarmelData();
        readStaitonsSouthernCoastalPlainData();
        readStaitonsTzfonData();
        readStaitonsYehudaData();
    }

    //menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                aboutUs();
                return true;
            case R.id.showWebPage:
                showWebPage();
                return true;
            case R.id.history:
                history();
                return true;
            case R.id.contact:
                contact();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void aboutUs() {
            Intent intent = new Intent(this, AboutUs.class);
            startActivity(intent);

    }

    private void showWebPage() {
        // Prepare Intent to open URL
        String url = "https://shirleybareket22.wixsite.com/pro-lironshirshirley";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        // Start Intent
        startActivity(intent);
    }

    private void history() {
    }

    private void contact() {
    }

    //btn onclick
    public void findCurrentLoction(View view) {
        Intent intent = new Intent(this, CurrentLocation.class);
        startActivity(intent);
    }

    public void searchAddress(View view) {
        Intent intent = new Intent(this, SearchAddress.class);
        startActivity(intent);
    }
    public void buildTrack(View view){
        Intent intent = new Intent(this, BuildingRunningTrack.class);
        startActivity(intent);
    }


    //read data fron csv files
    private void readStationLocData() {
        InputStream is = getResources().openRawResource(R.raw.mainstations);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {

            while ((line = reader.readLine()) != null) {
                StationSample sample = new StationSample();
                //Split by ','
                String[] tokens = line.split(",");
                //read data
                sample.setLatitude(tokens[2]);
                sample.setLongitude(tokens[3]);
                sample.setStation(tokens[1]);
                sample.setId(tokens[0]);

                stationArray.add(sample);

                Log.d("databasetest", "Just created:" + sample);
                //Log.d("databasetest", "id =" + sample.getId());
                //Log.d("databasetest", "s =" + sample.getStation());
                //Log.d("databasetest", "lng =" + sample.getlongitude());
                //Log.d("databasetest", "lat =" + sample.getlatitude());
                // Toast.makeText(this,"this is :"+ sample.toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Log.d("databasetest", "Error reading data" + line, e);
            e.printStackTrace();
        }
    }

    private void readStaitonsEilatData() {
        InputStream is = getResources().openRawResource(R.raw.stationseilat);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {

            while ((line = reader.readLine()) != null) {
                stationEilat sample = new stationEilat();
                //Split by ','
                String[] tokens = line.split(",");
                //read data
                if (tokens[13].length() > 0) {
                    sample.setStation(tokens[13]);
                } else {
                    sample.setStation("");
                }
                if (tokens[12].length() > 0) {
                    sample.setTime(tokens[12]);
                } else {
                    sample.setTime("");
                }
                if (tokens[11].length() > 0) {
                    sample.setSo2(tokens[11]);
                } else {
                    sample.setSo2("");
                }
                if (tokens[10].length() > 0) {
                    sample.setNox(tokens[10]);
                } else {
                    sample.setNox("");
                }
                if (tokens[9].length() > 0) {
                    sample.setNo2(tokens[9]);
                } else {
                    sample.setNo2("");
                }
                if (tokens[8].length() > 0) {
                    sample.setNo(tokens[8]);
                } else {
                    sample.setNo("");
                }
                if (tokens[7].length() > 0) {
                    sample.setO3(tokens[7]);
                } else {
                    sample.setO3("");
                }
                if (tokens[6].length() > 0) {
                    sample.setTsp(tokens[6]);
                } else {
                    sample.setTsp("");
                }
                if (tokens[5].length() > 0) {
                    sample.setWs(tokens[5]);
                } else {
                    sample.setWs("");
                }
                if (tokens[4].length() > 0) {
                    sample.setWd(tokens[4]);
                } else {
                    sample.setWs("");
                }
                if (tokens[3].length() > 0) {
                    sample.setTemp(tokens[3]);
                } else {
                    sample.setTemp("");
                }
                if (tokens[2].length() > 0) {
                    sample.setRh(tokens[2]);
                } else {
                    sample.setRh("");
                }
                if (tokens[1].length() > 0) {
                    sample.setGsr(tokens[1]);
                } else {
                    sample.setGsr("");
                }
                if (tokens[0].length() > 0) {
                    sample.setPm10(tokens[0]);
                } else {
                    sample.setPm10("");
                }

                stationEilatList.add(sample);

                Log.d("Eilat", "Just created:" + sample);
            }
        } catch (IOException e) {
            Log.d("Eilat", "Error reading data" + line, e);
            e.printStackTrace();
        }

    }
    ///////
    private void readStaitonsNorthernNegevData() {
        InputStream is = getResources().openRawResource(R.raw.stationsnorthernngev);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {

            while ((line = reader.readLine()) != null) {
                stationsNorthernNegev sample = new stationsNorthernNegev();
                //Split by ','
                String[] tokens = line.split(",");
                //read data

                if (tokens[16].length() > 0) {
                    sample.setStation(tokens[16]);
                } else {
                    sample.setStation("");
                }
                if (tokens[15].length() > 0) {
                    sample.setTime(tokens[15]);
                } else {
                    sample.setTime("");
                }
                if (tokens[14].length() > 0) {
                    sample.setSo2(tokens[14]);
                } else {
                    sample.setSo2("");
                }
                if (tokens[13].length() > 0) {
                    sample.setNox(tokens[13]);
                } else {
                    sample.setNox("");
                }
                if (tokens[12].length() > 0) {
                    sample.setNo2(tokens[12]);
                } else {
                    sample.setNo2("");
                }
                if (tokens[11].length() > 0) {
                    sample.setNo(tokens[11]);
                } else {
                    sample.setNo("");
                }
                if (tokens[10].length() > 0) {
                    sample.setO3(tokens[10]);
                } else {
                    sample.setO3("");
                }
                if (tokens[9].length() > 0) {
                    sample.setPm10(tokens[9]);
                } else {
                    sample.setPm10("");
                }
                if (tokens[8].length() > 0) {
                    sample.setPm25(tokens[8]);
                } else {
                    sample.setPm25("");
                }
                if (tokens[7].length() > 0) {
                    sample.setBenzen(tokens[7]);
                } else {
                    sample.setBenzen("");
                }
                if (tokens[6].length() > 0) {
                    sample.setToulen(tokens[6]);
                } else {
                    sample.setToulen("");
                }
                if (tokens[5].length() > 0) {
                    sample.setWs(tokens[5]);
                } else {
                    sample.setWs("");
                }
                if (tokens[4].length() > 0) {
                    sample.setWd(tokens[4]);
                } else {
                    sample.setWd("");
                }
                if (tokens[3].length() > 0) {
                    sample.setTemp(tokens[3]);
                } else {
                    sample.setTemp("");
                }
                if (tokens[2].length() > 0) {
                    sample.setRh(tokens[2]);
                } else {
                    sample.setRh("");
                }
                if (tokens[1].length() > 0) {
                    sample.setGsr(tokens[1]);
                } else {
                    sample.setGsr("");
                }
                if (tokens[0].length() > 0) {
                    sample.setRain(tokens[0]);
                } else {
                    sample.setRain("");
                }

                stationsNorthernNegevList.add(sample);

                Log.d("NorthernNegev", "Just created:" + sample);
            }
        } catch (IOException e) {
            Log.d("NorthernNegev", "Error reading data" + line, e);
            e.printStackTrace();
        }

    }
    //////////////////
    private void readStaitonsMobiltyData() {
        InputStream is = getResources().openRawResource(R.raw.stationsmobilty);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {

            while ((line = reader.readLine()) != null) {
                stationsMobilty sample = new stationsMobilty();
                //Split by ','
                String[] tokens = line.split(",");
                //read data

                if (tokens[15].length() > 0) {
                    sample.setStation(tokens[15]);
                } else {
                    sample.setStation("");
                }
                if (tokens[14].length() > 0) {
                    sample.setTime(tokens[14]);
                } else {
                    sample.setTime("");
                }
                if (tokens[13].length() > 0) {
                    sample.setSo2(tokens[13]);
                } else {
                    sample.setSo2("");
                }
                if (tokens[12].length() > 0) {
                    sample.setNox(tokens[12]);
                } else {
                    sample.setNox("");
                }
                if (tokens[11].length() > 0) {
                    sample.setNo2(tokens[11]);
                } else {
                    sample.setNo2("");
                }
                if (tokens[10].length() > 0) {
                    sample.setNo(tokens[10]);
                } else {
                    sample.setNo("");
                }
                if (tokens[9].length() > 0) {
                    sample.setO3(tokens[9]);
                } else {
                    sample.setO3("");
                }
                if (tokens[8].length() > 0) {
                    sample.setCo(tokens[8]);
                } else {
                    sample.setCo("");
                }
                if (tokens[7].length() > 0) {
                    sample.setPm10(tokens[7]);
                } else {
                    sample.setPm10("");
                }
                if (tokens[6].length() > 0) {
                    sample.setPm25(tokens[6]);
                } else {
                    sample.setPm25("");
                }
                if (tokens[5].length() > 0) {
                    sample.setPm1(tokens[5]);
                } else {
                    sample.setPm1("");
                }
                if (tokens[4].length() > 0) {
                    sample.setBenzen(tokens[4]);
                } else {
                    sample.setBenzen("");
                }
                if (tokens[3].length() > 0) {
                    sample.setToulen(tokens[3]);
                } else {
                    sample.setToulen("");
                }
                if (tokens[2].length() > 0) {
                    sample.setH2s(tokens[2]);
                } else {
                    sample.setH2s("");
                }
                if (tokens[1].length() > 0) {
                    sample.setWd(tokens[1]);
                } else {
                    sample.setWd("");
                }
                if (tokens[0].length() > 0) {
                    sample.setWs(tokens[0]);
                } else {
                    sample.setWs("");
                }

                stationsMobiltyList.add(sample);

                Log.d("Mobilty", "Just created:" + sample);
            }
        } catch (IOException e) {
            Log.d("Mobilty", "Error reading data" + line, e);
            e.printStackTrace();
        }

    }
    /////////////
    private void readStaitonsSouthernCoastalPlainData() {
        InputStream is = getResources().openRawResource(R.raw.stationssoutherncastalplain);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {

            while ((line = reader.readLine()) != null) {
                stationsSouthernCoastalPlain sample = new stationsSouthernCoastalPlain();
                //Split by ','
                String[] tokens = line.split(",");
                //read data

                if (tokens[15].length() > 0) {
                    sample.setStation(tokens[15]);
                } else {
                    sample.setStation("");
                }
                if (tokens[14].length() > 0) {
                    sample.setTime(tokens[14]);
                } else {
                    sample.setTime("");
                }
                if (tokens[13].length() > 0) {
                    sample.setSo2(tokens[13]);
                } else {
                    sample.setSo2("");
                }
                if (tokens[12].length() > 0) {
                    sample.setNox(tokens[12]);
                } else {
                    sample.setNox("");
                }
                if (tokens[11].length() > 0) {
                    sample.setNo2(tokens[11]);
                } else {
                    sample.setNo2("");
                }
                if (tokens[10].length() > 0) {
                    sample.setNo(tokens[10]);
                } else {
                    sample.setNo("");
                }
                if (tokens[9].length() > 0) {
                    sample.setO3(tokens[9]);
                } else {
                    sample.setO3("");
                }
                if (tokens[8].length() > 0) {
                    sample.setPm10((tokens[8]));
                } else {
                    sample.setPm10("");
                }
                if (tokens[7].length() > 0) {
                    sample.setPm25(tokens[7]);
                } else {
                    sample.setPm25("");
                }
                if (tokens[6].length() > 0) {
                    sample.setBenzen(tokens[6]);
                } else {
                    sample.setBenzen("");
                }
                if (tokens[5].length() > 0) {
                    sample.setToulen(tokens[5]);
                } else {
                    sample.setToulen("");
                }
                if (tokens[4].length() > 0) {
                    sample.setWs(tokens[4]);
                } else {
                    sample.setWs("");
                }
                if (tokens[3].length() > 0) {
                    sample.setWd(tokens[3]);
                } else {
                    sample.setWd("");
                }
                if (tokens[2].length() > 0) {
                    sample.setTemp(tokens[2]);
                } else {
                    sample.setTemp("");
                }
                if (tokens[1].length() > 0) {
                    sample.setRh(tokens[1]);
                } else {
                    sample.setRh("");
                }
                if (tokens[0].length() > 0) {
                    sample.setRain(tokens[0]);
                } else {
                    sample.setRain("");
                }

                stationsSouthernCoastalPlainList.add(sample);

                Log.d("outhernCoastalPlain", "Just created:" + sample);

            }
        } catch (IOException e) {
            Log.d("outhernCoastalPlain", "Error reading data" + line, e);
            e.printStackTrace();
        }

    }

    /////////////////

    private void readStaitonsYehudaData() {
        InputStream is = getResources().openRawResource(R.raw.stationsyehuda);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {

            while ((line = reader.readLine()) != null) {
                stationsYehuda sample = new stationsYehuda();
                //Split by ','
                String[] tokens = line.split(",");
                //read data

                if (tokens[14].length() > 0) {
                    sample.setStation(tokens[14]);
                } else {
                    sample.setStation("");
                }
                if (tokens[13].length() > 0) {
                    sample.setTime(tokens[13]);
                } else {
                    sample.setTime("");
                }
                if (tokens[12].length() > 0) {
                    sample.setSo2(tokens[12]);
                } else {
                    sample.setSo2("");
                }
                if (tokens[11].length() > 0) {
                    sample.setNox(tokens[11]);
                } else {
                    sample.setNox("");
                }
                if (tokens[10].length() > 0) {
                    sample.setNo2((tokens[10]));
                } else {
                    sample.setNo2("");
                }
                if (tokens[9].length() > 0) {
                    sample.setNo(tokens[9]);
                } else {
                    sample.setNo("");
                }
                if (tokens[8].length() > 0) {
                    sample.setO3(tokens[8]);
                } else {
                    sample.setO3("");
                }
                if (tokens[7].length() > 0) {
                    sample.setPm10(tokens[7]);
                } else {
                    sample.setPm10("");
                }
                if (tokens[6].length() > 0) {
                    sample.setPm25(tokens[6]);
                } else {
                    sample.setPm25("");
                }
                if (tokens[5].length() > 0) {
                    sample.setTsp(tokens[5]);
                } else {
                    sample.setTsp("");
                }
                if (tokens[4].length() > 0) {
                    sample.setWs(tokens[4]);
                } else {
                    sample.setWs("");
                }
                if (tokens[3].length() > 0) {
                    sample.setWd(tokens[3]);
                } else {
                    sample.setWd("");
                }
                if (tokens[2].length() > 0) {
                    sample.setTemp(tokens[2]);
                } else {
                    sample.setTemp("");
                }
                if (tokens[1].length() > 0) {
                    sample.setRh(tokens[1]);
                } else {
                    sample.setRh("");
                }
                if (tokens[0].length() > 0) {
                    sample.setGsr(tokens[0]);
                } else {
                    sample.setGsr("");
                }

                stationsYehudaList.add(sample);

                Log.d("Yehuda", "Just created:" + sample);

            }
        } catch (IOException e) {
            Log.d("Yehuda", "Error reading data" + line, e);
            e.printStackTrace();
        }

    }

///////////////////

private void readStaitonsJerusalemData() {
    InputStream is = getResources().openRawResource(R.raw.stationsjerusalem);
    BufferedReader reader = new BufferedReader(
            new InputStreamReader(is, Charset.forName("UTF-8"))
    );

    String line = "";
    try {

        while ((line = reader.readLine()) != null) {
            stationsJerusalem sample = new stationsJerusalem();
            //Split by ','
            String[] tokens = line.split(",");
            //read data

            if (tokens[16].length() > 0) {
                sample.setStation(tokens[16]);
            } else {
                sample.setStation("");
            }
            if (tokens[15].length() > 0) {
                sample.setTime(tokens[15]);
            } else {
                sample.setTime("");
            }
            if (tokens[14].length() > 0) {
                sample.setSo2(tokens[14]);
            } else {
                sample.setSo2("");
            }
            if (tokens[13].length() > 0) {
                sample.setNox(tokens[13]);
            } else {
                sample.setNox("");
            }
            if (tokens[12].length() > 0) {
                sample.setNo2(tokens[12]);
            } else {
                sample.setNo2("");
            }
            if (tokens[11].length() > 0) {
                sample.setNo(tokens[11]);
            } else {
                sample.setNo("");
            }
            if (tokens[10].length() > 0) {
                sample.setO3(tokens[10]);
            } else {
                sample.setO3("");
            }
            if (tokens[9].length() > 0) {
                sample.setCo(tokens[9]);
            } else {
                sample.setCo("");
            }
            if (tokens[8].length() > 0) {
                sample.setPm10(tokens[8]);
            } else {
                sample.setPm10("");
            }
            if (tokens[7].length() > 0) {
                sample.setPm25(tokens[7]);
            } else {
                sample.setPm25("");
            }
            if (tokens[6].length() > 0) {
                sample.setBenzen(tokens[6]);
            } else {
                sample.setBenzen("");
            }
            if (tokens[5].length() > 0) {
                sample.setToulen(tokens[5]);
            } else {
                sample.setToulen("");
            }
            if (tokens[4].length() > 0) {
                sample.setWs(tokens[4]);
            } else {
                sample.setWs("");
            }
            if (tokens[3].length() > 0) {
                sample.setWd(tokens[3]);
            } else {
                sample.setWd("");
            }
            if (tokens[2].length() > 0) {
                sample.setTemp(tokens[2]);
            } else {
                sample.setTemp("");
            }
            if (tokens[1].length() > 0) {
                sample.setRh(tokens[1]);
            } else {
                sample.setRh("");
            }
            if (tokens[0].length() > 0) {
                sample.setRain(tokens[0]);
            } else {
                sample.setRain("");
            }

            stationsJerusalemList.add(sample);

            Log.d("Jerusalem", "Just created:" + sample);

        }
    } catch (IOException e) {
        Log.d("Jerusalem", "Error reading data" + line, e);
        e.printStackTrace();
    }
}

    private void readStaitonsGushDanData() {
        InputStream is = getResources().openRawResource(R.raw.gushdanstations);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {

            while ((line = reader.readLine()) != null) {
                staitonsGushDan sample = new staitonsGushDan();
                //Split by ','
                String[] tokens = line.split(",");
                //read data


                if (tokens[16].length() > 0) {
                        sample.setStation(tokens[16]);
                    } else {
                        sample.setStation("");
                    }

                    if (tokens[15].length() > 0) {
                        sample.setTime(tokens[15]);
                    } else {
                        sample.setTime("");
                    }
                    if (tokens[14].length() > 0) {
                        sample.setSo2(tokens[14]);
                    } else {
                        sample.setSo2("");
                    }
                    if (tokens[13].length() > 0) {
                        sample.setNox(tokens[13]);
                    } else {
                        sample.setNox("");
                    }
                    if (tokens[12].length() > 0) {
                        sample.setNo2(tokens[12]);
                    } else {
                        sample.setNo2("");
                    }
                    if (tokens[11].length() > 0) {
                        sample.setNo(tokens[11]);
                    } else {
                        sample.setNo("");
                    }
                    if (tokens[10].length() > 0) {
                        sample.setO3(tokens[10]);
                    } else {
                        sample.setO3("");
                    }
                    if (tokens[9].length() > 0) {
                        sample.setCo(tokens[9]);
                    } else {
                        sample.setCo("");
                    }
                    if (tokens[8].length() > 0) {
                        sample.setPm10(tokens[8]);
                    } else {
                        sample.setPm10("");
                    }
                    if (tokens[7].length() > 0) {
                        sample.setPm25(tokens[7]);
                    } else {
                        sample.setPm25("");
                    }
                    if (tokens[6].length() > 0) {
                        sample.setPm1(tokens[6]);
                    } else {
                        sample.setPm1("");
                    }

                    if (tokens[5].length() > 0) {
                        sample.setWs(tokens[5]);
                    } else {
                        sample.setWs("");
                    }
                    if (tokens[4].length() > 0) {
                        sample.setWd(tokens[4]);
                    } else {
                        sample.setWd("");
                    }
                    if (tokens[3].length() > 0) {
                        sample.setTemp(tokens[3]);
                    } else {
                        sample.setTemp("");
                    }
                    if (tokens[2].length() > 0) {
                        sample.setRh(tokens[2]);
                    } else {
                        sample.setRh("");
                    }
                    if ( tokens[1].length() > 0) {
                        sample.setGsr(tokens[1]);
                    } else {
                        sample.setGsr("");
                    }
                    if (tokens[0].length() > 0) {
                        sample.setRain(tokens[0]);
                    } else {
                        sample.setRain("");

                }
                staitonsGushDenList.add(sample);
                //תחנה,תאריך ושעה,SO2,Nox,No2,No,O3,CO,PM10,PM2.5,PM1,WS,WD,Temp,RH,GSR,Rain
                Log.d("GushDen", "Just created:" + sample);
                /*Log.d("GushDen", "s =" + sample.getStation());
                Log.d("GushDen", "time =" + sample.getTime());
                Log.d("GushDen", "so2 =" + sample.getSo2());
                Log.d("GushDen", "nox =" + sample.getNox());
                Log.d("GushDen", "no =" + sample.getNo());
                Log.d("GushDen", "o3 =" + sample.getO3());
                Log.d("GushDen", "co =" + sample.getCo());
                Log.d("GushDen", "pm10 =" + sample.getPm10());
                Log.d("GushDen", "pm25 =" + sample.getPm25());
                Log.d("GushDen", "pm1 =" + sample.getPm1());
                Log.d("GushDen", "ws =" + sample.getWs());
                Log.d("GushDen", "wd =" + sample.getWd());
                Log.d("GushDen", "temp =" + sample.getTemp());
                Log.d("GushDen", "rh =" + sample.getRh());
                Log.d("GushDen", "gsr =" + sample.getGsr());
                Log.d("GushDen", "rain =" + sample.getRain());
                //Log.d("databasetest", "s =" + sample.getStation());
                //Log.d("databasetest", "lng =" + sample.getlongitude());
                //Log.d("databasetest", "lat =" + sample.getlatitude());
                */
                // Toast.makeText(this,"this is :"+ sample.toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Log.d("GushDen", "Error reading data" + line, e);
            e.printStackTrace();
        }
    }
/////////////////
private void readStaitonsInnerSheflaData() {
    InputStream is = getResources().openRawResource(R.raw.stationsinnershefla);
    BufferedReader reader = new BufferedReader(
            new InputStreamReader(is, Charset.forName("UTF-8"))
    );

    String line = "";
    try {

        while ((line = reader.readLine()) != null) {
            stationsInnerShefla sample = new stationsInnerShefla();
            //Split by ','
            String[] tokens = line.split(",");
            //read data

            if (tokens[14].length() > 0) {
                sample.setStation(tokens[14]);
            } else {
                sample.setStation("");
            }
            if (tokens[13].length() > 0) {
                sample.setTime(tokens[13]);
            } else {
                sample.setTime("");
            }
            if (tokens[12].length() > 0) {
                sample.setSo2(tokens[12]);
            } else {
                sample.setSo2("");
            }
            if (tokens[11].length() > 0) {
                sample.setNox(tokens[11]);
            } else {
                sample.setNox("");
            }
            if (tokens[10].length() > 0) {
                sample.setNo2(tokens[10]);
            } else {
                sample.setNo2("");
            }
            if (tokens[9].length() > 0) {
                sample.setNo(tokens[9]);
            } else {
                sample.setNo("");
            }
            if (tokens[8].length() > 0) {
                sample.setO3(tokens[8]);
            } else {
                sample.setO3("");
            }

            if (tokens[7].length() > 0) {
                sample.setPm10(tokens[7]);
            } else {
                sample.setPm10("");
            }
            if (tokens[6].length() > 0) {
                sample.setPm25(tokens[6]);
            } else {
                sample.setPm25("");
            }

            if (tokens[5].length() > 0) {
                sample.setWs(tokens[5]);
            } else {
                sample.setWs("");
            }
            if (tokens[4].length() > 0) {
                sample.setWd(tokens[4]);
            } else {
                sample.setWd("");
            }
            if (tokens[3].length() > 0) {
                sample.setTemp(tokens[3]);
            } else {
                sample.setTemp("");
            }
            if (tokens[2].length() > 0) {
                sample.setRh(tokens[2]);
            } else {
                sample.setRh("");
            }
            if (tokens[1].length() > 0) {
                sample.setGsr(tokens[1]);
            } else {
                sample.setGsr("");
            }
            if (tokens[0].length() > 0) {
                sample.setRain(tokens[0]);
            } else {
                sample.setRain("");
            }

            stationsInnerSheflaList.add(sample);

            Log.d("InnerShefla", "Just created:" + sample);

        }
    } catch (IOException e) {
        Log.d("InnerShefla", "Error reading data" + line, e);
        e.printStackTrace();
    }
}
//////////

private void readStaitonsArielData() {
    InputStream is = getResources().openRawResource(R.raw.stationsariel);
    BufferedReader reader = new BufferedReader(
            new InputStreamReader(is, Charset.forName("UTF-8"))
    );

    String line = "";
    try {

        while ((line = reader.readLine()) != null) {
            stationAriel sample = new stationAriel();
            //Split by ','
            String[] tokens = line.split(",");
            //read data

            if (tokens[6].length() > 0) {
                sample.setStation(tokens[6]);
            } else {
                sample.setStation("");
            }
            if (tokens[5].length() > 0) {
                sample.setTime(tokens[5]);
            } else {
                sample.setTime("");
            }

            if (tokens[4].length() > 0) {
                sample.setNox(tokens[4]);
            } else {
                sample.setNox("");
            }
            if (tokens[3].length() > 0) {
                sample.setNo2(tokens[3]);
            } else {
                sample.setNo2("");
            }
            if (tokens[2].length() > 0) {
                sample.setNo(tokens[2]);
            } else {
                sample.setNo("");
            }
            if (tokens[1].length() > 0) {
                sample.setO3(tokens[1]);
            } else {
                sample.setO3("");
            }

            if (tokens[0].length() > 0) {
                sample.setPm10(tokens[0]);
            } else {
                sample.setPm10("");
            }
                stationArielList.add(sample);

                Log.d("Ariel", "Just created:" + sample);

            }
        } catch(IOException e){
            Log.d("Ariel", "Error reading data" + line, e);
            e.printStackTrace();
        }
    }

////////////////
    private void readStaitonsJezreelVallyData() {
        InputStream is = getResources().openRawResource(R.raw.stationsjezreelvally);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {

            while ((line = reader.readLine()) != null) {
                stationsJezreelVally sample = new stationsJezreelVally();
                //Split by ','
                String[] tokens = line.split(",");
                //read data

                if (tokens[13].length() > 0) {
                    sample.setStation(tokens[13]);
                } else {
                    sample.setStation("");
                }
                if (tokens[12].length() > 0) {
                    sample.setTime(tokens[12]);
                } else {
                    sample.setTime("");
                }
                if (tokens[11].length() > 0) {
                    sample.setSo2(tokens[11]);
                } else {
                    sample.setSo2("");
                }
                if (tokens[10].length() > 0) {
                    sample.setNox(tokens[10]);
                } else {
                    sample.setNox("");
                }
                if (tokens[9].length() > 0) {
                    sample.setNo2(tokens[9]);
                } else {
                    sample.setNo2("");
                }
                if (tokens[8].length() > 0) {
                    sample.setNo(tokens[8]);
                } else {
                    sample.setNo("");
                }
                if (tokens[7].length() > 0) {
                    sample.setO3(tokens[7]);
                } else {
                    sample.setO3("");
                }

                if (tokens[6].length() > 0) {
                    sample.setPm10(tokens[6]);
                } else {
                    sample.setPm10("");
                }
                if (tokens[5].length() > 0) {
                    sample.setPm25(tokens[5]);
                } else {
                    sample.setPm25("");
                }

                if (tokens[4].length() > 0) {
                    sample.setWs(tokens[4]);
                } else {
                    sample.setWs("");
                }
                if (tokens[3].length() > 0) {
                    sample.setWd(tokens[3]);
                } else {
                    sample.setWd("");
                }
                if (tokens[2].length() > 0) {
                    sample.setTemp(tokens[2]);
                } else {
                    sample.setTemp("");
                }
                if (tokens[1].length() > 0) {
                    sample.setRh(tokens[1]);
                } else {
                    sample.setRh("");
                }
                if (tokens[0].length() > 0) {
                    sample.setGsr(tokens[0]);
                } else {
                    sample.setGsr("");
                }

                stationsJezreelVallyList.add(sample);

                Log.d("JezreelVally", "Just created:" + sample);

            }
        } catch (IOException e) {
            Log.d("JezreelVally", "Error reading data" + line, e);
            e.printStackTrace();
        }
    }

    ////////////
    private void readStaitonsHaifaAndKrayotData() {
        InputStream is = getResources().openRawResource(R.raw.stationshaifaandkrayot);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {

            while ((line = reader.readLine()) != null) {
                stationsHaifaAndKrayot sample = new stationsHaifaAndKrayot();
                //Split by ','
                String[] tokens = line.split(",");
                //read data

                if (tokens[20].length() > 0) {
                    sample.setStation(tokens[20]);
                } else {
                    sample.setStation("");
                }
                if (tokens[19].length() > 0) {
                    sample.setTime(tokens[19]);
                } else {
                    sample.setTime("");
                }
                if (tokens[18].length() > 0) {
                    sample.setSo2(tokens[18]);
                } else {
                    sample.setSo2("");
                }
                if (tokens[17].length() > 0) {
                    sample.setNox(tokens[17]);
                } else {
                    sample.setNox("");
                }
                if (tokens[16].length() > 0) {
                    sample.setNo2(tokens[16]);
                } else {
                    sample.setNo2("");
                }
                if (tokens[15].length() > 0) {
                    sample.setNo(tokens[15]);
                } else {
                    sample.setNo("");
                }
                if (tokens[14].length() > 0) {
                    sample.setNox_t(tokens[14]);
                } else {
                    sample.setNox_t("");
                }
                if (tokens[13].length() > 0) {
                    sample.setNoc2_t(tokens[13]);
                } else {
                    sample.setNoc2_t("");
                }
                if (tokens[12].length() > 0) {
                    sample.setNo_t(tokens[12]);
                } else {
                    sample.setNo_t("");
                }
                if (tokens[11].length() > 0) {
                    sample.setO3(tokens[11]);
                } else {
                    sample.setO3("");
                }
                if (tokens[10].length() > 0) {
                    sample.setCo(tokens[10]);
                } else {
                    sample.setCo("");
                }
                if (tokens[9].length() > 0) {
                    sample.setPm10(tokens[9]);
                } else {
                    sample.setPm10("");
                }
                if (tokens[8].length() > 0) {
                    sample.setPm25(tokens[8]);
                } else {
                    sample.setPm25("");
                }
                if (tokens[7].length() > 0) {
                    sample.setBenzen(tokens[7]);
                } else {
                    sample.setBenzen("");
                }
                if (tokens[6].length() > 0) {
                    sample.setToluene(tokens[6]);
                } else {
                    sample.setToluene("");
                }

                if (tokens[5].length() > 0) {
                    sample.setWs(tokens[5]);
                } else {
                    sample.setWs("");
                }
                if (tokens[4].length() > 0) {
                    sample.setWd(tokens[4]);
                } else {
                    sample.setWd("");
                }
                if (tokens[3].length() > 0) {
                    sample.setTemp(tokens[3]);
                } else {
                    sample.setTemp("");
                }
                if (tokens[2].length() > 0) {
                    sample.setRh(tokens[2]);
                } else {
                    sample.setRh("");
                }
                if (tokens[1].length() > 0) {
                    sample.setGsr(tokens[1]);
                } else {
                    sample.setGsr("");
                }
                if (tokens[0].length() > 0) {
                    sample.setRain(tokens[0]);
                } else {
                    sample.setRain("");
                }

                stationsHaifaAndKrayotList.add(sample);

                Log.d("HaifaAndKrayot", "Just created:" + sample);

            }
        } catch (IOException e) {
            Log.d("HaifaAndKrayot", "Error reading data" + line, e);
            e.printStackTrace();
        }
    }
    //////////////

    private void readStaitonsTzfonData() {
        InputStream is = getResources().openRawResource(R.raw.stationstzfon);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {

            while ((line = reader.readLine()) != null) {
                stationsTzfon sample = new stationsTzfon();
                //Split by ','
                String[] tokens = line.split(",");
                //read data

                if (tokens[12].length() > 0) {
                    sample.setStation(tokens[12]);
                } else {
                    sample.setStation("");
                }
                if (tokens[11].length() > 0) {
                    sample.setTime(tokens[11]);
                } else {
                    sample.setTime("");
                }
                if (tokens[10].length() > 0) {
                    sample.setSo2(tokens[10]);
                } else {
                    sample.setSo2("");
                }
                if (tokens[9].length() > 0) {
                    sample.setNox(tokens[9]);
                } else {
                    sample.setNox("");
                }
                if (tokens[8].length() > 0) {
                    sample.setNo2(tokens[8]);
                } else {
                    sample.setNo2("");
                }
                if (tokens[7].length() > 0) {
                    sample.setNo(tokens[7]);
                } else {
                    sample.setNo("");
                }
                if (tokens[6].length() > 0) {
                    sample.setO3(tokens[6]);
                } else {
                    sample.setO3("");
                }

                if (tokens[5].length() > 0) {
                    sample.setPm10(tokens[5]);
                } else {
                    sample.setPm10("");
                }
                if (tokens[4].length() > 0) {
                    sample.setPm25(tokens[4]);
                } else {
                    sample.setPm25("");
                }
                if (tokens[3].length() > 0) {
                    sample.setWs(tokens[3]);
                } else {
                    sample.setWs("");
                }
                if (tokens[2].length() > 0) {
                    sample.setWd(tokens[2]);
                } else {
                    sample.setWd("");
                }
                if (tokens[1].length() > 0) {
                    sample.setTemp(tokens[1]);
                } else {
                    sample.setTemp("");
                }
                if (tokens[0].length() > 0) {
                    sample.setRh(tokens[0]);
                } else {
                    sample.setRh("");
                }


                stationsTzfonList.add(sample);

                Log.d("Tzfon", "Just created:" + sample);

            }
        } catch (IOException e) {
            Log.d("Tzfon", "Error reading data" + line, e);
            e.printStackTrace();
        }
    }
//////////
    private void readStaitonsShronAndCarmelData() {
        InputStream is = getResources().openRawResource(R.raw.stationsshronandcarmel);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {

            while ((line = reader.readLine()) != null) {
                stationsShronAndCarmel sample = new stationsShronAndCarmel();
                //Split by ','
                String[] tokens = line.split(",");
                //read data

                if (tokens[12].length() > 0) {
                    sample.setStation(tokens[12]);
                } else {
                    sample.setStation("");
                }
                if (tokens[11].length() > 0) {
                    sample.setTime(tokens[11]);
                } else {
                    sample.setTime("");
                }
                if (tokens[10].length() > 0) {
                    sample.setSo2(tokens[10]);
                } else {
                    sample.setSo2("");
                }
                if (tokens[9].length() > 0) {
                    sample.setNox(tokens[9]);
                } else {
                    sample.setNox("");
                }
                if (tokens[8].length() > 0) {
                    sample.setNo2(tokens[8]);
                } else {
                    sample.setNo2("");
                }
                if (tokens[7].length() > 0) {
                    sample.setO3(tokens[7]);
                } else {
                    sample.setO3("");
                }
                if (tokens[6].length() > 0) {
                    sample.setCo(tokens[6]);
                } else {
                    sample.setCo("");
                }

                if (tokens[5].length() > 0) {
                    sample.setPm10(tokens[5]);
                } else {
                    sample.setPm10("");
                }
                if (tokens[4].length() > 0) {
                    sample.setPm25(tokens[4]);
                } else {
                    sample.setPm25("");
                }
                if (tokens[3].length() > 0) {
                    sample.setWs(tokens[3]);
                } else {
                    sample.setWs("");
                }
                if (tokens[2].length() > 0) {
                    sample.setWd(tokens[2]);
                } else {
                    sample.setWd("");
                }
                if (tokens[1].length() > 0) {
                    sample.setTemp(tokens[1]);
                } else {
                    sample.setTemp("");
                }
                if (tokens[0].length() > 0) {
                    sample.setRh(tokens[0]);
                } else {
                    sample.setRh("");
                }


                stationsShronAndCarmelsList.add(sample);

                Log.d("ShronAndCarmel", "Just created:" + sample);

            }
        } catch (IOException e) {
            Log.d("ShronAndCarmel", "Error reading data" + line, e);
            e.printStackTrace();
        }
    }


}


