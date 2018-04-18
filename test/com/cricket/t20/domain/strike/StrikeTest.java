package com.cricket.t20.domain.strike;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.data.PlayersInfo_TestData;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.enums.BowlableBall;

public class StrikeTest {

    @Test
    public void testStrike() {
        try{
            Player single = (new PlayersInfo_TestData()).getPlayer("single");
            Player doubleScorer = (new PlayersInfo_TestData()).getPlayer("double");

            Strike strike = new Strike(single, doubleScorer);
            Assert.assertNotNull(strike);
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testCloneStrike() {
        try{
            Player single = (new PlayersInfo_TestData()).getPlayer("single");
            Player doubleScorer = (new PlayersInfo_TestData()).getPlayer("double");

            Strike strike = new Strike(single, doubleScorer);
            Strike newStrike = strike.cloneStrike();
            
            Assert.assertNotEquals(strike, newStrike);
            Assert.assertEquals(strike.getStriker(), newStrike.getStriker());
            Assert.assertEquals(strike.getNonStriker(), newStrike.getNonStriker());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testChangeStrikeAndClone() {
        try{
            Player single = (new PlayersInfo_TestData()).getPlayer("single");
            Player doubleScorer = (new PlayersInfo_TestData()).getPlayer("double");

            Strike strike = new Strike(single, doubleScorer);
            Strike newStrike = strike.changeStrikeAndClone();
            
            Assert.assertNotEquals(strike, newStrike);
            Assert.assertEquals(strike.getStriker(), newStrike.getNonStriker());
            Assert.assertEquals(strike.getNonStriker(), newStrike.getStriker());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetStriker() {
        try{
            Player single = (new PlayersInfo_TestData()).getPlayer("single");
            Player doubleScorer = (new PlayersInfo_TestData()).getPlayer("double");

            Strike strike = new Strike(single, doubleScorer);
            Assert.assertNotNull(strike.getStriker());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetNonStriker() {
        try{
            Player single = (new PlayersInfo_TestData()).getPlayer("single");
            Player doubleScorer = (new PlayersInfo_TestData()).getPlayer("double");

            Strike strike = new Strike(single, doubleScorer);
            Assert.assertNotNull(strike.getNonStriker());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
