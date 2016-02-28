/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulators;

import com.przemo.probabilities.series.simulation.BooleanSeries;
import com.przemo.probabilities.series.simulation.PSeries;
import com.przemo.probabilities.statistics.SimulationStatistics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class averageLossesSeriesLengthTests {
    
    public averageLossesSeriesLengthTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void noEventsTest(){
        BooleanSeries bs = new BooleanSeries(.5);
        List<PSeries> pList = new ArrayList(){{add(bs);}};
        SimulationStatistics stat = new SimulationStatistics(pList);
        Assert.assertEquals(0, stat.getAverageLossesSeries());
    }
    
    @Test
    public void noLossesTest(){
        BooleanSeries bs = new BooleanSeries(.5);
        bs.getEvents().addAll(Arrays.asList(true, true, true));
        List<PSeries> pList = new ArrayList(){{add(bs);}};
        SimulationStatistics stat = new SimulationStatistics(pList);
        Assert.assertEquals(0, stat.getAverageLossesSeries());
    }
    
    @Test
    public void noSuccessesTest(){
        BooleanSeries bs = new BooleanSeries(.5);
        bs.getEvents().addAll(Arrays.asList(false, false, false));
        List<PSeries> pList = new ArrayList(){{add(bs);}};
        SimulationStatistics stat = new SimulationStatistics(pList);
        Assert.assertEquals(3, stat.getAverageLossesSeries());
    }
    
    @Test
    public void noSuccessSeveralTest(){
        BooleanSeries bs = new BooleanSeries(.5);
        bs.getEvents().addAll(Arrays.asList(false, false, false));
        BooleanSeries bs1 = new BooleanSeries(.5);
        bs1.getEvents().addAll(Arrays.asList(false, false, false, false, false, false, false));
        List<PSeries> pList = new ArrayList(){{add(bs);} {add(bs);}{add(bs1);}};
        SimulationStatistics stat = new SimulationStatistics(pList);
        Assert.assertEquals(4, stat.getAverageLossesSeries());
    }
    
    @Test
    public void lossesAndSuccessesSeveralTest(){
        BooleanSeries bs = new BooleanSeries(.5);
        bs.getEvents().addAll(Arrays.asList(false, false, true));
        BooleanSeries bs1 = new BooleanSeries(.5);
        bs1.getEvents().addAll(Arrays.asList(false, false, false, false, true, false, false));
        List<PSeries> pList = new ArrayList(){{add(bs);} {add(bs);}{add(bs1);}};
        SimulationStatistics stat = new SimulationStatistics(pList);
        Assert.assertEquals(2, stat.getAverageLossesSeries());
    }
}
