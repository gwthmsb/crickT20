package com.cricket.t20.tie.breaker;

import java.util.ArrayList;
import java.util.List;

import com.cricket.t20.commentary.Commentary;
import com.cricket.t20.commentary.InningsCommentary;
import com.cricket.t20.commentary.MatchEndComment;
import com.cricket.t20.commentary.SimpleComment;
import com.cricket.t20.data.PlayersInfo;
import com.cricket.t20.data.PlayersName;
import com.cricket.t20.data.TeamsName;
import com.cricket.t20.domain.player.Player;
import com.cricket.t20.domain.team.Team;
import com.cricket.t20.domain.team.TeamInningsDetails;
import com.cricket.t20.enums.BowlableBall;
import com.cricket.t20.exceptions.InvalidInputException;
import com.cricket.t20.utils.SimulatorUtils;

public class TieBreakerSimulator {

    private Commentary commentary;
    private List<Player> firstBattingTeamPlayers;
    private List<Player> secondBattingTeamPlayers;
    
    public TieBreakerSimulator(){
        commentary= Commentary.getCommentary();
        commentary.decorateCommentary();
        populatePlayers();
    }
    
    private void populatePlayers(){
        firstBattingTeamPlayers = new ArrayList<>();
        firstBattingTeamPlayers.add(PlayersInfo.getPlayer_tieBreaker(PlayersName.KIRAT));
        firstBattingTeamPlayers.add(PlayersInfo.getPlayer_tieBreaker(PlayersName.NODHI));
        
        secondBattingTeamPlayers = new ArrayList<>();
        secondBattingTeamPlayers.add(PlayersInfo.getPlayer_tieBreaker(PlayersName.DBV));
        secondBattingTeamPlayers.add(PlayersInfo.getPlayer_tieBreaker(PlayersName.MAMLA));
    }
    
    public void startSimulator(){
        try{
            Team firstBattingTeam = new Team(TeamsName.TEAM_LENGABURU, firstBattingTeamPlayers, 1, 1);
            commentary.publishCommentary(new InningsCommentary(firstBattingTeam));
            TeamInningsDetails firstBattingTeamInnings = firstBattingTeam.playOver(getBowlableBallsInOver(), 0);
            
            SimulatorUtils.makeSimulationIntuitive();
            commentary.publishCommentary(new SimpleComment("\n"));
            
            Team secondBattingTeam = new Team(TeamsName.TEAM_ENCHAI, secondBattingTeamPlayers, 1, 1, firstBattingTeamInnings.getTotalRuns()+1);
            commentary.publishCommentary(new InningsCommentary(secondBattingTeam));
            TeamInningsDetails secondBattingTeamInnings = secondBattingTeam.playOver(getBowlableBallsInOver(), 0);
            
            if(firstBattingTeam.getInningsDetails().getTotalRuns()==
                    secondBattingTeam.getInningsDetails().getTotalRuns()){
                commentary.publishCommentary(new SimpleComment("Both team scored equal runs. Its tie again"));
            }else{
                commentary.publishCommentary(new MatchEndComment(secondBattingTeamInnings.isRunChasedSuccessFully(),
                    secondBattingTeamInnings.getTeamName(), secondBattingTeamInnings.getTarget(), 
                    secondBattingTeamInnings.getRemainingWickets(),
                    secondBattingTeamInnings.getRemainingBallsInRecentlyCompletedOver()));
            }
            commentary.publishCommentary(new SimpleComment("\n"));
            commentary.publishCommentary(new SimpleComment(firstBattingTeam.getTeamName()));
            firstBattingTeam.publishPlayerCommentary();
            commentary.publishCommentary(new SimpleComment("\n"));
            commentary.publishCommentary(new SimpleComment(secondBattingTeam.getTeamName()));
            secondBattingTeam.publishPlayerCommentary();
        }catch(InvalidInputException e){
            e.printStackTrace();
        }
        commentary.endCommentary();
    }
    
    private List<BowlableBall> getBowlableBallsInOver(){
        List<BowlableBall> bowlableBalls = new ArrayList<>();
        for(int i=1;i<=6;i++)
            bowlableBalls.add(BowlableBall.GOOD);
        return bowlableBalls;
    }
    
}
