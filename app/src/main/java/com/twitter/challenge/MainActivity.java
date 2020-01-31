package com.twitter.challenge;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.twitter.challenge.core.TemperatureConverter;
import com.twitter.challenge.viewmodel.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView temperatureView = findViewById(R.id.temperature);
        final TextView windView = findViewById(R.id.wind);
        final ImageView cloudView = findViewById(R.id.cloud);

        WeatherViewModel weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        weatherViewModel.getWeather().observe(MainActivity.this, climate -> {
            Log.d("DEBUG", "W:" + climate);
            float temperature = climate.getWeather().getTemp();
            temperatureView.setText(getString(R.string.temperature,
                    temperature,
                    TemperatureConverter.celsiusToFahrenheit(temperature)));

            windView.setText(getString(R.string.wind,
                    climate.getWind().getSpeed(),
                    climate.getWind().getDeg()));

            cloudView.setVisibility(climate.getClouds().getCloudiness() > 50 ? View.VISIBLE : View.GONE);
        });

        final TextView standardDeviationView = findViewById(R.id.standard_deviation);

        weatherViewModel.isBusy().observe(this, isBusy-> {
            findViewById(R.id.progress).setVisibility(isBusy ? View.VISIBLE : View.GONE);
            standardDeviationView.setVisibility(!isBusy ? View.VISIBLE : View.GONE);
        });

        weatherViewModel.getStandardDeviation().observe(MainActivity.this, standardDeviation -> {
            Log.d("DEBUG", "Method getting trigger over and over again");
            standardDeviationView.setVisibility(View.VISIBLE);
            standardDeviationView.setText(getString(R.string.standard_deviation, standardDeviation));
        });

        findViewById(R.id.button).setOnClickListener(v -> {
            weatherViewModel.updateNextFiveDaysSD();
        });
    }

}
