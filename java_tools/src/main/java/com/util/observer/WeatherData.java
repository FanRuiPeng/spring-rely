package com.util.observer;

import java.util.Observable;

/**
 * Created by BMF on 2017/11/24.
 */
public class WeatherData extends Observable {
    private int temperature;

    public void notify(int temperature) {
        setChanged();
        notifyObservers(temperature);
    }

    public int getTemperature() {
        return temperature;
    }

    public WeatherData setTemperature(int temperature) {
        this.temperature = temperature;
        return this;
    }
}
