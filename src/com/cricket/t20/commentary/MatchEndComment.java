package com.cricket.t20.commentary;

import com.cricket.t20.references.Comment;

public class MatchEndComment implements Comment{
    
    private String team;
    private int margin;
    
    private boolean isTeamWin;
    private boolean chasing;
    private int ballsRemaining;
    private int wicketsRemaining;
    private String comment;
    
    public MatchEndComment(Boolean isTeamWin, String team, int margin, int wicketsRemaining) {
        this.isTeamWin=isTeamWin;
        this.team=team;
        this.margin=margin;
        this.wicketsRemaining=wicketsRemaining;
        this.populateComment();
    }
    
    public MatchEndComment(Boolean isTeamWin, String team, int margin, int wicketsRemaining, int ballsRemaining) {
        this(isTeamWin, team, margin, wicketsRemaining);
        this.ballsRemaining = ballsRemaining;
        this.chasing=true;
        this.populateComment();
    }
    
    private void populateComment(){
        comment="\n";
        if(isTeamWin){
            if(chasing){
                comment = comment.concat(team+ " won by "+wicketsRemaining+" wicket and "+ballsRemaining+ " balls remaining");
            }else{
                comment = comment.concat(team+ " won by "+margin);
            }
        }else{
            if(chasing){
                comment = comment.concat(team+ " lost by "+margin+" runs with "+wicketsRemaining+
                        " wicket and "+ballsRemaining+ " balls remaining");
            }else{
                comment = comment.concat(team+ " lost by "+wicketsRemaining+" wickets");
            }
        }

    }
    
    @Override
    public String getComment() {
        return comment;
    }

    public boolean isTeamWon(){
        return isTeamWin;
    }
    
    public String getTeamName(){
        return team;
    }
}
