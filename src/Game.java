import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import acm.graphics.*;
import acm.program.*;

public class Game extends GraphicsProgram {
	private static final long serialVersionUID = 1L;

	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 600;

	protected static boolean GameOver;

	protected static Bar bar;

	protected static Wall wall;

	protected static Ball ball;

	/**
	 *  Sound-Attribute
	 */
	private GameSound sound;
	private Thread soundT;

	protected static Brick[][] bricks;

	/** 
	 * Punkte-Attribute
	 */
	private int highScore;
	private GLabel highScoreL;
	private String highScoreString;

	/**
	 * Punktezähler der berührten Bloeken
	 */
	private GLabel yourScoreB;

	/**
	 *  Zeit-Attribute
	 */
	private int gameTime;
	private GLabel gameTimeLable;

	/**
	 * Dateileser und -schreiber
	 */
	private BufferedReader br = null;
	PrintWriter pw;

	/**
	 * initialisiert den Bildschirm
	 */
	@Override
	public void init() {
		addSound();
		addKeyListeners();
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		welcome();
	}

	/**
	 * Hauptteil des Spiels, der auch jedes Mal nach Spielende verwendet wird
	 */
	@Override
	public void run() {
		addWall();
		addBar();
		addBall();
		addBrick();
		addCounter();
	}

	/**
	 * Abspielen von Hintergründen
	 */
	private void addSound() {
		sound = new GameSound();
		soundT = new Thread(sound);
		soundT.start();
	}

	/**
	 * Begrüßungsbildschirm des Spiels, der den Gruppennamen, die Mitglieder und die Anweisungen erklärt
	 */
	private void welcome() {
		setBackground(Color.black);

		/**
		 *  Logo auf der Startseite
		 */
		GImage logo = new GImage("logo.png");
		logo.scale(0.3);
		add(logo, (getWidth() - logo.getWidth()) / 2, (getHeight() - logo.getHeight()) * 3 / 5);

		/**
		 * Entwicklerliste auf der Startseite
		 */
		GLabel devListLable = new GLabel("GeekBash : Imron, Myung, Kristijan, Hossain, Tulina");
		devListLable.setFont("Times-20");
		devListLable.setColor(Color.white);
		add(devListLable, (getWidth() - devListLable.getWidth()) / 2, (getHeight() - logo.getHeight()) * 2 / 5);

		/**
		 * Anleitungen
		 */
		GLabel instLable1 = new GLabel("Instructions: CLICK - To begin , SPACE - To start the ball");
		instLable1.setFont("Times-20");
		instLable1.setColor(Color.green);
		add(instLable1, 80, getHeight() - 2 * instLable1.getHeight());
		GLabel instLable2 = new GLabel("LEFT - To move Bar left, RIGHT - To moves Bar right");
		instLable2.setFont("Times-20");
		instLable2.setColor(Color.green);
		add(instLable2, 140, 540);

		waitForClick();
		removeAll();

	}

	/**
	 * Methode zum Hinzufügen einer Wand zur Spieleseite
	 */
	private void addWall() {
		wall = new Wall();
		add(wall);
	}

	/**
	 *  Methode zum Hinzufügen von Bar zur Spieleseite
	 */
	private void addBar() {
		bar = new Bar();
		add(bar);
	}

	/**
	 * Verfahren zum Erzeugen eines Objekts, das ein Ball ist und den Faden initialisiert
	 */
	private void addBall() {
		ball = new Ball();
		add(ball);
		Thread t1 = new Thread(ball);
		t1.start();
	}

