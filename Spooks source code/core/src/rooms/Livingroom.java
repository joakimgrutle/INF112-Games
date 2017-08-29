package rooms;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.oblig4.spooks.Hud;
import com.oblig4.spooks.ItemListing;
import com.oblig4.spooks.JumpScarePuzzleScreen;
import com.oblig4.spooks.KeyItemListing;
import com.oblig4.spooks.PauseMenu;
import com.oblig4.spooks.RoomState;
import com.oblig4.spooks.ScaryFace;
import com.oblig4.spooks.Spooks;
import com.oblig4.spooks.StateSpooks;
import com.oblig4.spooks.VictoryScreen;

import logicClasses.Item;
import logicClasses.JumpScareRandom;
import logicClasses.SpooksGame;

public class Livingroom implements Screen {
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

	// MAP EXPERIMENTAL
	private TmxMapLoader maploader;
	private TiledMap tMap;
	private OrthogonalTiledMapRenderer renderer;
	private MapProperties prop;
	private SpooksGame gamestate;
	private OrthographicCamera mapCamera;
	private SpriteBatch sb;
	private ImageButton roomChange1;
	private ImageButton roomChange2;
	private ImageButton roomChange3;
	private ImageButton roomChange4;
	private Image arrow1;
	private Image arrow2;
	private Image arrow3;
	private Image arrow4;
	private ImageButton puzzle;
	private ImageButton win;
	// Rain
	private ParticleEffect rainEffect;
	private RoomState rooms;
	private Sprite hudBG;

