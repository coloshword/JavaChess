import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;

public class Tile extends JPanel implements MouseListener{
    // Color RGBS
    static int[] rgbWhite = {204, 153, 255};
    static int[] rgbPurple = {211,211,211};
    static int[] rgbBlue = {183, 232, 238};
    // Variable for image to load
    BufferedImage image;
    // each tile on the chessboard will be a JPanel
    // each tile has 3 positions denoted by an integer
    // 1 is white, 2 is black, 3 is empty
    static final int tileSize = 75;
    int position;
    // constructor
    // int x and y correspond to the coordinates, int a to the position
    // the 2d array to keep positions
    static char[][] boardInfo = new char[8][8];
    static int delay = 1;
    boolean running = false;
    Timer timer;
    // has this Tile been clicked by mouseListener
    // the last clicked Tile
    static Tile lastClicked;
    static Tile destinationTile;
    static Tile[][] listTiles = new Tile[8][8];
    // instance variables
    int[] color;
    // the horizontal board position (0-7)
    int row;
    // the vertical board position (0-7)
    int column;
    public Tile(int row, int column, int a, int x, int y) {
        // rgbs of the two colors:
        // white
        // declares the position of the tile 
        position = a;
        this.row = row;
        this.column = column;
        this.setPreferredSize(new Dimension(tileSize, tileSize));
        this.setBounds(x + 50, y + 50, tileSize, tileSize);
        // all Tiles have a mouse listener
        this.addMouseListener(this);
        // tells us if the Tile has been clicked
        if((x + y) % 2 == 0) { 
            this.color = rgbWhite;
        }
        else {
            this.color = rgbPurple;
        }
        this.setBackground(new Color(this.color[0], this.color[1], this.color[2]));
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
        listTiles[row][column] = this;
        //startGame();
    }
    
    // corresponding mouse clicked events
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        // lastClicked has to be a tile with a piece on it
        // destinationTile has to be a tile without a piece on it
        // if lastClicked is a Tile, and the next click is a Tile with a piece, then destinationTile will stay as null, and lastClicked will just switch
        tileSelection(this);
        //terminalDisplayBoard(boardInfo);
        System.out.println("--------------------------------");
        //terminalDisplayPosition();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // Painting the image sprites

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 10, 10, this);
    }
    
    public void startGame() {
        running = true;
        //timer = new Timer(delay, this);
        timer.start();
    }

    public void tileSelection(Tile a) {
        // if this tile is not empty
        if(a.position != 3) {
            //this means lastClicked will change to this
            // but before this happens, we need to see if we need to change the previous tile from the color blue
            if(lastClicked != null) {
                lastClicked.changeTileColor(lastClicked.color);
            }
            // then we set lastClicked to the new Tile
            lastClicked = a;        
            lastClicked.changeTileColor(rgbBlue);
        }
        if(a.position == 3) {
            // this means the last clicked Tile was an empty Tile, meaning we move the Piece
            movePiece(a, lastClicked);
            destinationTile = null;
            lastClicked = null;
        }
        terminalDisplayBoard(boardInfo);
    }

    public void movePiece(Tile destTile, Tile startTile) {
        // moves the start Tile to the destination Tile
        // the end Tile is not empty
        destTile.position = startTile.position;
        // the starting Tile is now empty
        startTile.position = 3;
        // swap the values in the boardInfo char array
        // the value of the destination Tile will be the value of the start Tile
        boardInfo[destTile.row][destTile.column] = boardInfo[startTile.row][startTile.column];
        // and the startTile will be empty
        boardInfo[startTile.row][startTile.column] = '\u0000';
        // now if successfully moved, both variables should be set to null
        // before that happens, the startTile color should be shifted back to its original color to indicate it is no longer selected
        startTile.changeTileColor(startTile.color);
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


    public void changeTileColor(int[] rgb) {
        this.setBackground(new Color(rgb[0], rgb[1], rgb[2]));
    }

    //static methods
    public static void terminalDisplayBoard(char[][] info) {
        for(int row = 0; row < 8; row++) {
            for(int column = 0; column < 8; column++) {
                char print = info[row][column];
                if(print == '\u0000') {
                    print = ' ';
                }
                System.out.print("|" + print);
            }
            System.out.println("|");
        }
    }

    public static void terminalDisplayPosition() {
        for(int row = 0; row < 8; row++) {
            for(int column = 0; column < 8; column++) {
                System.out.print("|" + listTiles[row][column].position);
            }
            System.out.println("|");
        }
    }
}