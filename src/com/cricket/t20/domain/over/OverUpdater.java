package com.cricket.t20.domain.over;

import com.cricket.t20.domain.RunScored;
import com.cricket.t20.domain.strike.Strike;
import com.cricket.t20.domain.strike.StrikeUpdater;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.references.OverEndUpdater;
import com.cricket.t20.references.ScoreUpdater;

public class OverUpdater implements ScoreUpdater<Strike>, OverEndUpdater{

    private Over over;
    private ScoreUpdater<Strike> strikeUpdater;
    private BowlableBall ballType;
    
    private Strike strikeDetails;
    
    public OverUpdater(Over over){
        this.over=over;
        this.ballType= BowlableBall.GOOD;
        this.strikeUpdater = new StrikeUpdater(over.getStrikeDetails(), over.getNextPlayer());
    }
    
    public OverUpdater(Over over, BowlableBall ballType) {
        this.over = over;
        this.ballType = ballType;
        this.strikeUpdater = new StrikeUpdater(over.getStrikeDetails(), over.getNextPlayer());
    }
    
    @Override
    public void updateScore(RunScored score) {
        strikeUpdater.updateScore(score);
        strikeDetails = strikeUpdater.getUpdatedValue();
        over.getOverResults().updateScore(ballType, score, strikeDetails);
    }

    @Override
    public Strike getUpdatedValue() {
        return strikeDetails;
    }

    @Override
    public void changeStrikeOnOverEnd() {
        over.getOverResults().updateStrikeOnOverEnd();
    }
}
