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
