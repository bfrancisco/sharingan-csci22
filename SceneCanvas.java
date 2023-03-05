import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.event.*;

// to do: set up action listeners

public class SceneCanvas extends JComponent {
    private int width;
    private int height;
    private double centerX;
    private double centerY;
    private double eyeRadius; 
    private double boxWidth;
    private double eyeMoveThreshold;

    private int rotationSpeed;
    private Timer tomoeTimer;

    ArrayList<DrawingObject> drawingObjects;
    ArrayList<DrawingObject> sharinganList;
    private int sharinganIndex;
    ShapeColor shapeColor;

    public SceneCanvas(int w, int h){
        width = w;
        height = h;
        this.setPreferredSize(new Dimension(width, height));
        startCanvas();
    }

    public void startCanvas(){
        centerX = width / 2;
        centerY = height / 2;
        eyeRadius = height * 0.6185f;
        eyeMoveThreshold = eyeRadius * 0.06f; 
        boxWidth = Math.min(width, height);
        shapeColor = new ShapeColor();
        rotationSpeed = 1;

        drawingObjects = new ArrayList<DrawingObject>();
        setUpBG("Primary", "Secondary");
        
        sharinganList = new ArrayList<DrawingObject>();
        setUpSharingans();
        sharinganIndex = 0;

        setUpListeners();
    }

    private void setUpBG(String s1, String s2){
        drawingObjects.add(new RectGradient(0, 0, width, height, 0.41f, 1.0f, shapeColor.genColor(s1), shapeColor.genColor(s2)));
        drawingObjects.add(new SpeedGraphic(width*0.98, height*0.985, eyeRadius*0.045, eyeRadius));
    }

    private void setUpSharingans(){
        sharinganList.add(new TomoeSharingan(centerX, centerY, 1, eyeRadius, shapeColor.genColor("Tomoe"), shapeColor.genColor("Primary"), shapeColor.genColor("Secondary"), rotationSpeed));
    }

    private void setUpListeners(){
        MouseMotionListener mouseLoc = new MouseMotionListener(){
            @Override
            public void mouseMoved(MouseEvent m){
                double mx = m.getX();
                double my = m.getY();
                
                // look to the mouse
                double centerToBoundsX = (centerX - mx > 0) ? Math.min(centerX - mx, boxWidth) : Math.max(centerX - mx, -boxWidth);
                double centerToBoundsY = (centerX - my > 0) ? Math.min(centerX - my, boxWidth) : Math.max(centerX - my, -boxWidth);

                double translateX;
                double translateY;

                translateX = centerToBoundsX / eyeMoveThreshold;
                translateY = centerToBoundsY / eyeMoveThreshold;
                
                
                ((TomoeSharingan) sharinganList.get(sharinganIndex)).setEyeDisplacement(translateX, translateY);
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {}
        };
        this.addMouseMotionListener(mouseLoc);

        ActionListener tomoeAnimator = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				((TomoeSharingan) sharinganList.get(sharinganIndex)).animateTomoe();
                repaint();
			}
		};
        tomoeTimer = new Timer(10, tomoeAnimator);
        tomoeTimer.setRepeats(true);
        // https://stackoverflow.com/questions/24250717/java-swing-timer-slower-than-expected
        // Make timer more responsive when many events are queued.
        tomoeTimer.setCoalesce(false); 
        tomoeTimer.start();

        MouseWheelListener wheelListener = new MouseWheelListener(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = -(e.getWheelRotation());
                if (rotationSpeed + notches > 0 && rotationSpeed + notches <= 10){
                    rotationSpeed += notches;
                    ((TomoeSharingan) sharinganList.get(sharinganIndex)).setRotationSpeed(rotationSpeed);
                    ((SpeedGraphic) drawingObjects.get(1)).setSpeed(rotationSpeed);
                    repaint();
                }
            }
        };
        this.addMouseWheelListener(wheelListener);
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
