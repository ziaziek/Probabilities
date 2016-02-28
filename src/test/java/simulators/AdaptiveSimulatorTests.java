/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulators;

import com.przemo.probabilities.series.simulation.AdaptiveBettingSimulator;
import com.przemo.probabilities.series.simulation.BooleanSeries;
import java.util.Arrays;
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
public class AdaptiveSimulatorTests {
    
    private class ASim extends AdaptiveBettingSimulator{

        public ASim(int seriesLength, double k) {
            super(seriesLength, k);
        }
        
        public int lastSuccess(){
            return super.findLastSuccess();
        }
        
        public double stakesSum(int period){
            return super.findStakesSumForPeriod(period);
        }
    }
    
    private ASim sim=null;
    
    public AdaptiveSimulatorTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        sim = new ASim(5, 1.8);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void noEventsTest(){
       BooleanSeries bs1 = new BooleanSeries(.5);
        sim.getSeries().add(bs1); 
        Assert.assertEquals(0, sim.lastSuccess());
    }
    
    @Test
    public  void lastSuccessTestsAllFalse(){
        BooleanSeries bs1 = new BooleanSeries(.5);
        bs1.getEvents().addAll(Arrays.asList(false, false, false, false, false, false, false));
        sim.getSeries().add(bs1);
        Assert.assertEquals(7, sim.lastSuccess());
    }
    
    @Test
    public  void lastSuccessTestsLastTrue(){
        BooleanSeries bs1 = new BooleanSeries(.5);
        bs1.getEvents().addAll(Arrays.asList(false, false, false, false, false, false, true));
        sim.getSeries().add(bs1);
        Assert.assertEquals(0, sim.lastSuccess());
    }
    
    @Test
    public  void lastSuccessTests(){
        BooleanSeries bs1 = new BooleanSeries(.5);
        bs1.getEvents().addAll(Arrays.asList(false, true, true, false, false, false, false));
        sim.getSeries().add(bs1);
        Assert.assertEquals(4, sim.lastSuccess());
    }
    
    private void fillAccountHistory(){
        sim.getAccountHistory().put(0, 100.0);
        sim.getAccountHistory().put(1, 99.0);
        sim.getAccountHistory().put(2, 98.0);
        sim.getAccountHistory().put(3, 96.0);
        sim.getAccountHistory().put(4, 91.5);
    }
    
    @Test
    public void stakeSumPeriod0(){
        fillAccountHistory();
        Assert.assertEquals(0.0, sim.stakesSum(0));
    }
    
    @Test
    public void stakeSumPeriodGreaterThanHistory(){
        fillAccountHistory();
        Assert.assertEquals(8.5, sim.stakesSum(100));
    }
    
    @Test
    public void stakeSum(){
        fillAccountHistory();
        Assert.assertEquals(4.5, sim.stakesSum(1));
        Assert.assertEquals(7.5, sim.stakesSum(3));
    }
}
