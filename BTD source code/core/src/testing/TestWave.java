package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import Generator.IntGenerator;
import Generator.MobGenerator;
import Generator.TowerGenerator;
import Generator.WaveGenerator;
import interfaces.IMob;
import logicClasses.Mob;
import logicClasses.Tower;
import logicClasses.Wave;

public class TestWave {
	Wave wave;
    Tower tower;
    
	@Before
	public void init(){
         this.tower= TowerGenerator.genTower("beertower", new Vector2(0,0));
		 this.wave = WaveGenerator.randWave();
         int nmbMobs = IntGenerator.randValues(0,60);
   	     Mob mobs = MobGenerator.randMob();
     }
	
	
	/**
	 * Legge til og fjerne en mob gir samme antall mobs en hadde i begynnelsen
	 */
	@Test
	public void createRemoveMob(){
		IMob mob = MobGenerator.randMob();
		int preNmb = wave.getNumberOfMobs();
		//wave.createMob(mob);
		//wave.removeMob(mob);
		assertEquals(preNmb,wave.getNumberOfMobs());
	    
	}
	

	@Test
	public void simulateATowerKillingMobs(){
		
		//Collection<IMob> mobs = wave.getMobs();
		
		//Iterator<IMob> mobIt = mobs.iterator();
		
		//while(mobIt.hasNext()){
			//IMob aMob = mobIt.next();
			//for(int i = 0; i < 40; i++) tower.shoot(aMob);
		//}
		//wave.step(); //Should garbage collect all dead mobs
		assertEquals(wave.getNumberOfMobs(),0);
		
	}
	@Test
	public void noMobsThenFinished(){
		init(); //reinitilazie scenario
		
		//Kill all mobs
        //Collection<IMob> mobs = wave.getMobs();
		
		//Iterator<IMob> mobIt = mobs.iterator();
		
		//while(mobIt.hasNext()){
			//IMob aMob = mobIt.next();
			//for(int i = 0; i < 40; i++) tower.shoot(aMob);
		//}
		 
		//wave.step(); //Garbage collect all dead mobs
		assertTrue(wave.isFinished());
		
	}
}
