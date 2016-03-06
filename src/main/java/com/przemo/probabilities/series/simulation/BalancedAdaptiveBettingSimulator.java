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
public class BalancedAdaptiveBettingSimulator extends AdaptiveBettingSimulator {

    protected BooleanSeries window;
    private final int windowLength;
    private double stakes[];
    private double D1 = 0.0; //previous D
    private int sindex = 0; //current index of the stakes
    protected double tolerance = 1.1;
    private final int m = 4; //window length multiplier
    protected IBettingStrategy bettingStrategy;

    public BalancedAdaptiveBettingSimulator(int seriesLength, double k) {
        super(seriesLength, k);
        windowLength = (int) (m * k + 1);
        window = new BooleanSeries(windowLength);
    }
    
    public void setBettingStrategy(IBettingStrategy bettingStrategy) {
        this.bettingStrategy = bettingStrategy;
    }

    //specific method of bet assesement for this particular class. Based on balanced series theory
    //We assume that series will tend ro reach balance between successes and losses
    //We know that 99% of all probability series are balanced when their length is 130
    //Thus, before the history of betting reaches that length, some caution steps must be taken.
    @Override
    protected double assessBet(double p) {
        if(isVerbose()){
            System.out.println("Iteration: "+runIteraion);
        }
        //get me all events that I will use for chances assesement
        window.setEvents(getSeries().get(0).getEvents().subList(Math.max(0, runIteraion - windowLength), runIteraion));
            //take special steps now. Decide whether to decrease, increase or default the stake
            double[] stakesArray = getStakesArray(p);
            findStakesIndex(p, stakesArray);
            if(bettingStrategy!=null){
                return stakesArray[bettingStrategy.modifyStakesIndex(sindex, stakesArray)];
            } else {
                return stakesArray[sindex];
            }

    }

    protected void findStakesIndex(double p, final double[] stakes) {
        double D = p / window.getNumberOfSuccess() * window.getEvents().size();
        if (D > tolerance) {
            if (D >= D1) {
                sindex = Math.min(sindex + 1, stakes.length-1);
            } else if (D < D1) {
                sindex = Math.max(0, sindex - 1);
            }
        } else {
            sindex = 0;
        }
        if(isVerbose()){
            System.out.println("D="+D);
            System.out.println("Current stakes index = "+sindex);
            System.out.println("Number of succeses within window: "+ window.getNumberOfSuccess());
            System.out.println("Size window: "+window.getEvents().size());
        }
        D1 = D;
    }

    protected double[] getStakesArray(double p) {

        if (stakes == null || stakes.length == 0) {
            int stakesLength = (int) (m / (p * p));
            stakes = new double[stakesLength];
            int i;
            for (i = 0; i < 1 / p; i++) {
                stakes[i] = 1;
            }
            while (i < stakesLength) {
                stakes[i] = (int) (stakes[i - 1] / p);
                i++;
            }
            if (isVerbose()) {
                System.out.print("[");
                for (int w = 0; w < stakes.length - 1; w++) {
                    System.out.print(stakes[w] + ",");
                }
                System.out.println(stakes[stakes.length - 1] + "]");
            }
        }

        return stakes;

    }

    @Override
    protected void adjustBet() {
            //don't adjust the bet as it's done in a super class SimpleProgressiveBettingSimulator
    }

    
}
