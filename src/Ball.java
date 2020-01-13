import java.awt.Color;
import java.awt.Graphics;
import acm.graphics.*;
import acm.graphics.GOval;
public class Ball extends GOval implements Runnable{
	//variablen für ball
	private static double posx = 350;
	private static double posy = 480;
	private static double grossx =20;
	private static double grossy = 20;
	private static double PAUSE_TIME = 4;
	private static double dx=2;
	
	//konstruktoren
	public Ball() {
		super(posx , posy , grossx , grossy);
		setFilled(true);
		setColor(Color.GREEN);
	}
		//method moves ball to the right
	public void moveRight() {
		posx = posx + 2;
		setLocation(posx,getY());
	}

	//method moves ball to the left
	public void moveLeft() {
		posx = posx - 2;
		setLocation(posx,getY());
	}
	
	
	
	//methoden die den Ball bewegen
	/**
	 * @
	 */
	public void bewegung() {
		boolean xfirst = true;
		double xwert = 0;
		double ywert = -1;
		int i = 0;
		double platschl = 0;
		for( ;!Game.GameOver; i++){
			posy = getY() + ywert;
			posx = getX() + xwert;
			setLocation(posx,posy);
			pause(PAUSE_TIME);
			if(posy <= 3) {
				ywert = 1;
				if( xfirst ) {
					xfirst = false;
					xwert = 2;
				}
			}
			if(posx >= 661) {
				xwert = -2;
			}
			if(posx <= 3) {
				xwert = +2;
			}
			
			if(getBounds().intersects(Game.bar.getBounds())) {	
				platschl =  posx - Game.bar.getX();
				if(platschl > 15 && platschl < 20 )
					xwert = xwert + 2.2345;
				if(platschl > 50 && platschl < 55 )
					xwert = xwert + 2.1;
				if(platschl > 80 && platschl < 85 )
					xwert = xwert + 2.2345;
				ywert = -1;
			} else  
				Game.GameOver = false;
			if(posy >= 600)
				break;
			
			for(int k = 0;k<Game.bricks.length; k++) {
				if(Game.bricks[k].getX() == posx && Game.bricks[k].getY() == posy) {
					xwert = -xwert;
					
				}
				if(Game.bricks[k].getX() + Game.bricks[k].getWidth() == posx && Game.bricks[k].getY() == posy) {
					xwert = -xwert;
					
				}
				if(getBounds().intersects(Game.bricks[k].getBounds())) { 	
					if(Game.bricks[k].getX() == posx)
						xwert = -xwert;
					if(Game.bricks[k].getX() + Game.bricks[k].getWidth() == posx)
						xwert = -xwert;
					ywert = -ywert;
					Game.bricks[k].setLocation(-100, -100);
					
					if( xfirst ) {
						xfirst = false;
						xwert = 2;
					}
					break;
				}
			}
			
			System.out.println( "X:" + posx + ", Y:" + posy );
			i = 0;
		}

	}

	@Override
	public void run() {
		bewegung();
	}
}
	
