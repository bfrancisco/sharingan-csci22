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

public class SceneStarter {
  public static void main(String[] args) {
    // https://stackoverflow.com/questions/14991121/java-swing-how-to-deal-with-different-screen-dpi-and-density-settings
    // Some components do not scale well on other devices, code below fixes it.
    System.setProperty( "sun.java2d.uiScale", "1.0" );
    
    // instructions
    System.out.println("Use Mouse Wheel to adjust rotation speed.");
    System.out.println("[SECRET FEATURE] Transition to Sasuke's Mangekyou Sharingan!");
    
    
    boolean isFullScreen = false; // Change value to true if you want full screen. Alt + F4 to exit program if in full screen.
    double width = 1024;
    double height = 768;
    if (!isFullScreen){
      SceneFrame scene = new SceneFrame((int)width, (int)height, false);
      scene.setUpGUI();
    }
    else{
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      width = screenSize.getWidth();
      height = screenSize.getHeight();
      SceneFrame scene = new SceneFrame((int)width, (int)height, true);
      scene.setUpGUI();
    }

    
  }

}
