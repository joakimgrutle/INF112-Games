package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import Generator.IntGenerator;
import Generator.TDGameGenerator;
import Generator.TowerGenerator;
import logicClasses.TDGame;
import logicClasses.Tower;

public class TestTDGame {
	private TDGame tdGame;
	private Tower tower;


	@Before
	public void init(){
       this.tdGame      = TDGameGenerator.randTDGame();
       this.tower       = TowerGenerator.genTower("beertower", new Vector2(0,0));

	}
	/**
	 * Å kjøpe et tårn, gjør at en sitter igjen med korrekt antall beløp.
	 */
	@Test
	public void buyTowerGivesCorrectMoneyAmount(){
		 int moneyAmount = tdGame.getMoneyAmount();
	     if(moneyAmount >= tower.getPrice()){ 
		       int expMoneyAmount = moneyAmount - tower.getPrice(); 
	    	   tdGame.buyTower(tower);
	    	   assertEquals(tdGame.getMoneyAmount(),expMoneyAmount);
		      
	     }
	      
	      	
	}
	/**
	 * Å selge et tårn, gjør at en sitter igjen med korrekt antall beløp.
	 */
	@Test
	public void sellTowerGivesCorrectMoneyAmount(){
	           int moneyAmount = tdGame.getMoneyAmount();
		       int expMoneyAmount = moneyAmount + tower.getPrice(); 
	    	   tdGame.sellTower(tower);
	    	   assertEquals(tdGame.getMoneyAmount(),expMoneyAmount);
		      
	     
	}
	/**
	 * Å kjøpe for så å selge gjør at en sitter igjen med korrekt antall beløp.
	 */
	@Test
	public void buySellTowerGivesCorrectMoneyAmount(){
		   int moneyAmount = tdGame.getMoneyAmount();
		   if(moneyAmount >= tower.getPrice()){ 
	    	   tdGame.buyTower(tower);
	    	   tdGame.sellTower(tower);
	    	   assertEquals(tdGame.getMoneyAmount(),moneyAmount);
		      
	     }
	}
	
	@Test
	public void increaseWaveCounterIncreasesCounter(){
	      int waveCounter = tdGame.getWaveCounter();
	      tdGame.setCurrentWaveIndex(waveCounter+1);;
	      assertEquals(tdGame.getWaveCounter(),waveCounter+1);
	}
	
	@Test
	public void generateWavesIncreasesCounter(){
	      int waveCounter = tdGame.getWaveCounter();
	      tdGame.startNewRound();
	      assertEquals(tdGame.getWaveCounter(),waveCounter+1);
	}
}
