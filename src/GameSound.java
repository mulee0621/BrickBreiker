import java.io.FileInputStream;

import javazoom.jl.player.Player;



public class GameSound implements Runnable {
	@Override
	public void run() {
		playJumpSound();

	}
	private static void playJumpSound() {
		try {

			FileInputStream fis = new FileInputStream("gameSound.mp3");
			Player playMP3 = new Player(fis);
			playMP3.play();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
