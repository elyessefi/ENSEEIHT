package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String serverUrl = "http://172.20.10.10:8080/Counter5/Counter5";

    Chronometer chronometer;

    long pauseoffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the SensorManager instance
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get the switch button from the layout
        Switch switchButton = (Switch) findViewById(R.id.switchbtn);

        chronometer = findViewById(R.id.chronometer);

        // Initialize variables
        final long[] pauseoffset = {0};
        final int[] i = {0};
        final ArrayList<Float> data = new ArrayList<Float>();

        // Get the accelerometer sensor
        Sensor acceleroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // Switch is turned on
                    sensorManager.registerListener(sensorEventListener, acceleroSensor, SensorManager.SENSOR_DELAY_NORMAL);
                    chronometer.setBase(SystemClock.elapsedRealtime() - pauseoffset[0]);
                    chronometer.start();
                } else {
                    // Switch is turned off
                    sensorManager.unregisterListener(sensorEventListener);
                    pauseoffset[0] = SystemClock.elapsedRealtime() - chronometer.getBase();
                    chronometer.stop();
                    System.out.println("nombre des paquets : " + data.size());
                    System.out.println("durre : " + pauseoffset[0]);
                }
            }

            private SensorEventListener sensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                        // Get accelerometer values
                        float x = sensorEvent.values[0];
                        float y = sensorEvent.values[1];
                        float z = sensorEvent.values[2];

                        // Send accelerometer parameters
                        sendParameters(x, y, z);

                        // Store z-axis values
                        data.add(z);
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    // Handle accuracy changes if needed
                }
            };
        });
    }

    public void sendParameters(float x, float y, float z){

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, serverUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response from the server
                        ((TextView)findViewById(R.id.Z)).setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response
                        System.out.println("Failed");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Add parameters to the request
                Map<String, String> params = new HashMap<>();
                params.put("x", Float.toString(x));
                params.put("y", Float.toString(y));
                params.put("z", Float.toString(z));
                return params;
            }
        };

        // Add the request to the RequestQueue
        queue.add(request);
    }

}
