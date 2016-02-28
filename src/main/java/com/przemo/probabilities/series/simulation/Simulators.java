/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.probabilities.series.simulation;

/**
 *
 * @author Przemo
 */
public enum Simulators {
    
    FLAT_SIMULATOR("Flat Simulator", BettingSeriesSimulator.class),
    SIMPLE_PRG_SIMULATOR("Simple Progressive", SimpleProgressiveBettingSimulator.class),
    SIMPLE_ADAPTIVE("Simple Adaptive", AdaptiveBettingSimulator.class),
    ADAPTIVE_BALANCED("Adaptive Balanced", BalancedAdaptiveBettingSimulator.class);
    
    private final String name;
    private final Class<?> type;
    Simulators(String name, Class<?> type){
        this.name=name;
        this.type=type;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    public Class<?> getType(){
        return type;
    }
}
