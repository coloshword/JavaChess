import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;

public class Tile extends JPanel implements ActionListener, MouseListener{
    BufferedImage image;
    // each tile on the chessboard will be a JPanel
    // each tile has 3 positions denoted by an integer
    // 1 is white, 2 is black, 3 is empty
    static final int tileSize = 75;
    int position;
    // constructor
    // int x and y correspond to the coordinates, int a to the position
    // the 2d array to keep positions
    char[][] boardInfo = new char[8][8];
    static int delay = 75;
    boolean running = false;
    Timer timer;
    //BufferedImage image; 
    public Tile(int row, int column, int a, int x, int y) {
        // rgbs of the two colors:
        // white
        int[] rgbWhite = {204, 153, 255};
        int[] rgbPurple = {211,211,211};
        // declares the position of the tile 
        position = a;
        this.setPreferredSize(new Dimension(tileSize, tileSize));
        this.setBounds(x + 50, y + 50, tileSize, tileSize);
        // all Tiles have a mouse listener
        this.addMouseListener(this);
        if((x + y) % 2 == 0) { 
            this.setBackground(new Color(rgbWhite[0], rgbWhite[1], rgbWhite[2]));
        }
        else {
            this.setBackground(new Color(rgbPurple[0], rgbPurple[1], rgbPurple[2]));
        }
        this.setFocusable(true);
        this.defineBoardArray();
        if(position != 3) {
            String nameModifier = ""; 
            if(position == 2) {
                nameModifier = "b";
            }
            try {
                image = ImageIO.read(new File(nameModifier + boardInfo[row][column] + ".png"));
            } catch (IOException ex) {
                System.out.println("Something went wrong");
            }
        }
    }
    
    // corresponding mouse clicked events
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked");
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Exited");
    }

    // Painting the image sprites

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 10, 10, this);
    }
    
    public void startGame() {
        running = true;
        timer = new Timer(delay, this);
        timer.start();
    }

    //letter will represent the piece, capital means white, lowercase means black
    public void defineBoardArray() {
        boardInfo[0][0] = 'r';
        boardInfo[0][1] = 'k';
        boardInfo[0][2] = 'b';
        boardInfo[0][3] = 'q';
        boardInfo[0][4] = '=';
        boardInfo[0][5] = 'b';
        boardInfo[0][6] = 'k';
        boardInfo[0][7] = 'r';
        for(int i = 0; i < 8; i++) {
            boardInfo[1][i] = 'p';
        }

        boardInfo[7][0] = 'R';
        boardInfo[7][1] = 'K';
        boardInfo[7][2] = 'B';
        boardInfo[7][3] = 'Q';
        boardInfo[7][4] = '+';
        boardInfo[7][5] = 'B';
        boardInfo[7][6] = 'K';
        boardInfo[7][7] = 'R';
        for(int i = 0; i < 8; i++) {
            boardInfo[6][i] = 'P';
        }
    }

    // corresponding method for actionListener interface 
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}