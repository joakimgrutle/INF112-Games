package com.oblig4.spooks;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import logicClasses.WeatherData;

public class Spooks extends Game {

	public static AssetManager manager;

	public SpriteBatch batch;
	public Music menuMusic;
	public static TextureAtlas mainButtonsAtals;
	public static int width;
	public static int height;
	public static BitmapFont font;
	public static float VOLUME;
	public static Skin skin;
	public boolean rain;

	@Override
	public void create() {
		batch = new SpriteBatch();

		try {
			rain = WeatherData.doesItRain();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Button texture pack
		mainButtonsAtals = new TextureAtlas("ui/MainButton.pack");
		// Font
		font = new BitmapFont(Gdx.files.internal(("font/Spooks.fnt")));
		// Music
		this.VOLUME = 1f;
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/Spookey.wav"));
		menuMusic.setLooping(true);
		menuMusic.play();
		menuMusic.setVolume(VOLUME);

		// Create new mouse cursor
		Pixmap cursor = new Pixmap(Gdx.files.internal("SpooksCursor.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursor, 0, 0));

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		this.skin = new Skin(Gdx.files.internal("skin/tracer-ui.json"));

		this.setScreen(new MainMenuScreen(this));

		manager = new AssetManager();

		manager.load("sound/rain.wav", Music.class);
		manager.load("sound/footsteps1.wav", Sound.class);
		manager.load("sound/footsteps2.wav", Sound.class);
		manager.load("sound/addItemSound.wav", Sound.class);
		manager.load("sound/click.wav", Sound.class);
		manager.load("sound/combine2.wav",  Sound.class);
		manager.load("sound/burn.wav",  Sound.class);
		manager.load("sound/cut.wav",  Sound.class);		
		manager.load("sound/success1.wav",  Sound.class);		
		manager.load("sound/success2.wav",  Sound.class);		
		
		manager.finishLoading();

	}

	@Override
	public void render() {
		super.render();

		if (Gdx.input.isKeyPressed(Keys.ENTER) && Gdx.input.isKeyPressed(Keys.ALT_LEFT)
				&& Gdx.graphics.isFullscreen() == false)

			Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		else if (Gdx.input.isKeyPressed(Keys.ENTER) && Gdx.input.isKeyPressed(Keys.ALT_LEFT)
				&& Gdx.graphics.isFullscreen() == true) {
			Gdx.graphics.setWindowedMode(Spooks.width, Spooks.height);
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
