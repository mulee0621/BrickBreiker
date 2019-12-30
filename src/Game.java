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

public class Game extends GraphicsProgram {
	private static final long serialVersionUID = 1L;
	public static final int WINDOW_WIDTH=700;
	public static final int WINDOW_HEIGHT=600;
	protected static boolean GameOver = false;
	private Bar bar;
	protected static Wall wall;
	protected static Ball ball;

	// background and size of screen
	@Override
	public void init() {
		addKeyListeners();
		setBackground(Color.BLACK);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	@Override
	public void run() {
		addWall();
		addBar();
		addBall();
		addBrick();

	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			bar.moveRight();
			break;
		case KeyEvent.VK_LEFT:
			bar.moveLeft();
			break;
		case KeyEvent.VK_SPACE:
			moveBall();
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
	
	// method creates an object a bricks
		private void addBrick() {
			Brick [] bricks = new Brick[11];
			int x = 15;
			int y = 15;
			for (int i = 0; i < bricks.length; i++) {
				bricks[i]=new Brick(x,y,Color.GRAY);
				add(bricks[i]);
				x += 60;
			}	
			
			bricks[7].setColor(Color.RED);
		}


	public static void main(String[] args) {
		new Game().start();
	}
}