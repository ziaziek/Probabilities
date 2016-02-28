/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.probabilities.series.simulation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Przemo
 */
public class BettingSeriesSimulator extends SeriesSimulator {

    protected Set<ISimulatorListener> simLiisteners;

    protected final double DEFAULT_BET = 1.0;

    private double account; //initial account if none is set

    private double[][] betHistory;

    private boolean verbose;

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
    
    public double[][] getBetHistory() {
        return betHistory;
    }

    private final Map<Integer, Double> accountHistory = new HashMap<>();

    public Map<Integer, Double> getAccountHistory() {
        return accountHistory;
    }

    protected final double k; //odds

    protected double bet = DEFAULT_BET; //how much a single bet is

    public double getAccount() {
        return account;
    }

    /**
     * Sets the value of the account. WARNING: This clears the account history
     * and sets the current account value at position 0
     *
     * @param account
     */
    public final void setAccount(double account) {
        this.account = account;
        accountHistory.clear();;
        accountHistory.put(0, account);
        initBetHistory();
    }

    /**
     * Instantiate the simulator. Sets the PSeries length to 1, so that only one
     * series is taken into account with a number of events. This also sets te
     * account value to the default 100
     *
     * @param seriesLength length of events series
     * @param k odds
     */
    public BettingSeriesSimulator(int seriesLength, double k) {
        super(1, seriesLength);
        this.k = k;
        simLiisteners = new HashSet<>();
        setAccount(100);
    }

    protected final void initBetHistory() {
        betHistory = new double[2][getSeriesLength()];
        for (int i = 0; i < getSeriesLength(); i++) {
            betHistory[0][i] = i;
        }
    }

    @Override
    protected void onSuccess(PSeries<Boolean> ps) {
        super.onSuccess(ps);
        account += k * bet;
    }

    @Override
    public void doRunAction(Random r, double p, PSeries<Boolean> ps) {
        bet = assessBet(p);
        if(isVerbose()){
            System.out.println("Before betting:");
            System.out.println("Account: "+account);
            System.out.println("Assesed bet: "+bet);
        }     
        account -= Math.min(bet, account);
        if (r.nextDouble() < p) {
            onSuccess(ps);
        } else {
            onFailure(ps);
        }
        accountHistory.put(runIteraion, account);
        betHistory[1][runIteraion] = bet;
        notifyListeners(ps);
        if(isVerbose()){
            System.out.println("After betting:");
            System.out.println("Bet: "+bet);
            System.out.println("Account: "+account);         
        }
        if (account <= 0) {
            stopRun = true;
        }
    }

    public void registerListener(ISimulatorListener l) {
        simLiisteners.add(l);
    }

    public boolean unregisterListener(ISimulatorListener l) {
        return simLiisteners.remove(l);
    }

    protected double assessBet(double p) {
        return bet;
    }

    private void notifyListeners(PSeries<Boolean> s) {
        if (!s.getEvents().isEmpty()) {
            simLiisteners.stream().forEach((l) -> {
                l.simulationStepForAccountTaken(new SimulationStepEvent(this, runIteraion + 1, account, bet, (boolean) s.getEvents().get(runIteraion)));
            });
        }
    }
}
