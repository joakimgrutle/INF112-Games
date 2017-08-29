package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import logicClasses.WeatherData;



public class TestWeatherData {

	@Before
	public void init(){
	  
	}
	/**
	 * Hvis ingen vær server er tiljengelig, tas default
	 * værtype i bruk.
	 */
	@Test
	public void noServerGivesDefaultWeather(){
		try {
			if(!WeatherData.canFindWeatherServer())
				assertEquals(WeatherData.canFindWeatherServer(), false);
		} catch (IOException e) {
		        assertEquals(WeatherData.getDefault(), false);
		}
	}
}
