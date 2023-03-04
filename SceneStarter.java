public class SceneStarter {

  public static void main(String[] args) {
    // https://stackoverflow.com/questions/14991121/java-swing-how-to-deal-with-different-screen-dpi-and-density-settings
    // Some components do not scale well on other devices, code below fixes it.
    System.setProperty( "sun.java2d.uiScale", "1.0" );
    SceneFrame scene = new SceneFrame(1024, 768);
    scene.setUpGUI();

    // instructions
    System.out.println("Use Mouse Wheel to adjust rotation speed.");
    System.out.println("[SECRET FEATURE] Try to make Sasuke's Mangekyou Sharingan!");
  }

}
