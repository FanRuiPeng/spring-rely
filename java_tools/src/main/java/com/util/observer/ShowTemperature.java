package com.util.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by BMF on 2017/11/24.
 */
public class ShowTemperature implements Observer {
    private int temperature;

    @Override
    public void update(Observable o, Object arg) {
        temperature = (Integer) arg;
        System.out.println(this);
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "ShowTemperature{" +
                "temperature=" + temperature +
                '}';
    }
}
