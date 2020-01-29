import java.awt.Color;
import acm.graphics.*;
/**
 * Diese Klasse behandelt die Darstellung von Bl�cken und gibt Eigenschaften
 * von der x-Startposition, y-Startposition, Gr��e und Farben ein.
 * @author mulee
 *
 */
public class Brick extends GRect{
	public Brick(double x, double y, Color c) {
		super(x, y, 50, 20);
		setFilled(true);
		setColor(c);	
	}
}

