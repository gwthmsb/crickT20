package com.cricket.t20.commentary;

import com.cricket.t20.references.Comment;

public class AllOutComment implements Comment{

    private String teamName;
    private String comment;
    
    public AllOutComment(String teamName){
        this.teamName=teamName;
        this.comment = "\t"+this.teamName+" all out";
    }

    @Override
    public String getComment() {
        return comment;
    }
}
