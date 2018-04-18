package com.cricket.t20.commentary;

import com.cricket.t20.references.Comment;

public class SignOffComment implements Comment{

    @Override
    public String getComment() {
        return "\nCommentary team singing off till next game";
    }
}
