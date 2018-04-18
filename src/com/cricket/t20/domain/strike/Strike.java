package com.cricket.t20.domain.strike;

import com.cricket.t20.domain.player.Player;

public class Strike {

    private final Player striker;
    private final Player nonStriker;
    
    public Strike(Player striker, Player nonStriker){
        this.striker = striker;
        this.nonStriker = nonStriker;
        this.playersGotChanceToBat();
    }
    
    public Strike cloneStrike(){
        return new Strike(striker, nonStriker);
    }
    
    public Strike changeStrikeAndClone(){
        return new Strike(nonStriker, striker);
    }

    public Player getStriker() {
        return striker;
    }

    public Player getNonStriker() {
        return nonStriker;
    }
    
    private void playersGotChanceToBat(){
        if(striker!=null) this.striker.gotChanceToBat();
        if(nonStriker!=null) this.nonStriker.gotChanceToBat();
    }
}
