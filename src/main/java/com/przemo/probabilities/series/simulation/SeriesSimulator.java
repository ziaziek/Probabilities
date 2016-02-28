/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.probabilities.series.simulation;

import com.przemo.probabilities.statistics.SimulationStatistics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Przemo
 */
public class SeriesSimulator {
    
    private final int n; //number of simulations
    private final int seriesLength;

    public int getSeriesLength() {
        return seriesLength;
    }
    private final List<PSeries> series;
    private final SimulationStatistics stats;
    protected boolean stopRun=false;

    public boolean isStopRun() {
        return stopRun;
    }

    public void setStopRun(boolean stopRun) {
        this.stopRun = stopRun;
    }
    protected int runIteraion=0;
    
    public SimulationStatistics getStats() {
        return stats;
    }
    
    public List<PSeries> getSeries() {
        return series;
    }
    
    public SeriesSimulator(int numberOfSimulations, int seriesLength){
        this.n=numberOfSimulations;
        this.seriesLength=seriesLength;
        series = new ArrayList<>();
        this.stats = new SimulationStatistics(series);
    }
    
    public void run(double p){
        Random r = new Random();
        for(int i=0; i<n;i++){
            PSeries<Boolean> ps = new BooleanSeries(p);
            series.add(ps);
            for(int j=0;j<seriesLength; j++){
                if(stopRun){
                    return;
                }
                doRunAction(r, p, ps);
                runIteraion++;
            }  
        }
        stats.setNumberOfSeries(series.size());
        stats.setNumberOfEvents(n*seriesLength);
        System.out.println(stats);
    }

    protected void doRunAction(Random r, double p, PSeries<Boolean> ps) {
        if(r.nextDouble()<p){
            onSuccess(ps);
        } else {
            onFailure(ps);
        }
    }

    protected void onFailure(PSeries<Boolean> ps) {
        ps.getEvents().add(false);
        stats.setNumberOfFailure(stats.getNumberOfFailure()+1);
    }

    protected void onSuccess(PSeries<Boolean> ps) {
        ps.getEvents().add(true);
        stats.setNumberOfSuccess(stats.getNumberOfSuccess()+1);
    }
    
}
