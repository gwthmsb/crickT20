package com.cricket.t20.utils;

public class SimulatorUtils {
    
    private SimulatorUtils(){
        throw new IllegalStateException("Utility class");
    }
    
    public static void makeSimulationIntuitive(){
        try{
            Thread.currentThread().sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
