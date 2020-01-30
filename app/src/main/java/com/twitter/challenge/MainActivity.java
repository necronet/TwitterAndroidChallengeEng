package com.twitter.challenge;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.twitter.challenge.viewmodel.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView temperatureView = findViewById(R.id.temperature);

        WeatherViewModel weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        weatherViewModel.getWeather().observe(MainActivity.this, climate -> {
            Log.d("DEBUG", "W:" + climate);
            float temperature = climate.getWeather().getTemp();
            temperatureView.setText(getString(R.string.temperature,
                    temperature,
                    TemperatureConverter.celsiusToFahrenheit(temperature)));
        });
    }

}
