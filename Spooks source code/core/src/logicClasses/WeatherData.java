package logicClasses;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class WeatherData {
	private static String URLstring = "http://www.yr.no/place/Norway/Hordaland/Bergen/Bergen/forecast_hour_by_hour.xml";
	
	public static boolean doesItRain() throws IOException {
		try{        
			URL url = new URL(URLstring);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(url.openStream());

			String weather = doc.getElementsByTagName("symbol").item(0).getAttributes().item(0).getTextContent();
			if (weather.contains("regn") || weather.contains("Regn") || weather.contains("Rain") || weather.contains("rain")) {
				return true;
			}
		
		}catch(Exception e){
			//Default weather
			return true;
		}
		return false;
	}


	public static boolean canFindWeatherServer() throws IOException {
		URL url;
		int boolCheck = 0;
		try {
			url = new URL(URLstring);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			boolCheck = conn.getResponseCode();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	    
		return boolCheck == 200;
	}
	
	public static boolean hasSunRisen() {
		String[] date = new String[2];
		try{        
			System.out.println("url "+ URLstring);
			URL url = new URL(URLstring);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(url.openStream());
			
			System.out.println(doc.getElementsByTagName("sun").item(0).getAttributes().item(1).getTextContent());
			date = doc.getElementsByTagName("sun").item(0).getAttributes().item(1).getTextContent().split("T");
			LocalTime time = LocalTime.parse(date[1]);
			if (time.isBefore(LocalTime.now())) {
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}


}
