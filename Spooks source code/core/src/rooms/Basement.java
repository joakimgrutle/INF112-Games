package rooms;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.oblig4.spooks.Hud;
import com.oblig4.spooks.ItemListing;
import com.oblig4.spooks.KeyItemListing;
import com.oblig4.spooks.PauseMenu;
import com.oblig4.spooks.RoomState;
import com.oblig4.spooks.ScaryFace;
import com.oblig4.spooks.Spooks;
import com.oblig4.spooks.StateSpooks;

import logicClasses.Item;
import logicClasses.JumpScareRandom;
import logicClasses.SpooksGame;

public class Basement implements Screen {
	final Spooks game;
	private Texture img;
	private StateSpooks state;
	private Stage stage;
	private OrthographicCamera camera;
	private Viewport viewport;
	private Music gameMusicSnd;
	private Sound buttonClickSnd;
	private Hud hud;
	private Texture map;
	private Image arrow;
	private ImageButton roomChange;
	// MAP EXPERIMENTAL
	private TmxMapLoader maploader;
	private TiledMap tMap;
	private OrthogonalTiledMapRenderer renderer;
	private MapProperties prop;
	private SpooksGame gamestate;
	private OrthographicCamera mapCamera;
	SpriteBatch sb;
	private boolean hasPressed;
	private Label.LabelStyle font = new Label.LabelStyle(Spooks.skin.getFont("title"), Color.RED);
	private Label startText;

	// jumpscare
	JumpScareRandom rand;

	// Rain
	ParticleEffect rainEffect;
	RoomState rooms;
	private Sprite hudBG;

	SpriteBatch batch;
	ShaderProgram shader;
	Sprite sprite;
	private long startTime;

