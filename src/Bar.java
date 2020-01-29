/**
 * Diese Klasse dient der Herstellung von Plattform und der Festlegung ihrer Figur und ersten Position.
 * und die Bewegungsdistanz (die die Geschwindigkeit des Bars sein kann) einstellen und damit kann der Flattform nach hinten 
 * und in die richtige Richtung bewegt werden.
 */

import java.awt.Color;
import acm.graphics.GRect;

public class Bar extends GRect {
	private static double posX=300;
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
