package com.cricket.t20.commentary;

import com.cricket.t20.references.Comment;

public class OverStartOrEndComment implements Comment{

    private int over;
    private int runsScored;
    
    private int target;
    private boolean chasing;
    
    private String comment;

    public OverStartOrEndComment(int over, int runsScored) {
        this.over=over;
        this.runsScored=runsScored;
        this.populateComment();
    }
    
    public OverStartOrEndComment(int over, int runsScored, int target) {
        this(over, runsScored);
        this.target=target;
        this.chasing = true;
        this.populateComment();
    }
    
    private void populateComment(){
        if(chasing){
            comment = "\n"+over+" overs left. "+target+" runs to win";
        }else{
            comment = "\n"+over+" overs complete. "+runsScored+" runs scored in this over";
        }
    }
    
    @Override
    public String getComment() {
        return comment;
    }
}
