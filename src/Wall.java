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


public  class Wall extends GPen {

	
	public Wall(){
		
	super(1,600);
	drawLine(0,-599); 
	drawLine(697,0);
	drawLine(0,597);
	
	setColor(Color.YELLOW);
		
	}
	
	
	
	  
	
}
