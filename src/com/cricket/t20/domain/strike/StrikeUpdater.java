package com.cricket.t20.domain.strike;

import com.cricket.t20.domain.RunScored;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.references.ScoreUpdater;

public class StrikeUpdater implements ScoreUpdater<Strike>{

    private Strike strike;
    private Player nextBatsman;

    public StrikeUpdater(Strike strike, Player nextBatsman) {
        this.strike = strike;
        this.nextBatsman = nextBatsman;
    }
    
    @Override
    public void updateScore(RunScored score) {
        switch(score.getRun()){
        case SINGLE:
        case TRIPLE:
        case FIVER:
            this.strike.getStriker().updateScore(score);
            this.strike= strike.changeStrikeAndClone();
            break;
        case DOTBALL:
        case DOUBLE:
        case BOUNDARY:
        case SIX:
            this.strike.getStriker().updateScore(score);
            break;
        case OUT:
            this.strike.getStriker().updateScore(score);
            this.strike = new Strike(this.nextBatsman, this.strike.getNonStriker());
        }
    }

    @Override
    public Strike getUpdatedValue() {
        return strike;
    }

}
