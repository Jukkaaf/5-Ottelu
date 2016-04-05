package tablesAndGraphics;

import objects.User;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *  Table model used in EditPlayer.java.
 *  Shows users with their players and the players points.
 * 
 * @author Jukka
 */
public class PlayerTable extends AbstractTableModel{
    
    private ArrayList<User> users;
    
    public PlayerTable(ArrayList<User> users){
        this.users = users;
    }
    @Override
    public int getRowCount() {
        return users.size();
    }
    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int index, int column) {
        switch(column) {
            case(0):
                return users.get(index).getName();
            case(1):
                return users.get(index).getPlayer(0).getName();
            case(2):
                return users.get(index).getPlayer(0).getPoints();
            case(3):
                return users.get(index).getPlayer(1).getName();
            case(4):
                return users.get(index).getPlayer(1).getPoints();
            case(5):
                return users.get(index).getPlayer(2).getName();
            case(6):
                return users.get(index).getPlayer(2).getPoints();
            default:
                return null;
        }
    }


    @Override
    public Class getColumnClass(int column) {
            switch (column) {
                    case(0):
                            return String.class;
                    case(1):
                            return String.class;
                    case(2):
                            return Integer.class;
                    case(3):
                            return String.class;
                    case(4):
                            return Integer.class;
                    case(5):
                            return String.class;
                    case(6):
                            return Integer.class;
                    default:
                            return Object.class;
            }
    }
    @Override
    public String getColumnName(int column) {
            switch (column) {
                    case(0):
                            return "User";
                    case(1):
                            return "Player 1";
                    case(2):
                            return "Score 1";
                    case(3):
                            return "Player 2";
                    case(4):
                            return "Score 1";
                    case(5):
                            return "Player 3";
                    case(6):
                            return "Score 3";
                    default:
                            return null;
            }
    }
    @Override
    public boolean isCellEditable(int row, int col){ 
        return true; 
    }
    @Override
    public void setValueAt(Object value, int index, int column) {
        switch(column) {
            case(0):
                    users.get(index).setName((String)value);
                    break;
            case(1):
                    users.get(index).getPlayer(0).setName((String)value);
                    break;
            case(2):
                    users.get(index).getPlayer(0).setPoints((Integer)value);
                    break;
            case(3):
                    users.get(index).getPlayer(1).setName((String)value);
                    break;
            case(4):
                    users.get(index).getPlayer(1).setPoints((Integer)value);
                    break;
            case(5):                   
                    users.get(index).getPlayer(2).setName((String)value);
                    break;
            case(6):                   
                    users.get(index).getPlayer(2).setPoints((Integer)value);
        }
       fireTableCellUpdated(index, column);
    }
}