	SpriteBatch batch;
	ShaderProgram shader;
	Sprite sprite;
	public Livingroom(Spooks games, Stage stages, Hud huds, SpooksGame gameState) {

		Spooks.manager.get("sound/rain.wav", Music.class).setPan(-0.2f, 0.4f);
		Spooks.manager.get("sound/footsteps1.wav", Sound.class).play();

		batch = new SpriteBatch();
		huds.addsTimerToHudSinceYouGuysClearsTheStageAllTheTime();
		this.stage = stages;
		this.game = games;
		this.map = new Texture("background\\livingroom.png");
		this.hud = huds;
		this.hud.getInventory().reDraw();
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(Spooks.width, Spooks.height, camera);
		this.viewport = new FillViewport(Spooks.width, Spooks.height);
		sb = new SpriteBatch();
		this.gamestate = gameState;
		hudBG = new Sprite(hud.hudArea);
		hudBG.setSize(hud.hudArea.getWidth(), hud.hudArea.getHeight());
		hudBG.setPosition(viewport.getWorldWidth() - hudBG.getWidth(), 0);

		final JumpScareRandom rand = new JumpScareRandom();

		Texture texture = new Texture("images/Transparency100.png");
		TextureRegion textRegion = new TextureRegion(texture);
		TextureRegionDrawable drawable = new TextureRegionDrawable(textRegion);

		roomChange1 = new ImageButton(drawable);
		roomChange2 = new ImageButton(drawable);
		roomChange3 = new ImageButton(drawable);
		roomChange4 = new ImageButton(drawable);
		puzzle = new ImageButton(drawable);
		win = new ImageButton(drawable);
		roomChange1.setSize(128, 128);
		roomChange2.setSize(128, 128);
		roomChange3.setSize(128, 128);
		roomChange4.setSize(128, 128);
		puzzle.setSize(128, 128);
		win.setSize(128, 128);
		roomChange1.setPosition(Spooks.width / 5, Spooks.height - 150);
		roomChange2.setPosition(10, Spooks.height / 4);
		roomChange3.setPosition(Spooks.width - 150, Spooks.height / 2);
		roomChange4.setPosition(Spooks.width / 2, 150);
		puzzle.setPosition(Spooks.width / 4 + 130, Spooks.height / 2 + 30);
		win.setPosition(750, 450);
		roomChange1.getImage().setVisible(false);
		roomChange2.getImage().setVisible(false);
		roomChange3.getImage().setVisible(false);
		roomChange4.getImage().setVisible(false);
		puzzle.getImage().setVisible(false);
		win.getImage().setVisible(false);
		stage.addActor(roomChange1);// attic
		stage.addActor(roomChange2);// bathroom
		stage.addActor(roomChange3);// kitchen
		stage.addActor(roomChange4);// basement
		stage.addActor(puzzle);// puzzle
		stage.addActor(win);// win

		roomChange1.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				arrow1 = new Image(new Texture("images/arrow1_red.png"));
				arrow1.setPosition(Spooks.width / 5 + 128, Spooks.height - 150);
				arrow1.setSize(128, 128);
				arrow1.rotateBy(90);
				arrow1.setTouchable(Touchable.disabled);
				hud.stage.addActor(arrow1);
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				arrow1.remove();
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (rand.willScare()) {
					stage.clear();
					game.setScreen(new ScaryFace(game, stage, hud, gamestate, map));
					dispose();
				} else {
					stage.clear();
					game.setScreen(new Attic(game, stage, hud, gamestate));
					dispose();
				}
			}
		});

		roomChange2.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				arrow2 = new Image(new Texture("images/arrow1_red.png"));
				arrow2.setPosition(10 + 128, Spooks.height / 4 + 128);
				arrow2.setSize(128, 128);
				arrow2.setRotation(180);
				arrow2.setTouchable(Touchable.disabled);
				hud.stage.addActor(arrow2);
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				arrow2.remove();
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (rand.willScare()) {
					stage.clear();
					game.setScreen(new ScaryFace(game, stage, hud, gamestate, map));
					dispose();
				} else {
					stage.clear();
					game.setScreen(new Bathroom(game, stage, hud, gamestate));
					dispose();
				}
			}
		});

		roomChange3.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				arrow3 = new Image(new Texture("images/arrow1_red.png"));
				arrow3.setPosition(Spooks.width - 150, Spooks.height / 2);
				arrow3.setSize(128, 128);
				arrow3.setTouchable(Touchable.disabled);
				hud.stage.addActor(arrow3);
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				arrow3.remove();
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (rand.willScare()) {
					stage.clear();
					game.setScreen(new ScaryFace(game, stage, hud, gamestate, map));
					dispose();
				} else {
					stage.clear();
					game.setScreen(new Kitchen(game, stage, hud, gamestate));
					dispose();
				}
			}
		});

		roomChange4.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				arrow4 = new Image(new Texture("images/arrow1_red.png"));
				arrow4.setPosition(Spooks.width / 2, 150 + 128);
				arrow4.setSize(128, 128);
				arrow4.rotateBy(270);
				arrow4.setTouchable(Touchable.disabled);
				hud.stage.addActor(arrow4);
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				arrow4.remove();
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (rand.willScare()) {
					stage.clear();
					game.setScreen(new ScaryFace(game, stage, hud, gamestate, map));
					dispose();
				} else {
					stage.clear();
					game.setScreen(new Basement(game, stage, hud, gamestate));
					dispose();

				}
			}
		});

		puzzle.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (gamestate.getGhostItem() != null) {
					if (hud.getInventory().hasItem("remotecontrol")
							&& gamestate.getGhostItem().getItemName().equals("remotecontrol")) {
						stage.clear();
						game.setScreen(new JumpScarePuzzleScreen(game, stage, hud, gamestate));
					}
				}
			}

		});

		win.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (hud.getInventory().isKeyItemBurned("teddybear") && hud.getInventory().isKeyItemBurned("glove")
						&& hud.getInventory().isKeyItemBurned("earring")
						&& hud.getInventory().isKeyItemBurned("locket")) {
					stage.clear();
					game.setScreen(new VictoryScreen(game, gamestate, map));
				}
			}
		});

		Gdx.input.setInputProcessor(this.stage);
		state = StateSpooks.Run;

		ShaderProgram.pedantic = false;
		sprite = new Sprite(this.map);
		shader = new ShaderProgram(Gdx.files.internal("shaders/vignette.vsh"),
				Gdx.files.internal("shaders/vignette.fsh"));
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

	public void renderGame(float delta) {
		gamestate.startTimer();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		float indexX = viewport.getWorldWidth() / 3.7f;
		float indexY = viewport.getWorldHeight() / 2.3f;
		float posX = Gdx.input.getX() / indexX;
		float posY = (viewport.getWorldHeight() - Gdx.input.getY()) / indexY;
		shader.begin();
		shader.setUniformf("u_PosX", posX);
		shader.setUniformf("u_PosY", posY);
		shader.setUniformf("u_resolution", 350, 350);
		
		if(gamestate.lightFade()){
			shader.setUniformf("timer", gamestate.getShaderIndex(), gamestate.getShaderIndex(), gamestate.getShaderIndex());
		}
		else
			shader.setUniformf("timer", 0, 0, 0);
		shader.end();
		camera.update();

		batch.begin();
		batch.setShader(shader);
		batch.draw(sprite, 0, hud.hudArea.getHeight(), viewport.getWorldWidth(),
				viewport.getWorldHeight() - hud.hudArea.getHeight());
		for (Item item : gamestate.getLivingroomItemList())
			item.draw(batch);
		batch.end();
		game.batch.begin();
		game.batch.draw(hudBG, 0, 0);
		game.batch.end();
		

		// Check om spiller trykker på items rundt i rommet
		if (Gdx.input.justTouched()) {
			if (gamestate.getLivingroomItemList().size() > 0) {
				Vector2 point = new Vector2(Gdx.input.getX(), viewport.getWorldHeight() - Gdx.input.getY());
				for (Item e : gamestate.getLivingroomItemList()) {
					Rectangle pos = e.getSprite().getBoundingRectangle();
					if (pos.contains(point)) {
						e.getSprite().setPosition(-10, -10); // Move the
																// rectangle out
																// of the game
																// screen
						hud.addItemToHud(e);
						gamestate.getLivingroomItemList().remove(e);
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
			dispose();
		}
	}

	private void pauseGame() {
		game.setScreen(new PauseMenu(game, stage, hud, gamestate, "Livingroom"));
		dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		this.state = StateSpooks.Pause;
		Gdx.input.setInputProcessor(stage);

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
