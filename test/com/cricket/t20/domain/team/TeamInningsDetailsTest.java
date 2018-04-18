package com.cricket.t20.domain.team;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.data.PlayersInfo_TestData;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.strike.Strike;

public class TeamInningsDetailsTest {

    PlayersInfo_TestData info = new PlayersInfo_TestData();
    
    @Test
    public void testTeamInningsDetailsStringListOfPlayerIntIntStrike() {
        try{
            List<Player> players = new ArrayList<>();
            players.add(info.getPlayer("single"));
            players.add(info.getPlayer("double"));
            players.add(info.getPlayer("boundary"));
            
            Strike strike = new Strike(players.get(0), players.get(1));
            
            TeamInningsDetails details = new TeamInningsDetails("testTeam", players, 2, 1, strike);
            Assert.assertNotNull(details);
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testTeamInningsDetailsStringListOfPlayerIntIntStrikeInt() {
        try{
            List<Player> players = new ArrayList<>();
            players.add(info.getPlayer("single"));
            players.add(info.getPlayer("double"));
            players.add(info.getPlayer("boundary"));
            
            Strike strike = new Strike(players.get(0), players.get(1));
            
            TeamInningsDetails details = new TeamInningsDetails("testTeam", players, 2, 1, strike, 20);
            Assert.assertNotNull(details);
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testUpdateTeamInningsDetailsIntStrikeIntInt_valid1() {
        try{
            List<Player> players = new ArrayList<>();
            players.add(info.getPlayer("single"));
            players.add(info.getPlayer("double"));
            players.add(info.getPlayer("boundary"));
            
            Strike strike = new Strike(players.get(0), players.get(1));
            
            TeamInningsDetails details = new TeamInningsDetails("testTeam", players, 2, 1, strike);
            details.updateTeamInningsDetails(1, strike, 10, 0);
            
            Assert.assertEquals(0, details.getRemainingBallsInRecentlyCompletedOver());
            Assert.assertEquals(0, details.getRemainingOvers());
            Assert.assertEquals(1, details.getRemainingWickets());
            Assert.assertEquals(0, details.getTarget());
            Assert.assertEquals(10, details.getTotalRuns());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testUpdateTeamInningsDetailsIntStrikeIntInt_valid2() {
        try{
            List<Player> players = new ArrayList<>();
            players.add(info.getPlayer("single"));
            players.add(info.getPlayer("double"));
            players.add(info.getPlayer("boundary"));
            
            Strike strike = new Strike(players.get(0), players.get(1));
            
            TeamInningsDetails details = new TeamInningsDetails("testTeam", players, 2, 2, strike);
            details.updateTeamInningsDetails(1, strike, 10, 0);

            Assert.assertEquals(1, details.getRemainingOvers());
            Assert.assertEquals(1, details.getRemainingWickets());
            Assert.assertFalse(details.isInningsComplete());
            
            details.updateTeamInningsDetails(0, strike, 15, 2);
            
            Assert.assertEquals(2, details.getRemainingBallsInRecentlyCompletedOver());
            Assert.assertEquals(0, details.getRemainingOvers());
            Assert.assertEquals(0, details.getRemainingWickets());
            Assert.assertEquals(0, details.getTarget());
            Assert.assertEquals(25, details.getTotalRuns());
            Assert.assertTrue(details.isInningsComplete());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testUpdateTeamInningsDetailsIntStrikeIntIntInt() {
        try{
            List<Player> players = new ArrayList<>();
            players.add(info.getPlayer("single"));
            players.add(info.getPlayer("double"));
            players.add(info.getPlayer("boundary"));
            
            Strike strike = new Strike(players.get(0), players.get(1));
            
            TeamInningsDetails details = new TeamInningsDetails("testTeam", players, 2, 1, strike, 20);
            details.updateTeamInningsDetails(1, strike, 10, 0, 10);
            
            Assert.assertEquals(0, details.getRemainingBallsInRecentlyCompletedOver());
            Assert.assertEquals(0, details.getRemainingOvers());
            Assert.assertEquals(1, details.getRemainingWickets());
            Assert.assertEquals(10, details.getTarget());
            Assert.assertEquals(10, details.getTotalRuns());
            Assert.assertTrue(details.isInningsComplete());
            Assert.assertTrue(details.isChasing());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
