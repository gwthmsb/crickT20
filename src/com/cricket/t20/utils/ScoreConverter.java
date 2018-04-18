package com.cricket.t20.utils;

import java.util.EnumMap;
import java.util.Map;

import com.cricket.t20.enums.Run;

public class ScoreConverter {
    private static Map<Run, Integer> scoreToInt = new EnumMap<>(Run.class);
    
    static{
        populateScoreToIntMap();
    }
    
    private ScoreConverter(){
        throw new IllegalStateException("Util class");
    }
    
    private static void populateScoreToIntMap(){
        scoreToInt.put(Run.DOTBALL, 0);
        scoreToInt.put(Run.SINGLE, 1);
        scoreToInt.put(Run.DOUBLE, 2);
        scoreToInt.put(Run.TRIPLE, 3);
        scoreToInt.put(Run.BOUNDARY, 4);
        scoreToInt.put(Run.FIVER, 5);
        scoreToInt.put(Run.SIX, 6);
        scoreToInt.put(Run.OUT, 0);
    }
    
    public static int getScoreValue(Run score){
        return scoreToInt.get(score);
    }
}
