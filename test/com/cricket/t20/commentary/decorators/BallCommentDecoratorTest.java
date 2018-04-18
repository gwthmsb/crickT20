package com.cricket.t20.commentary.decorators;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.commentary.BallComment;
import com.cricket.t20.data.PlayersInfo_TestData;
import com.cricket.t20.domain.Ball;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.strike.Strike;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.references.Comment;

public class BallCommentDecoratorTest {

    @Test
    public void testBallCommentDecorator() {
        try{
            Player birat = (new PlayersInfo_TestData()).getPlayer("single");
            Player nodhi = (new PlayersInfo_TestData()).getPlayer("double");
            Strike strike = new Strike(birat, nodhi);
            
            Ball ball = new Ball(strike, BowlableBall.GOOD);
            ball.bowlBall();
            Comment com = new BallComment(ball, 1, 4);
            BallCommentDecorator dec = new BallCommentDecorator(com);
            dec.decorateComment();
            System.out.println("-------------- Ball Comment decorator");
            System.out.println(dec.getComment());
            Assert.assertNotNull(dec);
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
