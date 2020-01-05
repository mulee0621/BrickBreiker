import java.awt.Color;
import java.awt.Graphics;
import acm.graphics.*;
import acm.graphics.GOval;
public class Ball extends GOval implements Runnable{
	//variablen für ball
	private static double posx = 350;
	private static double posy = 480;
	private static double grossx =23;
	private static double grossy = 23;
	private static double PAUSE_TIME = 0.546;
	
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
		for( ;!Game.GameOver; i++){
			posy = getY() + ywert;
			posx = getX() + xwert;
			setLocation(posx,posy);
			pause(PAUSE_TIME);
			if(posy <= 20) {
				ywert = 1;
				if( xfirst ) {
					xfirst = false;
					xwert = 2;
				}
			}
			if(posx >= 650) {
				xwert = -2;
			}
			if(posx < 20) {
				xwert = +2;
			}
			if(posy >= 301) {					
				ywert = -1;
			}
			posy = getY() + ywert;
			posx = getX() + xwert;
			setLocation(posx,posy);
			System.out.println( "X:" + posx + ", Y:" + posy );
			i = 0;
		}

	}

	@Override
	public void run() {
		bewegung();
	}
}
	
