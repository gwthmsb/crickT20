package com.cricket.t20.last.four;

import java.util.ArrayList;
import java.util.List;

import com.cricket.t20.commentary.Commentary;
import com.cricket.t20.commentary.MatchEndComment;
import com.cricket.t20.commentary.OverStartOrEndComment;
import com.cricket.t20.commentary.SimpleComment;
import com.cricket.t20.data.PlayersInfo;
import com.cricket.t20.data.PlayersName;
import com.cricket.t20.data.TeamsName;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.team.Team;
import com.cricket.t20.domain.team.TeamInningsDetails;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.exceptions.InvalidInputException;
import com.cricket.t20.references.Comment;
import com.cricket.t20.utils.SimulatorUtils;

public class LastFourSimulator {
    
    private int noOfOvers;
    private int target;
    private int remainingWickets;
    private List<Player> players;
    private Commentary commentary;

    public LastFourSimulator(){
        this.noOfOvers=4;
        this.target=40;
        this.remainingWickets=3;
        this.players = new ArrayList<>();
        this.populatePlayers();
        this.commentary = Commentary.getCommentary();
    }
    
    private void populatePlayers(){
        this.players.add(PlayersInfo.getPlayer(PlayersName.KIRAT));
        this.players.add(PlayersInfo.getPlayer(PlayersName.NODHI));
        this.players.add(PlayersInfo.getPlayer(PlayersName.RUMRAH));
        this.players.add(PlayersInfo.getPlayer(PlayersName.HENRA));
    }
    
    
    public void startSimulation() {
        try{
            Team team=new Team(TeamsName.TEAM_LENGABURU, this.players, remainingWickets, noOfOvers, target);
            Comment comment = new OverStartOrEndComment(noOfOvers, 0, this.target);
            commentary.publishCommentary(comment);
            int overCount=0;
            while(overCount<noOfOvers){
                commentary.publishCommentary(new SimpleComment("\n"));
                TeamInningsDetails inningsDetail = team.playOver(getBowlableBallsInOver(), overCount);
                if(inningsDetail.isRunChasedSuccessFully()){
                    commentary.publishCommentary(new SimpleComment("\n"));
                    publishMatchEndCommentary(true, team.getTeamName(), 0, inningsDetail.getRemainingWickets(), 
                            (inningsDetail.getRemainingBallsInRecentlyCompletedOver()+inningsDetail.getRemainingOvers()*6));
                    break;
                }else if(inningsDetail.getRemainingWickets()<=0){
                    commentary.publishCommentary(new SimpleComment("\n"));
                    publishMatchEndCommentary(false, team.getTeamName(), inningsDetail.getTarget(), inningsDetail.getRemainingWickets(), 
                            (inningsDetail.getRemainingBallsInRecentlyCompletedOver()+inningsDetail.getRemainingOvers()*6));
                    break;
                }
                SimulatorUtils.makeSimulationIntuitive();
                overCount++;
            }
            if(team.getInningsDetails().getTarget()>1 && team.getInningsDetails().getRemainingWickets()>0){
                commentary.publishCommentary(new SimpleComment("\n"));
                publishMatchEndCommentary(false, team.getTeamName(), team.getInningsDetails().getTarget(),
                        team.getInningsDetails().getRemainingWickets(), 0);
            }else if(team.getInningsDetails().getTarget()==1){
                commentary.publishCommentary(new SimpleComment("\n"));
                commentary.publishCommentary(new SimpleComment("Both team scored equal runs. Its tie"));
            }
            commentary.publishCommentary(new SimpleComment("\n"));
            team.publishPlayerCommentary();
        }catch(InvalidInputException e){
            e.printStackTrace();
            System.out.println("Invalid input");
        }finally{
            commentary.endCommentary();
        }
    }
    
    private List<BowlableBall> getBowlableBallsInOver(){
        List<BowlableBall> bowlableBalls = new ArrayList<>();
        for(int i=1;i<=6;i++)
            bowlableBalls.add(BowlableBall.GOOD);
        return bowlableBalls;
    }
    
    private void publishMatchEndCommentary(Boolean isTeamWin, String team, int target, int remainingWickets, int ballsRemaining){
        Comment overEndComment = new MatchEndComment(isTeamWin, team, target, remainingWickets, ballsRemaining);
        commentary.publishCommentary(overEndComment);
    }
}
