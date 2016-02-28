/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.probabilities.gui.simulation;

import com.przemo.probabilities.series.simulation.BettingSeriesSimulator;
import com.przemo.probabilities.series.simulation.ISimulatorListener;
import com.przemo.probabilities.series.simulation.SimulationStepEvent;
import java.util.Map;
import javax.swing.SwingWorker;

/**
 *
 * @author Przemo
 */
public class BettingGuiSimulator extends SwingWorker<BettingSeriesSimulator, Map> implements ISimulatorListener{

    private final BettingSeriesSimulator simulator;
    private final double p;
    
    public BettingGuiSimulator(BettingSeriesSimulator sim, double p){
        this.simulator=sim;
        this.p=p;
        sim.registerListener(this);
    }
    @Override
    protected BettingSeriesSimulator doInBackground() throws Exception {
        if(!isCancelled()){
           simulator.run(p); 
        }
        return simulator;
    }

    @Override
    public void simulationStepForAccountTaken(SimulationStepEvent event) {
        publish(simulator.getAccountHistory());
        if(isCancelled()){
            simulator.setStopRun(true);
        }
    }


    
}
