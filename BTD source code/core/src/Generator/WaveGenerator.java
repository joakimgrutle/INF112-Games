package Generator;

import java.util.Collection;

import logicClasses.ogge;



public class WaveGenerator {
      public static Wave randWave(){
    	  int level = IntGenerator.randValues(0,100);

    	  return new Wave(level);
      }
}
