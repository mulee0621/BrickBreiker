import java.awt.Color;
import acm.graphics.GRect;
/**
 * Diese Klasse dient der Herstellung von Plattform und der Festlegung ihrer Figur und ersten Position.
 * und die Bewegungsdistanz (die die Geschwindigkeit des Bars sein kann) einstellen und damit kann der Flattform nach hinten 
 * und in die richtige Richtung bewegt werden.
 * @author Tulina Maharjan
 *
 */
public class Bar extends GRect {
	private static double posX=300;
	public static double getPosX() {
		return posX;
	}

	public static void setPosX(double posX) {
		Bar.posX = posX;
	}

	public static double getPosY() {
		return posY;
	}

	public static void setPosY(double posY) {
		Bar.posY = posY;
	}

	public double getWidth() {
		return width;
	}

	public static void setWidth(double width) {
		Bar.width = width;
	}

	public double getHeight() {
		return height;
	}

	public static void setHeight(double height) {
		Bar.height = height;
	}

	private static double posY=500;
	private static double width=100;
	private static double height=8;

	private static double dx=25;
	
	//dies ist Konstrukteur
	public Bar() {
		super(posX,posY,width,height);
		setFilled(true);
		setColor(Color.BLUE);
	}

	//Methode bewegt den Balken nach rechts
	public void moveRight() {
		if(getX()<581) move(dx,0);	
		else setLocation(581, getY());
	}

	//Methode bewegt den Balken nach links
	public void moveLeft() {
		if (getX()>2) move(-dx,0); 
	    else setLocation(2, getY());
	}

}
