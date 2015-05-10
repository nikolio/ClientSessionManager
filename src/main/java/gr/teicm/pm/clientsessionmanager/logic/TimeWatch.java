/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teicm.pm.clientsessionmanager.logic;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author user
 */
public class TimeWatch {    
    long starts;

    public static TimeWatch start() {
        return new TimeWatch();
    }

    private TimeWatch() {
        reset();
    }

    public TimeWatch reset() {
        starts = System.currentTimeMillis();
        return this;
    }

    public long getTime() {
        long ends = System.currentTimeMillis();
        return ends - starts;
    }

    public long getTime(TimeUnit unit) {
        return unit.convert(getTime(), TimeUnit.MILLISECONDS);
    }
}