package objects;

/**
 *  A class used for storing a players name and his or her score.
 * 
 * @author Jukka
 */
public class Player implements java.lang.Cloneable{
    private String name;
    private int points;
    /** 
     * BackUp is used to know if the player should be given full points when a round is over.
    */
    private boolean backUp = false;
    /** 
     * InTeam is used to check if the player is already used in the current match.
    */
    private boolean inTeam = false;
    
    private boolean disqualifyed = false;

    public boolean getDisqualifyed() {
        return disqualifyed;
    }

    public void setDisqualifyed(boolean disqualifyed) {
        this.disqualifyed = disqualifyed;
    }
    /** 
     * Returns if the player is currently in a team or not.
    */
    public boolean getInTeam() {
        return inTeam;
    }
    /** 
     * Sets if the player is currently in a team or not.
    */
    public void setInTeam(boolean inTeam) {
        this.inTeam = inTeam;
    }
    /** 
     * Returns if the player is a backup player or not.
    */
    public boolean getBackUp() {
        return backUp;
    }
    /** 
     * Sets if the player is a backup player or not.
    */
    public void setBackUp(boolean backUp) {
        this.backUp = backUp;
    }
    
    public Player(String name, int points){
        this.name = name;
        this.points = points;
    }
    
    public Player(){
        this.name = " ";
        this.points = 0;
    }
    
    public Object clone() throws CloneNotSupportedException {
        Player clone = (Player)super.clone();
        
        return clone;
    }
    /** 
     * Increases a player point amount. 
    */
    public int increasePoints(int ammount){
        return this.points = this.points + ammount;
    }
    /** 
     * Decreases a player point amount. 
    */
    public int decreasePoints(int ammount){
        return this.points = this.points - ammount;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getPoints(){
        return this.points;
    }
    
    public void setPoints(int points){
        this.points = points;
    }
}
