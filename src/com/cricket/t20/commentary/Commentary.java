package com.cricket.t20.commentary;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.cricket.t20.commentary.decorators.TieBreakerDecorator;
import com.cricket.t20.commentary.thread.CommentaryThreadFactory;
import com.cricket.t20.references.Comment;

public class Commentary {
    
    private ExecutorService service; 
    private BlockingQueue<Comment> commentaryQueue;
    private Commentator commentator;
    
    private Commentary(){
        this.service = Executors.newSingleThreadExecutor(new CommentaryThreadFactory());
        this.commentaryQueue = new LinkedBlockingQueue<>();
        this.startThread();
    }
 
    private void startThread(){
        commentator = new Commentator(commentaryQueue);
        service.execute(commentator);
    }
    
    private static final class HELPER{
        private static final Commentary INSTANCE = new Commentary();
    }
    
    public static Commentary getCommentary(){
        return HELPER.INSTANCE;
    }
    
    public void endCommentary(){
        Comment signOffComment = new SignOffComment();
        this.commentaryQueue.add(signOffComment);
        this.service.shutdown();
    }
    
    public void publishCommentary(Comment comment){
        this.commentaryQueue.add(comment);
    }
    
    public void decorateCommentary(){
        this.commentator.applyDecorator();
        
    }
    
    private class Commentator implements Runnable{
        private BlockingQueue<Comment> commentaryQueue;
        private boolean applyDecorator;
        
        Commentator(BlockingQueue<Comment> commentaryQueue) {
            this.commentaryQueue = commentaryQueue;
        }
        
        public void applyDecorator(){
            this.applyDecorator=true;
        }
        
        public void run(){
            while(true){
                try{
                    Comment comment = commentaryQueue.take();
                    
                    if(applyDecorator) {
                        TieBreakerDecorator decorator = new TieBreakerDecorator(comment);
                        comment = decorator.decorate();
                    }
                    System.out.print(comment.getComment());
                    if(comment instanceof SignOffComment){
                        break;
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
    
}
