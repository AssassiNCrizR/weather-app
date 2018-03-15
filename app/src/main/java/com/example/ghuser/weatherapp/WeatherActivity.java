package com.example.ghuser.weatherapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ghuser.weatherapp.common.Common;
import com.example.ghuser.weatherapp.helper.Helper;
import com.example.ghuser.weatherapp.model.OpenWeatherMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;

public class WeatherActivity extends AppCompatActivity {

    TextView txtCity,txtDescription,txtHumidity,txtCelsius,txtTime,txtLastUpdate;
    ImageView imageView;

    OpenWeatherMap openWeatherMap = new OpenWeatherMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        String place = getIntent().getExtras().getString("e1");

        //control
        txtCity = (TextView)findViewById(R.id.txtCity);
        txtLastUpdate = (TextView)findViewById(R.id.txtLastUpdate);
        txtDescription = (TextView)findViewById(R.id.txtDescription);
        txtHumidity = (TextView)findViewById(R.id.txtHumidity);
        txtTime = (TextView)findViewById(R.id.txtTime);
        txtCelsius = (TextView)findViewById(R.id.txtCelsius);
        imageView = (ImageView)findViewById(R.id.imageView);

        new GetWeather().execute(Common.apiRequest(place));
    }

    private class GetWeather extends AsyncTask<String,Void,String>{
        ProgressDialog pd = new ProgressDialog(WeatherActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please Wait...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];

            Helper http = new Helper();
            stream = http.getHTTPData(urlString);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.contains("Error: Not found city")) {
                pd.dismiss();
                return;
            }
            Gson gson = new Gson();
            Type mType = new TypeToken<OpenWeatherMap>(){}.getType();
            openWeatherMap = gson.fromJson(s,mType);
            pd.dismiss();

            txtCity.setText(String.format("%s,%s",openWeatherMap.getName(),openWeatherMap.getSys().getCountry()));
            txtLastUpdate.setText(String.format("Last Update: %s", Common.getDatenow()));
            txtDescription.setText(String.format("%s",openWeatherMap.getWeather().get(0).getDescription()));
            txtHumidity.setText(String.format("Humidity: %d%%",openWeatherMap.getMain().getHumidity()));
            txtTime.setText(String.format("Sunrise: %s / Sunset: %s",Common.unixTimeStampToDateTime(openWeatherMap.getSys().getSunrise()),Common.unixTimeStampToDateTime(openWeatherMap.getSys().getSunset())));
            txtCelsius.setText(String.format("Temperature: %.2f °C",openWeatherMap.getMain().getTemp()-273.15));
            Picasso.with(WeatherActivity.this)
                    .load(Common.getImage(openWeatherMap.getWeather().get(0).getIcon()))
                    .into(imageView);
        }
    }

    public void showForecast(View v){
        Intent intent = new Intent(this, ForecastActivity.class);
        startActivity(intent);
    }
}
