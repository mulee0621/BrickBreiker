import java.awt.Color;
import java.awt.Graphics;
import acm.graphics.*;
import acm.graphics.GOval;

public class Ball extends GImage implements Runnable {
	// variablen für ball
	private static double posx = 340;
	private static double posy = 460;
	private static double PAUSE_TIME = 10;
	protected static double dx = 0;
	protected static double dy = -3;
	 boolean isBarTouched=true;
	 protected static boolean bob=true;
	
	

	// konstruktor
	public Ball() {
		super("Kreitermithelmet.png");
		setLocation(posx, posy);
		scale(0.23);
	}

	// methode die den Ball bewegt
	/**
	 * @
	 */
	public void bewegung() {
		while (!Game.GameOver) {

			// movement
			move(dx, dy);
			pause(PAUSE_TIME);

			// collision with ceiling
			if (getY() < 5)
				dy = -dy;

			// collision with bar
			if (Game.ball.getY() + 5 < Game.bar.getY() && Game.ball.getBounds().intersects(Game.bar.getBounds())) {
				bob=false;
				
				dx = 0;
				
				

				// right side of bar
				if (Game.ball.getX() + 15 > Game.bar.getX() + 60) {
					dy = -dy;
					dx += 1.5;
					// middle side of bar
				} else if (Game.ball.getX() + 15 > Game.bar.getX() + 40)
					dy = -dy;
				// left side of bar
				else {
					dy = -dy;
					dx -= 1.5;
				}

			}
			bob=true;	
			// collision with wall
			if (getX() >= 661 || getX() <= 3)
				dx = -dx;

			// collision with bricks
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 11; j++) {
					if (Game.ball.getBounds().intersects(Game.bricks[i][j].getBounds())) {
						
						
						dx = 0;
						double xBall = Game.ball.getX() + 15;
						double xBrick = Game.bricks[i][j].getX();

						// right side of brick
						if (xBall > xBrick + 30) {
							dy = -dy;
							dx += 0.5;

							// middle side of brick
						} else if (xBall > xBrick + 10)
							dy = -dy;

						// left side of brick
						else {
							dy = -dy;
							dx -= 0.5;
						}
						Game.bricks[i][j].setLocation(-100, -100);
					}
				}
			}

			// gameover
			if (getY() >= 600) {
				Game.GameOver = true;
				break;
			}

		}

	}

	@Override
	public void run() {
		bewegung();
	}
}
