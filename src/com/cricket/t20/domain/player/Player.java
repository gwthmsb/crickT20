package com.cricket.t20.domain.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.cricket.t20.domain.RunScored;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.enums.Run;
import com.cricket.t20.enums.RunType;
import com.cricket.t20.utils.WeighedRandomNumGenerator;

public class Player {

    private String name;
    private String team;
    private Map<Run, Integer> weighedProbability;
    private int runsScored;
    private int ballsFaced;
    private List<Run> runsPerBall;
    private boolean batted;
    private boolean out;
    
    private WeighedRandomNumGenerator randomNumGen;
    
    public Player(String name, String team, Map<Run, Integer> weighedProbability){
        this.name = name;
        this.team = team;
        this.runsPerBall = new ArrayList<>();
        this.weighedProbability = weighedProbability;
        this.randomNumGen = new WeighedRandomNumGenerator(getWeighedProbForRandomNumGen());
    }

    private NavigableMap<Integer, Run> getWeighedProbForRandomNumGen(){
        NavigableMap<Integer, Run> runsMap = new TreeMap<>();

        int count=0;
        for(Map.Entry<Run, Integer> entry: weighedProbability.entrySet()){
            runsMap.put((count=count+entry.getValue()), entry.getKey());
        }
        return runsMap;
    }

    public RunScored playTheBall(BowlableBall ballType){
        Run run = this.randomNumGen.next();
        return new RunScored(run, getRunType(ballType));
    }
    
    /**
     * Extra condition on type of run can be added here. Like condition for Legbyes or Byes.
     * Same goes with ball type. Like Wide ball or no-ball or free hit
     * 
     *  if(ballType.WIDE.equals(ballType)){
     *   }
     */
    private RunType getRunType(BowlableBall ballType){
        return RunType.LEGITIMATE;
    }
    
    /**
     * Condition check for different ways through which run is scored can be added here
     * Like, LegByes, Byes 
     */
    public void updateScore(RunScored score){
        this.runsScored+=score.getRunInt();
        this.ballsFaced++;
        this.runsPerBall.add(score.getRun());
        if(Run.OUT.equals(score.getRun())) out=true;
    }
    
    public String getName() {
        return name;
    }

    public String getTeam(){
        return team;
    }
    
    public Map<Run, Integer> getWeighedProbability() {
        return weighedProbability;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public List<Run> getRunsPerBall() {
        return runsPerBall;
    }

    public boolean isGotOut() {
        return out;
    }
    
    public void gotChanceToBat(){
        this.batted = true;
    }
    
    public boolean didBat(){
        return this.batted;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Player Name : ").append(name).append("\tTeam : ").append(team).append("\tRuns scored : ").append(runsScored)
                .append("\tBalls played : "+ballsFaced+"\tOut : "+out);
        return sb.toString();
    }
}
