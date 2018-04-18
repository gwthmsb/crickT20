package com.cricket.t20.domain.over;

import java.util.List;

import com.cricket.t20.commentary.BallComment;
import com.cricket.t20.commentary.Commentary;
import com.cricket.t20.domain.Ball;
import com.cricket.t20.domain.RunScored;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.strike.Strike;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.references.OverEndUpdater;
import com.cricket.t20.references.ScoreUpdater;

public class Over {
    private int overNum;
    private List<Player> players;
    private Strike strikeDetails;
    private List<BowlableBall> bowlableBalls;
    private int noOfWicketsLeft;
    
    private OverResults overResults;
    
    private Over(){
        this.overResults = new OverResults(this.noOfWicketsLeft);
    }
    
    public Over(int overNum, List<Player> players, Strike strikeDetails, List<BowlableBall> bowlableBalls, int noOfWicketsLeft){
        this();
        this.overNum = overNum;
        this.players = players;
        this.strikeDetails = strikeDetails;
        this.bowlableBalls = bowlableBalls;
        this.noOfWicketsLeft = noOfWicketsLeft;
        this.overResults = new OverResults(noOfWicketsLeft);
    }
    
    public Over(int overNum, List<Player> players, Strike strikeDetails, List<BowlableBall> bowlableBalls, int noOfWicketsLeft, int target){
        this(overNum, players, strikeDetails, bowlableBalls, noOfWicketsLeft);
        this.overResults = new OverResults(noOfWicketsLeft, target);
    }
    
    public void startOver(){
        for(BowlableBall bowlableBall : bowlableBalls){
            if(noOfWicketsLeft<=0)
                break;
            if(overResults.isChasingTarget() && overResults.getRemainingTarget()<=0)
                break;
            int ballNum = (7-overResults.getBallsRemaining());
            Ball ball = new Ball(strikeDetails, bowlableBall);
            ball.bowlBall();
            RunScored score = ball.getScoreForBall();
            updateScore(score, bowlableBall);
            Commentary.getCommentary().publishCommentary(new BallComment(ball, overNum, ballNum));
        }
        updateStrikeAtOverEnd();
    }
    
    private void updateScore(RunScored score, BowlableBall bowlableBall){
        ScoreUpdater<Strike> updater = new OverUpdater(this, bowlableBall);
        updater.updateScore(score);
        this.strikeDetails = updater.getUpdatedValue();
        this.noOfWicketsLeft = this.overResults.wicketsRemaining();
    }
    
    private void updateStrikeAtOverEnd(){
        if(this.noOfWicketsLeft>0){
            OverEndUpdater updater = new OverUpdater(this);
            updater.changeStrikeOnOverEnd();
        }
    }
    
    public Player getNextPlayer(){
        // Because already first two players were taken
        int index = players.size()-noOfWicketsLeft+1;
        if(index>=players.size())
            return null;
        return this.players.get(players.size()-noOfWicketsLeft+1);
    }
    
    public Strike getStrikeDetails(){
        return this.strikeDetails;
    }
    
    public OverResults getOverResults(){
        return this.overResults;
    }
}
