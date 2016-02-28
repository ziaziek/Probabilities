/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulators;

import com.przemo.probabilities.series.simulation.BettingSeriesSimulator;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Przemo
 */
public class BettingSimulatorTests {
    
    private BettingSeriesSimulator sim;
    
    public BettingSimulatorTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        sim = new BettingSeriesSimulator(10, 2.0);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void negativeAccountTest(){     
        sim.setAccount(-1);
        sim.run(.5);
        Assert.assertEquals(0, sim.getSeries().size());
    }
    
    @Test
    public void positiveAccountTest(){
        sim.setAccount(10);
        sim.run(.5);
        Assert.assertTrue(sim.getSeries().size()==1 && sim.getSeries().get(0).getEvents().size()>0);
    }
}