	/**
	 * Methode zum Hinzufügen von 5 Reihen von Bloecken zur Spieleseite
	 */
	private void addBrick() {
		bricks = new Brick[6][11];
		Color color[] = { Color.red, Color.orange, Color.yellow, Color.green, Color.blue };
		int x = 18;
		int y = 18;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 11; j++) {
				bricks[i][j] = new Brick(x, y, color[i]);
				add(bricks[i][j]);
				x += 60;
			}
			y += 40;
			x = 18;
		}
	}

	/**
	 * Diese Methode zählt die Zeit und das Punkteergebnis und 
	 * enthält auch die Hauptschleife des Spiels.
	 */
	private void addCounter() {
		addHighScore();
		initialiseScore();
		while (!GameOver)
			countScore();
		GameOver();

	}
	/**
	 * Diese Methode wird verwendet, wenn das Spiel vorbei ist.
	 */
	private void GameOver() {

		/**
		 * Die Musik hört auf, wenn die Partie vorbei ist.
		 */
		soundT.stop();

		/**
		 * GameOver Label
		 */
		GImage over = new GImage("GameOver.png");
		over.scale(0.2);
		add(over, (getWidth() - over.getWidth()) / 2, (getHeight() - over.getHeight()) / 2);

		/**
		 * Neustart-Label
		 */
		GLabel newGame = new GLabel("CLICK - NEW game!");
		newGame.setFont("Times-20");
		newGame.setColor(Color.white);
		add(newGame, getWidth() / 2, getHeight() * 4 / 5);

		/**
		 * hohe Punktzahl speichern
		 */
		if (highScore < Ball.bricksScore) {
			highScore = Ball.bricksScore;
			writeNewHighScore();
		}

		waitForClick();
		removeAll();
		beginNewGame();

	}

	/**
	 * diese Methode, um ein neues Spiel zu beginnen, nachdem das Spiel beendet ist
	 */
	private void beginNewGame() {
		GameOver = false;
		gameTime = 0;
		Ball.bricksOutside = 0;
		Ball.bricksScore = 0;

		addSound();
		run();

	}

	/**
	 *  diese Methode, um eine hohe Punktzahl in die textdatei zu schreiben
	 */
	private void writeNewHighScore() {
		try {
			pw = new PrintWriter(new FileWriter("highScore.txt"), false);
			pw.println(highScore);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 *  diese Methode zählt die Zeit und das Punktergebnis
	 */
	private void countScore() {
		/**
		 * um die Laufzeit des Spiels anzuzeigen
		 */
		gameTimeLable.setLabel("TIME: " + gameTime);
		add(gameTimeLable, 700 / 2 - gameTimeLable.getWidth() / 2, 530);

		/**
		 * um zu zeigen, wie viele Bloecken verschwunden sind
		 */
		yourScoreB.setLabel("BRICKS SCORE: " + Ball.bricksScore);
		add(yourScoreB, 680 - yourScoreB.getWidth(), 530);
		gameTime++;
		pause(100);

		/**
		 *  die Hintergrundmusik zu wiederholen, nachdem 1000 Punkte gesammelt wurden
		 */
		if (gameTime % 1000 == 0) {
			soundT.stop();
			addSound();
		}
	}

	/**
	 * zum Erstellen von Zeit- und Punktebezeichnungen und fügt dem Bildschirm
	 */
	private void initialiseScore() {
		gameTimeLable = new GLabel("TIME: " + gameTime);
		gameTimeLable.setFont("Arial-18");
		gameTimeLable.setColor(Color.green);

		yourScoreB = new GLabel("BRICKS: " + Ball.bricksScore);
		yourScoreB.setFont("Arial-18");
		yourScoreB.setColor(Color.green);

	}

	/**
	 * Diese Methode fügt dem Bildschirm ein gelesene Highscore-Label hinzu.
	 */
	private void addHighScore() {
		readHighScore();
		highScoreL = new GLabel("High Score: " + highScore);
		highScoreL.setFont("Arial-18");
		highScoreL.setColor(Color.green);
		add(highScoreL, 10, 530);
	}

	/**
	 * Diese Methode liest die hoesten Punktzahl aus der Datei
	 */
	private void readHighScore() {
		try {
			br = new BufferedReader(new FileReader("highScore.txt"));
			highScoreString = br.readLine();
			highScore = Integer.parseInt(highScoreString);

		} catch (FileNotFoundException e) {
			System.err.println("The file you specified does not exist.");
		} catch (IOException e) {
			System.err.println("Some other IO exception occured. Message: " + e.getMessage());
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Bei dieser Methode wird der Balken nach rechts oder links bewegt, 
	 * je nachdem, welche Taste gedrückt wird.
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			if (!GameOver)
				bar.moveRight();
			break;
		case KeyEvent.VK_LEFT:
			if (!GameOver)
				bar.moveLeft();
			break;
		}
	}

	public static void main(String[] args) {
		new Game().start();
	}
}