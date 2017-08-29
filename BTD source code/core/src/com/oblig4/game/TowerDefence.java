package com.oblig4.game;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import logicClasses.WeatherData;

/**
 * Class used to start up the game
 * 
 * @author Oyvind
 *
 */
public class TowerDefence extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public Music menuMusic;
	public static Skin uiSkin;
	public static float VOLUME;
	public static final int width = 1280;
	public static final int height = 736;
	public static boolean rain;

	// Assetmanager @nikolai 06.05
	public static AssetManager manager;

	// Create what is necessary to start the main-menu
	@Override
	public void create() {
		batch = new SpriteBatch();

		// Assetmanager @nikolai 06.05
		manager = new AssetManager();
		manager.load("sound/pew.ogg", Sound.class);
		manager.load("sound/spy.ogg", Sound.class);

		// buy sounds x5
		manager.load("sound/tower/buy/buy-beer.ogg", Sound.class);
		manager.load("sound/tower/buy/buy-jager.ogg", Sound.class);
		manager.load("sound/tower/buy/buy-wine.ogg", Sound.class);
		manager.load("sound/tower/buy/buy-vodka.ogg", Sound.class);
		manager.load("sound/tower/buy/buy-whiskey.ogg", Sound.class);

		// sell sounds x5
		manager.load("sound/tower/sell/sell-beer.ogg", Sound.class);
		manager.load("sound/tower/sell/sell-jager.ogg", Sound.class);
		manager.load("sound/tower/sell/sell-wine.ogg", Sound.class);
		manager.load("sound/tower/sell/sell-vodka.ogg", Sound.class);
		manager.load("sound/tower/sell/sell-whiskey.ogg", Sound.class);

		// attack sounds x5
		manager.load("sound/tower/attack/attack-beer.ogg", Sound.class);
		manager.load("sound/tower/attack/attack-jager.ogg", Sound.class);
		manager.load("sound/tower/attack/attack-wine.ogg", Sound.class);
		manager.load("sound/tower/attack/attack-vodka.ogg", Sound.class);
		manager.load("sound/tower/attack/attack-whiskey.ogg", Sound.class);

		// puke sounds ALL START
		String pukeLocalization = "sound/mob/puke/puke-";
		String pukeLocalizationSpecific = pukeLocalization;

		// puke sounds A-PART
		for (int i = 1; i <= 29; i++) {
			pukeLocalizationSpecific += "A";
			pukeLocalizationSpecific += i;
			pukeLocalizationSpecific += ".ogg";

			manager.load(pukeLocalizationSpecific, Sound.class);
			pukeLocalizationSpecific = pukeLocalization;
		}

		// puke sounds H-PART
		for (int i = 1; i <= 20; i++) {
			pukeLocalizationSpecific += "H";
			pukeLocalizationSpecific += i;
			pukeLocalizationSpecific += ".ogg";

			manager.load(pukeLocalizationSpecific, Sound.class);
			pukeLocalizationSpecific = pukeLocalization;
		}

		// puke sounds K-PART
		for (int i = 1; i <= 15; i++) {
			pukeLocalizationSpecific += "K";
			pukeLocalizationSpecific += i;
			pukeLocalizationSpecific += ".ogg";

			manager.load(pukeLocalizationSpecific, Sound.class);
			pukeLocalizationSpecific = pukeLocalization;
		}

		// puke sounds N-PART
		for (int i = 1; i <= 5; i++) {
			pukeLocalizationSpecific += "N";
			pukeLocalizationSpecific += i;
			pukeLocalizationSpecific += ".ogg";

			manager.load(pukeLocalizationSpecific, Sound.class);
			pukeLocalizationSpecific = pukeLocalization;
		}

		// puke sounds S-PART
		for (int i = 1; i <= 37; i++) {
			pukeLocalizationSpecific += "S";
			pukeLocalizationSpecific += i;
			pukeLocalizationSpecific += ".ogg";

			manager.load(pukeLocalizationSpecific, Sound.class);
			pukeLocalizationSpecific = pukeLocalization;
		}

		manager.finishLoading();

		uiSkin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
		font = new BitmapFont();
		this.VOLUME = 1.0f;

		// Create new mouse cursor
		Pixmap cursor = new Pixmap(Gdx.files.internal("cursor_hand.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursor, 0, 0));

		// Start menu music
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/music/main.ogg"));
		menuMusic.setLooping(true);
		menuMusic.play();
		menuMusic.setVolume(TowerDefence.VOLUME);

		// Get weather
		try {
			rain = WeatherData.doesItRain();

		} catch (IOException e) {
			e.printStackTrace();
		}
		// rain = true;
		this.setScreen(new SplashScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	// Clean up the mess
	@Override
	public void dispose() {
		batch.dispose();
		uiSkin.dispose();
		menuMusic.dispose();
		font.dispose();
		manager.dispose();
	}
}
