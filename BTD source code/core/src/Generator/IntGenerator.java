package Generator;

import java.util.Random;

public class IntGenerator{

	
	
	public static Integer randValues() {
	       Random rnd = new Random();
	       return rnd.nextInt();
	}
	
	public static Integer randValues (int min, int max){
	     Random rnd = new Random();
		 return rnd.nextInt((max - min) + 1) + min;
	}

}
