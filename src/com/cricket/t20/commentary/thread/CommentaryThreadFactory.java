package com.cricket.t20.commentary.thread;

import java.util.concurrent.ThreadFactory;

public class CommentaryThreadFactory implements ThreadFactory{
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("Commentator");
        return thread;
      }
}
