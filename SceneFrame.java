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

import java.awt.*;
import javax.swing.*;

public class SceneFrame {
    // https://stackoverflow.com/questions/11570356/jframe-in-full-screen-java
    static GraphicsDevice device = GraphicsEnvironment
    .getLocalGraphicsEnvironment().getScreenDevices()[0];
    private JFrame frame;
    private double width;
    private double height;
    private SceneCanvas sceneCanvas;

    public SceneFrame(int w, int h, boolean fullscreen){
        frame = new JFrame();
        width = w;
        height = h;
        if (fullscreen)
            device.setFullScreenWindow(frame);
        sceneCanvas = new SceneCanvas((int)width, (int)height);
    }

    public void setUpGUI(){
        Container contentPane = frame.getContentPane();
        
        // format GUI here
        contentPane.add(sceneCanvas, BorderLayout.CENTER);

        frame.setTitle(" Midterm Project - Francisco, James Bryan - 222677");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.setResizable(false);
    }
}   
