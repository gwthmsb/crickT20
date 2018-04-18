package com.cricket.t20.domain.team;

import java.util.List;

import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.strike.Strike;

public class TeamInningsDetails {

    private String teamName;
    private List<Player> players;
    private boolean isChasing;
    private boolean inningsComplete;
    private int remainingWickets;
    private int remainingOvers;
    private int remainingBallsInRecentlyCompletedOver;
    private int noOfOvers;
    private int totalRuns;
    
    private int target;
    private Strike strikeDetails;
    private boolean runChasedSuccessFully;
    
    public TeamInningsDetails(String teamName, List<Player> players, int remainingWickets, int noOfOvers, Strike strike){
        this.teamName=teamName;
        this.players=players;
        this.strikeDetails=strike;
        this.remainingWickets=remainingWickets;
        this.noOfOvers=noOfOvers;
        this.remainingOvers=noOfOvers;
    }
    
    public TeamInningsDetails(String teamName, List<Player> players, int remainingWickets, int noOfOvers, Strike strike, int target){
        this(teamName, players, remainingWickets, noOfOvers, strike);
        this.target=target;
        this.isChasing=true;
    }

    public void updateTeamInningsDetails(int wicketsRemaining, Strike strikeDetails, int runsScored, int ballsRemaining){
        if(!inningsComplete){
            this.remainingOvers--;
            this.remainingWickets=wicketsRemaining;
            this.strikeDetails=strikeDetails;
            this.totalRuns+=runsScored;
            this.remainingBallsInRecentlyCompletedOver=ballsRemaining;
            
            if(this.remainingWickets<=0 || this.remainingOvers<=0)
                this.inningsComplete=true;
        }else{
            System.out.println(teamName+" innings has completed");
        }
    }
    
    public void updateTeamInningsDetails(int wicketsRemaining, Strike strikeDetails, int runsScored, int ballsRemaining, int target){
        if(!inningsComplete){
            this.updateTeamInningsDetails(wicketsRemaining, strikeDetails, runsScored, ballsRemaining);
            this.target=target;
            if(this.target<=0){
                this.inningsComplete=true;
                this.runChasedSuccessFully=true;
            }                
        }else{
            System.out.println(teamName+" innings has completed");
        }
    }
    
    public boolean isChasing() {
        return isChasing;
    }

    public boolean isInningsComplete() {
        return inningsComplete;
    }

    public int getRemainingWickets() {
        return remainingWickets;
    }

    public int getRemainingOvers() {
        return remainingOvers;
    }

    public int getNoOfOvers() {
        return noOfOvers;
    }

    public int getTarget() {
        return target;
    }

    public int getRemainingBallsInRecentlyCompletedOver() {
        return remainingBallsInRecentlyCompletedOver;
    }

    public Strike getStrikeDetails() {
        return strikeDetails;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }
    
    public int getTotalRuns() {
        return totalRuns;
    }
    
    public void updateCompleteInnings(){
        this.inningsComplete=true;
    }
    
    public boolean isRunChasedSuccessFully() {
        return runChasedSuccessFully;
    }
}
