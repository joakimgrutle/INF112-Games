package com.oblig4.game;

import java.awt.event.KeyEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import logicClasses.TDGame;
import logicClasses.Tower;

/**
 * Class for the gamescreen
 * 
 * @author Oyvind
 *
 */
public class GameScreen implements Screen {
	final TowerDefence game;
	private Texture img;
	private State state;
	private Stage stage;
	private OrthographicCamera camera;
	private Viewport viewport;
	public static Music gameMusicSnd;
	private Sound buttonClickSnd;
	private Hud hud;
	private Texture map;
	private boolean pressed = false;
	// MAP EXPERIMENTAL
	private TmxMapLoader maploader;
	private TiledMap tMap;
	private OrthogonalTiledMapRenderer renderer;
	private MapProperties prop;
	private TDGame gamestate;
	private OrthographicCamera mapCamera;
	SpriteBatch sb;

	// SOUND and MUSIC
	private Sound sound;

	// Rain
	ParticleEffect rainEffect;

	// Pause Menu

	// ShapeRenderer used for debugging and placement zones @author Nikolai
	// @date 06.05
	private ShapeRenderer sr;

	public GameScreen(TowerDefence game, TDGame gamestate2) {
		// ShapeRenderer used for debugging and placement zones @author Nikolai
		// @date 06.05
		sr = new ShapeRenderer();
		game.menuMusic.stop();

		// Music n sound 2017 party hardy @nikolai 06.05
		sound = TowerDefence.manager.get("sound/pew.ogg", Sound.class);

		this.game = game;
		game.menuMusic.pause();
		this.sb = new SpriteBatch();
		this.stage = new Stage();

		if (gamestate2 == null) {
			this.gamestate = new TDGame();
			this.gameMusicSnd = Gdx.audio.newMusic(Gdx.files.internal("sound/music/testXXX.ogg"));
			this.gameMusicSnd.setLooping(true);
			 this.gameMusicSnd.play();
			this.gameMusicSnd.setVolume(TowerDefence.VOLUME);
		} else
			this.gamestate = gamestate2;

		this.state = State.Run;
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, TowerDefence.width, TowerDefence.height);

		this.buttonClickSnd = Gdx.audio.newSound(Gdx.files.internal("sound/MenuSelectSound.ogg"));
		this.hud = new Hud(game, gamestate);
		this.map = new Texture(Gdx.files.internal("image/utkast/nightTest2.png"));
		this.viewport = new FillViewport(TowerDefence.width, TowerDefence.height);

		// MAP STUFF
		maploader = new TmxMapLoader();
		tMap = maploader.load("image/utkast/nightTest2.tmx");
		renderer = new OrthogonalTiledMapRenderer(tMap, 1);

		prop = tMap.getProperties();

		int mapWidth = prop.get("width", Integer.class); // num tiles
		int mapHeight = prop.get("height", Integer.class);

		int tileWidth = prop.get("tilewidth", Integer.class); // pixel pr tile
		int tileHeight = prop.get("tileheight", Integer.class);

		int pixelWidth = mapWidth * tileWidth; // final rez
		int pixelHeight = mapHeight * tileHeight;

		// Weather stuff
		if (TowerDefence.rain) {
			rainEffect = new ParticleEffect();
			rainEffect.load(Gdx.files.internal("particles/rain"), Gdx.files.internal(""));
			rainEffect.getEmitters().first().setPosition(0, Gdx.graphics.getHeight());
			rainEffect.start();
		}

		mapCamera = new OrthographicCamera();
		mapCamera.setToOrtho(false, pixelWidth, pixelHeight);
		renderer.setView(mapCamera);

