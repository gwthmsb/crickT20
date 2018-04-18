package com.cricket.t20.utils;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.enums.Run;
import com.cricket.t20.utils.WeighedRandomNumGenerator;

public class WeighedRandomNumGeneratorTest {

    @Test
    public void testWeighedRandomNumGenerator() {
        try{
            NavigableMap<Run, Integer> scoreMap = new TreeMap<>();
            scoreMap.put(Run.SINGLE,25);
            scoreMap.put(Run.DOTBALL,5);
            scoreMap.put(Run.DOUBLE,15);
            scoreMap.put(Run.TRIPLE,5);
            scoreMap.put(Run.BOUNDARY,20);
            scoreMap.put(Run.FIVER,2);
            scoreMap.put(Run.SIX,13);
            scoreMap.put(Run.OUT,15);

            NavigableMap<Integer, Run> map = new TreeMap<>();
            int count=0;
            for(Map.Entry<Run, Integer> entrySet : scoreMap.entrySet()){
                map.put((count+=entrySet.getValue()), entrySet.getKey());
            }
            
            WeighedRandomNumGenerator num = new WeighedRandomNumGenerator(map);
            
            Map<Run, Integer> runMap = new HashMap<>();
            
            for(int i=0 ;i<100;i++){
                Run run = num.next();
                Integer j = runMap.get(run);
                if(j==null){
                    runMap.put(run, 1);
                }else{
                    runMap.put(run,(j+1));
                }
            }
            
            int totalCount=0;
            
            for(Map.Entry<Run, Integer> entrySet : runMap.entrySet()){
                System.out.println(entrySet.getKey() + " : "+entrySet.getValue());
                totalCount+=entrySet.getValue();
            }
            
            Assert.assertEquals(100, totalCount);
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
