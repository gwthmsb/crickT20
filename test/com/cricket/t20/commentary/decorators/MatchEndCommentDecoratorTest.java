package com.cricket.t20.commentary.decorators;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.cricket.t20.commentary.MatchEndComment;
import com.cricket.t20.references.Comment;

public class MatchEndCommentDecoratorTest {

    @Test
    public void testMatchEndCommentDecorator() {
        try{
            Comment comment = new MatchEndComment(true, "team1", 4, 2);
            MatchEndCommentDecorator dec = new MatchEndCommentDecorator(comment);
            dec.decorateComment();
            System.out.println(dec.getComment());
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
