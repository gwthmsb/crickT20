package com.cricket.t20.domain.over;

import java.util.ArrayList;
import java.util.List;

import com.cricket.t20.domain.RunScored;
import com.cricket.t20.domain.strike.Strike;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.enums.Run;

public class OverResults {

    private int runsScored;
    private List<RunScored> runsPerBall;
    private int ballsRemaining;
    private int wicketsRemaining;
    private Strike strikeDetails;
    
    private boolean chasingTarget;
    private int target;
    
    public OverResults(int wicketsRemaining){
        this.wicketsRemaining = wicketsRemaining;
        this.ballsRemaining = 6;
        this.runsPerBall = new ArrayList<>();
    }
    
    public OverResults(int wicketsRemaining, int target){
        this(wicketsRemaining);
        this.chasingTarget=true;
        this.target = target;
    }
    
    public void updateScore(BowlableBall ballType, RunScored runScored, Strike strike){
       if(this.ballsRemaining>0 && !(this.chasingTarget && target<=0)){
           if(this.chasingTarget && target>0)
               this.target -= runScored.getRunInt();
           
           this.runsScored += runScored.getRunInt();
           this.runsPerBall.add(runScored);
           this.strikeDetails = strike;

           if(runScored.getRun().equals(Run.OUT))
               wicketsRemaining--;
           
           if(ballType.equals(BowlableBall.GOOD))
               ballsRemaining--;
       }else{
           if(chasingTarget)
               System.out.println("Target reached");
           else
               System.out.println("Over is complete");
       }
    }
    
    public void updateStrikeOnOverEnd(){
        this.strikeDetails=strikeDetails.changeStrikeAndClone();
    }
    
    public int getBallsRemaining(){
        return this.ballsRemaining;
    }
    
    public int wicketsRemaining(){
        return this.wicketsRemaining;
    }

    public int getRemainingTarget(){
        return this.target;
    }
    
    public boolean isChasingTarget(){
        return this.chasingTarget;
    }
    
    public Strike getStrikeDetails(){
        return this.strikeDetails;
    }
    
    public int getRunsScoredForOver(){
        return this.runsScored;
    }
}
