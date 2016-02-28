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
public class AdaptiveBettingSimulator extends SimpleProgressiveBettingSimulator {

    public AdaptiveBettingSimulator(int seriesLength, double k) {
        super(seriesLength, k);
    }

    @Override
    protected double assessBet(double p) {
        int ls = findLastSuccess();
        double ret = (int) (findStakesSumForPeriod(ls) / (k - 1)) + 1;
        if (ret == 0) {
            return bet;
        } else {
            return ret;
        }
    }

    protected final int findLastSuccess() {
        if (!getSeries().isEmpty() && getSeries().get(0) != null && !getSeries().get(0).getEvents().isEmpty() && getSeries().get(0).getEvents().get(0) instanceof Boolean) {
            int n = getSeries().get(0).getEvents().size();
            while (n > 0 && !(Boolean) getSeries().get(0).getEvents().get(n - 1)) {
                n--;
            }
            return getSeries().get(0).getEvents().size() - n;
        }
        return 0;

    }

    protected final double findStakesSumForPeriod(int period) {
        double sum = 0;
        if (runIteraion > 1) {
            if (period > getAccountHistory().size()) {
                period = getAccountHistory().size() - 1;
            }
            for (int i = 0; i < period; i++) {
                if(getAccountHistory().get(runIteraion - i)!=null && getAccountHistory().get(runIteraion - i - 1)!=null){
                    sum += -getAccountHistory().get(runIteraion - i) + getAccountHistory().get(runIteraion - i - 1);
                }
                
            }
        }

        return sum;
    }

}
