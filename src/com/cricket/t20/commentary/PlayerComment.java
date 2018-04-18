package com.cricket.t20.commentary;

import com.cricket.t20.domain.player.Player;
import com.cricket.t20.references.Comment;

public class PlayerComment implements Comment{

    private Player player;
    private String comment="\n";
    
    public PlayerComment(Player player){
        this.player = player;
        this.populateComment();
    }

    private void populateComment(){
        String balls = (player.getBallsFaced()==1)?" ball":" balls";
        String notOut=(!player.isGotOut() && player.didBat())?"*":"";
        comment = comment.concat(player.getName()+" - "+player.getRunsScored()+notOut+
                "("+player.getBallsFaced()+balls+")");
    }
    
    @Override
    public String getComment() {
        return comment;
    }
    
}
