import acm.graphics.*;

/**
 *  Diese Klasse ist dafuer zustaendig, dass die der Ball erstellt, bewegt und nach eine gewisse Zeit die Bloecken wieder erstellen.
 * @author Kristijan Lazeski
 *
 */
public class Ball extends GImage implements Runnable {

	// variablen fuer ball
	/**
	 * @variable posx diese Variable ist genutzt fuer die bewegung auf die x Achse
	 * @variable posy diese Variable ist genutzt fuer die bewegung auf die y Achse
	 * @variable PAUSE_TIME Variable ist genutz fuer die Geschwindigkeit der Ball
	 * @variable bricksOutside Variable ist genutzt fuer die Erstellung von der Bloeken
	 * @variable bricksScore Variable ist genutzt fuer die Aufzahlung von der Punktzahl
	 */
	private static double posx = 350;
	public static double getPosx() {
		return posx;
	}

	public static void setPosx(double posx) {
		Ball.posx = posx;
	}

	public static double getPosy() {
		return posy;
	}

	public static void setPosy(double posy) {
		Ball.posy = posy;
	}

	public static double getPAUSE_TIME() {
		return PAUSE_TIME;
	}

	public static void setPAUSE_TIME(double pAUSE_TIME) {
		PAUSE_TIME = pAUSE_TIME;
	}

	private static double posy = 470;
	private static double PAUSE_TIME = 4;
	static int bricksOutside;
	static int bricksScore;

	// konstruktoren
	/**
	 * Diese Methode stellt den Ball mit photo auf der Bildschirm.
	 */
	public Ball() {
		super("Kreitermithelmet.png");
		setLocation(posx, posy);
		scale(0.25);
	}

	// methoden die den Ball bewegen
	/**
	 * Diese Methode ist Zustaendig fuer die Bewegung von der Ball und di kollision von der Ball mit der Wand, Platform und Bloecke
	 * @variable xfirst diese Variable ist genutzt um der erste Kollision von der Ball nach rechts zu schieben
	 * @variable xwert diese Variable ist Zustaendig fuer die Verschiebung von der Ball
	 * @variable ywert diese Variable ist Zustaendig fuer die Verschiebung von der Ball
	 * @variable i diese Variable ist dafuer Zustandig damit die for schleife sich Unendlich wiederhollt
	 * @variable platschl diese Variable kurz fuer (platform schlag) gibt drei faelle wo der Ball sich einbischen anders bewegt
	 * 
	 */
	public void bewegung() {
		boolean xfirst = true;
		double xwert = 0;
		double ywert = -1;
		int i = 0;
		double platschl = 0;

		for (; !Game.GameOver; i++) { 														// Haupt schleife fuer ball bewegung
			posy = getY() + ywert; 															// POSITION Y (variable berechnung fuer berechnung von position)
			posx = getX() + xwert; 															// POSITION X (variable berechnung fuer berechnung von position)
			setLocation(posx, posy); 														// setze den ball auf die gegebenen koordinaten
			pause(PAUSE_TIME); 																// pause die die Geschwindigkeit von unser Ball regaelt
			if (posy <= 3) { 																// if fuer kollision mit wand ganz oben
				ywert = 1; 																	// geh nach unten dannach
				if (xfirst) { 																// wert fuer nach unten
					xfirst = false; 														// erste kollision geht immer nach rechts
					xwert = 2;																// wert fr nach rechts
				}
			}
			if (posx >= 661) { 																// if fuer kollision mit wand rechts
				xwert = -2; 																// wert fuer nach links
			}
			if (posx <= 3) { 																// if fuer kollision mit wand links
				xwert = +2; 																// wert fuer nach rechts
			}

			if (getBounds().intersects(Game.bar.getBounds())) { 							// if fuer kollision mit platform
				platschl = posx - Game.bar.getX();											// Beschtimmte werte fuer speziall abschiesen von den Ball
				if (platschl > 15 && platschl < 20)

					xwert = xwert + 2.2345;
				if (platschl > 50 && platschl < 55)
					xwert = xwert + 2.1;
				if (platschl > 80 && platschl < 85)
					xwert = xwert + 2.2345;
				ywert = -1;

			}
			if (posy >= 600) { 																// if fuer unterfallen von den ball
				Game.GameOver = true;
			}
			for (int k = 0; k < 5; k++) {
				for (int j = 0; j < 11; j++) {
					if (Game.ball.getBounds().intersects(Game.bricks[k][j].getBounds())) { // Haupt schleife fuer
																							// Kollision mit bloecke
						ywert = -ywert;														// geh nach untetn
						Game.bricks[k][j].setLocation(-100, -100);							// verschiebe die Bloecke auf diese koordinaen
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
	 * Diese Methode ist dafuer Zustaendig um die Bloecke wieder zu erstellen 
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