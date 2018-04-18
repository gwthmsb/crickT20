package com.cricket.t20.commentary;

import static org.junit.Assert.*;

import org.junit.Test;

public class OverEndCommentTest {

    @Test
    public void testOverEndCommentIntInt() {
        try{
            System.out.println("------------------------- OverEndComment");
            OverStartOrEndComment comment = new OverStartOrEndComment(3, 9);
            comment.getComment();
        }catch(Exception e){
            e.printStackTrace();
            fail("Not yet implemented");
        }
    }

    @Test
    public void testOverEndCommentIntIntInt() {
        try{
            System.out.println("------------------------- OverEndComment");
            OverStartOrEndComment comment = new OverStartOrEndComment(4, 9, 35);
            comment.getComment();
        }catch(Exception e){
            e.printStackTrace();
            fail("Not yet implemented");
        }
    }
}
