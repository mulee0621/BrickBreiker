
import acm.graphics.*;
/**
 * Diese Klasse ist dafür zuständig, dass die der Ball erstellt, bewegt und nach eine gewisse Zeit die Blöcke wieder erstellen  
 * @author Kiko
 *
 */

public class Ball extends GImage implements Runnable {
	// variablen für ball
	/**
	 * @variable posx diese Variable ist genutzt für die bewegung auf die x Achse
	 * @variable posy diese Variable ist genutzt für die bewegung auf die y Achse
	 * @variable PAUSE_TIME Variable ist genutz für die Geschwindigkeit der Ball
	 * @variable bricksOutside Variable ist genutzt für die Erstellung von Blöcke
	 * @variable bricksScore Variable ist genutzt für die Aufzahlung von der Punktzahl
	 */
	private static double posx = 350;
	private static double posy = 470;
	private static double PAUSE_TIME = 4;
	static int bricksOutside;
	static int bricksScore;

	// konstruktoren
	/**
	 * 
	 */
	public Ball() {
		super("Kreitermithelmet.png");
		setLocation(posx, posy);
		scale(0.25);
	}

	// methoden die den Ball bewegen
	/**
	 * Diese Methode ist Zuständig für die Bewegung von der Ball und di kollision von der Ball mit der Wand, Platform und Blöcke
	 * @variable xfirst diese Variable ist genutzt um der erste Kollision von der Ball nach rechts zu schieben
	 * @variable xwert diese Variable ist Zuständig für die Verschiebung von der Ball
	 * @variable ywert diese Variable ist Zuständig für die Verschiebung von der Ball
	 * @variable i diese Variable ist dafür Zustandig damit die for schleife sich Unendlich wiederhollt
	 * @variable platschl diese Variable kurz für (platform schlag) gibt drei fälle wo der Ball sich einbischen anders bewegt
	 * 
	 */
	public void bewegung() {
		boolean xfirst = true;
		double xwert = 0;
		double ywert = -1;
		int i = 0;
		double platschl = 0;
		for (; !Game.GameOver; i++) { 														// Haupt schleife für ball bewegung
			posy = getY() + ywert; 															// POSITION Y (variable berechnung für berechnung von position)
			posx = getX() + xwert; 															// POSITION X (variable berechnung für berechnung von position)
			setLocation(posx, posy); 														// setze den ball auf die gegebenen koordinaten
			pause(PAUSE_TIME); 																// pause die die Geschwindigkeit von unser Ball regält
			if (posy <= 3) { 																// if für kollision mit wand ganz oben
				ywert = 1; 																	// geh nach unten dannach
				if (xfirst) { 																// wert für nach unten
					xfirst = false; 														// erste kollision geht immer nach rechts
					xwert = 2;																// wert für nach rechts
				}
			}
			if (posx >= 661) { 																// if für kollision mit wand rechts
				xwert = -2; 																// wert für nach links
			}
			if (posx <= 3) { 																// if für kollision mit wand links
				xwert = +2; 																// wert für nach rechts
			}

			if (getBounds().intersects(Game.bar.getBounds())) { 							// if für kollision mit platform
				platschl = posx - Game.bar.getX();											// Beschtimmte werte für speziall abschiesen von den Ball
				if (platschl > 15 && platschl < 20)
					xwert = xwert + 2.2345;
				if (platschl > 50 && platschl < 55)
					xwert = xwert + 2.1;
				if (platschl > 80 && platschl < 85)
					xwert = xwert + 2.2345;
				ywert = -1;
			}
			if (posy >= 600) { 																// if für unterfallen von den ball
				Game.GameOver = true;
			}
			for (int k = 0; k < 5; k++) {
				for (int j = 0; j < 11; j++) {
					if (Game.ball.getBounds().intersects(Game.bricks[k][j].getBounds())) { // Haupt schleife für
																							// Kollision mit böocke
						ywert = -ywert;														// geh nach untetn
						Game.bricks[k][j].setLocation(-100, -100);							// verschiebe die Blöcke auf diese koordinaen
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
	/**
	 * Diese Methode ist dafür Zuständig um die Blöcke wieder zu erstellen 
	 */
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