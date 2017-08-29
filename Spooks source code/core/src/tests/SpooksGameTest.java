package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import logicClasses.SpooksGame;


public class SpooksGameTest {

	SpooksGame game;
	
	@Before
	public void init(){
		game = new SpooksGame();
	}
	
	@Test
	public void elapsedTimeIgnoresTimeStopped() throws InterruptedException {
		init();
		// game runs for 100ms
		Thread.sleep(100); 	
		// timer stopped for 200ms
		game.stopTimer();	
		Thread.sleep(200);	
		// elapsed time should now be 100 (+ runtime)
		assertTrue(game.getElapsedTime() >= 100 && game.getElapsedTime() <= 120);
	}
	
	@Test
	public void addOneMinuteAddsOneMinutte(){
		init();	
		long before = game.getTimeBeforeGameEnds();
		game.addOneMinute();
		long after = game.getTimeBeforeGameEnds();
		assertTrue(after - before == 60000);
	}

}
