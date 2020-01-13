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
	private GameSound sound;
	private Thread soundT;
	protected static boolean oneTime=true;
	protected static Brick [] bricks ;  

	

	// background and size of screen
	@Override
	public void init() {
		addSound();
		addKeyListeners();
		setBackground(Color.BLACK);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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

	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			bar.moveRight();
			ball.moveRight();
			break;
		case KeyEvent.VK_LEFT:
			bar.moveLeft();
			ball.moveRight();
			break;
		case KeyEvent.VK_SPACE:
			 moveBall();
			break;
		}
	}

	private void moveBall() {
		
		if (oneTime) {
		Thread t1 = new Thread(ball);
		t1.start();
		oneTime=false; }
		
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
		bricks = new Brick[66];
		int x = 15;
		int y = 15;
		for (int i = 0; i < bricks.length; i++) {
			while(i<=10) {
				bricks[i]=new Brick(x,y,Color.yellow);
				add(bricks[i]);
				x += 60;
				i++;
			} x = 15;
			while(i>10 && i<=21) {
				y = 55;
				bricks[i]=new Brick(x,y,Color.red);
				add(bricks[i]);
				x += 60;
				i++;
			} x = 15;
			while(i>21 && i<=32) {
				y = 95;
				bricks[i]=new Brick(x,y,Color.blue);
				add(bricks[i]);
				x += 60;
				i++;
			} x = 15;
			while(i>32 && i<=43) {
				y = 135;
				bricks[i]=new Brick(x,y,Color.yellow);
				add(bricks[i]);
				x += 60;
				i++;
			} x = 15;
			while(i>43 && i<=54) {
				y = 175;
				bricks[i]=new Brick(x,y,Color.red);
				add(bricks[i]);
				x += 60;
				i++;
			} x = 15;
			while(i>54 && i<=65) {
				y = 215;
				bricks[i]=new Brick(x,y,Color.blue);
				add(bricks[i]);
				x += 60;
				i++;
			}
					/* 
					if(k == 1) bricks[i]=new Brick(x,y,Color.green);
					if(k == 3) bricks[i]=new Brick(x,y,Color.blue);
					if(k == 5) bricks[i]=new Brick(x,y,Color.magenta);
				
					if(i == 1) bricks[i]=new Brick(x,y,Color.cyan);
					if(i == 4) bricks[i]=new Brick(x,y,Color.red);
					if(i == 7) bricks[i]=new Brick(x,y,Color.orange);
					if(i == 10) bricks[i]=new Brick(x,y,Color.pink);
					 */
				
				
		
		}
	}


	public static void main(String[] args) {
		new Game().start();
	}
}