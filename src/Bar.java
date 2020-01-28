import java.awt.Color;

import acm.graphics.GRect;

public class Bar extends GRect {
	private static double posX=300;
	private static double posY=500;
	private static double width=100;
	private static double height=8;

	private static double dx=25;
	
	//this is constructor
	public Bar() {
		super(posX,posY,width,height);
		setFilled(true);
		setColor(Color.BLUE);
	}

	//method moves bar to the right
	public void moveRight() {
		if(getX()<581) move(dx,0);	
		else setLocation(581, getY());
	}

	//method moves bar to the left
	public void moveLeft() {
		if (getX()>2) move(-dx,0); 
	    else setLocation(2, getY());
	}

}
