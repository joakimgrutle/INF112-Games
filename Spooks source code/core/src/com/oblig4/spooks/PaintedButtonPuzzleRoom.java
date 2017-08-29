package com.oblig4.spooks;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import logicClasses.Item;
import logicClasses.PaintedButtonPuzzle;

public class PaintedButtonPuzzleRoom {
	private PaintedButtonPuzzle puzzle;
	Table pauseTable;
	public String exitRoom;
	private static Skin uiSkin;
	
	PaintedButtonPuzzleRoom(Viewport viewport, final RoomState roomState) {
		pauseTable = new Table();
		puzzle = new PaintedButtonPuzzle();
		
		System.out.println(puzzle.getDescription());//temporary
		
		pauseTable.setX(viewport.getWorldWidth() / 2);
		pauseTable.setY(viewport.getWorldHeight() / 2);
		uiSkin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
		
		ButtonOwnStyle redButtonStyle = new ButtonOwnStyle(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/redButtonUp.png")))),
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/redButtonDown.png")))));
		redButtonStyle.font=Spooks.font;
		TextButton redButton = new TextButton("", redButtonStyle);
		redButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
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
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/blueButtonUp.png")))),
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/blueButtonDown.png")))));
		blueButtonStyle.font=Spooks.font;
		TextButton blueButton = new TextButton("", blueButtonStyle);
		blueButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
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
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/greenButtonUp.png")))),
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/greenButtonDown.png")))));
		greenButtonStyle.font=Spooks.font;
		TextButton greenButton = new TextButton("", greenButtonStyle);
		greenButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
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
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/yellowButtonUp.png")))),
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/yellowButtonDown.png")))));
		yellowButtonStyle.font=Spooks.font;
		TextButton yellowButton = new TextButton("", yellowButtonStyle);
		yellowButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				puzzle.pressButton(Color.YELLOW);
			}
		});
		pauseTable.add(yellowButton);
		pauseTable.row();
		
		TextButton backButton = new TextButton("Back", uiSkin);
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(puzzle.getPuzzleComplete()){
					// TODO do what we need to do if puzzle is finished
				}
				roomState.moveToRoom(exitRoom);
			}
		});
		pauseTable.add(backButton);
	}

	public void setExitRoom(String exitRoom) {
		this.exitRoom=exitRoom;
	}
	public Item getPuzzleNote(){
		return puzzle.getDescriptionItem();
	}
	public boolean isFinished(){
		return puzzle.getPuzzleComplete();
	}
	
}
