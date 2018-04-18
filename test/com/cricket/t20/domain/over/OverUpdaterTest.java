package com.cricket.t20.domain.over;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.data.PlayersInfo_TestData;
import com.cricket.t20.domain.RunScored;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.strike.Strike;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.enums.Run;
import com.cricket.t20.enums.RunType;
import com.cricket.t20.references.OverEndUpdater;
import com.cricket.t20.references.ScoreUpdater;

public class OverUpdaterTest {

    static PlayersInfo_TestData testData = new PlayersInfo_TestData();
    
    @Test
    public void testOverUpdater() {
        try{
            
            Player single = testData.getPlayer("single");
            Player doubleScorrer = testData.getPlayer("double");
            Player out = testData.getPlayer("out");
            List<Player> list = new ArrayList<>();
            
            list.add(single);
            list.add(doubleScorrer);
            list.add(out);
            
            Strike strike = new Strike(single, doubleScorrer);
            List<BowlableBall> bowlableBalls = new ArrayList<>();
            
            for(int i=0;i<6;i++){
                bowlableBalls.add(BowlableBall.GOOD);
            }
            
            Over over = new Over(2, list, strike, bowlableBalls, 2);
            
            ScoreUpdater<Strike> overUpdater = new OverUpdater(over);
            
            Assert.assertNotNull(overUpdater);
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testOverEndUpdater() {
        try{
            Player single = testData.getPlayer("single");
            Player doubleScorrer = testData.getPlayer("double");
            Player out = testData.getPlayer("out");
            List<Player> list = new ArrayList<>();
            
            list.add(single);
            list.add(doubleScorrer);
            list.add(out);
            
            Strike strike = new Strike(single, doubleScorrer);
            List<BowlableBall> bowlableBalls = new ArrayList<>();
            
            for(int i=0;i<6;i++){
                bowlableBalls.add(BowlableBall.GOOD);
            }
            
            Over over = new Over(2, list, strike, bowlableBalls, 2);
            
            OverEndUpdater overUpdater = new OverUpdater(over);
            
            Assert.assertNotNull(overUpdater);
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }


    @Test
    public void testUpdateScore() {
        try{
            Player single = testData.getPlayer("single");
            Player doubleScorrer = testData.getPlayer("double");
            Player out = testData.getPlayer("out");
            List<Player> list = new ArrayList<>();
            
            list.add(single);
            list.add(doubleScorrer);
            list.add(out);
            
            Strike strike = new Strike(single, doubleScorrer);
            List<BowlableBall> bowlableBalls = new ArrayList<>();
            
            for(int i=0;i<6;i++){
                bowlableBalls.add(BowlableBall.GOOD);
            }
            
            Over over = new Over(2, list, strike, bowlableBalls, 2);
            
            ScoreUpdater<Strike> overUpdater = new OverUpdater(over);
            
            overUpdater.updateScore(new RunScored(Run.DOUBLE, RunType.LEGITIMATE));
            Assert.assertEquals(2, over.getOverResults().getRunsScoredForOver());
            
            overUpdater.updateScore(new RunScored(Run.TRIPLE, RunType.LEGITIMATE));
            Assert.assertEquals(5, over.getOverResults().getRunsScoredForOver());
            
            overUpdater.updateScore(new RunScored(Run.DOTBALL, RunType.LEGITIMATE));
            Assert.assertEquals(5, over.getOverResults().getRunsScoredForOver());
            
            overUpdater.updateScore(new RunScored(Run.SIX, RunType.LEGITIMATE));
            Assert.assertEquals(11, over.getOverResults().getRunsScoredForOver());
            
            overUpdater.updateScore(new RunScored(Run.BOUNDARY, RunType.LEGITIMATE));
            Assert.assertEquals(15, over.getOverResults().getRunsScoredForOver());
            
            overUpdater.updateScore(new RunScored(Run.OUT, RunType.LEGITIMATE));
            Assert.assertEquals(15, over.getOverResults().getRunsScoredForOver());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetUpdatedValue() {
        try{
            Player single = testData.getPlayer("single");
            Player doubleScorrer = testData.getPlayer("double");
            Player out = testData.getPlayer("out");
            List<Player> list = new ArrayList<>();
            
            list.add(single);
            list.add(doubleScorrer);
            list.add(out);
            
            Strike strike = new Strike(single, doubleScorrer);
            List<BowlableBall> bowlableBalls = new ArrayList<>();
            
            for(int i=0;i<6;i++){
                bowlableBalls.add(BowlableBall.GOOD);
            }
            
            Over over = new Over(2, list, strike, bowlableBalls, 2);
            
            ScoreUpdater<Strike> overUpdater = new OverUpdater(over);
            
            overUpdater.updateScore(new RunScored(Run.DOUBLE, RunType.LEGITIMATE));
            
            Strike newStrike = overUpdater.getUpdatedValue();
            Assert.assertNotNull(newStrike);
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testChangeStrikeOnOverEnd() {
        try{
            System.out.println("----------------------------- OverEndUpdaterTest");
            Player doubleScorrer = testData.getPlayer("double");
            Player out = testData.getPlayer("out");
            List<Player> list = new ArrayList<>();
            
            list.add(doubleScorrer);
            list.add(out);
            
            Strike strike = new Strike(doubleScorrer, out);
            List<BowlableBall> bowlableBalls = new ArrayList<>();
            
            for(int i=0;i<6;i++){
                bowlableBalls.add(BowlableBall.GOOD);
            }
            
            Over over = new Over(2, list, strike, bowlableBalls, 1);
            over.startOver();
            
            OverEndUpdater overUpdater = new OverUpdater(over);
            
            Assert.assertNotNull(overUpdater);
            /*
             * overUpdater.changeStrikeOnOverEnd() can not be tested until unless WeighedRandomNumberGenerator logic is 
             * changed to some robust implementation
             * 
             * Then we can use below player object
             * 
             * Player doubleScorrer = testData.getPlayer("onlyDouble");
             */
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
