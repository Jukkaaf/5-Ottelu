package tablesAndGraphics;


import objects.Team;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * Table model used in the main window to show current teams.
 * Shows both player names and their points.
 * 
 * @author Jukka
 */
public class TeamTable extends AbstractTableModel {
    // list of the teams that are currently competing in the match
    // it is a list of arraylists with players in them
    private ArrayList<Team> teams;
    
    public TeamTable(ArrayList<Team> teams){
        this.teams = teams;
    }
    
    @Override
    public int getRowCount() {
        int number = 0;
        for(int count = 0; count < teams.size(); count++){
            if(number < teams.get(count).getTeamSize()){
                number = teams.get(count).getTeamSize();
            }
        }
        return number;
    }
    
    @Override
    public int getColumnCount() {
        return teams.size();
    }

    @Override
    public Object getValueAt(int index, int column) {
        if(column >= teams.size() || index >= teams.get(column).getTeamSize()){
            return " ";
        }
        return new StringBuilder(teams.get(column).getPlayer(index).getPoints() + " " + teams.get(column).getPlayer(index).getName()).toString();
    }

    @Override
    public Class getColumnClass(int column) {
        return String.class;
    }
    
    @Override
    public String getColumnName(int column) {
        return teams.get(column).getName();
    }
    
    @Override
    public boolean isCellEditable(int row, int col){ 
        return false; 
    }
}
