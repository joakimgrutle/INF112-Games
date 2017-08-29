package logicClasses;

import java.util.Random;

public class JumpScareRandom {
	private Random rand;
	
	public boolean willScare(){
		rand = new Random();
		int r = rand.nextInt(100);
		if(r == 20)
			return true;
		return false;
	}
}
