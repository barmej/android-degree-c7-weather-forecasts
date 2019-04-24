package com.barmej.weatherforecasts;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.barmej.weatherforecasts.adapters.DaysForecastAdapter;
import com.barmej.weatherforecasts.adapters.HoursForecastAdapter;
import com.barmej.weatherforecasts.entity.Forecast;
import com.barmej.weatherforecasts.fragments.PrimaryWeatherInfoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity that show current weather info, next hours & days forecasts
 */
public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    private HoursForecastAdapter mHoursForecastAdapter;
    private DaysForecastAdapter mDaysForecastsAdapter;

    private RecyclerView mHoursForecastsRecyclerView;
    private RecyclerView mDaysForecastRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new instance from PrimaryWeatherInfo fragment
        PrimaryWeatherInfoFragment primaryWeatherInfoFragment = new PrimaryWeatherInfoFragment();

        // Get an instance of FragmentManager and assign it to mFragmentManager variable
        mFragmentManager = getSupportFragmentManager();

        // Get instance of FragmentTransaction
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        // Add the fragment into the layout by adding it in the FrameLayout with id header
        fragmentTransaction.add(R.id.header, primaryWeatherInfoFragment);

        // Commit the changes
        fragmentTransaction.commit();

        // Create new HoursForecastAdapter and set it to RecyclerView
        mHoursForecastAdapter = new HoursForecastAdapter(this);
        mHoursForecastsRecyclerView = findViewById(R.id.rv_hours_forecast);
        mHoursForecastsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mHoursForecastsRecyclerView.setAdapter(mHoursForecastAdapter);

        // Create new DaysForecastAdapter and set it to RecyclerView
        mDaysForecastsAdapter = new DaysForecastAdapter(this);
        mDaysForecastRecyclerView = findViewById(R.id.rv_days_forecast);
        mDaysForecastRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDaysForecastRecyclerView.setAdapter(mDaysForecastsAdapter);

        // Add dummy empty objects to hours forecasts list
        List<Forecast> hourForecasts = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            hourForecasts.add(new Forecast());
        }

        // Add dummy empty objects to days forecasts list
        List<List<Forecast>> daysForecasts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Forecast> hoursForecasts = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                hourForecasts.add(new Forecast());
            }
            daysForecasts.add(hoursForecasts);
        }

        // Show forecasts lists
        mHoursForecastAdapter.updateData(hourForecasts);
        mDaysForecastsAdapter.updateData(daysForecasts);

    }



}
