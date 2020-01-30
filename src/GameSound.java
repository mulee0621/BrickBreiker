import java.io.FileInputStream;
import javazoom.jl.player.Player;
/**
 * Diese Klasse spielt Musik im Hintergrund mit eigenem Thread.
 * @author Imron Gamidli
 *
 */
public class GameSound implements Runnable {
	 /**
	  * Dieser Method wird gerunnt wenn ein Thread erstellt wird. 
	  */
	@Override
	public void run() {
		try {

			FileInputStream fis = new FileInputStream("gameSound.mp3");
			Player playMP3 = new Player(fis);
			playMP3.play();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
