package com.cricket.t20.data;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cricket.t20.domain.player.Player;
import com.cricket.t20.enums.Run;

public class PlayersInfo_TestData {

    public PlayersInfo_TestData(){
    }
    
    private  Map<String, Map<Run, Integer>> playerWeighedProbability = new HashMap<>();
    private  Map<String, Player> players = new HashMap<>();

    private  Map<String, String> playersAndTeam = new HashMap<>();
    private  List<String> playersName = new ArrayList<>();
    
    {
        populatePlayersNameAndWeighedProbability();
        populatePlayers();
    }
    
    public  Player getPlayer(String player){
        return players.get(player);
    }
    
    private  void populatePlayers(){
        playersName.forEach(palyerName-> {
            Player player = new Player(palyerName, playersAndTeam.get(palyerName), playerWeighedProbability.get(palyerName));
            players.put(palyerName, player);
        });
    }
    
    private  void populatePlayersNameAndWeighedProbability(){
        playersName.add("single");
        playersName.add("double");
        playersName.add("out");
        playersName.add("boundary");
        playersName.add("onlyDouble");
        
        playerWeighedProbability.put("single", getWeighedProbability(5, 70, 5, 5, 5, 5, 3, 2));
        playerWeighedProbability.put("double", getWeighedProbability(5, 5, 70, 5, 5, 1, 4, 5));
        playerWeighedProbability.put("out", getWeighedProbability(5, 5, 1, 4, 5, 5, 5, 70));
        playerWeighedProbability.put("boundary", getWeighedProbability(5, 5, 5, 5, 70, 1, 4, 5));
        playerWeighedProbability.put("onlyDouble", getWeighedProbability(0, 0, 100, 0, 0, 0, 0, 0));
        
        playersAndTeam.put("single", "Lengaburu");
        playersAndTeam.put("double", "Lengaburu");
        playersAndTeam.put("out", "Lengaburu");
        playersAndTeam.put("boundary", "Lengaburu");
        playersAndTeam.put("onlyDouble", "Lengaburu");
    }
    
    private  Map<Run, Integer> getWeighedProbability(int dotball, int single, int doublerun, int triple, int boundary,
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