		// Sort of constructor for pauseMenu
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		switch (state) {
		case Run:
			renderGame(delta);
			break;
		case Pause:
			pauseGame();
			break;
		}
	}

	// Method for rendering the game
	private void renderGame(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// MAP STUFF
		renderer.render();

		game.batch.begin();
		Sprite hudBG = new Sprite(hud.hudArea);

		hudBG.setSize(hud.hudArea.getWidth(), hud.hudArea.getHeight());
		hudBG.setPosition(viewport.getWorldWidth() - hudBG.getWidth(), 0);
		hudBG.draw(game.batch);

		// Weather stuff
		if (TowerDefence.rain) {
			rainEffect.setPosition(0, Gdx.graphics.getHeight());
			rainEffect.draw(game.batch, delta);
			rainEffect.setPosition(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight());
			rainEffect.draw(game.batch, delta);
			rainEffect.setPosition(Gdx.graphics.getWidth() / 4 - 100, Gdx.graphics.getHeight());
			rainEffect.draw(game.batch, delta);
			rainEffect.setPosition(Gdx.graphics.getWidth() - 400, Gdx.graphics.getHeight());
			rainEffect.draw(game.batch, delta);
			if (rainEffect.isComplete())
				rainEffect.reset();
			// End of weather stuff
		}
		game.batch.end();

		// Check if player wants to build tower
		if (Gdx.input.isButtonPressed(Buttons.LEFT) && hud.buildTower) {
			hud.buyTower();
			TDGame.getInstance().setGhostTower(null);
			hud.buildTower = false;
		} else if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
			TDGame.getInstance().setGhostTower(null);
			hud.buildTower = false;
		}

		gamestate.render(sb);

		// Update logic
		gamestate.update(System.currentTimeMillis());

		// Draw hud background
		hud.spriteBatch.begin();
		hud.spriteBatch.draw(hud.hudArea, TowerDefence.width - hud.hudArea.getWidth(), 0);
		hud.spriteBatch.end();

		// Draw hud
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.act();
		hud.stage.draw();
		hud.update(0.05f);
		hud.drawNewWaveButton(gamestate);

		// Check if user pressed a tower and opens/closes such windows if needed
		towerInfoWindowCheck();

		if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			pause();

		/*
		 * PLACEMENT ZONES
		 * 
		 * BEGIN
		 * 
		 */

		if (hud.buildTower) {
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Vector2 pos = new Vector2(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY()));
			Vector2 circlePos = new Vector2();

			Rectangle placementRectangle = this.gamestate.mapping
					.getDrawableRectangle(this.gamestate.mapping.getFinalOKPlacementList(), pos);

			int rangeRadius = 0;
			Circle circ = new Circle();
			circ.setPosition(placementRectangle.getCenter(circlePos));
			circ.setRadius(1);
			rangeRadius = gamestate.getGhostTower().getRange();

			circ.setRadius(rangeRadius);
			sr.setColor(1f, 1f, 0f, 1f);
			sr.begin(ShapeType.Line);
			sr.circle(circ.x, circ.y, circ.radius);
			sr.end();

			sr.setColor(0f, 1f, 0f, 0.4f);

			try {
				if (this.gamestate.mapping.containsSpecificTile(this.gamestate.mapping.getLocationsOccupied(), pos)) {
					sr.setColor(1f, 0f, 0f, 0.4f);
				}
			} catch (NullPointerException e) {
				System.out.println("Nullpointer in gamescreen at building a tower");
			}

			sr.begin(ShapeType.Filled);

			sr.rect(placementRectangle.getX(), placementRectangle.getY(), placementRectangle.getWidth(),
					placementRectangle.getHeight());
			sr.end();
		}

		/*
		 * PLACEMENT ZONES
		 * 
		 * END
		 * 
		 */

		if (gamestate.lostGame())
			game.setScreen(new GameOverScreen(game, gamestate));

	}

	// Check if user pressed a tower and opens/closes such windows if needed
	private void towerInfoWindowCheck() {
		// Draw Range
		Vector2 pointDraw = new Vector2(Gdx.input.getX(), viewport.getWorldHeight() - Gdx.input.getY());
		for (Tower tower : gamestate.getTowerList()) {
			Rectangle rectDraw = tower.getSprite().getBoundingRectangle();
			if (rectDraw.contains(pointDraw)) {
				Circle circ = new Circle(tower.getPosition().x + 16, tower.getPosition().y + 16, tower.getRange());
				sr.end();
				sr.setColor(1f, 1f, 0f, 1f);
				sr.begin(ShapeType.Line);
				sr.circle(circ.x, circ.y, circ.radius);
				if (tower.canUpgradeTower(tower)) {
					sr.setColor(0f, 1f, 1f, 1f);
					circ = new Circle(tower.getPosition().x + 16, tower.getPosition().y + 16,
							tower.getSingleUpgradeRange());
					sr.circle(circ.x, circ.y, circ.radius);
				}
				sr.end();
			}
		}

		// If tower is pressed show info window/tab
		if (Gdx.input.justTouched()) {
			Vector2 point = new Vector2(Gdx.input.getX(), viewport.getWorldHeight() - Gdx.input.getY());
			boolean opened = false;
			for (Tower tower : gamestate.getTowerList()) {
				Rectangle pos = tower.getSprite().getBoundingRectangle();
				if (pos.contains(point)) {
					tower.infoWindow(hud);
					opened = true;
				}
				if (!opened) {
					for (Tower t : TDGame.getInstance().getTowerList()) {
						if (t.window != null)
							t.window.remove();
					}
				}
			}
		}
	}

	// Method for drawing the pause screen
	private void pauseGame() {
		game.setScreen(new PauseMenu(game, gamestate));
	}

	/**
	 * Konstruktør ish for pauseMenu
	 */
	@Override
	public void resize(int width, int height) {
		hud.stage.getViewport().update(width, height, true);
		stage.getViewport().update(width, height, true);
		// viewport.update(width, height);
		// camera.update();
	}

	@Override
	public void pause() {
		this.state = State.Pause;
		// Start listening
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void resume() {
		this.state = State.Run;
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
		gameMusicSnd.dispose();
		buttonClickSnd.dispose();
	}

}
