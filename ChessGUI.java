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
        // the white set pieces 
        for(int row = 0; row < 2; row++) {
            for (int column = 0; column < 8; column++) {
                this.add(new Tile(row, column, 2, column * 75, row * 75));
            }
        }

        // the empty tiles 
        for(int row = 2; row < 6; row++) {
            for(int column = 0; column < 8; column++) {
                this.add(new Tile(row, column, 3, column * 75, row * 75));
            }
        }

        for(int row = 6; row < 8; row++) {
            for(int column = 0; column < 8; column++) {
                this.add(new Tile(row, column, 1, column * 75, row * 75));
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