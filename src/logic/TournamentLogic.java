package logic;

import objects.Tournament;
import objects.Match;
import objects.Team;
import objects.Player;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;


/**
 * The heart of the program. This is where players get randomly assigned to teams
 * and points get allocated to them.
 * 
 * @author Jukka
 */
public class TournamentLogic {
    private Tournament tournament;
    // list of all players seperate from the users
    // for simpler management of players, since the user only matters when data is saved
    // this list will stay complete
    private ArrayList<Player> playerList;
    // list of the players who have not yet been selected to play
    // players will be removed from this list once they have been used
    private ArrayList<Player> unusedPlayers;
    // list of the teams that are currently competing in the match
    // it is a list of arraylists with players in them
    private ArrayList<Team> currentTeams;
    // list of players that play a second time so that everyone gets to play atleast once
    //private ArrayList<Player> backupPlayers = new ArrayList<Player>();
    //boolean backup = false;
    
    private int currentMatchIndex = 0;
    private int currentRoundIndex = 0;
    private int currentMaxRounds = 0;
    private Match currentMatch;
    private Random rand;
    
    UndoManager undoManager;
    
    public TournamentLogic(Tournament tournament){
        this.tournament = tournament;
        currentTeams = new ArrayList<Team>();
        currentMatch = tournament.getMatchList().get(currentMatchIndex);
        generateLists();
        calculateRoundAmmount();      
        rand = new Random();
        undoManager = new UndoManager();
    }
    /** 
     * Calculate the total amount of rounds that are needed so that all players get to play in the match.  
    */
    private void calculateRoundAmmount(){      
        currentRoundIndex = 0;
        currentMaxRounds = 0;
        int playersPerRound = 0;
        int totalCurrentPlayers = playerList.size();
        
        for(int count = 0; count < currentMatch.getTeamsAmmount(); count++){
            playersPerRound += currentMatch.getTeamSizeIndex(count);
        }
        // if remaider is 0
        if((totalCurrentPlayers % playersPerRound) == 0){
            currentMaxRounds = totalCurrentPlayers / playersPerRound;
        }
        else{
            currentMaxRounds = totalCurrentPlayers / playersPerRound;
            // not enoung player for to get everyone to play once
            currentMaxRounds += 1;
        }
    }
    /** 
     * Generate 2 lists with the players.
     * Players who have played in the match will be removed from unused players.
    */
    private void generateLists(){
        playerList = generatePlayerList();
        unusedPlayers = generatePlayerList();
    }
    /** 
     * Generates a list of all players.
    */
    private ArrayList<Player> generatePlayerList(){
        ArrayList<Player> list = new ArrayList<Player>(tournament.getUserList().size());
        for(int count = 0; count < tournament.getUserList().size(); count++){
            for(int count2 = 0; count2 < tournament.getUserList().get(count).getMaxPlayers(); count2++){
                if(!tournament.getUserList().get(count).getPlayer(count2).getName().equalsIgnoreCase(" ")){
                    list.add(tournament.getUserList().get(count).getPlayer(count2));
                    // we have to make sure that there are no old backup players in the next round
                    tournament.getUserList().get(count).getPlayer(count2).setBackUp(false);
                }     
            }
        }
        return list;
    }
    /** 
     * Generates a list of new teams with players in them.
    */
    public void createNewTeams(int ammount){
        // make sure the currentTeams arraylist is empty
        currentTeams.clear();
        // make sure the inTeam boolean is false for all players
        for(int count = 0; count < playerList.size(); count++){
            playerList.get(count).setInTeam(false);
        }       
        for(int count = 0; count < ammount; count++){
            currentTeams.add(randomTeam(count));
        }
    }
    /** 
     * Creates a team with random players in it.
    */
    private Team randomTeam(int index){       
        Team team = new Team("Team " + index);
        team.setRank(index);
        for(int count = 0; count < currentMatch.getTeamSizeIndex(index); count++){
            // if there are unusedplayer left, we will add a random one to the team
            if(!unusedPlayers.isEmpty()){
                Player temp = getRandomUnUsedPlayer();
                if(temp != null){
                    team.addPlayer(temp);
                    temp.setInTeam(true);
                }
                else{
                    System.out.println("RandomTeam(), unUsed Player was null " + index);
                }
            }
            // when all the unused players are used, we take random used players
            else{
                Player temp = getRandomUsedPlayer();
                if(temp != null){
                    team.addPlayer(temp);
                    temp.setInTeam(true);
                    temp.setBackUp(true);
                }
                else{
                    System.out.println("RandomTeam(), used Player was also null " + index);
                }
            }
        } 
        return team; 
    }
    /** 
     * Returns a player who has not played in this match.
     * If none left returns null.
    */
    private Player getRandomUnUsedPlayer(){
        Player temp = null;
        
        int max = unusedPlayers.size();       
        // if there are still remaining unused players
        if(max > 0){
            int randomNum = rand.nextInt(max);
            if(randomNum > -1 && randomNum < max){
                temp = unusedPlayers.get(randomNum);
                unusedPlayers.remove(randomNum);
            }
            else{
                System.out.println("GetRandomUnUsedPlayer(), random number is out of bounds " + randomNum + " " + max);
            }
        }       
        return temp;
    }
    /** 
     * Returns a player who has played in the match on a previous round.
     * Marks that the player is a backup player so that he will not be give maximum points again.
    */
    private Player getRandomUsedPlayer(){               
        Player temp = null;
        boolean again = true;
        boolean unusedPlayer = true;
        int failSafe = 0;
        int counter = 0;
        
        int max = playerList.size();       
        if(max > 0){
            while(again){
                // if the program has failed 5000 times to find a random used player who is not in a team
                // it will go trough the players one by one
                // i chose 5000 because if we just quickly ask for random numbers we will get the same result
                // previously i had 100 here, was not enough. I kept getting the same players over and over
                if(failSafe > 5000){
                    if(counter < playerList.size()){
                        temp = playerList.get(counter);
                        counter++;  
                    }
                    else{
                        System.out.println("GetRandomUsedPlayer(), no usedplayers left");
                        return null;
                    }
                }
                // when we are still under 5000 attempts
                else{
                    int randomNum = rand.nextInt(max);
                    if(randomNum > -1 && randomNum < max){
                        temp = playerList.get(randomNum);
                    }
                    else{
                        System.out.println("GetRandomUsedPlayer(), random number is out of bounds " + randomNum + " " + max);
                        return null;
                    }
                    failSafe++;
                }               
                if(temp != null){
                    // check if the chosen player is alrady in a team
                    // previously i used to check the currentTeams list and see if the player was there
                    // but this caused problems since the lists update too slow
                    if(temp.getInTeam()){
                        unusedPlayer = false;
                    }
                    // also check if the player has been disqualifyed
                    if(temp.getDisqualifyed()){
                        unusedPlayer = false;
                    }
                }
                // if the player is already in a team we try again
                if(!unusedPlayer){
                    again = true;
                    unusedPlayer = true;
                }
                else{
                    again = false;
                }
            }        
        }    
        return temp;
    }
    
