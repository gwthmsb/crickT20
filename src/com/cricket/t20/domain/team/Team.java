package com.cricket.t20.domain.team;

import java.util.List;

import com.cricket.t20.commentary.AllOutComment;
import com.cricket.t20.commentary.Commentary;
import com.cricket.t20.commentary.OverStartOrEndComment;
import com.cricket.t20.commentary.PlayerComment;
import com.cricket.t20.domain.over.Over;
import com.cricket.t20.domain.over.OverResults;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.strike.Strike;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.exceptions.InvalidInputException;
import com.cricket.t20.references.Comment;

public class Team {

    private String teamName;
    private List<Player> players;
    private TeamInningsDetails inningsDetails; 
    private Commentary commentary;
    
    public Team(String teamName, List<Player> players, int remainingWickets, int noOfOvers) throws InvalidInputException{
        if(players.size()-remainingWickets !=1){
            throw new InvalidInputException("Input for remaining wickets should be one less than no of players");
        }
        if(players.size()==1){
            throw new InvalidInputException("Single batsman can not play cricket");
        }
        
        this.teamName=teamName;
        this.players=players;
        this.commentary= Commentary.getCommentary();
        this.inningsDetails = new TeamInningsDetails(teamName, players, remainingWickets, noOfOvers,new Strike(players.get(0), players.get(1)));
    }
    
    public Team(String teamName, List<Player> players, int remainingWickets, int noOfOvers, int target) throws InvalidInputException{
        this(teamName,players,remainingWickets,noOfOvers);
        this.inningsDetails = new TeamInningsDetails(teamName, players, remainingWickets, noOfOvers, new Strike(players.get(0),players.get(1)), target);
    }

    public TeamInningsDetails playOver(List<BowlableBall> bowlableBalls, int overCount){
        Over over;
        if(inningsDetails.isChasing()){
            over = new Over(overCount, players, inningsDetails.getStrikeDetails(), bowlableBalls,
                inningsDetails.getRemainingWickets(), inningsDetails.getTarget());
        }else{
            over = new Over(overCount, players, inningsDetails.getStrikeDetails(), bowlableBalls,
                    inningsDetails.getRemainingWickets());
        }
        
        over.startOver();
        OverResults overResults = over.getOverResults();
        
        if(inningsDetails.isChasing()){
            this.inningsDetails.updateTeamInningsDetails(overResults.wicketsRemaining(), overResults.getStrikeDetails(), 
                    overResults.getRunsScoredForOver(), overResults.getBallsRemaining(), overResults.getRemainingTarget());
        }else{
            this.inningsDetails.updateTeamInningsDetails(overResults.wicketsRemaining(), overResults.getStrikeDetails(),
                    overResults.getRunsScoredForOver(), overResults.getBallsRemaining());
        }
        publishOverStartOrEndCommenatary(inningsDetails.getRemainingOvers(), overResults.getRunsScoredForOver(), overResults.getRemainingTarget());
        return inningsDetails;
    }
    
    public String getTeamName() {
        return teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public TeamInningsDetails getInningsDetails() {
        return this.inningsDetails;
    }
    
    private void publishOverStartOrEndCommenatary(int over, int runsScored, int target){
        if(this.inningsDetails.getRemainingWickets()==0){
            Comment allOutComment = new AllOutComment(this.teamName);
            commentary.publishCommentary(allOutComment);
        }
        if(!this.inningsDetails.isInningsComplete()){
            if(this.inningsDetails.isChasing()){
                Comment overEndComment = new OverStartOrEndComment(over, runsScored, target);
                commentary.publishCommentary(overEndComment);
            }else{
                Comment overEndComment = new OverStartOrEndComment(over, runsScored);
                commentary.publishCommentary(overEndComment);
            }
        }
    }
    
    public void publishPlayerCommentary(){
        for(Player player :players){
            Comment playerCommentary= new PlayerComment(player);
            commentary.publishCommentary(playerCommentary);
        }
    }
}
