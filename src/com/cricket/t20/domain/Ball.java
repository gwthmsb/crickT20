package com.cricket.t20.domain;

import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.strike.Strike;
import com.cricket.t20.enums.BowlableBall;

public class Ball {

    private Strike strike;
    private RunScored runScored;
    private boolean ballBowled;
    private BowlableBall ballType;
    
    public Ball(Strike strike, BowlableBall ballType){
        this.strike = strike;
        this.ballType = ballType;
    }
    
    public void bowlBall(){
        this.ballBowled = true;
        runScored = this.strike.getStriker().playTheBall(this.ballType);
    }
    
    public Strike getStrike(){
        return this.strike;
    }
    
    public RunScored getScoreForBall(){
        return this.runScored;
    }
    
    public boolean isBallBowled() {
        return ballBowled;
    }
    
    public Player getStriker(){
        return strike.getStriker();
    }
}
