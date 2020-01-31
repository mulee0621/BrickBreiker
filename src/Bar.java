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
	private static final long serialVersionUID = 1L;
	private static double posX=300;
	private static double posY=500;
	private static double width=100;
	private static double height=8;
	private static double dx=25;
	
	/**
	 * Diese Methode gibt x position von Bar 
	 * @return posx- x position von Bar
	 */
	public static double getPosX() {
		return posX;
	}
	/**
	 * Diese Methode legt x position von Bar
	 * @param posX - x position von Bar
	 */
	public static void setPosX(double posX) {
		Bar.posX = posX;
	}
	/**
	 * Diese Methode gibt y position von Bar
	 * @return posY- y position von Bar
	 */
	public static double getPosY() {
		return posY;
	}
	/**
	 * Diese Methode legt y position von Bar
	 * @param posY y position von Bar
	 */
	public static void setPosY(double posY) {
		Bar.posY = posY;
	}
	/**
	 * Diese Methode gibt width von Bar
	 */
	public double getWidth() {
		return width;
	}
	/**
	 * Diese Methode legt width von Bar
	 * @param width Breite von Bar
	 */
	public static void setWidth(double width) {
		Bar.width = width;
	}
	/**
	 * Diese Methode gibt height von Bar
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * Diese Methode legt height von Bar
	 * @param height Höhe von Bar
	 */
	public static void setHeight(double height) {
		Bar.height = height;
	}

	
	
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
