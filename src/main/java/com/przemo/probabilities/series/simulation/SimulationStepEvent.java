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
public class SimulationStepEvent {

    private final Object source;
    private final double account;
    private final boolean event;

    public double getAccount() {
        return account;
    }

    public boolean isEvent() {
        return event;
    }

    public double getBet() {
        return bet;
    }

    public Object getSource() {
        return source;
    }

    public int getCurrentIteration() {
        return currentIteration;
    }
    private final double bet;
    private final int currentIteration;
    
    public SimulationStepEvent(Object source, int iteration, double account, double bet, boolean event) {
        this.account=account;
        this.bet=bet;
        this.currentIteration=iteration;
        this.event=event;
        this.source=source;
    }
    
}
