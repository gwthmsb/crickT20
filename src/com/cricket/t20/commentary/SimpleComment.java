package com.cricket.t20.commentary;

import com.cricket.t20.references.Comment;

public class SimpleComment implements Comment{

    private String comment;
    public SimpleComment(String comment){
        this.comment = "\n"+comment;
    }
    
    public String getComment(){
        return comment;
    }
}
