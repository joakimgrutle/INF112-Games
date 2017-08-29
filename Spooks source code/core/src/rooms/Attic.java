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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
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
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.oblig4.spooks.ButtonOwnStyle;
import com.oblig4.spooks.Hud;
import com.oblig4.spooks.ItemListing;
import com.oblig4.spooks.KeyItemListing;
import com.oblig4.spooks.PauseMenu;
import com.oblig4.spooks.ScaryFace;
import com.oblig4.spooks.Spooks;
import com.oblig4.spooks.StateSpooks;

import keyItems.Glove;
import logicClasses.Item;
import logicClasses.JumpScareRandom;
import logicClasses.KeyItem;
import logicClasses.SpooksGame;

public class Attic implements Screen {

	final Spooks game;
	private StateSpooks state;
	private Stage stage;
	private OrthographicCamera camera;
	private Viewport viewport;
	private Music gameMusicSnd;
	private Sound buttonClickSnd;
	private Hud hud;
	private Texture map;
	private SpooksGame gamestate;
	private SpriteBatch sb;

	private Sprite hudBG;
	private ImageButton roomChange;
	private ImageButton puzzle;
	private Image arrow;

	ShaderProgram shader;
	Sprite sprite;
	SpriteBatch batch;
	

	public Attic(Spooks games, Stage stages, Hud huds, SpooksGame gameState) {

		Spooks.manager.get("sound/rain.wav", Music.class).setPan(0, 1.0f);
		Spooks.manager.get("sound/footsteps1.wav", Sound.class).play();

		batch = new SpriteBatch();
		huds.addsTimerToHudSinceYouGuysClearsTheStageAllTheTime();
		this.game = games;
		this.stage = stages;
		this.map = new Texture("background\\attic.png");
		this.hud = huds;
		this.hud.getInventory().reDraw();
		this.gamestate = gameState;
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(Spooks.width, Spooks.height, camera);
		this.viewport = new FillViewport(Spooks.width, Spooks.height);
		hudBG = new Sprite(hud.hudArea);
		hudBG.setSize(hud.hudArea.getWidth(), hud.hudArea.getHeight());
		hudBG.setPosition(viewport.getWorldWidth() - hudBG.getWidth(), 0);

		TextureRegionDrawable mainBtnUp = new TextureRegionDrawable(Spooks.mainButtonsAtals.findRegion("MenuButton1"));
		TextureRegionDrawable mainBtnDown = new TextureRegionDrawable(
				Spooks.mainButtonsAtals.findRegion("MenuButton2"));
		ButtonOwnStyle btnStyleMenu = new ButtonOwnStyle(mainBtnUp, mainBtnDown);
		btnStyleMenu.font = Spooks.font;

		final JumpScareRandom rand = new JumpScareRandom();

		Texture texture = new Texture("images/Transparency100.png");
		TextureRegion textRegion = new TextureRegion(texture);
		TextureRegionDrawable drawable = new TextureRegionDrawable(textRegion);

		roomChange = new ImageButton(drawable);

		puzzle = new ImageButton(drawable);
		roomChange.setSize(128, 128);
		puzzle.setSize(128, 128);
		roomChange.setPosition((Spooks.width / 2) - 100, 120);
		puzzle.setPosition(Spooks.width / 1.6f, Spooks.height / 4);

		roomChange.getImage().setVisible(false);
		puzzle.getImage().setVisible(false);
		stage.addActor(roomChange);
		stage.addActor(puzzle);

		roomChange.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				arrow = new Image(new Texture("images/arrow1_red.png"));
				arrow.setPosition((Spooks.width / 2) - 100, 150 + 128);
				arrow.setSize(128, 128);
				arrow.rotateBy(270);
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
				if (rand.willScare()) {
					stage.clear();
					game.setScreen(new ScaryFace(game, stage, hud, gamestate, map));
					dispose();
				} else {
					stage.clear();
					game.setScreen(new Livingroom(game, stage, hud, gamestate));
					dispose();
				}
			}
		});

		puzzle.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stage.clear();
				game.setScreen(new PinPuzzleRoom(game, stage, hud, gamestate));
				dispose();
			}
		});
		AddInvisibleObject hand = new AddInvisibleObject(game, stage, hud, 550, 220, "hand");

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
		if (gamestate.lightFade()) {
			shader.setUniformf("timer", gamestate.getShaderIndex(), gamestate.getShaderIndex(),
					gamestate.getShaderIndex());
		} else
			shader.setUniformf("timer", 0, 0, 0);
		shader.end();
		camera.update();

		batch.begin();
		batch.setShader(shader);
		batch.draw(sprite, 0, hud.hudArea.getHeight(), viewport.getWorldWidth(),
				viewport.getWorldHeight() - hud.hudArea.getHeight());
		for (Item item : gamestate.getAtticItemList())
			item.draw(batch);
		batch.end();
		game.batch.begin();
		game.batch.draw(hudBG, 0, 0);
		game.batch.end();

		// Check om spiller trykker på items rundt i rommet
		if (Gdx.input.justTouched()) {
			if (gamestate.getAtticItemList().size() > 0) {
				Vector2 point = new Vector2(Gdx.input.getX(), viewport.getWorldHeight() - Gdx.input.getY());
				for (Item e : gamestate.getAtticItemList()) {
					Rectangle pos = e.getSprite().getBoundingRectangle();
					if (pos.contains(point)) {
						e.getSprite().setPosition(-10, -10); // Move the
																// rectangle out
																// of the game
						if(e instanceof Glove){
							if(hud.getInventory().hasItem("hand") && gamestate.getGhostItem() != null){
								if(gamestate.getGhostItem().getItemName().equals("hand") && !gamestate.hasGlove){
									hud.addItemToHud(e);
									gamestate.getAtticItemList().remove(e);
								}
							}
						}
						else{
							hud.addItemToHud(e);
							gamestate.getAtticItemList().remove(e);
						}
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
		game.setScreen(new PauseMenu(game, stage, hud, gamestate, "Attic"));
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
