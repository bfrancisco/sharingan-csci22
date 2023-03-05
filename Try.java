import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Try extends JPanel {
    double width = 1024;
    double height = 768;
    // Rectangle2D rectangle;
    Mangekyo mangekyo;
    public Try() {
        mangekyo = new Mangekyo(width/2, height/2, 616.0f/2, 0, height);
    }

    public void paint(Graphics g) {
        // cast Graphics object to Graphics2D object for better quality
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform reset = new AffineTransform();
        
        // paint/draw/fill here
        // g2d.fill(rectangle);
        mangekyo.draw(g2d, reset);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Try");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Try());
        frame.setSize(1024, 768);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}