package com.cricket.t20.domain.team;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.data.PlayersInfo_TestData;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.enums.BowlableBall;

public class TeamTest {

    PlayersInfo_TestData info = new PlayersInfo_TestData();
    
    @Test
    public void testTeamStringListOfPlayerIntInt() {
        try{
            List<Player> players = new ArrayList<>();
            players.add(info.getPlayer("single"));
            players.add(info.getPlayer("double"));
            players.add(info.getPlayer("boundary"));
            
            Team team = new Team("testTeam", players, 2, 2);
            Assert.assertNotNull(team);
            Assert.assertEquals("testTeam", team.getTeamName());
            Assert.assertEquals(3, team.getPlayers().size());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testTeamStringListOfPlayerIntIntInt() {
        try{
            List<Player> players = new ArrayList<>();
            players.add(info.getPlayer("single"));
            players.add(info.getPlayer("double"));
            players.add(info.getPlayer("boundary"));
            
            Team team = new Team("testTeam", players, 2, 2,40);
            Assert.assertNotNull(team);
            Assert.assertEquals("testTeam", team.getTeamName());
            Assert.assertEquals(3, team.getPlayers().size());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testPlayOver() {
        try{
            System.out.println("-------------------------------------- Team PlayOver test");
            List<Player> players = new ArrayList<>();
            players.add(info.getPlayer("single"));
            players.add(info.getPlayer("double"));
            players.add(info.getPlayer("boundary"));
            
            Team team = new Team("testTeam", players, 2, 2);
            
            List<BowlableBall> bowlableBalls = new ArrayList<>();
            
            for(int i=0;i<6;i++){
                bowlableBalls.add(BowlableBall.GOOD);
            }
            
            TeamInningsDetails details = team.playOver(bowlableBalls, 0);
            
            Assert.assertNotNull(team);
            Assert.assertEquals(2, details.getNoOfOvers());
            Assert.assertEquals(1, details.getRemainingOvers());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
