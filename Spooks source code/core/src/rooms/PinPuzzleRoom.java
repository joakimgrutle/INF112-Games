package rooms;

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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.oblig4.spooks.Hud;
import com.oblig4.spooks.ItemListing;
import com.oblig4.spooks.PauseMenu;
import com.oblig4.spooks.ScaryFace;
import com.oblig4.spooks.Spooks;
import com.oblig4.spooks.StateSpooks;

import logicClasses.Item;
import logicClasses.KeyItem;
import logicClasses.Pin;
import logicClasses.SpooksGame;

public class PinPuzzleRoom implements Screen {

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
	private TextButton roomChange;

	// puzzle
	public static Skin uiSkin;
	Pin pin;
	Table pauseTable;
	Label show1;
	Label show2;
	Label show3;

	SpriteBatch batch;
	ShaderProgram shader;
	Sprite sprite;

	public PinPuzzleRoom(Spooks games, Stage stages, Hud huds, SpooksGame gameState) {
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

		pauseTable = new Table();
		pin = new Pin();
		uiSkin = Spooks.skin;
		TextButton tall1 = new TextButton("1", uiSkin);
		TextButton tall2 = new TextButton("2", uiSkin);
		TextButton tall3 = new TextButton("3", uiSkin);
		TextButton tall4 = new TextButton("4", uiSkin);
		TextButton tall5 = new TextButton("5", uiSkin);
		TextButton tall6 = new TextButton("6", uiSkin);
		TextButton tall7 = new TextButton("7", uiSkin);
		TextButton tall8 = new TextButton("8", uiSkin);
		TextButton tall9 = new TextButton("9", uiSkin);
		TextButton tallReset = new TextButton("Reset", uiSkin);
		TextButton tallBack = new TextButton("Back", uiSkin);
		TextButton tallGo = new TextButton("Accept", uiSkin);

		// Setup listeners for the buttons
		tall1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Spooks.manager.get("sound/click.wav", Sound.class).play();
				pin.input(1);
			}
		});
		tall2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Spooks.manager.get("sound/click.wav", Sound.class).play();
				pin.input(2);
			}
		});
		tall3.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Spooks.manager.get("sound/click.wav", Sound.class).play();
				pin.input(3);
			}
		});
		tall4.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Spooks.manager.get("sound/click.wav", Sound.class).play();
				pin.input(4);
			}
		});
		tall5.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Spooks.manager.get("sound/click.wav", Sound.class).play();
				pin.input(5);
			}
		});
		tall6.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Spooks.manager.get("sound/click.wav", Sound.class).play();
				pin.input(6);
			}
		});
		tall7.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Spooks.manager.get("sound/click.wav", Sound.class).play();
				pin.input(7);
			}
		});
		tall8.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Spooks.manager.get("sound/click.wav", Sound.class).play();
				pin.input(8);
			}
		});
		tall9.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Spooks.manager.get("sound/click.wav", Sound.class).play();
				pin.input(9);
			}
		});
		tallReset.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pin.reset();
			}
		});
		tallBack.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stage.clear();
				game.setScreen(new Attic(game, stage, hud, gamestate));
			}
		});
		tallGo.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (pin.isFinish() && !SpooksGame.getInstance().isSolvedPinPuzzle()) {
					Spooks.manager.get("sound/success2.wav", Sound.class).play();
					hud.addItemToHud(KeyItem.createKeyItem("locket"));
					
					SpooksGame.getInstance().setSolvedPinPuzzle(true);
					stage.clear();
					game.setScreen(new Attic(game, stage, hud, gamestate));
				} else{
					if(new Random().nextInt(5)>3) // random chance to die if fail
						game.setScreen(new ScaryFace(game, stage, hud, gamestate, map));
					else
						pin.reset();
				}
			}
		});

		pauseTable.setX(viewport.getWorldWidth() / 2);
		pauseTable.setY(viewport.getWorldHeight() / 2);
		show1 = new Label("*", game.skin);
		show2 = new Label("*", game.skin);
		show3 = new Label("*", game.skin);
		pauseTable.add(show1);
		pauseTable.add(show2);
		pauseTable.add(show3);
		pauseTable.row();
		// For å hoppe over row
		Label test4 = new Label("", game.skin);
		pauseTable.add(test4);
		pauseTable.row();
		// Add buttons to table and the table to the stage
		pauseTable.add(tall1);
		pauseTable.add(tall2);
		pauseTable.add(tall3);
		pauseTable.row();
		pauseTable.add(tall4);
		pauseTable.add(tall5);
		pauseTable.add(tall6);
		pauseTable.row();
		pauseTable.add(tall7);
		pauseTable.add(tall8);
		pauseTable.add(tall9);
		pauseTable.row();
		pauseTable.add(tallReset);
		pauseTable.add(tallBack);
		pauseTable.add(tallGo);
		stage.addActor(pauseTable);

		Gdx.input.setInputProcessor(stage);
		state = StateSpooks.Run;
		ShaderProgram.pedantic = false;
		sprite = new Sprite(this.map);
		shader = new ShaderProgram(Gdx.files.internal("shaders/vignette.vsh"),
				Gdx.files.internal("shaders/vignette.fsh"));

	}

	public void updateNumbers() {
		show1.setText(pin.getnumber(0));
		show2.setText(pin.getnumber(1));
		show3.setText(pin.getnumber(2));
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

		updateNumbers();

		float indexX = viewport.getWorldWidth() / 3.7f;
		float indexY = viewport.getWorldHeight() / 2.3f;
		float posX = 637 / indexX;
		float posY = (viewport.getWorldHeight() - 415) / indexY;
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
		for (Item item : gamestate.getBathroomItemList())
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
					if (gamestate.getInventory().inv.size() > 0 || gamestate.getInventory().keyInv.size()>0)
						checkInfoWindow();
				}
		stage.act();
		stage.draw();

		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		gamestate.render(game.batch);

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			stage.clear();
			game.setScreen(new Attic(game, stage, hud, gamestate));
		}
		hud.updateTimer();
		if (gamestate.lostGame()) {
			game.setScreen(new ScaryFace(game, stage, hud, gamestate, map));
		}
	}

	private void pauseGame() {
		game.setScreen(new PauseMenu(game, stage, hud, gamestate, "Attic"));
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
