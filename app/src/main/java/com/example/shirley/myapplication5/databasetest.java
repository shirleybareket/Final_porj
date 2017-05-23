package com.example.shirley.myapplication5;

import android.support.v7.app.AppCompatActivity;

public class databasetest extends AppCompatActivity {
/*
    List<StationSample> stationArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databasetest);
        readStationData();


    }


    private void readStationData() {
        InputStream is = getResources().openRawResource(R.raw.station);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {

            while ((line = reader.readLine()) != null) {
                //Split by ','
                String[] tokens = line.split(",");
                //read data
                StationSample sample = new StationSample();
                if (tokens[0].length() > 0) {
                    sample.setId(Integer.parseInt(tokens[0]));
                } else {
                    sample.setId(0);
                }
                if (tokens[1].length() > 0) {
                    sample.setStation(tokens[1]);
                } else {
                    sample.setStation("null");
                }
                if (tokens.length >= 3 && tokens[2].length() > 0) {
                    sample.setLongitude(Double.parseDouble(tokens[2]));
                } else {
                    sample.setLongitude(0.0);
                }
                if (tokens.length >= 3 && tokens[3].length() > 0) {
                    sample.setLatitude(Double.parseDouble(tokens[3]));
                } else {
                    sample.setLatitude(0.0);
                }


                stationArray.add(sample);

                Log.d("databasetest", "Just created:" + sample);
            }
        } catch (IOException e) {
            Log.d("databasetest", "Error reading data" + line, e);
            e.printStackTrace();
        }
    }

   */ }