import javax.swing.*;

public class Game {
	public static void main(String[] args) {
		
		//Imlon gonna do this part
		 // irritating imlo gonna do this as if.
		
		JFrame f = new JFrame();// creating instance of JFrame
		f.setSize(1080, 720);// 400 width and 500 height
		
		JButton b = new JButton("click");// creating instance of JButton
		b.setBounds(200, 400, 100, 40);// x axis, y axis, width, height

		f.add(b);// adding button in JFrame


		f.setLayout(null);// using no layout managers
		f.setVisible(true);// making the frame visible
	}
}