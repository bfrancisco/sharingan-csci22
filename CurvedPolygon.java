import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CurvedPolygon extends JPanel {
  
  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    // Create a GeneralPath object to store the shape
    GeneralPath polygon = new GeneralPath();
    
    // Set the starting point of the shape
    polygon.moveTo(50, 50);
    
    // Add curves to the shape
    polygon.curveTo()
    polygon.curveTo(75, 25, 100, 50);
    polygon.quadTo(125, 75, 150, 50);
    polygon.quadTo(175, 25, 200, 50);
    polygon.quadTo(225, 75, 250, 50);
    
    // Close the shape
    polygon.closePath();

    // Draw the shape
    g2d.setPaint(Color.BLUE);
    g2d.fill(polygon);
    g2d.draw(polygon);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Curved Polygon");
    frame.add(new CurvedPolygon());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 200);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}