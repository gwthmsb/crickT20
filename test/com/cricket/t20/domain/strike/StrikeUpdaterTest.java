package com.cricket.t20.domain.strike;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.data.PlayersInfo_TestData;
import com.cricket.t20.domain.RunScored;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.enums.Run;
import com.cricket.t20.enums.RunType;
import com.cricket.t20.references.ScoreUpdater;

public class StrikeUpdaterTest {

    @Test
    public void testStrikeUpdaterStrikePlayer() {
        try{
            Player single = (new PlayersInfo_TestData()).getPlayer("single");
            Player doubleScorer = (new PlayersInfo_TestData()).getPlayer("double");
            Player out = (new PlayersInfo_TestData()).getPlayer("out");
            Strike strike = new Strike(single, doubleScorer);
            
            ScoreUpdater<Strike> strikeUpdater = new StrikeUpdater(strike, out);
            
            Assert.assertNotNull(strikeUpdater);
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testUpdateScore() {
        try{
            Player single = (new PlayersInfo_TestData()).getPlayer("single");
            Player doubleScorer = (new PlayersInfo_TestData()).getPlayer("double");
            Player out = (new PlayersInfo_TestData()).getPlayer("out");
            Strike strike = new Strike(single, doubleScorer);
            
            ScoreUpdater<Strike> strikeUpdater = new StrikeUpdater(strike, out);
            RunScored runScored = new RunScored(Run.SINGLE, RunType.LEGITIMATE);
            
            strikeUpdater.updateScore(runScored);
            Strike newStrike = strikeUpdater.getUpdatedValue();
                
            Assert.assertEquals(strike.getStriker(), newStrike.getNonStriker());
            Assert.assertEquals(strike.getNonStriker(), newStrike.getStriker());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