    public void disqualifyPlayer(int column, int row){
        if(row < currentTeams.get(column).getTeamSize()){
            Player temp = currentTeams.get(column).getPlayer(row);
            temp.setDisqualifyed(true);
            replacePlayer(column, row, false);
        }
    }
    
    public void replaceWithNull(int column, int row){
        Player temp = currentTeams.get(column).getPlayer(row);
        // if the player is not bakcup, put back to the players list
        if(!temp.getBackUp()){
            unusedPlayers.add(temp);
        }
        Player clearPlayer = new Player();
        clearPlayer.setBackUp(true);
        currentTeams.get(column).removePlayer(row);
        currentTeams.get(column).addPlayer(row, clearPlayer);
    }
    
    /** 
     * Replaces a player in a team with a new one.
     * If there are still unused players left, it picks one of them.   
     * AddBack is used to decide if we want to add the player back to the pool.
    */
    public void replacePlayer(int column, int row, boolean addBack){     
        if(row < currentTeams.get(column).getTeamSize()){
            Player temp = currentTeams.get(column).getPlayer(row);
            Player temp2 = getRandomUnUsedPlayer();
            
            // unused player
            if(temp2 != null && temp != null){
                // if the replaced player was backup
                if(temp.getBackUp()){
                    //temp.setBackUp(false);
                }
                currentTeams.get(column).removePlayer(row);
                currentTeams.get(column).addPlayer(row, temp2);
                if(addBack){
                    unusedPlayers.add(temp);
                }             
            }
            // when there are no more unused players left
            else if(temp2 == null && temp != null){
                temp2 = getRandomUsedPlayer();
                // if the player that is being replaced was a backupplayer
                // we make him a non backup
                if(temp.getBackUp() && temp2 != null){
                    temp.setBackUp(false);
                    temp2.setBackUp(true);          
                    currentTeams.get(column).removePlayer(row);
                    currentTeams.get(column).addPlayer(row, temp2);
                }
                //was not in backup
                else if(temp2 != null){
                    temp2.setBackUp(true);   
                    currentTeams.get(column).removePlayer(row);
                    currentTeams.get(column).addPlayer(row, temp2);
                    if(addBack){
                        unusedPlayers.add(temp);
                    } 
                }
            }
        }
    }
    /** 
     * Loads next match and resets everything.   
    */
    public void loadNextMatch(){     
        if(currentMatchIndex < tournament.getMatches() - 1){        
            currentMatch.setCompleted(true);         
            playerList.clear();
            unusedPlayers.clear();
            currentMatchIndex++;          
            currentMatch = tournament.getMatchList().get(currentMatchIndex);
            // clears backup status from players
            generateLists();
            createNewTeams(currentMatch.getTeamsAmmount());
            calculateRoundAmmount();
            // once you load the next match there is no going back
            undoManager.discardAllEdits();
        }
    }
    /** 
     * Loading the next round.
     * Basically just getting new teams and discarding undomanager edits.
    */
    public void loadNextRound(){
        if(currentMaxRounds > currentRoundIndex + 1){
            currentRoundIndex++;
            createNewTeams(currentMatch.getTeamsAmmount());
            undoManager.discardAllEdits();
        }    
    }
    
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }
    /** 
     * Sorts the players based on their points
     * for the scoreboard.
    */
    public ArrayList<Player> getSortedPlayerList(){      
        boolean buble = true;       
        while(buble){
            buble = false;
            for(int count = 0; count < playerList.size() - 1; count++){
                if(playerList.get(count).getPoints() < playerList.get(count + 1).getPoints()){
                    Player temp = playerList.get(count + 1);
                    playerList.remove(count + 1);
                    playerList.add(count, temp);
                    buble = true;
                }
            }
        }      
        return playerList;
    }
    
    public ArrayList<Team> getCurrentTeams(){
        return currentTeams;
    }
    
    public Match getCurrentMatch(){
        return currentMatch;
    }
    
    public int getCurrentMatchIndex(){
        return currentMatchIndex;
    }
    /** 
     * Moves teams rank up. 
    */
    public void moveTeamUp(int index){
        // moves teams rank up
        if(index != 0){
            for(int count = 0; count < currentTeams.size(); count++){
                if(currentTeams.get(count).getRank() == index - 1){
                    currentTeams.get(count).setRank(index);
                }
                else if(currentTeams.get(count).getRank() == index){
                    currentTeams.get(count).setRank(index - 1);
                }
            }
        }
    }
    /** 
     * Moves teams rank down. 
    */
    public void moveTeamDown(int index){
        if(index < currentTeams.size() - 1){
            for(int count = 0; count < currentTeams.size(); count++){
                if(currentTeams.get(count).getRank() == index + 1){
                    currentTeams.get(count).setRank(index);
                }
                else if(currentTeams.get(count).getRank() == index){
                    currentTeams.get(count).setRank(index + 1);
                }
            }
        }
    }
    
    public int getCurrentTeamsAmmount(){
        return currentTeams.size();
    }
    /** 
     * Changes a teams name.
     * Returns the index of the team in the arraylist.
    */
    public int setTeamName(int index, String name){     
        for(int count = 0; count < currentTeams.size(); count++){
            if(currentTeams.get(count).getRank() == index){
                currentTeams.get(count).setName(name);
                return count;
            }
        }
        return 0;
    }
    /** 
     * Allocates points to the teams.
    */
    public void allocatePoints(){
        if(!currentMatch.getCompleted()){
            undoManager.addEdit(new PointAllocation(currentTeams, currentMatch.getPointsList()));        
        }
    }
    
    public boolean undo(){
        if (undoManager.canUndo()){
            undoManager.undo();            
            return true;
        }
        return false;
    }
    
    public boolean redo(){
        if (undoManager.canRedo()){
            undoManager.redo();  
            return true;
        }
        return false;
    }
    
    public boolean canUndo(){
        return undoManager.canUndo();
    }
    
    public boolean canRedo(){
        return undoManager.canRedo();
    }
    
    public boolean canChangeMatch(){
        if(currentRoundIndex + 1 == currentMaxRounds && unusedPlayers.size() == 0){
            if(currentMatchIndex + 1 < tournament.getMatches()){
                return true;
            }               
        }
        else if(currentMatch.getCompleted()){
            return true;
        }
        return false;
    }
    
    public boolean canChangeRound(){
        // Commented out because if enough players are disqualified the program can get stuck.
        // Not the best way to fix the issue since it can cause rounds with only reused players.
        // This is a minor issue though since those rounds can simply be skipped.
        /*
        if(unusedPlayers.size() == 0){
            return false;
        }
        */
        if(currentRoundIndex + 1 < currentMaxRounds && !currentMatch.getCompleted()){
            return true;
        }
        return false;
    }
    
    public int getCurrentMaxRounds(){
        return currentMaxRounds;
    }
    
    public int getCurrentRoundIndex(){
        return currentRoundIndex;
    }

}
/** 
* Pointallocation is a class that implements undoableedit.
* It takes care of allocating points to players and taking them away if needed.
*/
class PointAllocation implements UndoableEdit{    
    ArrayList<Team> teams;
    ArrayList<Integer> points;
    
    public PointAllocation(ArrayList<Team> teams, ArrayList<Integer> points){
        this.teams = teams;
        this.points = points;
        
        allocatePoints();
    }
    
    public void allocatePoints(){
        for(int count = 0; count < teams.size(); count++){
            teams.get(count).increaseTeamPoints(points.get(teams.get(count).getRank()));
        }
    }
    
    public void redo() {
        allocatePoints();
    }

    public void undo() {
        for(int count = 0; count < teams.size(); count++){
            teams.get(count).decreaseTeamPoints(points.get(teams.get(count).getRank()));
        }
    }
    
    public boolean addEdit(UndoableEdit edit) {
        return false;
    }

    public boolean replaceEdit(UndoableEdit edit) {
        return false;
    }

    public String getPresentationName() {
        return "Point allocation";
    }

    public String getUndoPresentationName() {
        return "Undo point allocation";
    }

    public String getRedoPresentationName() {
        return "Redo point allocation";
    }

    public boolean canRedo() {
            return true;
    }

    public boolean canUndo() {
            return true;
    }

    public void die() {

    }

    public boolean isSignificant() {
        return true;
    } 
}