import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

// to do: set up action listeners

public class SceneCanvas extends JComponent {
    private int width;
    private int height;
    ArrayList<DrawingObject> drawingObjects; // <-- This
    ArrayList<DrawingObject> sharinganList;
    private int sharinganIndex;
    ShapeColor shapeColor;

    public SceneCanvas(int w, int h){
        width = w;
        height = h;
        setPreferredSize(new Dimension(width, height));

        shapeColor = new ShapeColor();

        drawingObjects = new ArrayList<DrawingObject>();
        setUpBG("BG1", "BG2");
        
        sharinganList = new ArrayList<DrawingObject>();
        setUpSharingans();
        sharinganIndex = 0;

    }

    private void setUpBG(String s1, String s2){
        drawingObjects.add(new RectGradient(0, 0, width, height, 0.41f, 1.0f, shapeColor.genColor(s1), shapeColor.genColor(s2)));
    }

    private void setUpSharingans(){
        sharinganList.add(new Tomoe(width/2, height/2, 1, shapeColor.genColor("Tomoe"), 0));
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        AffineTransform af = g2d.getTransform();

        // draw all drawing objects
        for (DrawingObject obj : drawingObjects){
            obj.draw(g2d, af);
        }
        
        // Draw current sharingan given sharinganIndex
        sharinganList.get(sharinganIndex).draw(g2d, af);

    }
}
