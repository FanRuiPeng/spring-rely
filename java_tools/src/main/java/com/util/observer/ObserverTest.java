package com.util.observer;

/**
 * Created by BMF on 2017/11/24.
 */
public class ObserverTest {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        ShowTemperature showTemperature = new ShowTemperature();
        ShowTemperature showTemperature1 = new ShowTemperature();
        weatherData.addObserver(showTemperature);
        weatherData.addObserver(showTemperature1);
        weatherData.notify(4);
    }
}
