import java.awt.Color;
import java.awt.Graphics;
import acm.graphics.*;
import acm.graphics.GOval;

public class Ball extends GImage implements Runnable{
	//variablen f�r ball

	private static double posx = 350;
	private static double posy = 470;
	private static double grossx =20;
	private static double grossy = 20;
	private static double PAUSE_TIME = 4;
	private static double dx=2;
	
	//konstruktoren
	public Ball() {
		super("Kreitermithelmet.png");
		setLocation(posx, posy);
		scale(0.25);
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
		for( ;!Game.GameOver; i++){							//Haupt schleife f�r ball bewegung
			posy = getY() + ywert;							// POSITION Y (variable berechnung f�r berechnung von position)
			posx = getX() + xwert;							// POSITION X (variable berechnung f�r berechnung von position)
			setLocation(posx,posy);							// setze den ball auf die gegebenen koordinaten
			pause(PAUSE_TIME);								// pause die die Geschwindigkeit von unser Ball reg�lt
			if(posy <= 3) {									// if f�r kollision mit wand ganz oben
				ywert = 1;									// geh nach unten dannach
				if( xfirst ) {								// wert f�r nach unten
					xfirst = false;							// erste kollision geht immer nach rechts
					xwert = 2;								// wert f�r nach rechts
				}
			}
			if(posx >= 661) {								// if f�r kollision mit wand rechts
				xwert = -2;									// wert f�r nach links
			}
			if(posx <= 3) {									// if f�r kollision mit wand links
				xwert = +2;									// wert f�r nach rechts
			}
			
			if(getBounds().intersects(Game.bar.getBounds())) {		// if f�r kollision mit platform
				platschl =  posx - Game.bar.getX();					// Beschtimmte werte f�r speziall abschiesen von den Ball
				if(platschl > 15 && platschl < 20 )
					xwert = xwert + 2.2345;
				if(platschl > 50 && platschl < 55 )
					xwert = xwert + 2.1;
				if(platschl > 80 && platschl < 85 )
					xwert = xwert + 2.2345;
				ywert = -1;
			} 
			if(posy >= 600) {								// if f�r unterfallen von den ball
				Game.GameOver = true;
				break;
			}
			


			for(int k = 0;k<Game.bricks.length; k++) {								// Haupt schleife f�r Kollision mit b�ocke
				if(Game.bricks[k].getX() == posx && Game.bricks[k].getY() == posy) {	// if f�r kollision zwischen ball und block von die linke seite von den block
					xwert = -xwert;														// was danach passieren soll
					
				}
				if(Game.bricks[k].getX() + Game.bricks[k].getWidth() == posx && Game.bricks[k].getY() == posy) {	// if f�r kollision zwischen ball und block von die rechte seite von den block
					xwert = -xwert;														// was danach passieren soll
					
				}

	
				if(getBounds().intersects(Game.bricks[k].getBounds())) { 				// if f�r kollision zwischen ball und block mit BOUNDS
					if(Game.bricks[k].getX() == posx)									// if f�r kollision zwischen ball und block von die linke seite von den block
						xwert = -xwert;
					if(Game.bricks[k].getX() + Game.bricks[k].getWidth() == posx)		// if f�r kollision zwischen ball und block von die rechte seite von den block
						xwert = -xwert;
					ywert = -ywert;
					Game.bricks[k].setLocation(-100, -100);								// die bl�cke auser von den screen setzen
					
					if( xfirst ) {														// erste kollision geht immer nach rechts
						xfirst = false;
						xwert = 2;
					}
					break;
				}
			}
			
			System.out.println( "X:" + posx + ", Y:" + posy );
			i = 0;													// i wird auf 0 gesetzt damit die schleife unendlich wird ohne dass risiko von einen int overflow
		}

	}

	@Override
	public void run() {
		bewegung();
	}
}
	
