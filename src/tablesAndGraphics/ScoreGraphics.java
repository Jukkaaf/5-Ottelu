package tablesAndGraphics;

import objects.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 *  A custom scoreboard component that paints the players in a list in order of their points.
 * 
 * @author Jukka
 */
public class ScoreGraphics extends JPanel implements Printable{
    // all the player listed in the order of their points
    ArrayList<Player> playerList;
    
    public void addPlayerList(ArrayList<Player> playerList){
        this.playerList = playerList;
        setPreferredSize(new Dimension(500, playerList.size() * 23 + 20));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g = g.create();
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 20);
        g2.setFont(font);

        if(playerList != null){
            for(int count = 0; count < playerList.size(); count++){
                g2.setColor(Color.red);
                g2.drawString(count+1 + ". ", 0, 23 * count + 20);
                g2.setColor(Color.black);
                g2.drawString(playerList.get(count).getPoints() + "  " + playerList.get(count).getName(), 35, 23 * count + 20);
            }
        }        
    }
    /**
    *  This method is used to print the scoreboard.
    */
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2 = (Graphics2D)g;

        paintComponent(g2);
        return PAGE_EXISTS;
    }
}
