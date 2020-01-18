import java.awt.Color;
import java.awt.Graphics;
import acm.graphics.*;
import acm.graphics.GOval;
public class Ball extends GImage implements Runnable{
	//variablen für ball
	private static double posx = 350;
	private static double posy = 460;

	
	
	private static double PAUSE_TIME = 10;
	protected static double dx=0;
	protected static double dy=-3;
	
	//konstruktoren
	public Ball() {
		super("Kreitermithelmet.png");
		setLocation(posx, posy);
		scale(0.20);
	}
	//methoden die den Ball bewegen
	/**
	 * @
	 */
	public void bewegung() {
		while(!Game.GameOver) {
			
			//movement
			move(dx,dy);
			pause(PAUSE_TIME);
			
			//collision with ceiling 
			if(getY()<5) dy=-dy; 
			
			//collision with bar
			if (Game.ball.getBounds().intersects(Game.bar.getBounds()))  {
				dx=0;
				double xBall=Game.ball.getX()+15;
				double xBar=Game.bar.getX();
				
				
				if(xBall>xBar+65) {dy=-dy; dx+=1.5;}  	//right
				else if(xBall>xBar+25) dy=-dy; 			//middle
				else {dy=-dy; dx-=1.5;}					//left
				
			}
			
			//collision with  wall
			if(getX() >= 661 || getX() <= 3) dx=-dx;
			

			
			// if fr unterfallen von den ball
			if(getY() >= Game.bar.getY()+10) {								
				Game.GameOver = true;
				break;
			}
			
			//collision with bricks
			for(int k = 0;k<Game.bricks.length; k++) {
			if (Game.ball.getBounds().intersects(Game.bricks[k].getBounds())) {
				dy=-dy;
				Game.bricks[k].setLocation(-100,-100);
			}
				
			}
			
			}
		

	}

	@Override
	public void run() {
		bewegung();
	}
}
	
