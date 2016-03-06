/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.probabilities;

import com.przemo.probabilities.gui.MainForm;
import com.przemo.probabilities.series.simulation.BalancedAdaptiveBettingSimulator;
import com.przemo.probabilities.series.simulation.HalfAccountStrategy;
import com.przemo.probabilities.series.simulation.SeriesSimulator;
import javax.swing.SwingUtilities;

/**
 *
 * @author Przemo
 */
public class Application {
    
    public static void main(String[] args){
        //betSimulate();
        textMode();
    }
    
    private static void textMode(){
        BalancedAdaptiveBettingSimulator sim = new BalancedAdaptiveBettingSimulator(1000, 2.0);
        sim.setVerbose(true);
        sim.setBettingStrategy(new HalfAccountStrategy(sim));
        sim.run(0.5);
    }
    
    private static void betSimulate(){
        MainForm form = new MainForm();
        
        SwingUtilities.invokeLater(() -> {
            form.setVisible(true);   
        });
    }
    
    private static void discover() throws InterruptedException{
        final int maxLosses=97;
        final int maxRepetitioons = 25;
        int cl=0, i=0 ;
        int maxCl=0;
        while(cl<maxLosses && i<maxRepetitioons){
            SeriesSimulator sim = new SeriesSimulator(1000, 130);
            sim.run(.5);   
            cl=sim.getStats().getLongestLosesSeriesOfAll();
            if(cl>maxCl){
                maxCl=cl;
            }
            Thread.sleep(75);
            i++;
        }
        System.out.println("Maximu of losses of all of all: "+ maxCl);
    }
}
