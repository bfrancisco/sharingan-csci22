/**
This is a template for a Java file.
@author James Bryan M. Francisco (222677)
@version March 6, 2023
**/
/*
I have not discussed the Java language code in my program 
with anyone other than my instructor or the teaching assistants 
assigned to this course.
I have not used Java language code obtained from another student, 
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program 
was obtained from another source, such as a textbook or website, 
that has been clearly noted with a proper citation in the comments 
of my program.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.net.URL;
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
    private Timer rotationTimer;
    private Timer transitionTimer;

    ArrayList<DrawingObject> drawingObjects;
    ArrayList<DrawingObject> sharinganList;
    private int sharinganIndex;
    private int sharinganCount;
    ShapeColor shapeColor;

    URL aud = this.getClass().getClassLoader().getResource("sasukebgm.wav");
    URL aud2 = this.getClass().getClassLoader().getResource("sharingansfx.wav");
    URL aud3 = this.getClass().getClassLoader().getResource("mangekyosfx-start.wav");
    URL aud4 = this.getClass().getClassLoader().getResource("mangekyosfx-end.wav");

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
        sharinganCount = 2;

        drawingObjects = new ArrayList<DrawingObject>();
        setUpBG("Primary", "Secondary");
        
        sharinganList = new ArrayList<DrawingObject>();
        setUpSharingans();
        sharinganIndex = 0;

        setUpListeners();

    }

    private void setUpBG(String s1, String s2){
        drawingObjects.add(new RectGradient(0, 0, width, height, 0.41f, 1.0f, shapeColor.genColor(s1), shapeColor.genColor(s2)));
        drawingObjects.add(new SpeedGraphic(width*0.98, 0, eyeRadius*0.045, eyeRadius));
        playSound(aud, true);
    }

    private void setUpSharingans(){
        sharinganList.add(new TomoeSharingan(centerX, centerY, 1, eyeRadius, shapeColor.genColor("Tomoe"), shapeColor.genColor("Primary"), shapeColor.genColor("Secondary"), rotationSpeed));
        sharinganList.add(new MangekyoSharingan(centerX, centerY, 1, eyeRadius, shapeColor.genColor("Primary"), shapeColor.genColor("Secondary"), rotationSpeed, boxWidth));
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
                
                double dX = centerX - m.getX();
                double dY = centerY - m.getY();
                double pupilRadius = eyeRadius * 0.12f;
                boolean atPupil = (Math.sqrt(dX*dX + dY*dY) <= pupilRadius);

                if (sharinganList.get(sharinganIndex) instanceof TomoeSharingan){
                    if (atPupil && rotationSpeed == 10)
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                    else
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    ((TomoeSharingan) sharinganList.get(sharinganIndex)).setEyeDisplacement(translateX, translateY);
                    ((TomoeSharingan) sharinganList.get(sharinganIndex)).setMoveScaling(hypoScaling);
                }
                else if (sharinganList.get(sharinganIndex) instanceof MangekyoSharingan){
                    if (atPupil && rotationSpeed == 1)
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                    else
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    ((MangekyoSharingan) sharinganList.get(sharinganIndex)).setEyeDisplacement(translateX, translateY);
                    ((MangekyoSharingan) sharinganList.get(sharinganIndex)).setMoveScaling(hypoScaling);
                }
                
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {}
        };
        this.addMouseMotionListener(mouseLoc);

        ActionListener rotationAnimator = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				if (sharinganList.get(sharinganIndex) instanceof TomoeSharingan){
                    ((TomoeSharingan) sharinganList.get(sharinganIndex)).animateTomoe();
                }
                else if (sharinganList.get(sharinganIndex) instanceof MangekyoSharingan){
                    ((MangekyoSharingan) sharinganList.get(sharinganIndex)).animateMangekyo();
                }
                repaint();
			}
		};
        rotationTimer = new Timer(10, rotationAnimator);
        rotationTimer.setRepeats(true);
        rotationTimer.start();

        MouseWheelListener wheelListener = new MouseWheelListener(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = -(e.getWheelRotation());
                if (rotationSpeed + notches > 0 && rotationSpeed + notches <= 10){
                    if ((sharinganList.get(sharinganIndex) instanceof TomoeSharingan) && rotationSpeed + notches == 10){
                        playSound(aud2, false);
                    }
                    rotationSpeed += notches;
                    if (sharinganList.get(sharinganIndex) instanceof TomoeSharingan){
                        ((TomoeSharingan) sharinganList.get(sharinganIndex)).setRotationSpeed(rotationSpeed);
                    }
                    else if (sharinganList.get(sharinganIndex) instanceof MangekyoSharingan){
                        ((MangekyoSharingan) sharinganList.get(sharinganIndex)).setRotationSpeed(rotationSpeed);
                    }
 
                    ((SpeedGraphic) drawingObjects.get(1)).setSpeed(rotationSpeed);
                    repaint();
                }
                
            }
        };
        this.addMouseWheelListener(wheelListener);
        
        ActionListener transitionAnimator = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                if (sharinganList.get(sharinganIndex) instanceof TomoeSharingan){    
                    try{
                        AudioInputStream in = AudioSystem.getAudioInputStream(aud3);
                        Clip clip = AudioSystem.getClip();
                        clip.open(in);
                        clip.loop(0);
                        
                        while(clip.getMicrosecondLength() != clip.getMicrosecondPosition()){}
        
                    }
                    catch(Exception e){
                        System.out.println("Error playing music.");
                    }
                    playSound(aud4, false);
                }
                else{
                    playSound(aud2, false);
                }

                nextSharingan();
                
            }
        };
        transitionTimer = new Timer(10, transitionAnimator);
        transitionTimer.setRepeats(false);

        MouseListener mouseLis = new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                double dX = centerX - e.getX();
                double dY = centerY - e.getY();
                double pupilRadius = eyeRadius * 0.12f;
                boolean atPupil = (Math.sqrt(dX*dX + dY*dY) <= pupilRadius);

                if (e.getButton() == MouseEvent.BUTTON1 && atPupil){
                    if ((sharinganList.get(sharinganIndex) instanceof TomoeSharingan) && rotationSpeed == 10){
                        transitionTimer.start();
                    }
                    
                    else if ((sharinganList.get(sharinganIndex) instanceof MangekyoSharingan) && rotationSpeed == 1){
                        ((TomoeSharingan) sharinganList.get(0)).setRotationSpeed(1);
                        transitionTimer.start();
                        ((MangekyoSharingan) sharinganList.get(1)).setRotationSpeed(10);
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}  
        };
        this.addMouseListener(mouseLis);
        
    }

    // https://stackoverflow.com/questions/66443421/audiosystem-successfully-plays-clip-but-only-once-lineunavailableexceptio
    public void playSound(URL aud, boolean loop) {
        try{
			AudioInputStream in = AudioSystem.getAudioInputStream(aud);
			Clip clip = AudioSystem.getClip();
			clip.open(in);
            if (loop)
			    clip.loop(Clip.LOOP_CONTINUOUSLY);
            else
                clip.loop(0);
        }
        catch(Exception e){
            System.out.println("Error playing music.");
        }
    }

    public void nextSharingan(){
        sharinganIndex = (sharinganIndex + 1) % sharinganCount;
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        AffineTransform af = g2d.getTransform();

        // draw all drawing objects except the sharingan
        for (DrawingObject obj : drawingObjects){
            obj.draw(g2d, af);
        }
        
        // Draw current sharingan given sharinganIndex
        sharinganList.get(sharinganIndex).draw(g2d, af);

    }
}
