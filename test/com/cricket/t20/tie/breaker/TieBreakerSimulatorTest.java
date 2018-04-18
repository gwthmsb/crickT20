package com.cricket.t20.tie.breaker;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

public class TieBreakerSimulatorTest {

    @Test
    public void testTieBreakerSimulator() {
        TieBreakerSimulator sim = new TieBreakerSimulator();
        Assert.assertNotNull(sim);
    }

    @Test
    public void testStartSimulator() {
        try{
            TieBreakerSimulator sim = new TieBreakerSimulator();
            sim.startSimulator();
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
