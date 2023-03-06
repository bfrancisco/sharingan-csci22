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
