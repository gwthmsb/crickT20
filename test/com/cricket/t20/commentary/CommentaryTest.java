package com.cricket.t20.commentary;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.data.PlayersInfo_TestData;
import com.cricket.t20.domain.Ball;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.strike.Strike;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.references.Comment;

public class CommentaryTest {

    @Test
    public void testGetCommentary() {
        try{
            Commentary commentary = Commentary.getCommentary();
            Assert.assertNotNull(commentary);
            commentary.endCommentary();
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testPublishCommentary() {
        try{
            System.out.println("------------------------- Commentary test");
            Commentary commentary = Commentary.getCommentary();
            
            Player birat = (new PlayersInfo_TestData()).getPlayer("double");
            Player nodhi = (new PlayersInfo_TestData()).getPlayer("boundary");
            Strike strike = new Strike(birat, nodhi);
            
            Ball ball = new Ball(strike, BowlableBall.GOOD);
            ball.bowlBall();
            
            Comment comment = new BallComment(ball, 1,1);
            ball.bowlBall();
            Comment comment2 = new BallComment(ball, 1,2);

            commentary.publishCommentary(comment);
            commentary.publishCommentary(comment2);
            
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
