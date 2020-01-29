import java.awt.Color;
import acm.graphics.*;

/**
 * Diese Klasse behandelt die Darstellung von Bloecken und gibt Eigenschaften
 * von der x-Startposition, y-Startposition, Groeﬂe und Farben ein.
 * @author Myunguen Lee
 */
public class Brick extends GRect{
	private static final long serialVersionUID = 1L;

/**
 * 
 * @param x x-Koordinate von Brick
 * @param y y-Koordinate von Brick
 * @param c Farbe von Brick
 */
	public Brick(double x, double y, Color c) {
		super(x, y, 50, 20);
		setFilled(true);
		setColor(c);	
	}
}

