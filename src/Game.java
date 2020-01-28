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

	// sound attributes
	private GameSound sound;
	private Thread soundT;

	protected static Brick[][] bricks;

	// score attributes
	private int highScore;
	private GLabel highScoreL;
	private String highScoreString;

	// score counter by n of bricks
	private GLabel yourScoreB;

	// time attributes
	private int gameTime;
	private GLabel gameTimeLable;

	// file reader and writer
	private BufferedReader br = null;
	PrintWriter pw;

	// initializes screen
	@Override
	public void init() {
		addSound();
		addKeyListeners();
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		welcome();
	}

	// main body of the game, used each time after game over
	@Override
	public void run() {
		addWall();
		addBar();
		addBall();
		addBrick();
		addCounter();
	}

	// enables sound
	private void addSound() {
		sound = new GameSound();
		soundT = new Thread(sound);
		soundT.start();
	}

	// welcome screen of the game
	private void welcome() {
		setBackground(Color.black);

		// logo on the screen
		GImage logo = new GImage("logo.png");
		logo.scale(0.3);
		add(logo, (getWidth() - logo.getWidth()) / 2, (getHeight() - logo.getHeight()) * 3 / 5);

		// developers list on the screen
		GLabel devListLable = new GLabel("GeekBash : Imron, Myung, Kristijan, Hossain, Tulina");
		devListLable.setFont("Times-20");
		devListLable.setColor(Color.white);
		add(devListLable, (getWidth() - devListLable.getWidth()) / 2, (getHeight() - logo.getHeight()) * 2 / 5);

		// instructions
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

	// method adds wall to the screen
	private void addWall() {
		wall = new Wall();
		add(wall);
	}

	// method adds Bar to the screen
	private void addBar() {
		bar = new Bar();
		add(bar);
	}

	// method creates an object a ball and initializes the thread
	private void addBall() {
		ball = new Ball();
		add(ball);
		Thread t1 = new Thread(ball);
		t1.start();
	}

	// method adds 5 rows of bricks to the screen
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
	 * This method counts the time and score this method also contains main loop of
	 * game.
	 */
	private void addCounter() {
		addHighScore();
		initialiseScore();
		while (!GameOver)
			countScore();
		GameOver();

	}

	// this method is used when it is game over
	private void GameOver() {

		// music stops when gameover
		soundT.stop();

		// GameOver and Restart labels
		GImage over = new GImage("GameOver.png");
		over.scale(0.2);
		add(over, (getWidth() - over.getWidth()) / 2, (getHeight() - over.getHeight()) / 2);

		GLabel newGame = new GLabel("CLICK - NEW game!");
		newGame.setFont("Times-20");
		newGame.setColor(Color.white);
		add(newGame, getWidth() / 2, getHeight() * 4 / 5);

		// saving high score
		if (highScore < Ball.bricksScore) {
			highScore = Ball.bricksScore;
			writeNewHighScore();
		}

		waitForClick();
		removeAll();
		beginNewGame();

	}

	// this method starts new new game after game over
	private void beginNewGame() {
		GameOver = false;
		gameTime = 0;
		Ball.bricksOutside = 0;
		Ball.bricksScore = 0;

		addSound();
		run();

	}

	// this method writes high score to the file
	private void writeNewHighScore() {
		try {
			pw = new PrintWriter(new FileWriter("highScore.txt"), false);
			pw.println(highScore);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// this mehtod counts the time and score
	private void countScore() {
		// time
		gameTimeLable.setLabel("TIME: " + gameTime);
		add(gameTimeLable, 700 / 2 - gameTimeLable.getWidth() / 2, 530);

		// bricks score
		yourScoreB.setLabel("BRICKS SCORE: " + Ball.bricksScore);
		add(yourScoreB, 680 - yourScoreB.getWidth(), 530);
		gameTime++;
		pause(100);

		// music repeats after 1000 points
		if (gameTime % 1000 == 0) {
			soundT.stop();
			addSound();
		}
	}

	// this method creates time and score label and adds to the screen
	private void initialiseScore() {
		gameTimeLable = new GLabel("TIME: " + gameTime);
		gameTimeLable.setFont("Arial-18");
		gameTimeLable.setColor(Color.green);

		yourScoreB = new GLabel("BRICKS: " + Ball.bricksScore);
		yourScoreB.setFont("Arial-18");
		yourScoreB.setColor(Color.green);

	}

	// this method reads high score from file and adds high score label to the
	// screen
	private void addHighScore() {
		readHighScore();
		highScoreL = new GLabel("High Score: " + highScore);
		highScoreL.setFont("Arial-18");
		highScoreL.setColor(Color.green);
		add(highScoreL, 10, 530);
	}

	// this method reads high score from file
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

	// this method makes bar to move to the right or to the left depending on which
	// key is pressed
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