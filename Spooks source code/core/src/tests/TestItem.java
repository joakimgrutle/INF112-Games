package tests;

import static org.junit.Assert.*;
import items.*;
import logicClasses.Item;
import org.junit.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TestItem {
	
	private BoltCutter boltCutter = new BoltCutter(null);
	private BrokenBoltCutter brokenBoltCutter = new BrokenBoltCutter(null);
	private BoltCutterAndScrew boltCutterAndScrew = new BoltCutterAndScrew(null);
	private Screw screw = new Screw(null);
	private Candle candle = new Candle(null);
	private Candlelight candlelight = new Candlelight(null);
	private Candlestick candlestick = new Candlestick(null);
	private Pumpkin pumpkin = new Pumpkin(null);
	private PumpkinCarvedRemote pumpkinCarvedRemote = new PumpkinCarvedRemote(null);
	private Screwdriver screwdriver = new Screwdriver(null);
	private Knife knife = new Knife(null);
	


	
private static Item createItem(String i) {
		
		String item = i.toLowerCase();

		switch (item) {
		// Case for single item.
		case "boltcutter":
			return new BoltCutter(null);
		case "brokenboltcutter":
			return new BrokenBoltCutter(null);
		case "boltcutterandscrew":
			return new BoltCutterAndScrew(null);
		case "candle":
			return new Candle(null);
		case "candlelight":
			return new Candlelight(null);
		case "candlestick":
			return new Candlestick(null);
		case "candlestickandcandle":
			return new CandlestickAndCandle(null);
		case "key":
			return new Key(null);
		case "knife":
			return new Knife(null);
		case "lighter":
			return new Lighter(null);
		case "pumpkin":
			return new Pumpkin(null);
		case "pumpkincarvedremote":
			return new PumpkinCarvedRemote(null);
		case "remotecontrol":
			return new RemoteControl(null);
		case "screw":
			return new Screw(null);
		case "screwdriver":
			return new Screwdriver(null);
			
			
		// Case for combinations
		case "screw" + "brokenboltcutter":
			return new BoltCutterAndScrew(null);
		case "brokenboltcutter" + "screw":
			return new BoltCutterAndScrew(null);
		
		case "boltcutterandscrew" + "screwdriver":
			return new BoltCutter(null);
		case "screwdriver" + "boltcutterandscrew":
			return new BoltCutter(null);

		case "knife" + "pumpkin":
			return new PumpkinCarvedRemote(null);
		case "pumpkin" + "knife":
			return new PumpkinCarvedRemote(null);
		
		case "candle" + "candlestick":
			return new CandlestickAndCandle(null);
		case "candlestick" + "candle":
			return new CandlestickAndCandle(null);
		
		case "candlestickandcandle" + "lighter":
			return new Candlelight(null);
		case "lighter" + "candlesitckandcandle":
			return new Candlelight(null);
		
		
		

		default:
			return null;
		}
	}
	
	@Test
	public void testNamePrint(){
		Item A = createItem("boltcutter");
		Item B = createItem("brokenboltcutter");
		Item C = createItem("boltcutteranDscrew");
		Item D = createItem("candle");
		Item E = createItem("candlelight");
		Item F = createItem("candlestick");
		Item G = createItem("knife");
		
		
		assertEquals(A.getItemName(),boltCutter.getItemName());
		assertEquals(B.getItemName(),brokenBoltCutter.getItemName());
		assertEquals(C.getItemName(),boltCutterAndScrew.getItemName());
		assertEquals(D.getItemName(),candle.getItemName());
		assertEquals(E.getItemName(),candlelight.getItemName());
		assertEquals(F.getItemName(),candlestick.getItemName());
		assertEquals(G.getItemName(),knife.getItemName());
		
		
	}
	@Test
	public void combineItemsTest(){
		Item a = createItem(screw.getItemName() + brokenBoltCutter.getItemName());
		assertEquals(a.getItemName(),boltCutterAndScrew.getItemName());
		Item b = createItem("lol");
		assertEquals(b,null);
		Item c = createItem(screw.getItemName() + boltCutter.getItemName());
		assertEquals(c,null);
		
	}
	

}
