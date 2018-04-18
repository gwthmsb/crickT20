package com.cricket.t20.domain.player;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.data.PlayersInfo_TestData;
import com.cricket.t20.domain.RunScored;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.enums.Run;
import com.cricket.t20.enums.RunType;

public class PlayerTest {

    static PlayersInfo_TestData data = new PlayersInfo_TestData();
    
    @Test
    public void testPlayer() {
        try{
            Assert.assertNotNull(data.getPlayer("single"));
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testPlayTheBall() {
        try{
            Player player = data.getPlayer("single");
            RunScored runScored = player.playTheBall(BowlableBall.GOOD);
             
            Assert.assertNotNull(runScored);
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testUpdateScore() {
        try{
            Player player = data.getPlayer("double");
            RunScored runScored = player.playTheBall(BowlableBall.GOOD);
             
            Assert.assertNotNull(runScored);
            player.updateScore(runScored);
            
            Assert.assertEquals(runScored.getRunInt(), player.getRunsScored());
            
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetName() {
        try{
            Player player = data.getPlayer("single");
            Assert.assertEquals("single", player.getName());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetTeam() {
        try{
            Player player = data.getPlayer("single");
            Assert.assertEquals("Lengaburu", player.getTeam());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetRunsScored() {
        try{
            Player player = data.getPlayer("boundary");
            RunScored runScored = player.playTheBall(BowlableBall.GOOD);
             
            Assert.assertNotEquals(runScored.getRunInt(), player.getRunsScored());
            Assert.assertEquals(0, player.getRunsScored());
            Assert.assertNotNull(runScored);
            player.updateScore(runScored);
            
            Assert.assertEquals(runScored.getRunInt(), player.getRunsScored());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetBallsFaced() {
        try{
            Player player = data.getPlayer("single");
            RunScored runScored = player.playTheBall(BowlableBall.GOOD);
             
            Assert.assertEquals(0, player.getBallsFaced());
            Assert.assertNotNull(runScored);
            player.updateScore(runScored);
            
            Assert.assertEquals(1, player.getBallsFaced());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetRunsPerBall() {
        try{
            Player player = data.getPlayer("onlyDouble");
            RunScored runScored = player.playTheBall(BowlableBall.GOOD);
             
            Assert.assertEquals(0, player.getBallsFaced());
            Assert.assertNotNull(runScored);
            player.updateScore(runScored);
            
            Assert.assertNotNull(player.getRunsPerBall());
            Assert.assertNotSame(0, player.getRunsPerBall().size());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testIsGotOut() {
        try{
            Player player = data.getPlayer("out");
            RunScored runScored = new RunScored(Run.OUT, RunType.LEGITIMATE);
            
            player.updateScore(runScored);
            Assert.assertTrue(player.isGotOut());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
