package com.cricket.t20.commentary;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.cricket.t20.references.Comment;

public class MatchEndCommentTest {

    @Test
    public void testMatchEndCommentStringInt_gameWon() {
        try{
            System.out.println("------------------------- MatchEndComment");
            Comment comment = new MatchEndComment(true, "TeamOne", 10, 0);
            comment.getComment();
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testMatchEndCommentStringIntInt_gameWon() {
        try{
            System.out.println("------------------------- MatchEndComment");
            Comment comment = new MatchEndComment(true,"TeamTwo",0, 5,3);
            comment.getComment();
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testMatchEndCommentStringInt_gameLost() {
        try{
            System.out.println("------------------------- MatchEndComment");
            Comment comment = new MatchEndComment(false, "TeamOne", 0, 4);
            comment.getComment();
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testMatchEndCommentStringIntInt_gameLost() {
        try{
            System.out.println("------------------------- MatchEndComment");
            Comment comment = new MatchEndComment(false,"TeamTwo",20, 5,0);
            comment.getComment();
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
