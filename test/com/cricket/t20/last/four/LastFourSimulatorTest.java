package com.cricket.t20.last.four;

import org.junit.Assert;
import org.junit.Test;

public class LastFourSimulatorTest {

    @Test
    public void testLastFourSimulator() {
        LastFourSimulator simulator = new LastFourSimulator();
        Assert.assertNotNull(simulator);
    }

    @Test
    public void testStartSimulation() {
        try{
            LastFourSimulator simulator = new LastFourSimulator();
            simulator.startSimulation();
        }catch(Exception e){
            Assert.fail(e.getMessage());
        }
    }

}
