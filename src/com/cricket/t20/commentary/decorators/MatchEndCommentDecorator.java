package com.cricket.t20.commentary.decorators;

import com.cricket.t20.commentary.MatchEndComment;
import com.cricket.t20.references.Comment;

public class MatchEndCommentDecorator implements TieBreakerDecoratorInterface{

    private Comment comment;
    private String decoratedComment;

    public MatchEndCommentDecorator(Comment comment) {
        this.comment=comment;
    }
    
    @Override
    public String getComment() {
        return decoratedComment;
    }

    @Override
    public void decorateComment() {
        decoratedComment = comment.getComment();
        if(comment instanceof MatchEndComment){
            MatchEndComment com = (MatchEndComment)comment;
            if(com.isTeamWon()){
                decoratedComment="\t "+com.getTeamName()+" wins!"+comment.getComment();
            }
        }
    }
    
}
