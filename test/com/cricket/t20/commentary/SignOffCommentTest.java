package com.cricket.t20.commentary;

import org.junit.Test;

import com.cricket.t20.references.Comment;

public class SignOffCommentTest {

    @Test
    public void testPrintComment() {
        Comment comment = new SignOffComment();
        comment.getComment();
    }

}
