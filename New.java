import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;

public class New {
    char[][] boardInfo = new char[8][8];
    public static void main(String[] args) {
        new JFrame();
        try {
            JPanel panel = new JPanel();
            System.out.println("bk" + ".png");
            BufferedImage image = ImageIO.read(new File("bk" + ".png"));
            JLabel label = new JLabel(new ImageIcon(image));
            panel.add(label);
        } catch (IOException ex) {
            System.out.println("Something went wrong");
        }
    }
}
