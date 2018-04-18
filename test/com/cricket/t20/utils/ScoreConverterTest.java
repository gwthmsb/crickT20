package com.cricket.t20.utils;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.enums.Run;

public class ScoreConverterTest {

    @Test
    public void testGetScoreValue() {
        try{
            Assert.assertEquals(0, ScoreConverter.getScoreValue(Run.DOTBALL));
            Assert.assertEquals(1, ScoreConverter.getScoreValue(Run.SINGLE));
            Assert.assertEquals(2, ScoreConverter.getScoreValue(Run.DOUBLE));
            Assert.assertEquals(3, ScoreConverter.getScoreValue(Run.TRIPLE));
            Assert.assertEquals(4, ScoreConverter.getScoreValue(Run.BOUNDARY));
            Assert.assertEquals(5, ScoreConverter.getScoreValue(Run.FIVER));
            Assert.assertEquals(6, ScoreConverter.getScoreValue(Run.SIX));
            Assert.assertEquals(0, ScoreConverter.getScoreValue(Run.OUT));
           
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
