/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulators;

import com.przemo.probabilities.series.simulation.BooleanSeries;
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
public class lossesSeriesLengthsTests {
    
    public lossesSeriesLengthsTests() {
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
    public void noEvetsTest(){
        BooleanSeries s = new BooleanSeries(.5);
        Assert.assertEquals(0, s.getLossesSeriesLengths().size());
    }
    
    @Test
    public void noLossesTest(){
        BooleanSeries s = new BooleanSeries(.5);
        s.getEvents().addAll(Arrays.asList(true, true, true));
        Assert.assertEquals(0, s.getLossesSeriesLengths().size());
    }
    
    @Test
    public void noSuccessTest(){
        BooleanSeries s = new BooleanSeries(.5);
        s.getEvents().addAll(Arrays.asList(false, false, false));
        Assert.assertEquals(1, s.getLossesSeriesLengths().size());
        Assert.assertEquals(3, s.getLossesSeriesLengths().get(0).intValue());
    }
    
    @Test
    public void lossesAndSuccessesTest(){
        BooleanSeries s = new BooleanSeries(.5);
        s.getEvents().addAll(Arrays.asList(true, false, false, false, false, false, false, false, true, true, false, false, false, true, true, true, true, true, false, false));
        List<Integer> losses =  s.getLossesSeriesLengths();
        Assert.assertEquals(3,losses.size());
        Assert.assertEquals(7, losses.get(0).intValue());
        Assert.assertEquals(3, losses.get(1).intValue());
        Assert.assertEquals(2, losses.get(2).intValue());        
    }
}
