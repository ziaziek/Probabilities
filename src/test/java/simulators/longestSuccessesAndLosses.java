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
public class longestSuccessesAndLosses {
    
    public longestSuccessesAndLosses() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void longestBooleanLossesTest(){
        BooleanSeries bs = new BooleanSeries(0.5);
        bs.getEvents().addAll(Arrays.asList(true, false, false, false, false, false, true, true, false, false, false, true, true, true, true, true, false, false));
        Assert.assertEquals(18, bs.getEvents().size());
        Assert.assertEquals(5, bs.getLongestLossesSeries());
    }
    
    @Test
    public void logestBooleanLosesOfAllTest(){
        List<PSeries> bsList = new ArrayList();
        bsList.add(new BooleanSeries(0.5));
        bsList.add(new BooleanSeries(.5));
        bsList.get(0).getEvents().addAll(Arrays.asList(true, false, false, false, false, false, true, true, false, false, false, true, true, true, true, true, false, false));
        bsList.get(1).getEvents().addAll(Arrays.asList(true, false, false, false, false, false, false, false, true, true, false, false, false, true, true, true, true, true, false, false));
        Assert.assertEquals(18, bsList.get(0).getEvents().size());
        Assert.assertEquals(20, bsList.get(1).getEvents().size());
        Assert.assertEquals(7, new SimulationStatistics(bsList).getLongestLosesSeriesOfAll());
    }
}
