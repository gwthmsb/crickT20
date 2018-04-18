package com.cricket.t20.commentary.decorators;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.cricket.t20.commentary.BallComment;
import com.cricket.t20.commentary.MatchEndComment;
import com.cricket.t20.data.PlayersInfo_TestData;
import com.cricket.t20.domain.Ball;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.strike.Strike;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.references.Comment;

public class TieBreakerDecoratorTest {

    @Test
    public void testTieBreakerDecorator() {
        try{
            Player birat = (new PlayersInfo_TestData()).getPlayer("single");
            Player nodhi = (new PlayersInfo_TestData()).getPlayer("double");
            Strike strike = new Strike(birat, nodhi);
            
            Ball ball = new Ball(strike, BowlableBall.GOOD);
            ball.bowlBall();
            Comment com = new BallComment(ball, 1, 4);
            
            TieBreakerDecorator dec = new TieBreakerDecorator(com);
            Comment decCom = dec.decorate();
            Assert.assertTrue(decCom instanceof BallCommentDecorator);
            
            
            Comment comment = new MatchEndComment(true, "team1", 4, 2);
            TieBreakerDecorator dec2 = new TieBreakerDecorator(comment);
            Comment decComment = dec2.decorate();
            Assert.assertTrue(decComment instanceof MatchEndCommentDecorator);
            
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
