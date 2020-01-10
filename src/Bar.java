import java.awt.Color;

import acm.graphics.GRect;

public class Bar extends GRect {
	private static double posX=300;
	private static double posY=500;
	private static double width=100;
	private static double height=8;
<<<<<<< HEAD
	private static double dx=20.5;
=======
	private static double dx=30;
>>>>>>> 64cc5f46ea1086d184d07950c4508db3ce6da120
	
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
