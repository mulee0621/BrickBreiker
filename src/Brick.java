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

public class Brick extends GRect{
	public Brick() {
		super(100, 50, 50, 30);
		setFilled(true);
		setColor(Color.RED);
	}
}
