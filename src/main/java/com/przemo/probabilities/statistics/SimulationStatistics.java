/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.probabilities.statistics;

import com.przemo.probabilities.series.simulation.PSeries;
import java.util.List;

/**
 *
 * @author Przemo
 */
public class SimulationStatistics {
    
    private int numberOfSeries;
    private int numberOfEvents;
    private int numberOfSuccess;
    private int numberOfFailure;
    private final List<PSeries> series;
    
    public SimulationStatistics(List<PSeries> series){
        this.series=series;
    }
    
    public int getNumberOfSeries() {
        return numberOfSeries;
    }

    public void setNumberOfSeries(int numberOfSeries) {
        this.numberOfSeries = numberOfSeries;
    }

    public int getNumberOfEvents() {
        return numberOfEvents;
    }

    public void setNumberOfEvents(int numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }

    public int getNumberOfSuccess() {
        return numberOfSuccess;
    }

    public void setNumberOfSuccess(int numberOfSuccess) {
        this.numberOfSuccess = numberOfSuccess;
    }

    public int getNumberOfFailure() {
        return numberOfFailure;
    }

    public void setNumberOfFailure(int numberOfFailure) {
        this.numberOfFailure = numberOfFailure;
    }
    
    public int getNumberOfBalancedSeries(){
        int s = 0;
        for(PSeries p : series){
            double v = p.getP()*p.getEvents().size();
            int ns = p.getNumberOfSuccess();
            if((Math.sqrt((ns-v)*(ns-v))/p.getEvents().size())<0.1){
                s++;
            }
        }
        return s;
    }
    
    public int getLongestLosesSeriesOfAll(){
        return series.stream().mapToInt(x ->x.getLongestLossesSeries()).max().orElse(0);
    }
    
    public int getAverageLossesSeries(){
        int sumOfAll=0;
        int nOfAll=0;
        for(PSeries p: series){
            List<Integer> seriesLossesSeries = p.getLossesSeriesLengths();
            sumOfAll+=seriesLossesSeries.stream().mapToInt(x->(x)).sum();
            nOfAll+=seriesLossesSeries.size();
        }
        if(nOfAll==0){
            return 0;
        } else {
            return sumOfAll/nOfAll;
        }     
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Statistics: \n");
        sb.append("Number of series: ");
        sb.append(getNumberOfSeries());
        sb.append("\n");
        sb.append("Number of all events: ").append(getNumberOfEvents()).append("\n");
        sb.append("Number of successes: ").append(getNumberOfSuccess()).append("\n");
        sb.append("Percentage of successes: ").append(100*getNumberOfSuccess()/getNumberOfEvents()).append("%\n");
        sb.append("Longest losses series: ").append(getLongestLosesSeriesOfAll()).append("\n");
        sb.append("Average length of a losses series: ").append(getAverageLossesSeries()).append("\n");
        int balanced = getNumberOfBalancedSeries();
        sb.append("Number of balanced series: ").append(balanced).append("\n");
        sb.append("Percentage of balanced series: ").append(100*balanced/getNumberOfSeries()).append("%");
        return sb.toString();
    }
}
