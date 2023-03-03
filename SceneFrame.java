import java.awt.*;
import javax.swing.*;

public class SceneFrame {
    private JFrame frame;
    private int width;
    private int height;
    private SceneCanvas sceneCanvas;

    public SceneFrame(int w, int h){
        frame = new JFrame();
        width = w;
        height = h;
        sceneCanvas = new SceneCanvas(width, height);
    }

    public void setUpGUI(){
        // frame.setSize(width, height);
        Container contentPane = frame.getContentPane();
        
        // format GUI here
        contentPane.add(sceneCanvas, BorderLayout.CENTER);

        frame.setTitle(" Midterm Project - Francisco, James Bryan - 222677");
        // frame.getContentPane().setPreferredSize(new Dimension(width, height));
        // frame.pack();
        frame.setSize(1024, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}   
