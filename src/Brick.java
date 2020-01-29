import java.awt.Color;
import acm.graphics.*;
/**
 * Diese Klasse behandelt die Darstellung von Blöcken und gibt Eigenschaften
 * von der x-Startposition, y-Startposition, Größe und Farben ein.
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

