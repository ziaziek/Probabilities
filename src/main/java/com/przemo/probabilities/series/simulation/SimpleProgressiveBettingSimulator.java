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
public class SimpleProgressiveBettingSimulator extends BettingSeriesSimulator {

    private int subsequentFailures=0;
    
    public SimpleProgressiveBettingSimulator(int seriesLength, double k) {
        super(seriesLength, k);
    }

    protected void adjustBet(){
        bet = DEFAULT_BET;
    }
    
    @Override
    protected double assessBet(double p) {
        double r = bet;
        if(subsequentFailures>1/p){
            r*=(1+p);
        }
        return r;
    }   

    @Override
    protected void onFailure(PSeries<Boolean> ps) {
        super.onFailure(ps);
        subsequentFailures++;
    }

    @Override
    protected void onSuccess(PSeries<Boolean> ps) {
        super.onSuccess(ps);
        subsequentFailures=0;
        adjustBet();
    }
    
    
    
}
