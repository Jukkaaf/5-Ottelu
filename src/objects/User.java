package objects;

import java.util.ArrayList;
/**
 *  A class that basically just contains players.
 *  Mainly here just because possible future expansions to the program.
 * 
 * @author Jukka
 */
public class User implements java.lang.Cloneable{
    private String name;
    private ArrayList<Player> players;
    private int PlayerAmmount = 0;
    private static int MAXPLAYERS = 3;
    
    public User(String name){
        this.name = name;
        players = new ArrayList<Player>(MAXPLAYERS);
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
    }
    
    public Object clone() throws CloneNotSupportedException {
        User clone = (User)super.clone();
        
        ArrayList<Player> temp = new ArrayList<Player>(this.players.size());
        try{
            for(int count = 0; count < this.players.size(); count++){
                temp.add((Player)this.getPlayer(count).clone());
            }
        }
        catch (CloneNotSupportedException e){
            System.out.println("CloneNotSupportedException in User Clone(), while copying players");
        }
        clone.players = temp;
        
        return clone;
    }
    
    public void addPlayer(Player newplayer){
        if(PlayerAmmount < MAXPLAYERS){
            players.set(PlayerAmmount, newplayer);
            PlayerAmmount++;
        }
    }
    
    public void addPlayer(String playername, int points){
        if(PlayerAmmount < MAXPLAYERS){
            Player newplayer = new Player(playername, points);
            players.set(PlayerAmmount, newplayer);
            PlayerAmmount++;
        }
    }
    
    public void removePlayer(int index){
        if(index < MAXPLAYERS){
            players.get(index).setName(" ");
            players.get(index).setPoints(0);
        }
    }
    
    public void replacePlayer(int index, Player newplayer){
        players.set(index, newplayer);
    }
    
    public Player getPlayer(int index){
        if(index < MAXPLAYERS){
            return players.get(index);
        }
        return null;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int sizePlayerList(){
        return players.size();
    }
    
    public int getMaxPlayers(){
        return MAXPLAYERS;
    }
}
