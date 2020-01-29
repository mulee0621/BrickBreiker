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




/*
 * Dieser Klass behandelt sich der Wand des Spiels 
 * 
 * 
 * @author Hussain
 */
public  class Wall extends GPen {
	
	/**
	 * 
	 * @param - 1,600 -  x und y koordinate 
	 * @param - 0,-599.5  - x und y Position von der Wand
	 * @param - 683,0 -  x und y Position von der Wand
	 * @param - 0,600 -  x und y Position von der Wand 
	 */
	public Wall(){
		
	super(1,600);
	drawLine(0,-599.5); 
	drawLine(683,0);
	drawLine(0,600);
	setColor(Color.YELLOW);
		
	}
	
	
	
	  
	
}
