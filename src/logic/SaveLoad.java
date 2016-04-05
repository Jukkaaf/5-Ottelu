package logic;

import objects.Tournament;
import objects.Match;
import objects.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 * Where everything regarding saving and loading is done.
 * 
 * @author Jukka Ala-Fossi
 */
public class SaveLoad {
    
    private Tournament currentTournament;
    private JFileChooser chooser = new JFileChooser();
    private String filePath;
    
    public SaveLoad(Tournament tournament){
        this.currentTournament = tournament;
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
    }
    /** 
     * Normal save. Saves to the same folder where the tournament was last saved.
    */
    public void save(){
        if(canSave()){
            if(checkValidity(filePath)){
                createFiles(filePath);
            }
        }
    }
    /** 
     * Check if this tournament has been saved before.
    */
    public boolean canSave(){
        if(filePath != null){
            return true;
        }
        return false;
    }
    /** 
     * Saving the tournament in to a new folder.
    */
    public void saveAs(){  
        StringBuilder path = new StringBuilder();
        // the folder chosen, and then we create the folder and files
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooser.getSelectedFile();
            filePath = fileToSave.getAbsolutePath();
            path.append(filePath);
            
            String path2 = path.toString();
            if(createFolder(path2)){
                createFiles(path2);
            }
        }
    }
    
    /** 
     * Basic loading method. Used to choose the folder.
    */
    public boolean load(){ 
        // star with choosing a folder
        if(chooseFolder()){
            StringBuilder path = new StringBuilder();
            path.append(chooser.getSelectedFile());
            String tempFilePath = path.toString();
            
            // check if we can load this 
            if(checkValidity(tempFilePath)){
                filePath = tempFilePath;
                // load all the seperate files in the folder
                loadTournament(filePath);
                loadMatch(filePath);
                loadUser(filePath);
                
                // lets check if we have to create an automatic backup of the tournament in its current state
                if(currentTournament.getAutoBackUp()){
                    path.append("/backup");
                    String path2 = path.toString();
        
                    if(createFolder(path2)){
                        createFiles(path2);
                    }
                }    
                return true;
            }
        }
        return false;
    }
    /** 
     * Loading the file that contains the tournaments settings and name.
    */
    private void loadTournament(String path){
        String error = "tournament.txt invalid";
        
        try{
            FileInputStream fis = new FileInputStream(path + "/tournament.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line = null;
            if((line = br.readLine()) != null) {
                currentTournament.setName(line);
            }
            else{
                System.out.println(error);
            }
            if((line = br.readLine()) != null) {
                currentTournament.setMatches(new Integer(line));
            }
            else{
                System.out.println(error);
            }
            
            if((line = br.readLine()) != null) {
                if(line.equals("true")){
                    currentTournament.setAutoBackUp(true);
                }
                else if(line.equals("false")){
                    currentTournament.setAutoBackUp(false);
                }
            }
            else{
                System.out.println(error);
            }
            
            if((line = br.readLine()) != null) {
                if(line.equals("true")){
                    currentTournament.setSaveOnQuit(true);
                }
                else if(line.equals("false")){
                    currentTournament.setSaveOnQuit(false);
                }
            }
            else{
                System.out.println(error);
            }
            
            if((line = br.readLine()) != null) {
                if(line.equals("true")){
                    currentTournament.setSaveGame(true);
                }
                else if(line.equals("false")){
                    currentTournament.setSaveGame(false);
                }
            }
            else{
                System.out.println(error);
            }
            
            br.close();
        }
        catch(IOException e){
            System.out.println("IO error while loading tournament");
        }
    }
    /** 
     * Loading the file that contains the information for each match in the tournament.
    */
    private void loadMatch(String path){     
        try{
            FileInputStream fis = new FileInputStream(path + "/match.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            ArrayList<Match> list = new ArrayList<Match>(0);
                       
            String line = null;
            
            while((line = br.readLine()) != null) {
                // this is where a new matches data starts
                if(line.equals("*")){                 
                    // get name and team ammount
                    String name = br.readLine();
                    
                    boolean temp = false;
                    line = br.readLine();
                    if(line.equals("true")){
                        temp = true;
                    }
                    Match match = new Match(name, temp);
                    
                    line = br.readLine();
                    match.updateMatchLists(new Integer(line));
                
                    line = br.readLine();
                    StringBuffer number = new StringBuffer();
                    // read the player ammount for each team
                    int index = 0;
                    for(int count = 0; count < line.length(); count++){
                        if(line.charAt(count) != ','){
                            number.append(line.charAt(count));
                        }
                        else{
                            // player ammount read
                            if(index < match.getTeamsAmmount()){
                                String number2 = number.toString();
                                match.setTeamSizeIndex(index, new Integer(number2));
                                number = new StringBuffer();
                                index++;
                            }
                        }
                    }
                    // read the point ammount for each position
                    line = br.readLine();
                    number = new StringBuffer();
                    index = 0;
                    for(int count = 0; count < line.length(); count++){
                        if(line.charAt(count) != ','){
                            number.append(line.charAt(count));
                        }
                        else{
                            // player ammount read
                            if(index < match.getTeamsAmmount()){
                                String number2 = number.toString();
                                match.setPointsIndex(index, new Integer(number2));
                                number = new StringBuffer();
                                index++;
                            }
                        }
                    }
                    
                    // match should be completely read, and it will be saved
                    
                    list.add(match);
                } 
            }
            // all matches read
            currentTournament.setMatchList(list);
            
            br.close();
        }
        catch(IOException e){
            System.out.println("IO error while loading match");
        }
    }
    /** 
     * Loading the file that contains all the user and player information.
    */
    private void loadUser(String path){    
        try{
            FileInputStream fis = new FileInputStream(path + "/user.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            
            ArrayList<User> list = new ArrayList<User>(0);
            
            String line = null;
            line = br.readLine();
            while(line != null) {
                if(line.equals("*")){                 
                    // get username
                    line = br.readLine();
                    User temp = new User(line);
                    
                    line = br.readLine();
                    while(line != null && !line.equals("*")){
                        
                        Integer inttemp = new Integer(br.readLine());
                        temp.addPlayer(line, inttemp);
                        
                        // next line
                        line = br.readLine();
                    }
                    list.add(temp);
                }
            }
            
            currentTournament.setUserList(list);
      
            br.close();
        }
        catch(IOException e){
            System.out.println("IO error while loading user");
        }
    }
    /** 
     * Check if the given path contains all the needed files.
    */
    private boolean checkValidity(String path){   
        if(!checkFile(path + "/tournament.txt")){
            System.out.println("tournament file missing");
            return false;
        }
        if(!checkFile(path + "/match.txt")){
            System.out.println("match file missing");
            return false;
        }
        if(!checkFile(path + "/user.txt")){
            System.out.println("user file missing");
            return false;
        }
        return true;
    }
    /** 
     * Check if the path exists.
    */
    private boolean checkFile(String path){    
        boolean validity = true;     
        File tempfile = new File(path);
        if(!tempfile.exists() || tempfile.isDirectory()) {
            validity = false;
        }       
        return validity;
    }
    
    private boolean chooseFolder(){          
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){ 
            return true;
        }
        else{
            System.out.println("No Selection ");
            return false;
        }
    }
    
    private boolean createFolder(String path){
        boolean success = (new File(path)).mkdirs();
        return success;
    }
    /** 
     * Prints a text file based on the path and content.
    */
    private void printFile(String path, String content){    
        try {
            File file = new File(path);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter filew = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferw = new BufferedWriter(filew);
            bufferw.write(content);
            bufferw.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    /** 
     * Method that calls all the separate file creation methods.
    */
    private void createFiles(String path){
        createTournamentFile(path);
        createMatchFile(path);
        createUserFile(path);
    }
    /** 
     * Creates a file with basic information and settings.
    */
    private void createTournamentFile(String path){
        StringBuilder content = new StringBuilder();
        
        content.append(currentTournament.getName());
        content.append(System.lineSeparator());
        content.append(currentTournament.getMatches());
        content.append(System.lineSeparator());
        if(currentTournament.getAutoBackUp()){
            content.append("true");
        }
        else{
            content.append("false");
        }
        content.append(System.lineSeparator());
        if(currentTournament.getSaveOnQuit()){
            content.append("true");
        }
        else{
            content.append("false");
        }
        content.append(System.lineSeparator());
        if(currentTournament.getSaveGame()){
            content.append("true");
        }
        else{
            content.append("false");
        }   
        String finalContent = content.toString();
        printFile(path + "/tournament.txt", finalContent);
    }
    /** 
     * Creates a file with the information for all the matches.
    */
    private void createMatchFile(String path){      
        StringBuilder content = new StringBuilder();     
        int matches = currentTournament.getMatches();
              
        for(int count = 0; count < matches; count++){
            // for every match
            Match temp = currentTournament.getMatchList().get(count);
            content.append("*");
            content.append(System.lineSeparator());
            content.append(temp.getName());
            content.append(System.lineSeparator());
            
            if(temp.getCompleted()){
                content.append("true");
            }
            else{
                content.append("false");
            }
            content.append(System.lineSeparator());
            
            int teams = temp.getTeamsAmmount();
            content.append(teams);
            content.append(System.lineSeparator());
            
            for(int count2 = 0; count2 < teams; count2++){
                content.append(temp.getTeamSizeIndex(count2));
                content.append(",");
            }
            content.append(System.lineSeparator());
            for(int count2 = 0; count2 < teams; count2++){
                content.append(temp.getPointsIndex(count2));
                content.append(",");
            }
            content.append(System.lineSeparator());
        }
        String finalContent = content.toString();
        printFile(path + "/match.txt", finalContent);
    }
    /** 
     * Creates a file with all the user and player information.
    */
    private void createUserFile(String path){   
        StringBuilder content = new StringBuilder();      
        int users = currentTournament.getUserList().size();
        
        for(int count = 0; count < users; count++){
            User temp = currentTournament.getUserList().get(count);
            content.append("*");
            content.append(System.lineSeparator());
            content.append(temp.getName());
            content.append(System.lineSeparator());
            
            for(int count2 = 0; count2 < temp.sizePlayerList(); count2++){
                content.append(temp.getPlayer(count2).getName());
                content.append(System.lineSeparator());
                content.append(temp.getPlayer(count2).getPoints());
                content.append(System.lineSeparator());
            }
        }
        
        String finalContent = content.toString();
        printFile(path + "/user.txt", finalContent);
    }
}
