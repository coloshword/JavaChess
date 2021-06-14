import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel{
    // each tile on the chessboard will be a JPanel
    // each tile has 3 positions denoted by an integer
    // 1 is white, 2 is black, 3 is empty
    static final int tileSize = 75;
    int position;
    // constructor
    // int x and y correspond to the coordinates, int a to the position
    public Tile(int a, int x, int y) {
        // rgbs of the two colors:
        // white
        int[] rgbWhite = {204, 153, 255};
        int[] rgbPurple = {211,211,211};
        // declares the position of the tile 
        position = a;
        this.setPreferredSize(new Dimension(tileSize, tileSize));
        this.setBounds(x, y, tileSize, tileSize);
        if((x + y) % 2 == 0) { 
            this.setBackground(new Color(rgbWhite[0], rgbWhite[1], rgbWhite[2]));
        }
        else {
            this.setBackground(new Color(rgbPurple[0], rgbPurple[1], rgbPurple[2]));
        }
        this.setFocusable(true);
    }
}
