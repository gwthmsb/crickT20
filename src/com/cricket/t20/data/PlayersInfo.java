package com.cricket.t20.data;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cricket.t20.domain.player.Player;
import com.cricket.t20.enums.Run;

public class PlayersInfo {

    private PlayersInfo(){
        throw new IllegalStateException("Static Class PlayersInfo");
    }
    
    private static Map<String, Map<Run, Integer>> playerWeighedProbability = new HashMap<>();
    private static Map<String, Player> players = new HashMap<>();

    private static Map<String, Map<Run, Integer>> playerWeighedProbability_tieBreaker = new HashMap<>();
    private static Map<String, Player> players_tieBreaker = new HashMap<>();
    private static List<String> playersName_tieBreaker = new ArrayList<>();
    
    private static Map<String, String> playersAndTeam = new HashMap<>();
    private static List<String> playersName = new ArrayList<>();
    
   
    static{
        populatePlayersNameAndWeighedProbability();
        populatePlayers();
        populatePlayersForTieBreaker();
    }
    
    public static Player getPlayer(String player){
        return players.get(player);
    }
    
    public static Player getPlayer_tieBreaker(String player){
        return players_tieBreaker.get(player);
    }
    
    private static void populatePlayers(){
        playersName.forEach(palyerName-> {
            Player player = new Player(palyerName, playersAndTeam.get(palyerName), playerWeighedProbability.get(palyerName));
            players.put(palyerName, player);
        });
    }
    
    private static void populatePlayersForTieBreaker(){
        playersName_tieBreaker.forEach(palyerName-> {
            Player player = new Player(palyerName, playersAndTeam.get(palyerName), playerWeighedProbability_tieBreaker.get(palyerName));
            players_tieBreaker.put(palyerName, player);
        });
    }
    
    private static void populatePlayersNameAndWeighedProbability(){
        playersName.add(PlayersName.KIRAT);
        playersName.add(PlayersName.NODHI);
        playersName.add(PlayersName.RUMRAH);
        playersName.add(PlayersName.HENRA);
        
        playerWeighedProbability.put(PlayersName.KIRAT, getWeighedProbability(5, 30, 25, 10, 15, 1, 9, 5));
        playerWeighedProbability.put(PlayersName.NODHI, getWeighedProbability(10, 40, 20, 5, 10, 1, 4, 10));
        playerWeighedProbability.put(PlayersName.RUMRAH, getWeighedProbability(20, 30, 15, 5, 5, 1, 4, 20));
        playerWeighedProbability.put(PlayersName.HENRA, getWeighedProbability(30, 25, 5, 0, 5, 1, 4, 30));
        
        playersAndTeam.put(PlayersName.KIRAT, TeamsName.TEAM_LENGABURU);
        playersAndTeam.put(PlayersName.NODHI, TeamsName.TEAM_LENGABURU);
        playersAndTeam.put(PlayersName.RUMRAH, TeamsName.TEAM_LENGABURU);
        playersAndTeam.put(PlayersName.HENRA, TeamsName.TEAM_LENGABURU);
        playersAndTeam.put(PlayersName.DBV, TeamsName.TEAM_ENCHAI);
        playersAndTeam.put(PlayersName.MAMLA, TeamsName.TEAM_ENCHAI);
        
        playerWeighedProbability_tieBreaker.put(PlayersName.KIRAT, getWeighedProbability(5, 10, 25, 10, 25, 1, 14, 10));
        //playerWeighedProbability_tieBreaker.put(PlayersName.KIRAT, getWeighedProbability(0, 0, 0, 0, 0, 0, 0, 100));
        playerWeighedProbability_tieBreaker.put(PlayersName.NODHI, getWeighedProbability(5, 15, 15, 10, 20, 1, 19, 15));
        playerWeighedProbability_tieBreaker.put(PlayersName.DBV, getWeighedProbability(5, 10, 25, 10, 25, 1, 14, 10));
        playerWeighedProbability_tieBreaker.put(PlayersName.MAMLA, getWeighedProbability(10, 15, 15, 10, 20, 1, 19, 10));
        
        playersName_tieBreaker.add(PlayersName.KIRAT);
        playersName_tieBreaker.add(PlayersName.NODHI);
        playersName_tieBreaker.add(PlayersName.DBV);
        playersName_tieBreaker.add(PlayersName.MAMLA);
        
    }
    
    private static Map<Run, Integer> getWeighedProbability(int dotball, int single, int doublerun, int triple, int boundary,
            int fiver, int six, int out){
        Map<Run, Integer> weighedProbability= new EnumMap<>(Run.class);
        
        for(Run run: Run.values()){
            switch(run){
            case DOTBALL:
                weighedProbability.put(run, dotball);
                break;
            case SINGLE:
                weighedProbability.put(run, single);
                break;
            case DOUBLE:
                weighedProbability.put(run, doublerun);
                break;
            case TRIPLE:
                weighedProbability.put(run, triple);
                break;
            case BOUNDARY:
                weighedProbability.put(run, boundary);
                break;
            case FIVER:
                weighedProbability.put(run, fiver);
                break;
            case SIX:
                weighedProbability.put(run, six);
                break;
            case OUT:
                weighedProbability.put(run, out);
                break;
            }
        }
        
        return weighedProbability;
    }
}
