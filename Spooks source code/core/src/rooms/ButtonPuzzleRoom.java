package rooms;

import java.awt.Color;
import java.util.Random;
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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.oblig4.spooks.ButtonOwnStyle;
import com.oblig4.spooks.Hud;
import com.oblig4.spooks.ItemListing;
import com.oblig4.spooks.RoomState;
import com.oblig4.spooks.ScaryFace;
import com.oblig4.spooks.Spooks;
import com.oblig4.spooks.StateSpooks;

import logicClasses.Item;
import logicClasses.KeyItem;
import logicClasses.PaintedButtonPuzzle;
import logicClasses.SpooksGame;

public class ButtonPuzzleRoom implements Screen {
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
	SpriteBatch sb;

	// Rain
	ParticleEffect rainEffect;
	RoomState rooms;
	private Sprite hudBG;
	private TextButton roomChange;

	// Puzzle
	private PaintedButtonPuzzle puzzle;
	Table pauseTable;
	public String exitRoom;
	private static Skin uiSkin;

	ShaderProgram shader;
	Sprite sprite;
	SpriteBatch batch;

	public ButtonPuzzleRoom(Spooks games, Stage stages, Hud huds, SpooksGame gameState) {
		batch = new SpriteBatch();
		huds.addsTimerToHudSinceYouGuysClearsTheStageAllTheTime();
		this.game = games;
		this.stage = stages;
		this.map = new Texture("background\\bathroom.png");
		this.gamestate = gameState;
		this.hud = huds;
		this.hud.getInventory().reDraw();
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(Spooks.width, Spooks.height, camera);
		this.viewport = new FillViewport(Spooks.width, Spooks.height);
		hudBG = new Sprite(hud.hudArea);
		hudBG.setSize(hud.hudArea.getWidth(), hud.hudArea.getHeight());
		hudBG.setPosition(viewport.getWorldWidth() - hudBG.getWidth(), 0);

		pauseTable = new Table();
		puzzle = gameState.getButtonPuzzle();

		pauseTable.setX(viewport.getWorldWidth() / 2.4f);
		pauseTable.setY(viewport.getWorldHeight() / 1.8f);
		uiSkin = Spooks.skin;

		ButtonOwnStyle redButtonStyle = new ButtonOwnStyle(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/redButtonUp.png")))),
				new TextureRegionDrawable(
						new TextureRegion(new Texture(Gdx.files.internal("images/redButtonDown.png")))));
		redButtonStyle.font = Spooks.font;
		TextButton redButton = new TextButton("", redButtonStyle);
		redButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				puzzle.pressButton(Color.RED);
			}
		});
		pauseTable.add(redButton);

		ButtonOwnStyle blueButtonStyle = new ButtonOwnStyle(
				new TextureRegionDrawable(
						new TextureRegion(new Texture(Gdx.files.internal("images/blueButtonUp.png")))),
				new TextureRegionDrawable(
						new TextureRegion(new Texture(Gdx.files.internal("images/blueButtonDown.png")))));
		blueButtonStyle.font = Spooks.font;
		TextButton blueButton = new TextButton("", blueButtonStyle);
		blueButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				puzzle.pressButton(Color.BLUE);
			}
		});
		pauseTable.add(blueButton);

		ButtonOwnStyle greenButtonStyle = new ButtonOwnStyle(
				new TextureRegionDrawable(
						new TextureRegion(new Texture(Gdx.files.internal("images/greenButtonUp.png")))),
				new TextureRegionDrawable(
						new TextureRegion(new Texture(Gdx.files.internal("images/greenButtonDown.png")))));
		greenButtonStyle.font = Spooks.font;
		TextButton greenButton = new TextButton("", greenButtonStyle);
		greenButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				puzzle.pressButton(Color.GREEN);
			}
		});
		pauseTable.add(greenButton);

		ButtonOwnStyle yellowButtonStyle = new ButtonOwnStyle(
				new TextureRegionDrawable(
						new TextureRegion(new Texture(Gdx.files.internal("images/yellowButtonUp.png")))),
				new TextureRegionDrawable(
						new TextureRegion(new Texture(Gdx.files.internal("images/yellowButtonDown.png")))));
		yellowButtonStyle.font = Spooks.font;
		TextButton yellowButton = new TextButton("", yellowButtonStyle);
		yellowButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				puzzle.pressButton(Color.YELLOW);
			}
		});
		pauseTable.add(yellowButton);
		stage.addActor(pauseTable);

		roomChange = new TextButton("Back", uiSkin);
		roomChange.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// if failed and a random chance
				if (puzzle.getFailed() && new Random().nextInt(5)>3) {
					stage.clear();
					game.setScreen(new ScaryFace(game, stage, hud, gamestate, map));
				} else {
					if (puzzle.getPuzzleComplete() && !SpooksGame.getInstance().isSolvedColorPuzzle()){
						hud.addItemToHud(KeyItem.createKeyItem("teddybear"));
						SpooksGame.getInstance().setSolvedColorPuzzle(true);
					}
					stage.clear();
					game.setScreen(new Bathroom(game, stage, hud, gamestate));
				}
			}
		});
		roomChange.setX(pauseTable.getX() - 40);
		roomChange.setY(pauseTable.getY() - 60);
		stage.addActor(roomChange);
		Gdx.input.setInputProcessor(stage);
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
		}
	}

	public void renderGame(float delta) {
		gamestate.startTimer();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		float indexX = viewport.getWorldWidth() / 3.7f;
		float indexY = viewport.getWorldHeight() / 2.3f;
		float posX = 531 / indexX;
		float posY = (viewport.getWorldHeight() - 378) / indexY;
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

		batch.end();
		game.batch.begin();
		hudBG.draw(game.batch);

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
								
								Spooks.manager.get("sound/success1.wav", Sound.class).play();
								
								gamestate.getBasementItemList().remove(e);
								break;
							}
						}
					}
					if (gamestate.getInventory().inv.size() > 0 || gamestate.getInventory().keyInv.size()>0)
						checkInfoWindow();
				}
		stage.act();
		stage.draw();

		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		gamestate.render(game.batch);

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			stage.clear();
			game.setScreen(new Bathroom(game, stage, hud, gamestate));
		}
		hud.updateTimer();
		if (gamestate.lostGame()) {
			game.setScreen(new ScaryFace(game, stage, hud, gamestate, map));
		}

	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		// viewport.update(width, height);
		// camera.update();
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
		// TODO Auto-generated method stub

	}

	public void setExitRoom(String exitRoom) {
		this.exitRoom = exitRoom;
	}

	public Item getPuzzleNote() {
		return puzzle.getDescriptionItem();
	}

	public boolean isFinished() {
		return puzzle.getPuzzleComplete();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	private void checkInfoWindow() {
		Vector2 point = new Vector2(Gdx.input.getX(),
				SpooksGame.getInstance().getHud().stage.getViewport().getWorldHeight() - Gdx.input.getY());
		for (ItemListing item : SpooksGame.getInstance().getInventory().inv) {
			Rectangle pos = item.getItem().getSprite().getBoundingRectangle();
			for (ItemListing it : SpooksGame.getInstance().getInventory().inv) {
				if (it.getItem().window != null)
					it.getItem().window.remove();
			}
		}
	}

}
