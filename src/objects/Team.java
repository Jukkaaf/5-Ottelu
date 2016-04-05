package objects;


import java.util.ArrayList;

/**
 *  Team is a temporary list of players that will be deleted after the round if over.
 *  Useful for allocating points to all the members of the team.
 * 
 * @author Jukka
 */
public class Team {
    private String name;
    private int rank;
    private ArrayList<Player> players;
    
    public Team(String name){
        this.name = name;
        players = new ArrayList<Player>();
    }
    /** 
     * Increases the teams point amount.
     * Backup players will get half of the points.
    */
    public void increaseTeamPoints(int point){
        // increase the points of everyone in the team
        // backup players will get half of the points
        for(int count = 0; count < players.size(); count++){
            if(!players.get(count).getBackUp()){
                players.get(count).increasePoints(point);
            }
            else{
                players.get(count).increasePoints(point / 2);
            }
        }
    }
    /** 
     * Decreases the teams point amount.
     * Backup players will loose half of the points.
    */
    public void decreaseTeamPoints(int point){
        // decrease the points of everyone in the team
        // backup players will loose half of the points
        for(int count = 0; count < players.size(); count++){           
            if(!players.get(count).getBackUp()){
                players.get(count).decreasePoints(point);
            }
            else{
                players.get(count).decreasePoints(point / 2);
            }
        }
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void addPlayer(Player player){
        players.add(player);
    }
    
    public void addPlayer(int index, Player player){
        players.add(index, player);
    }
    
    public Player getPlayer(int index){
        return players.get(index);
    }
    
    public void removePlayer(int index){
        players.remove(index);
    }
    
    public int getTeamSize(){
        return players.size();
    }
    
    public int getRank(){
        return this.rank;
    }
    
    public void setRank(int rank){
        this.rank = rank;
    }
}
