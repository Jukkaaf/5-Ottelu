/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablesAndGraphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import objects.Team;

/**
 *
 * Custom renderer for the team table in the mainWindow.
 * It draws all the backup players names in red.
 * 
 * @author Jukka
 */
public class TeamCellRenderer extends JLabel implements	TableCellRenderer {
    private boolean selected;
    private boolean focus;
    
    private ArrayList<Team> teams;
    
    public TeamCellRenderer(ArrayList<Team> teams){
        this.teams = teams;
    }
       
    public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ){
        this.selected = isSelected;
        this.focus = hasFocus;

        setForeground(Color.black);
        // check if the cell is the cell of a backup player
        // backup players will be displayed in red
        if(teams.size() > column){
            if(teams.get(column).getTeamSize() > row){
                if(teams.get(column).getPlayer(row).getBackUp()){
                    setForeground(Color.red);
                } 
            }
        }         
        setText((String)value);
        return this;
    }

    public void paint( Graphics g ){
        Color bColor = Color.white;
        if(focus){
            bColor = Color.cyan;
        }       
        g.setColor( bColor );
        g.fillRect( 0, 0, getWidth() - 1, getHeight() - 1 );
        super.paint( g );
    }
    
}
