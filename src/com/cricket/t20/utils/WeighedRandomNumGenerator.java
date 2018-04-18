package com.cricket.t20.utils;

import java.util.NavigableMap;
import java.util.Random;

import com.cricket.t20.enums.Run;

public class WeighedRandomNumGenerator {
    private final NavigableMap<Integer, Run> scoreMap;
    private final Random random;
    
    public WeighedRandomNumGenerator(NavigableMap<Integer, Run> scoreMap) {
        this.random = new Random();
        this.scoreMap = scoreMap;
    }
    
    public Run next() {
        int value = random.nextInt(100);
        return scoreMap.higherEntry(value).getValue();
    }
}
