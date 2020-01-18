import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;

import java.util.Random;

public class Game extends GraphicsProgram {
	private static final long serialVersionUID = 1L;
	public static final int WINDOW_WIDTH=700;
	public static final int WINDOW_HEIGHT=600;
	protected static boolean GameOver = false;
	protected static Bar bar;
	protected static Wall wall;
	protected static Ball ball;
	private boolean isBalMov=false;
	private GameSound sound;
	private Thread soundT;
	protected static boolean oneTime=true;
	protected static Brick [][] bricks ; 
	
	// score stuff
		private int highScore;
		private GLabel highScoreL;
		private String highScoreString;
		private int yourScore;
		private GLabel yourScoreL;
		private BufferedReader br = null;
		PrintWriter pw;

	

	// background and size of screen
	@Override
	public void init() {
		addSound();
		addKeyListeners();
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		welcome();
	}

	private void addSound() {
		sound = new GameSound();
		soundT=new Thread(sound);
		soundT.start();
	} 

	@Override
	public void run() {
		
		addWall();
		addBar();
		addBall();
		addBrick();
		addCounter();
		
		

	}
	private void addCounter() {
		addHighScore();
		initialiseScore();

		while (!GameOver) 
			countScore();

		 GameOver();
		
	}

	private void GameOver() {

		yourScoreL.setColor(Color.green);

		//music stops when gameover
		soundT.stop();
		
		// GameOver and Restart labels
		GImage over = new GImage("GameOver.png");
		over.scale(0.2);
		add(over, (getWidth() - over.getWidth()) / 2, getHeight() / 2 - over.getHeight());
	
		// high score
		if (highScore < yourScore) {
			highScore = yourScore;
			writeNewHighScore();
		}

		waitForClick();
		removeAll();
		beginNewGame();
		
	}

	private void beginNewGame() {
		GameOver = false;
		yourScore = 0;
		oneTime=true;
		
		//sound starts again after gameover
		addSound();
		
		run();
		
		
		
	}


	private void writeNewHighScore() {
		try {
			pw = new PrintWriter(new FileWriter("highScore.txt"), false);
			pw.println(highScore);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void countScore() {
		yourScoreL.setLabel("YOUR SCORE: " + yourScore);
		add(yourScoreL, 680-yourScoreL.getWidth(), 530);
		yourScore++;
		pause(100);
		
		//music repeats after 1000 points
		if(yourScore%1000==0)  { soundT.stop(); addSound();}
	}

	private void initialiseScore() {
		yourScoreL = new GLabel("CURRENT SCORE: " + yourScore);
		yourScoreL.setFont("Arial-18");
		yourScoreL.setColor(Color.green);
		
	}

	private void addHighScore() {
		
		readHighScore();
		
		highScoreL = new GLabel("High Score: " + highScore);
		highScoreL.setFont("Arial-18");
		highScoreL.setColor(Color.green);
		add(highScoreL, 10, 530);
		
	}

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

	private void welcome() {
		setBackground(Color.black);
		GLabel l = new GLabel("Brick Braker");
		l.setFont("Times-40");
		l.setColor(Color.green);
		add(l, (getWidth() - l.getWidth()) / 2, (getHeight() - l.getAscent()) / 2);

		GLabel l2 = new GLabel("Imron, Myung, Kristijan, Hossain, Tulina");
		l2.setFont("Times-30");
		l2.setColor(Color.green);
		add(l2, (getWidth() - l2.getWidth()) / 2, l2.getAscent() + (getHeight() - l.getAscent()) / 2);

		GLabel l1 = new GLabel("Instructions: Space-Start the ball, RIGHT-Bar moves right,");
		l1.setFont("Times-20");
		l1.setColor(Color.green);
		add(l1, 0, getHeight() - 2 * l1.getHeight());

		GLabel l15 = new GLabel("LEFT-Bar moves left,  CLICK to begin");
		l15.setFont("Times-20");
		l15.setColor(Color.green);
		add(l15, 0, 540);
		
		waitForClick();
		removeAll();

	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			if(!GameOver) bar.moveRight();
			break;
		case KeyEvent.VK_LEFT:
			if(!GameOver) bar.moveLeft();
			break;
		}
	}

	private void moveBall() {
		Thread t1 = new Thread(ball);
		t1.start();
		
	}

	// method creates an object a ball
	private void addBall() {
		ball = new Ball();
		add(ball);
		Thread t1 = new Thread(ball);
		t1.start();
	}

	// method creates an object a bar
	private void addBar() {
		bar = new Bar();
		add(bar);
	}

	// method creates an object a wall
	private void addWall() {
		wall = new Wall();
		add(wall);
		
	}
	
	// method creates map of bricks
	private void addBrick() {
		bricks = new Brick[6][11];
		Color color[] = {Color.red,Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta};
		int x = 18;
		int y = 18;
		
		for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 11; j++) {
            	bricks[i][j]=new Brick(x,y,color[i]);
				add(bricks[i][j]);
				x += 60;
            }
            y+=40;
            x = 18;
           }
	}

	public static void main(String[] args) {
		new Game().start();
	}
}