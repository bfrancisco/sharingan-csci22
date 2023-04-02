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
