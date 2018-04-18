package com.cricket.t20.commentary;

import com.cricket.t20.domain.team.Team;
import com.cricket.t20.references.Comment;

public class InningsCommentary implements Comment{
    
    private String comment="\n";
    public InningsCommentary(Team team) {
        this.comment = this.comment.concat(team.getTeamName() + " innings:");    
    }
    
    @Override
    public String getComment() {
        return comment;
    }

}
