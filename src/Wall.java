
import java.awt.Color;
import acm.graphics.*;

/**
 * 
 * Dieser Klass behandelt sich der Wand des Spiels
 * @author Hussain Ali
 *
 */
public  class Wall extends GPen {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param - 1,600 -  x und y koordinate 
	 * @param - 0,-599.5  - x und y Position von der Wand
	 * @param - 683,0 -  x und y Position von der Wand
	 * @param - 0,600 -  x und y Position von der Wand 
	 */
	public Wall(){
		
	super(1,600);
	drawLine(0,-599.5); 
	drawLine(683,0);
	drawLine(0,600);
	setColor(Color.YELLOW);
		
	}
	
}
