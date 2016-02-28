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
public interface ISimulatorListener {
    
    void simulationStepForAccountTaken(SimulationStepEvent event);
    
}
