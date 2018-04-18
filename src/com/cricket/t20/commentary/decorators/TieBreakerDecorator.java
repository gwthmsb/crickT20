package com.cricket.t20.commentary.decorators;

import com.cricket.t20.commentary.BallComment;
import com.cricket.t20.commentary.MatchEndComment;
import com.cricket.t20.references.Comment;

public class TieBreakerDecorator {

    private Comment comment;
    
    public TieBreakerDecorator(Comment comment){
        this.comment=comment;
    }
    
    public Comment decorate(){
        if(comment instanceof BallComment){
            TieBreakerDecoratorInterface dec = new BallCommentDecorator(comment);
            dec.decorateComment();
            comment = dec;
        }
        if(comment instanceof MatchEndComment){
            TieBreakerDecoratorInterface dec = new MatchEndCommentDecorator(comment);
            dec.decorateComment();
            comment = dec;
        }
        return comment;
    }
}
