import java.awt.Graphics;
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

public class Brick extends GraphicsProgram {
	public Brick() {
		GRect rect1 = new GRect(100, 50, 50, 30);
		rect1.setFilled(true);
		rect1.setColor(Color.RED);
		add(rect1);
		
		GRect rect2 = new GRect(200, 50, 50, 30);
		rect2.setFilled(true);
		rect2.setColor(Color.YELLOW);
		add(rect2);

	}
}
