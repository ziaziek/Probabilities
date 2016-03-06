/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.probabilities.series.simulation;

/**
 * If the bet is bigger than half of the account, the stake is lowered according to the stakes array.
 * @author Przemo
 */
public class HalfAccountStrategy implements IBettingStrategy{

    private final BettingSeriesSimulator simulator;
    
    /**
     * Initialise class
     * @param sim Betting simulator for which the strategy will be excercised
     */
    public HalfAccountStrategy(BettingSeriesSimulator sim){
        this.simulator = sim;
    }
    
    @Override
    public int modifyStakesIndex(int startIndex, double[] stakesArray) { 
        while(simulator.getAccount()<stakesArray[startIndex]*2 && startIndex>0){
            startIndex--;
        }
            return startIndex;
    }
}
