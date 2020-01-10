import java.awt.Color;
import java.awt.Graphics;
import acm.graphics.*;
import acm.graphics.GOval;
public class Ball extends GOval implements Runnable{
	//variablen f�r ball
	private static double posx = 350;
	private static double posy = 480;
	private static double grossx =20;
	private static double grossy = 20;
	private static double PAUSE_TIME = 4;
	
	//konstruktoren
	public Ball() {
		super(posx , posy , grossx , grossy);
		setFilled(true);
		setColor(Color.GREEN);
	}
	
	
	//methoden die den Ball bewegen
	/**
	 * @
	 */
	public void bewegung() {
		boolean xfirst = true;
		int xwert = 0;
		int ywert = -1;
		int i = 0;
		int count = 0;
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
				ywert = -1;
			} else  
				Game.GameOver = false;
			if(posy >= 600)
				break;
			
			for(int k = 3;k<Game.bricks.length; k++) {
				if(getBounds().intersects(Game.bricks[k].getBounds())) { 	
					Game.bricks[k].setLocation(-100, -100);
					ywert = -ywert;
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
	
