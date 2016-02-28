/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.probabilities.series.simulation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Przemo
 * @param <T>
 */
public abstract class PSeries<T> {
    
    private final double p;

    public double getP() {
        return p;
    }
    private List<T> events;

    public List<T> getEvents() {
        return events;
    }

    public void setEvents(List<T> events) {
        this.events = events;
    }
    
    public PSeries(double p){
        this.p=p;
        events = new ArrayList<>();
    }
    
    public abstract int getNumberOfSuccess();
    
    public abstract int getLongestLossesSeries();
    
    public abstract List<Integer> getLossesSeriesLengths();
}
