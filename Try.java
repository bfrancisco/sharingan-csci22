import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Try extends JPanel {
    Rectangle2D rectangle;
    double width = 1024;
    double height = 768;
    public Try() {
        double l = 500, w = 500;
        rectangle = new Rectangle2D.Double((width/2) -(w/2), (height/2) - (l/2), w, l);
    }

    public void paint(Graphics g) {
        // cast Graphics object to Graphics2D object for better quality
        Graphics2D g2d = (Graphics2D) g;

        // paint/draw/fill here
        g2d.setPaint(Color.black);
        g2d.fill(rectangle);
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