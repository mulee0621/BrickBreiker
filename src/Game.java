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
	protected static boolean GameOver=false;
	
	//background and size  of screen 
	@Override
	public void init() {
		addKeyListeners();
		setBackground(Color.black);
		setSize(700, 600);
	}
	@Override
	public void run() {
		addWall();
		addBar();
		addBall();
	}


	//method creates an object a ball
	private void addBall() {
		
	}
	//method creates an object a bar
	private void addBar() {
		
	}
	//method creates an object a wall
	private void addWall() {
		
	}
	public static void main(String[] args) {
		new Game().start();
	}
}