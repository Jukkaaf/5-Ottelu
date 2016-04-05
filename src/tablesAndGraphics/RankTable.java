package tablesAndGraphics;


import objects.Team;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *  Table model used in the mainWindow to show what ranks the teams are in.
 * 
 * @author Jukka
 */
public class RankTable extends AbstractTableModel {
    // list of the teams that are currently competing in the match
    // it is a list of arraylists with players in them
    private ArrayList<Team> teams;
    
    public RankTable(ArrayList<Team> teams){
        this.teams = teams;
    }
    
    @Override
    public int getRowCount() {
        return teams.size();
    }
    
    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int index, int column) {
        for(int count = 0; count < teams.size(); count++){
            if(teams.get(count).getRank() == index){
                return teams.get(count).getName();
            }
        }
        
        return null;
    }

    @Override
    public Class getColumnClass(int column) {
        return String.class;
 
    }
    
    @Override
    public String getColumnName(int column) {
        return "Rank";
    }
    
    @Override
    public boolean isCellEditable(int row, int col){ 
        return false; 
    }
    
    @Override
    public void setValueAt(Object value, int index, int column) {
        teams.get(index).setName((String)value);
        fireTableCellUpdated(index, column);
    }
}
