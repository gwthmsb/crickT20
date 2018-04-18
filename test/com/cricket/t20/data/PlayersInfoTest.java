package com.cricket.t20.data;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.domain.player.Player;
import com.cricket.t20.enums.Run;

public class PlayersInfoTest {

    @Test
    public void testGetPlayer() {
        try{
            Player player = (new PlayersInfo_TestData()).getPlayer("single");
            Assert.assertEquals("single", player.getName());
            Assert.assertEquals("Lengaburu", player.getTeam());
            Assert.assertEquals(0, player.getRunsScored());
            Assert.assertEquals(5, player.getWeighedProbability().get(Run.DOTBALL).intValue());
            Assert.assertEquals(70, player.getWeighedProbability().get(Run.SINGLE).intValue());
            Assert.assertEquals(5, player.getWeighedProbability().get(Run.BOUNDARY).intValue());
            Assert.assertEquals(2, player.getWeighedProbability().get(Run.OUT).intValue());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}

