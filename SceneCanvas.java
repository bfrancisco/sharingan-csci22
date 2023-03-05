import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.net.URL;
// import java.io.*;
import javax.sound.sampled.*;

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

    String sharinganSFX;
    String sasukeBGM;

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
        playBGM();
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
                double centerToBoundsY = (centerY - my > 0) ? Math.min(centerY - my, boxWidth) : Math.max(centerY - my, -boxWidth);

                double translateX = centerToBoundsX / eyeMoveThreshold;
                double translateY = centerToBoundsY / eyeMoveThreshold;
                double hypotenuse = Math.sqrt(translateX*translateX + translateY*translateY);
                // hypotenuse ranges from 0 to ~25. ~11.5 is approx. midpoint
                double hypoScaling = 1.0f - (0.012f * (hypotenuse-11.5));
                
                ((TomoeSharingan) sharinganList.get(sharinganIndex)).setEyeDisplacement(translateX, translateY);
                ((TomoeSharingan) sharinganList.get(sharinganIndex)).setMoveScaling(hypoScaling);
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
                    if (rotationSpeed + notches == 10){
                        playSFX();
                    }
                    rotationSpeed += notches;
                    ((TomoeSharingan) sharinganList.get(sharinganIndex)).setRotationSpeed(rotationSpeed);
                    ((SpeedGraphic) drawingObjects.get(1)).setSpeed(rotationSpeed);
                    repaint();
                }
                
            }
        };
        this.addMouseWheelListener(wheelListener);
    }

    public void playBGM() {
        try{
            // ONLY WORKS IF STRING IS DIRECTLY PASSED TO METHOD !!!
            URL aud = this.getClass().getClassLoader().getResource("sasukebgm.wav");
			AudioInputStream in = AudioSystem.getAudioInputStream(aud);
			Clip clip = AudioSystem.getClip();
			clip.open(in);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e){
            System.out.println("Error playing BGM.");
        }
    }

    public void playSFX() {
        try{
            // ONLY WORKS IF STRING IS DIRECTLY PASSED TO METHOD !!!
            URL aud2 = this.getClass().getClassLoader().getResource("sharingansfx.wav");
            Clip clip2 = AudioSystem.getClip();
            AudioInputStream in2 = AudioSystem.getAudioInputStream(aud2);
			clip2.open(in2);
            clip2.loop(0);
        }
        catch(Exception e){
            System.out.println("Error playing SFX.");
        }
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
