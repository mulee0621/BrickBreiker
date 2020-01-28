
import acm.graphics.*;


public class Ball extends GImage implements Runnable {
	// variablen f�r ball
	private static double posx = 350;
	private static double posy = 470;
	private static double PAUSE_TIME = 4;
	static int bricksOutside;
	static int bricksScore;

	// konstruktoren
	public Ball() {
		super("Kreitermithelmet.png");
		setLocation(posx, posy);
		scale(0.25);
	}

	// methoden die den Ball bewegen
	/**
	 * @
	 */
	public void bewegung() {
		boolean xfirst = true;
		double xwert = 0;
		double ywert = -1;
		int i = 0;
		double platschl = 0;
		for (; !Game.GameOver; i++) { 														// Haupt schleife f�r ball bewegung
			posy = getY() + ywert; 															// POSITION Y (variable berechnung f�r berechnung von position)
			posx = getX() + xwert; 															// POSITION X (variable berechnung f�r berechnung von position)
			setLocation(posx, posy); 														// setze den ball auf die gegebenen koordinaten
			pause(PAUSE_TIME); 																// pause die die Geschwindigkeit von unser Ball reg�lt
			if (posy <= 3) { 																// if f�r kollision mit wand ganz oben
				ywert = 1; 																	// geh nach unten dannach
				if (xfirst) { 																// wert f�r nach unten
					xfirst = false; 														// erste kollision geht immer nach rechts
					xwert = 2;																// wert f�r nach rechts
				}
			}
			if (posx >= 661) { 																// if f�r kollision mit wand rechts
				xwert = -2; 																// wert f�r nach links
			}
			if (posx <= 3) { 																// if f�r kollision mit wand links
				xwert = +2; 																// wert f�r nach rechts
			}

			if (getBounds().intersects(Game.bar.getBounds())) { 							// if f�r kollision mit platform
				platschl = posx - Game.bar.getX();											// Beschtimmte werte f�r speziall abschiesen von den Ball
				if (platschl > 15 && platschl < 20)
					xwert = xwert + 2.2345;
				if (platschl > 50 && platschl < 55)
					xwert = xwert + 2.1;
				if (platschl > 80 && platschl < 85)
					xwert = xwert + 2.2345;
				ywert = -1;
			}
			if (posy >= 600) { 																// if f�r unterfallen von den ball
				Game.GameOver = true;
			}
			for (int k = 0; k < 5; k++) {
				for (int j = 0; j < 11; j++) {
					if (Game.ball.getBounds().intersects(Game.bricks[k][j].getBounds())) { // Haupt schleife f�r
																							// Kollision mit b�ocke
						ywert = -ywert;														// geh nach untetn
						Game.bricks[k][j].setLocation(-100, -100);							// verschiebe die Bl�cke auf diese koordinaen
						bricksOutside++;													// zaehler feur vie fiele bloecke waren getroffen
						bricksScore++;														// zaehler feur vie fiele bloecke waren getroffen
						if (bricksOutside == 55) {
							setLocation(350, 470);
							ywert=-1;
							generateNewBricks();}											//aufruff von der methode
						if (xfirst) { 														// erste kollision geht immer nach rechts
							xfirst = false;
							xwert = 2;
						}
						break;																//schleife abbruch
					}
				}

			}

			
			i = 0; 																			// i wird auf 0 gesetzt damit die schleife unendlich wird ohne dass risiko von
																							// einen int overflow
		}

	}

	private void generateNewBricks() {
		int x = 18;
		int y = 18;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 11; j++) {
				Game.bricks[i][j].setLocation(x,y);
				x += 60;
			}
			y += 40;
			x = 18;

		}
		bricksOutside=0;
	}

	@Override
	public void run() {
		bewegung();
	}
}