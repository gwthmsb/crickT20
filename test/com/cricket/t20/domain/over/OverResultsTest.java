package com.cricket.t20.domain.over;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.data.PlayersInfo_TestData;
import com.cricket.t20.domain.RunScored;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.strike.Strike;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.enums.Run;
import com.cricket.t20.enums.RunType;

public class OverResultsTest {

    @Test
    public void testOverResultsInt() {
        try{
            OverResults result = new OverResults(2);
            Assert.assertNotNull(result);
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testOverResultsIntInt() {
        try{
            OverResults result = new OverResults(2, 3);
            Assert.assertNotNull(result);
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testUpdateScore() {
        try{
            Player birat = (new PlayersInfo_TestData()).getPlayer("Birat");
            Player nodhi = (new PlayersInfo_TestData()).getPlayer("Nodhi");
            Strike strike = new Strike(birat, nodhi);
            
            OverResults result = new OverResults(2);
            
            result.updateScore(BowlableBall.GOOD, new RunScored(Run.BOUNDARY, RunType.LEGITIMATE), strike);
            Assert.assertEquals(5,result.getBallsRemaining());
            Assert.assertEquals(4, result.getRunsScoredForOver());
            Assert.assertEquals(strike.getStriker(), result.getStrikeDetails().getStriker());
            
            result.updateScore(BowlableBall.GOOD, new RunScored(Run.SINGLE, RunType.LEGITIMATE), strike);
            Assert.assertEquals(4,result.getBallsRemaining());
            Assert.assertEquals(5, result.getRunsScoredForOver());
            Assert.assertEquals(strike.getStriker(), result.getStrikeDetails().getStriker());
            
            result.updateScore(BowlableBall.GOOD, new RunScored(Run.BOUNDARY, RunType.LEGITIMATE), strike);
            Assert.assertEquals(3,result.getBallsRemaining());
            Assert.assertEquals(9, result.getRunsScoredForOver());
            Assert.assertEquals(strike.getStriker(), result.getStrikeDetails().getStriker());
            
            result.updateScore(BowlableBall.GOOD, new RunScored(Run.SIX, RunType.LEGITIMATE), strike);
            Assert.assertEquals(2,result.getBallsRemaining());
            Assert.assertEquals(15, result.getRunsScoredForOver());
            Assert.assertEquals(strike.getStriker(), result.getStrikeDetails().getStriker());
            
            result.updateScore(BowlableBall.GOOD, new RunScored(Run.DOTBALL, RunType.LEGITIMATE), strike);
            Assert.assertEquals(1,result.getBallsRemaining());
            Assert.assertEquals(15, result.getRunsScoredForOver());
            Assert.assertEquals(strike.getStriker(), result.getStrikeDetails().getStriker());
            
            result.updateScore(BowlableBall.GOOD, new RunScored(Run.BOUNDARY, RunType.LEGITIMATE), strike);
            Assert.assertEquals(0,result.getBallsRemaining());
            Assert.assertEquals(19, result.getRunsScoredForOver());
            Assert.assertEquals(strike.getStriker(), result.getStrikeDetails().getStriker());
            
            
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testWicketsRemaining() {
        try{
            Player birat = (new PlayersInfo_TestData()).getPlayer("Birat");
            Player nodhi = (new PlayersInfo_TestData()).getPlayer("Nodhi");
            Strike strike = new Strike(birat, nodhi);
            
            OverResults result = new OverResults(1);
            
            result.updateScore(BowlableBall.GOOD, new RunScored(Run.OUT, RunType.LEGITIMATE), strike);
            Assert.assertEquals(5,result.getBallsRemaining());
            Assert.assertEquals(0, result.getRunsScoredForOver());
            Assert.assertEquals(0, result.wicketsRemaining());
            Assert.assertEquals(strike.getStriker(), result.getStrikeDetails().getStriker());
            
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetRemainingTarget() {
        try{
            
            Player birat = (new PlayersInfo_TestData()).getPlayer("Birat");
            Player nodhi = (new PlayersInfo_TestData()).getPlayer("Nodhi");
            Strike strike = new Strike(birat, nodhi);
            
            OverResults result = new OverResults(1, 12);
            
            result.updateScore(BowlableBall.GOOD, new RunScored(Run.SIX, RunType.LEGITIMATE), strike);
            Assert.assertEquals(6, result.getRunsScoredForOver());
            Assert.assertEquals(1, result.wicketsRemaining());
            Assert.assertEquals(6, result.getRemainingTarget());
            
            result.updateScore(BowlableBall.GOOD, new RunScored(Run.SIX, RunType.LEGITIMATE), strike);
            Assert.assertEquals(12, result.getRunsScoredForOver());
            Assert.assertEquals(1, result.wicketsRemaining());
            Assert.assertEquals(0, result.getRemainingTarget());
            
            result.updateScore(BowlableBall.GOOD, new RunScored(Run.SIX, RunType.LEGITIMATE), strike);
            Assert.assertEquals(12, result.getRunsScoredForOver());
            Assert.assertEquals(1, result.wicketsRemaining());
            Assert.assertEquals(0, result.getRemainingTarget());
            
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testIsChasingTarget() {
        try{
            OverResults result = new OverResults(1, 12);
            
            Assert.assertTrue(result.isChasingTarget());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
