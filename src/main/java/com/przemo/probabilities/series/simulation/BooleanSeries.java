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
 */
public class BooleanSeries extends PSeries<Boolean> {

    public BooleanSeries(double p) {
        super(p);
    }

    @Override
    public int getNumberOfSuccess() {
        int s = 0;
        s = getEvents().stream().filter((b) -> (b)).map((_item) -> 1).reduce(s, Integer::sum);
                    return s;
    }

    @Override
    public int getLongestLossesSeries() {
        return getLossesSeriesLengths().stream().mapToInt(x->(x)).max().orElse(0);
    }

    @Override
    public List<Integer> getLossesSeriesLengths() {
        List<Integer> l = new ArrayList();
        Integer v = 0;
        int size = getEvents().size();
        int i=0;
        while(i<size){
            if(!getEvents().get(i)){
                v++;
            } else {      
                if(v>0){
                   l.add(v); 
                }    
                v=0;
            }
            i++;
        }
        if(v>0){
            l.add(v);
        }
        return l;
    }
    
}
