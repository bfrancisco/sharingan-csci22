import java.awt.*;
import java.util.HashMap;

public class ShapeColor {
    private HashMap<String, Color> shapeColor = new HashMap<String, Color>();
    
    public ShapeColor(){
        shapeColor.put("Primary", new Color(176, 0, 3));
        shapeColor.put("Secondary", new Color(88, 0, 4));
        shapeColor.put("Tomoe", new Color(0, 0, 0));
    }

    public Color genColor(String shapeName){
        try{
            return shapeColor.get(shapeName);
        }
        catch (Exception e){
            System.out.printf("%s not in HashMap.\n", shapeName);
            return null;
        }
    }
}