	public Basement(Spooks games, Stage stages, Hud huds, SpooksGame gameState) {
		Spooks.manager.get("sound/rain.wav", Music.class).setPan(0.7f, 0.085f);

		Spooks.manager.get("sound/footsteps1.wav", Sound.class).play();
		batch = new SpriteBatch();
		this.game = games;
		this.sb = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(Spooks.width, Spooks.height, camera);
		this.viewport = new FillViewport(Spooks.width, Spooks.height);
		this.map = new Texture("background\\basement.png");
		if (stages == null) {
			this.stage = new Stage(viewport, sb);
		} else
			this.stage = stages;
		if (huds == null)
			this.hud = new Hud(Spooks.width, Spooks.height, game.batch, this.stage);
		else {
			this.hud = huds;
			huds.addsTimerToHudSinceYouGuysClearsTheStageAllTheTime();
		}
		hudBG = new Sprite(this.hud.hudArea);
		hudBG.setSize(this.hud.hudArea.getWidth(), this.hud.hudArea.getHeight());
		hudBG.setPosition(viewport.getWorldWidth() - hudBG.getWidth(), 0);
		this.hud.getInventory().reDraw();

		if (gameState == null) {
			this.gamestate = new SpooksGame(this.hud, game);
		} else {
			this.gamestate = gameState;

		}

		rand = new JumpScareRandom();

		Texture texture = new Texture("images/Transparency100.png");
		TextureRegion textRegion = new TextureRegion(texture);
		TextureRegionDrawable drawable = new TextureRegionDrawable(textRegion);
		roomChange = new ImageButton(drawable);
		roomChange.setSize(128, 128);
		roomChange.setPosition(Spooks.width - 150, Spooks.height - 150);
		roomChange.getImage().setVisible(false);
		stage.addActor(roomChange);

		roomChange.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				arrow = new Image(new Texture("images/arrow1_red.png"));
				arrow.setPosition(Spooks.width - 150, Spooks.height - 150);
				arrow.setSize(128, 128);
				arrow.setTouchable(Touchable.disabled);
				hud.stage.addActor(arrow);
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				arrow.remove();
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!gamestate.isBasementOpened) {
					if (gamestate.getGhostItem() != null || gamestate.isBasementOpened) {
						if ((hud.getInventory().hasItem("boltcutter")
								&& gamestate.getGhostItem().getItemName().equals("boltcutter"))
								|| gamestate.isBasementOpened) {
							if (rand.willScare()) {
								stage.clear();

								game.setScreen(new ScaryFace(game, stage, hud, gamestate, map));
							} else {
								stage.clear();
								gamestate.setGhostItem(null);
								gamestate.isBasementOpened = true;
								game.setScreen(new Livingroom(game, stage, hud, gamestate));
							}
						}
					}

				} else {
					if (rand.willScare()) {
						stage.clear();

						game.setScreen(new ScaryFace(game, stage, hud, gamestate, map));
					} else {
						stage.clear();
						gamestate.setGhostItem(null);
						gamestate.isBasementOpened = true;
						game.setScreen(new Livingroom(game, stage, hud, gamestate));
					}
				}

			}
		});
		if (!hud.getInventory().hasItem("boltcutterandscrew") && !hud.getInventory().hasItem("boltcutter")) {
			AddInvisibleObject boltcutter = new AddInvisibleObject(game, stage, hud, 750, 220, "brokenboltcutter");
		}

		state = StateSpooks.Run;

		if (gamestate.isFirstTimeBasement())
			state = StateSpooks.NotRun;

		if (!gamestate.isFirstTimeBasement())
			state = StateSpooks.Run;

		startText = new Label("Press anywhere to start the game", font);

		Gdx.input.setInputProcessor(this.stage);
		ShaderProgram.pedantic = false;
		sprite = new Sprite(this.map);
		shader = new ShaderProgram(Gdx.files.internal("shaders/vignette.vsh"),
				Gdx.files.internal("shaders/vignette.fsh"));
	}

	@Override
	public void show() {
		startTime = TimeUtils.millis();

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
		case NotRun:
			waitForStart();
			break;
		}

	}

	private void waitForStart() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		game.batch.draw(map, 0, 0);
		game.batch.end();

		startText.remove();
		startText = new Label(
				"Welcome to Spooks!\nYou just woke up in a abondened house but you still feel that\nthere is some sort of pressence here.\nTo get out of the house find all the 4 haunted items and burn them.\nPress anywhere to start the journey.\nGood luck!",
				font);
		startText.setPosition(Spooks.width / 2 - 400, Spooks.height / 2);
		stage.addActor(startText);
		stage.act();
		stage.draw();

		if (Gdx.input.isTouched()) {
			state = StateSpooks.Run;
			gamestate.setFirstTimeBasement(false);
			startText.remove();
			
			Item e = Item.createItem("flashlight");
			hud.getInventory().addItem(e);
		}

	}

	public void renderGame(float delta) {
		gamestate.startTimer();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.begin();
		batch.draw(sprite, 0, hud.hudArea.getHeight(), viewport.getWorldWidth(),
				viewport.getWorldHeight() - hud.hudArea.getHeight());
		for (Item item : gamestate.getBasementItemList())
			item.draw(batch);
		batch.end();
		game.batch.begin();
		game.batch.draw(hudBG, 0, 0);
		game.batch.end();

		// Check om spiller trykker på items rundt i rommet
		if (Gdx.input.justTouched()) {
			if (gamestate.getBasementItemList().size() > 0) {
				Vector2 point = new Vector2(Gdx.input.getX(), viewport.getWorldHeight() - Gdx.input.getY());
				for (Item e : gamestate.getBasementItemList()) {
					Rectangle pos = e.getSprite().getBoundingRectangle();
					if (pos.contains(point)) {
						e.getSprite().setPosition(-10, -10); // Move the
																// rectangle out
																// of the game
																// screen
						hud.addItemToHud(e);
						gamestate.getBasementItemList().remove(e);
						break;
					}
				}
			}
			if (gamestate.getInventory().inv.size() > 0 || gamestate.getInventory().keyInv.size() > 0)
				checkInfoWindow();
		}

		stage.act();
		stage.draw();

		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		gamestate.render(game.batch);

		if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			pause();
		hud.updateTimer();
		if (gamestate.lostGame()) {
			game.setScreen(new ScaryFace(game, stage, hud, gamestate, map));
		}
	}

	private void pauseGame() {
		game.setScreen(new PauseMenu(game, stage, hud, gamestate, "Basement"));
		dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		this.state = StateSpooks.Pause;
		// Start listening
		Gdx.input.setInputProcessor(stage);

	}

	private void stateChecker() {

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		sb.dispose();
		batch.dispose();
		shader.dispose();
		map.dispose();
	}

	private void checkInfoWindow() {
		Vector2 point = new Vector2(Gdx.input.getX(),
				SpooksGame.getInstance().getHud().stage.getViewport().getWorldHeight() - Gdx.input.getY());
		for (ItemListing it : SpooksGame.getInstance().getInventory().inv) {
			if (it.getItem().window != null)
				it.getItem().window.remove();
		}
		for (KeyItemListing it : SpooksGame.getInstance().getInventory().keyInv) {
			if (it.getItem().window != null)
				it.getItem().window.remove();
		}

	}

}
