import static org.junit.Assert.*;

import org.junit.Test;

public class BallTest {

	public class Balltesting {
		public void testBall() {
			Ball test = new Ball();
			double x = test.getPosx();
			assserEquals(350 , x);
			double y = test.getPosy();
			assserEquals(470 , y);
			double p = test.getPAUSE_TIME();
			assserEquals(4 , p);
		}

		private boolean assserEquals(double i, double x) {
			if (i==x) 
				return true;
			return false;
		}

		public void testBewegung() {
			Ball test = new Ball();
			double output = test.getPosx();
			assserEquals(350 , output);
			double y = test.getPosy();
			assserEquals(470 , y);
			double p = test.getPAUSE_TIME();
			assserEquals(4 , p);
		}

		public void testRun() {
			Ball test = new Ball();
			double output = test.getX();
			assserEquals(350 , output);
			double y = test.getPosy();
			assserEquals(470 , y);
			double p = test.getPAUSE_TIME();
			assserEquals(4 , p);
		}

	}

}
