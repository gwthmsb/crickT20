package com.cricket.t20.domain.over;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.data.PlayersInfo_TestData;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.strike.Strike;
import com.cricket.t20.enums.BowlableBall;

public class OverTest {

    @Test
    public void testOverIntListOfPlayerStrikeListOfBowlableBallInt() {
        try{
            
            Player single = (new PlayersInfo_TestData()).getPlayer("single");
            Player doubleScorrer = (new PlayersInfo_TestData()).getPlayer("double");
            Player out = (new PlayersInfo_TestData()).getPlayer("out");
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
            
            Assert.assertNotNull(over);
            Assert.assertEquals(out, over.getNextPlayer());
            Assert.assertEquals(6, over.getOverResults().getBallsRemaining());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testOverIntListOfPlayerStrikeListOfBowlableBallIntInt() {
        try{
            Player single = (new PlayersInfo_TestData()).getPlayer("single");
            Player doubleScorrer = (new PlayersInfo_TestData()).getPlayer("double");
            Player out = (new PlayersInfo_TestData()).getPlayer("out");
            List<Player> list = new ArrayList<>();
            
            list.add(single);
            list.add(doubleScorrer);
            list.add(out);
            
            Strike strike = new Strike(single, doubleScorrer);
            List<BowlableBall> bowlableBalls = new ArrayList<>();
            
            for(int i=0;i<6;i++){
                bowlableBalls.add(BowlableBall.GOOD);
            }
            
            Over over = new Over(2, list, strike, bowlableBalls, 2, 24);
            
            Assert.assertNotNull(over);
            Assert.assertEquals(out, over.getNextPlayer());
            Assert.assertEquals(6, over.getOverResults().getBallsRemaining());
            Assert.assertTrue(over.getOverResults().isChasingTarget());
            Assert.assertEquals(24, over.getOverResults().getRemainingTarget());
        }catch(Exception e){;
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testStartOver_forOneOver() {
        try{
            System.out.println("--------------------------------");
            System.out.println("Commentary for test start over for one overs");
            Player out = (new PlayersInfo_TestData()).getPlayer("out");
            Player doubleScorer = (new PlayersInfo_TestData()).getPlayer("double");
            Player boundary = (new PlayersInfo_TestData()).getPlayer("boundary");
            List<Player> list = new ArrayList<>();
            
            list.add(out);
            list.add(doubleScorer);
            list.add(boundary);
            
            Strike strike = new Strike(doubleScorer, out);
            List<BowlableBall> bowlableBalls = new ArrayList<>();
            
            for(int i=0;i<6;i++){
                bowlableBalls.add(BowlableBall.GOOD);
            }
            
            Over over = new Over(1, list, strike, bowlableBalls, 2);
            over.startOver();
            OverResults overResults = over.getOverResults();
            
            //Needed to print commentary
            Thread.currentThread().sleep(1000);
            
            Assert.assertNotNull(overResults);
            System.out.println("Final score board for players");
            System.out.println(out.toString());
            System.out.println(doubleScorer.toString());
            System.out.println(boundary.toString());
            System.out.println("Team score : "+over.getOverResults().getRunsScoredForOver());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    
    @Test
    public void testStartOver_forTwoOver() {
        try{
            System.out.println("--------------------------------");
            System.out.println("Commentary for test start over for two overs");
            Player out = (new PlayersInfo_TestData()).getPlayer("out");
            Player doubleScorer = (new PlayersInfo_TestData()).getPlayer("double");
            Player boundary = (new PlayersInfo_TestData()).getPlayer("boundary");
            List<Player> list = new ArrayList<>();
            
            list.add(out);
            list.add(doubleScorer);
            list.add(boundary);
            
            Strike strike = new Strike(doubleScorer, out);
            List<BowlableBall> bowlableBalls = new ArrayList<>();
            
            for(int i=0;i<6;i++){
                bowlableBalls.add(BowlableBall.GOOD);
            }
            int wicketsInhand=2;
            int totalScore=0;
            for(int i=0;i<2;i++){
                Over over = new Over(i, list, strike, bowlableBalls, wicketsInhand);
                over.startOver();
                OverResults overResults = over.getOverResults();
                wicketsInhand=overResults.wicketsRemaining();
                strike = overResults.getStrikeDetails();
                if(wicketsInhand==0)
                    break;
                Assert.assertNotNull(overResults);
                totalScore+=overResults.getRunsScoredForOver();
            }
            //Needed to print commentary
            Thread.currentThread().sleep(1000);
            
            System.out.println("Final score board for players");
            System.out.println(out.toString());
            System.out.println(doubleScorer.toString());
            System.out.println(boundary.toString());
            System.out.println("Team score : "+totalScore);
            
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    
    

    @Test
    public void testGetNextPlayer() {
        try{
            Player out = (new PlayersInfo_TestData()).getPlayer("out");
            Player doubleScorer = (new PlayersInfo_TestData()).getPlayer("double");
            Player boundary = (new PlayersInfo_TestData()).getPlayer("boundary");
            List<Player> list = new ArrayList<>();
            
            list.add(out);
            list.add(doubleScorer);
            list.add(boundary);
            
            Strike strike = new Strike(doubleScorer, out);
            List<BowlableBall> bowlableBalls = new ArrayList<>();
            
            for(int i=0;i<6;i++){
                bowlableBalls.add(BowlableBall.GOOD);
            }
            Over over = new Over(1, list, strike, bowlableBalls, 2);
            
            Assert.assertEquals(boundary, over.getNextPlayer());
            
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }


    @Test
    public void testGetOverResults() {
        try{
            System.out.println("--------------------------------");
            System.out.println("Commentary for test get over results for two overs");
            Player out = (new PlayersInfo_TestData()).getPlayer("out");
            Player doubleScorer = (new PlayersInfo_TestData()).getPlayer("double");
            Player boundary = (new PlayersInfo_TestData()).getPlayer("boundary");
            List<Player> list = new ArrayList<>();
            
            list.add(out);
            list.add(doubleScorer);
            list.add(boundary);
            
            Strike strike = new Strike(doubleScorer, out);
            List<BowlableBall> bowlableBalls = new ArrayList<>();
            
            for(int i=0;i<6;i++){
                bowlableBalls.add(BowlableBall.GOOD);
            }
            int wicketsInhand=2;
            int totalScore=0;
            for(int i=0;i<2;i++){
                Over over = new Over(i, list, strike, bowlableBalls, wicketsInhand);
                over.startOver();
                OverResults overResults = over.getOverResults();
                
                Assert.assertNotNull(overResults);
                
                wicketsInhand=overResults.wicketsRemaining();
                strike = overResults.getStrikeDetails();
                totalScore+=overResults.getRunsScoredForOver();
                if(wicketsInhand==0)
                    break;
               
            }
            //Needed to print commentary
            Thread.currentThread().sleep(1000);
            
            System.out.println("Final score board for players");
            System.out.println(out.toString());
            System.out.println(doubleScorer.toString());
            System.out.println(boundary.toString());
            System.out.println("Team score : "+totalScore);
            
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
