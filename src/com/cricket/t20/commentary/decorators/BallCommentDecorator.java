package com.cricket.t20.commentary.decorators;

import com.cricket.t20.commentary.BallComment;
import com.cricket.t20.references.Comment;

public class BallCommentDecorator implements TieBreakerDecoratorInterface{

    private Comment comment;
    private String decoratedComment;
    
    public BallCommentDecorator(Comment comment){
        this.comment=comment;
    }

    @Override
    public String getComment() {
        return decoratedComment;
    }

    @Override
    public void decorateComment() {
        if(comment instanceof BallComment){
            BallComment com = (BallComment)comment;
            switch(com.getRunScored()){
            case BOUNDARY:
            case SIX:
            case OUT:
                this.decoratedComment = comment.getComment().concat("!");
                break;
            default:
                this.decoratedComment = comment.getComment();
            }
        }
    }
}
