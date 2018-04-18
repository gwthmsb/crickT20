package com.cricket.t20.domain;

import com.cricket.t20.enums.Run;
import com.cricket.t20.enums.RunType;
import com.cricket.t20.utils.ScoreConverter;

public class RunScored {
    private Run run;
    private RunType type;
    private int runInt;
    
    public RunScored(Run run, RunType type){
        this.run = run;
        this.type = type; 
        this.runInt = ScoreConverter.getScoreValue(run);
    }

    public Run getRun() {
        return run;
    }

    public RunType getType() {
        return type;
    }

    public int getRunInt() {
        return runInt;
    }
  
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Run : ").append(run);
        return sb.toString();
    }
}
