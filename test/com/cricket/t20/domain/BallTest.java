package com.cricket.t20.domain;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.data.PlayersInfo_TestData;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.strike.Strike;
import com.cricket.t20.enums.BowlableBall;

public class BallTest {

    @Test
    public void testBall() {
        try{
            Player birat = (new PlayersInfo_TestData()).getPlayer("single");
            Player nodhi = (new PlayersInfo_TestData()).getPlayer("double");
            Strike strike = new Strike(birat, nodhi);
            
            Ball ball = new Ball(strike, BowlableBall.GOOD);
            
            Assert.assertNotNull(ball);
            
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testBowlBall() {
        try{
            Player birat = (new PlayersInfo_TestData()).getPlayer("single");
            Player nodhi = (new PlayersInfo_TestData()).getPlayer("double");
            Strike strike = new Strike(birat, nodhi);
            
            Ball ball = new Ball(strike, BowlableBall.GOOD);

            Assert.assertNull(ball.getScoreForBall());
            ball.bowlBall();
            Assert.assertNotNull(ball.getScoreForBall());
            
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetStrikeBeforeBallBowled() {
        try{
            Player birat = (new PlayersInfo_TestData()).getPlayer("single");
            Player nodhi = (new PlayersInfo_TestData()).getPlayer("double");
            Strike strike = new Strike(birat, nodhi);
            
            Ball ball = new Ball(strike, BowlableBall.GOOD);
            Assert.assertEquals(strike, ball.getStrike());

            ball.bowlBall();
            Assert.assertEquals(strike, ball.getStrike());

        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetScoreForBall() {
        try{
            Player birat = (new PlayersInfo_TestData()).getPlayer("single");
            Player nodhi = (new PlayersInfo_TestData()).getPlayer("double");
            Strike strike = new Strike(birat, nodhi);
            
            Ball ball = new Ball(strike, BowlableBall.GOOD);
            Assert.assertNull(ball.getScoreForBall());

            ball.bowlBall();
            Assert.assertNotNull(ball.getScoreForBall());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testIsBallBowled() {
        try{
            Player birat = (new PlayersInfo_TestData()).getPlayer("single");
            Player nodhi = (new PlayersInfo_TestData()).getPlayer("double");
            Strike strike = new Strike(birat, nodhi);
            
            Ball ball = new Ball(strike, BowlableBall.GOOD);
            Assert.assertFalse(ball.isBallBowled());
            ball.bowlBall();
            Assert.assertTrue(ball.isBallBowled());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetStriker() {
        try{
            Player birat = (new PlayersInfo_TestData()).getPlayer("single");
            Player nodhi = (new PlayersInfo_TestData()).getPlayer("double");
            Strike strike = new Strike(birat, nodhi);
            
            Ball ball = new Ball(strike, BowlableBall.GOOD);
            Assert.assertNotNull(ball.getStriker());
            ball.bowlBall();
            Assert.assertNotNull(ball.getStriker());
            Assert.assertEquals(birat, ball.getStriker());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
