package com.cricket.t20.commentary;

import com.cricket.t20.domain.Ball;
import com.cricket.t20.enums.Run;
import com.cricket.t20.references.Comment;

public class BallComment implements Comment{

    private Ball ball;
    private int over;
    private int ballNo;
    
    private String comment = "\n";
    
    public BallComment(Ball ball, int over, int ballNo){
        this.ball = ball;
        this.over = over;
        this.ballNo = ballNo;
        this.populateComment();
    }
    
    private void populateComment(){
        String runs=" runs";
        if(ball.getScoreForBall().getRun().equals(Run.SINGLE))
            runs=" run";
        
        if(ball.getScoreForBall().getRun().equals(Run.OUT)){
            comment = comment.concat(this.over+"."+this.ballNo+" "+ball.getStriker().getName() + " got out");
        }else{
            comment = comment.concat(this.over+"."+this.ballNo+" "+ball.getStriker().getName() + " scores "+ball.getScoreForBall().getRunInt() + runs);
        }
    }
    
    public String getComment(){
        return comment;
    }
    
    public Run getRunScored(){
        return ball.getScoreForBall().getRun();
    }
}
