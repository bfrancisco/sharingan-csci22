import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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
        Container contentPane = frame.getContentPane();
        
        // format GUI here
        contentPane.add(sceneCanvas, BorderLayout.CENTER);

        frame.setTitle(" Midterm Project - Francisco, James Bryan - 222677");
        frame.getContentPane().setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.getRootPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // This is only called when the user releases the mouse button.
                // https://stackoverflow.com/questions/2106367/listen-to-jframe-resize-events-as-the-user-drags-their-mouse
                // https://stackoverflow.com/questions/13800180/reset-jcomponent-to-default-value

                contentPane.remove(0);
                contentPane.add(new SceneCanvas(frame.getWidth(), frame.getHeight()));
                System.out.println("componentResized");
            }
        });
    }

}   
