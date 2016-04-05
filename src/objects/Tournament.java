package objects;

import java.util.ArrayList;
/**
 *  Tournament is the main object in this program.
 *  It contains a list of users and it also contains a list of matches.
 * 
 * @author Jukka
 */


public class Tournament implements java.lang.Cloneable{
    private static final int MAXMATCHES = 25;
    private static final int MAXTEAMS = 12;
    private static final int DEFAULTMATCHES = 5;
    
    private String name;
    // how many different matches are there
    private int matches;
    // list of all the seperate matches
    private ArrayList<Match> matchlist;
    // list of all the users in the tournamen
    // a user contains 0 to 3 players
    private ArrayList<User> userlist;
    
    // options
    // backup the tournament on load
    private boolean autoBackUp = false;
    private boolean saveOnQuit = false;
    // save when user loads next game
    private boolean saveGame = false;
    
    public Tournament(String name){
        this.name = name;
        matchlist = new ArrayList<Match>(DEFAULTMATCHES);
        userlist = new ArrayList<User>(DEFAULTMATCHES);
        updateMatchList(DEFAULTMATCHES);
    }
    
    public static int getMAXMATCHES() {
        return MAXMATCHES;
    }

    public static int getMAXTEAMS() {
        return MAXTEAMS;
    }
    
    public Object clone() throws CloneNotSupportedException {
        Tournament clone = (Tournament)super.clone();
        return clone;
    }
    /** 
     * Deep copying a tournament object. 
    */
    public void copyTournament(Tournament t2){  
        this.setName(t2.getName());
        this.setMatches(t2.getMatches());
        
        // deep copy match list
        ArrayList<Match> temp = new ArrayList<Match>(t2.getMatches());
        try{
            for(int count = 0; count < t2.getMatches(); count++){
                temp.add((Match)t2.getMatchList().get(count).clone());
            }
        }
        catch (CloneNotSupportedException e){
            System.out.println("CloneNotSupportedException in CopyTournament, while copying matchlist");
        }
        
        this.setMatchList(temp);
        
        // deep copy user list       
        ArrayList<User> temp2 = new ArrayList<User>(t2.userlist.size());
        try{
            for(int count = 0; count < t2.userlist.size(); count++){              
                temp2.add((User)t2.getUserList().get(count).clone());
            }
        }
        catch (CloneNotSupportedException e){
            System.out.println("CloneNotSupportedException in CopyTournament, while copying playerlist");
        }
        
        this.setUserList(temp2);

    }
    /** 
     * When the amount of matches changes this method updates the match list. 
    */
    public void updateMatchList(int value){
        this.matches = value;
              
        // only generate new matches if the list has empty places for them
        if(matchlist.size() < matches){
            for(int count = matchlist.size(); count < matches; count++){
                // create new match object for the list
                Match genericMatch = new Match("Edit me", false);
                matchlist.add(genericMatch);
            }
        }
        
        // if the list if too big
        else if(matches < matchlist.size()){
            do{
                // delete the last one untill the size is correct
                matchlist.remove(matchlist.size() - 1);
            }while(matches < matchlist.size());
        }
    }
    
    public void addUser(String username){
        User newuser = new User(username);
        userlist.add(newuser);
    }
    
    public void removeUser(User olduser){
        int index = userlist.indexOf(olduser);
        if(index != -1 && index < userlist.size()){
            userlist.remove(index);
        }
    }
    
    public void removeUser(int index){
        if(index != -1 && index < userlist.size()){
            userlist.remove(index);
        }
    }
    
    public void addPlayer(User user, String playername, int points){
        int index = userlist.indexOf(user);
        if(index != -1 && index < userlist.size()){
            userlist.get(index).addPlayer(playername, points);
        }
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getMatches(){
        return this.matches;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setMatches(int matches){
        this.matches = matches;
    }
    
    public void setMatchList(ArrayList<Match> list){
        matchlist = list;
    }
    
    public void setUserList(ArrayList<User> list){
        userlist = list;
    }
    
    public ArrayList<Match> getMatchList(){
        return matchlist;
    }
    
    public ArrayList<User> getUserList(){
        return userlist;
    }
    
    public void setAutoBackUp(boolean temp){
        autoBackUp = temp;
    }
    
    public void setSaveOnQuit(boolean temp){
        saveOnQuit = temp;
    }
    
    public void setSaveGame(boolean temp){
        saveGame = temp;
    }
    
    public boolean getAutoBackUp(){
        return autoBackUp;
    }
    
    public boolean getSaveOnQuit(){
        return saveOnQuit;
    }
    
    public boolean getSaveGame(){
        return saveGame;
    }
}
