import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

// to do: set up action listeners

public class SceneCanvas extends JComponent {
    private int width;
    private int height;
    ArrayList<DrawingObject> Sharingans;
    private int sharinganIndex;

    public SceneCanvas(int w, int h){
        width = w;
        height = h;
        setPreferredSize(new Dimension(width, height));

        Sharingans = new ArrayList<DrawingObject>();
        sharinganIndex = 0;

        Sharingans.add(new Tomoe((double)w/2, (double)h/2, 50)); // testing, will delete

    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        AffineTransform af = g2d.getTransform();

        // background
        Color backgroundColor = Color.GRAY;
        Rectangle2D.Double background = new Rectangle2D.Double(0,0,width,height);
        g2d.setColor(backgroundColor);
        g2d.fill(background);

        
        // Draw current sharingan given sharinganIndex
        Sharingans.get(sharinganIndex).draw(g2d, af);

    }
}
