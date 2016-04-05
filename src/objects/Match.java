package objects;

import java.util.ArrayList;
/**
 *  Match is a class that includes all the information for one match in the tournament.
 * 
 * @author Jukka
 */
public class Match implements java.lang.Cloneable{
    private static final int DEFAULTTEAMS = 2;
    private static final int DEFAULTEAMSIZE = 2;
    private static final int MAXPLAYERS = 25;
    private static final int MAXPOINTS = 25;
    
    private String name;
    private int teams;
    // teamsize contains all the teams sizes in order
    private ArrayList<Integer> teamsize;
    // contains all the points you get from different ranks in order
    private ArrayList<Integer> points;
    // is the match completed. Incase the program is restarted in the middle of the tournmanet
    private boolean completed = false;
    
    
    public Match(String name, boolean completed){
        this.name = name;
        this.teams = DEFAULTTEAMS;
        teamsize = new ArrayList<Integer>(DEFAULTTEAMS);
        points = new ArrayList<Integer>(DEFAULTTEAMS);
        updateTeamSizeList();
        updatePointsList();
        this.completed = completed;
    }
    
    public Object clone() throws CloneNotSupportedException {
        Match clone = (Match)super.clone();
        
        for(int count = 0; count < this.teams; count++){
            clone.setTeamSizeIndex(count, this.getTeamSizeIndex(count));
        }
        for(int count = 0; count < this.teams; count++){
            clone.setPointsIndex(count, this.getPointsIndex(count));
        }
        
        return clone;
    }
    /** 
     * When the amount of teams changes this method calls for updates on the
     * teamsize and points lists.
    */
    public void updateMatchLists(int value){
        setTeams(value);
        updateTeamSizeList();
        updatePointsList();
    }
    /** 
     * This method makes sure that the list of team sizes is the right size.
    */
    private void updateTeamSizeList(){
        if(teamsize.size() < teams){
            for(int count = teamsize.size(); count < teams; count++){
                // add a default sized team
                teamsize.add(DEFAULTEAMSIZE);
            }
        }       
        // if the list if too big
        else if(teams < teamsize.size()){
            do{
                // delete the last one untill the size is correct
                teamsize.remove(teamsize.size() - 1);
            }while(teams < teamsize.size());
        }
    }
    /** 
     * This method makes sure that the list of points given to teams is the right size.
    */
    private void updatePointsList(){   
        if(points.size() < teams){
            for(int count = points.size(); count < teams; count++){
                // add a default sized team
                points.add(DEFAULTEAMSIZE);
            }
        }
        // if the list if too big
        else if(teams < points.size()){
            do{
                // delete the last one untill the size is correct
                points.remove(points.size() - 1);
            }while(teams < points.size());
        }
    }
    /** 
     * Sets the size of a team in a certain index of the teamsize arraylist.
    */
    public void setTeamSizeIndex(int index, int value){
        if(value > MAXPLAYERS){
            value = MAXPLAYERS;
        }
        else if(value < 1){
            value = 1;
        }
        // if index is in bounds
        if(index < this.teams){
            teamsize.set(index, value);  
        }       
    }
    /** 
     * Sets the points of a rank in a certain index of the points arraylist.
    */
    public void setPointsIndex(int index, int value){
        if(value > MAXPOINTS){
            value = MAXPOINTS;
        }
        else if(value < 0){
            value = 0;
        }
        // if index is in bounds
        if(index < this.teams){
            points.set(index, value);  
        } 
    }
    
    public int getTeamSizeIndex(int index){
        return teamsize.get(index);
    }
    
    public int getPointsIndex(int index){
        return points.get(index);
    }
    
    public ArrayList<Integer> getPointsList(){
        return points;
    }
    
    public String getName(){
        return name;
    }
    
    public int getTeamsAmmount(){
        return teams;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setTeams(int teams){
        this.teams = teams;
    }
    
    public void setCompleted(boolean completed){
        this.completed = completed;
    }
    public boolean getCompleted(){
        return this.completed;
    }
}
