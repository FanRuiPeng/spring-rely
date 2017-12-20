package com.util.observer;

/**
 * Created by BMF on 2017/11/24.
 */
public class ObserverTest {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        ShowTemperature showTemperature = new ShowTemperature();
        weatherData.addObserver(showTemperature);
        weatherData.notify(4);
    }
}
