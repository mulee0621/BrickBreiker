import javax.swing.*;

public class Game {
	public static void main(String[] args) {
		
		//Imlon gonna do this part
		
		
		JFrame f = new JFrame();// creating instance of JFrame

		JButton b = new JButton("click");// creating instance of JButton
		b.setBounds(200, 400, 100, 40);// x axis, y axis, width, height

		f.add(b);// adding button in JFrame

		f.setSize(500, 500);// 400 width and 500 height
		f.setLayout(null);// using no layout managers
		f.setVisible(true);// making the frame visible
	}
}