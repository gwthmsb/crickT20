package com.cricket.t20.commentary;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.cricket.t20.data.PlayersInfo_TestData;
import com.cricket.t20.domain.Ball;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.strike.Strike;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.references.Comment;

public class BallCommentTest {

    @Test
    public void testPrintComment() {
        try{
            System.out.println("------------------------- BallEndComment");
            Player birat = (new PlayersInfo_TestData()).getPlayer("single");
            Player nodhi = (new PlayersInfo_TestData()).getPlayer("double");
            Strike strike = new Strike(birat, nodhi);
            
            Ball ball = new Ball(strike, BowlableBall.GOOD);
            ball.bowlBall();
            
            Comment comment = new BallComment(ball, 1,5);
            comment.getComment();
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
