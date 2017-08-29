package com.oblig4.game;
	import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

	public class SplashScreen implements Screen{
	    
	    private SpriteBatch batch;
	    private TowerDefence myGame;
	    private Texture texture, blank;
	    private OrthographicCamera camera;
	    private long startTime;
	    private int rendCount;
	    private Viewport viewport;
	    private float elapsedTime;
		private int speed;
		private Stage stage;
		private Animation rightAnimation, bimboRight, dudebroRight, studentRight, hoboRight;
		private Sprite sprite, bimbo, dudebro, student, hobo;
	    
	    public SplashScreen(final TowerDefence game) {
	    	stage = new Stage();
	    	sprite = new Sprite(new Texture("image\\src\\boss_right.png"));
	    	bimbo = new Sprite(new Texture("image\\src\\bimbo_right.png"));
	    	dudebro = new Sprite(new Texture("image\\src\\dudebro_right.png"));
	    	student = new Sprite(new Texture("image\\src\\student_right.png"));
	    	hobo = new Sprite(new Texture("image\\src\\homeless_right.png"));
	    	
	    	sprite.setPosition(250, 350);
	    	bimbo.setPosition(250, 280);
	    	dudebro.setPosition(250, 210);
	    	student.setPosition(250, 140);
	    	hobo.setPosition(250, 70);
	    	this.speed = 2;
	    	elapsedTime = 0;
	    	blank = new Texture("whitePixelHP.png");
	    	viewport = new FitViewport(TowerDefence.width,TowerDefence.height);
	    	Label.LabelStyle titleFont = new Label.LabelStyle(TowerDefence.uiSkin.getFont("title"), Color.WHITE);
			Label gameOverLabel = new Label("BERGEN TD", titleFont);
			gameOverLabel.setX(TowerDefence.width / 3 - 60);
			gameOverLabel.setY( TowerDefence.height - 100);
			stage.addActor(gameOverLabel);
			
	        myGame = game; // ** get Game parameter **//
	        camera = new OrthographicCamera();
	        camera.setToOrtho(false, TowerDefence.width, TowerDefence.height);
	        batch = new SpriteBatch();
	        Gdx.input.setInputProcessor(stage);
	        
	        Texture rightImg = new Texture("image\\src\\boss_right.png");
	        Texture brightImg = new Texture("image\\src\\bimbo_right.png");
	        Texture drightImg = new Texture("image\\src\\dudebro_right.png");
	        Texture srightImg = new Texture("image\\src\\student_right.png");
	        Texture hrightImg = new Texture("image\\src\\homeless_right.png");
	        
			TextureRegion[][] tempRightFrames = TextureRegion.split(rightImg, 32, 32);
			TextureRegion[][] btempRightFrames = TextureRegion.split(brightImg, 32, 32);
			TextureRegion[][] dtempRightFrames = TextureRegion.split(drightImg, 32, 32);
			TextureRegion[][] stempRightFrames = TextureRegion.split(srightImg, 32, 32);
			TextureRegion[][] htempRightFrames = TextureRegion.split(hrightImg, 32, 32);
			TextureRegion[] rightFrames = new TextureRegion[4];
			TextureRegion[] brightFrames = new TextureRegion[4];
			TextureRegion[] drightFrames = new TextureRegion[4];
			TextureRegion[] srightFrames = new TextureRegion[4];
			TextureRegion[] hrightFrames = new TextureRegion[4];
			int index = 0;
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < 4; j++) {
					rightFrames[index] = tempRightFrames[i][j];
					brightFrames[index] = btempRightFrames[i][j];
					drightFrames[index] = dtempRightFrames[i][j];
					srightFrames[index] = stempRightFrames[i][j];
					hrightFrames[index++] = htempRightFrames[i][j];
				}
			}
			this.speed = this.speed * 5;
			rightAnimation = new Animation(1f / this.speed, rightFrames);
			bimboRight = new Animation(1f / this.speed, brightFrames);
			dudebroRight = new Animation(1f / this.speed, drightFrames);
			studentRight = new Animation(1f / this.speed, srightFrames);
			hoboRight = new Animation(1f / this.speed, hrightFrames);
			this.speed = this.speed / 5;
			rightAnimation.setPlayMode(PlayMode.LOOP);
			bimboRight.setPlayMode(PlayMode.LOOP);
			dudebroRight.setPlayMode(PlayMode.LOOP);
			studentRight.setPlayMode(PlayMode.LOOP);
			hoboRight.setPlayMode(PlayMode.LOOP);
		}
	    @Override
	    
	
		

	    public void render(float delta) {
	    	elapsedTime += Gdx.graphics.getDeltaTime();
	    	speed = speed * 5;
			rightAnimation.setFrameDuration(1f / speed);
			bimboRight.setFrameDuration(1f / speed);
			dudebroRight.setFrameDuration(1f / speed);
			studentRight.setFrameDuration(1f / speed);
			hoboRight.setFrameDuration(1f / speed);
			speed = speed / 5;
			sprite.setPosition((250 * (elapsedTime / 2.1f)) + 210, 55);
			bimbo.setPosition(250 * (elapsedTime / 2.1f) + 278, 470);
	    	dudebro.setPosition(250 * (elapsedTime / 2.1f) + 272, 390);
	    	student.setPosition(250 * (elapsedTime / 2.1f) + 266, 310);
	    	hobo.setPosition(250 * (elapsedTime / 2.1f) + 262, 230);
	        Gdx.gl.glClearColor(0, 0, 0, 1);
	        batch.setProjectionMatrix(camera.combined);
	        batch.begin();
	        batch.disableBlending();
		        
	        batch.draw((TextureRegion) rightAnimation.getKeyFrame(elapsedTime, true), sprite.getX(),
					sprite.getY(), this.sprite.getWidth() * 2, this.sprite.getHeight() * 4);
	        
	        batch.draw((TextureRegion) bimboRight.getKeyFrame(elapsedTime, true), bimbo.getX(),
	        		bimbo.getY(), this.bimbo.getWidth() * 0.7f, this.bimbo.getHeight() * 1.7f);
	        
	        batch.draw((TextureRegion) dudebroRight.getKeyFrame(elapsedTime, true), dudebro.getX(),
	        		dudebro.getY(), this.dudebro.getWidth() * 0.8f, this.dudebro.getHeight() * 1.8f);
	        
	        batch.draw((TextureRegion) studentRight.getKeyFrame(elapsedTime, true), student.getX(),
	        		student.getY(), this.student.getWidth() * 0.9f, this.student.getHeight() * 1.9f);
	        
	        batch.draw((TextureRegion) hoboRight.getKeyFrame(elapsedTime, true), hobo.getX(),
	        		hobo.getY(), this.hobo.getWidth(), this.hobo.getHeight() * 2);
	        batch.end();
	        rendCount++;
	        if (TimeUtils.millis()>(startTime + 5000)) myGame.setScreen(new MainMenuScreen(myGame));
	        stage.act();
	        stage.draw();
	    }

	    @Override
	    public void resize(int width, int height) {
	    }

	    @Override
	    public void show() {
	         //** texture is now the splash image **//
	        
	        startTime = TimeUtils.millis();
	    }

	    @Override
	    public void hide() {
	    }

	    @Override
	    public void pause() {
	    }

	    @Override
	    public void resume() {
	    }

	    @Override
	    public void dispose() {
	        texture.dispose();
	        batch.dispose();
	        stage.dispose();
	    }

	}
