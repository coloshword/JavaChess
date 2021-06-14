import javax.swing.*;
import java.awt.*;


public class ChessGUI extends JFrame{
    // create the GUI class
    public ChessGUI() {
        // now creates the 64 Tile objects
        // make the frame a different color first
        this.setSize(700, 750);
        this.getContentPane().setBackground(new Color(222, 184, 135));
        int numRows = 8;
        int numColumns = 8;
        for(int i = 0; i < numRows; i++) {
            for(int a = 0; a < numColumns; a++) {
                this.add(new Tile(3, a * 75, i * 75));
            }
        }
        // now it will create the tiles and add them into the JFrame 
        this.setTitle("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        // makes it so that the size of the GUI is dependent on the inner components
        this.setLayout(null);
        // puts it in the middle of the screen 
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
