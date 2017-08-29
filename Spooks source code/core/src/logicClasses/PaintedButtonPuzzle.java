
package logicClasses;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.oblig4.spooks.Spooks;

import items.Note;


// Author: Hans Botnen

public class PaintedButtonPuzzle {
	private Random rand= new Random();
	private int n; //number of buttons to press
	private int currentStep; // the index of the button of the current step of the puzzle
	private ArrayList<Boolean> pressed; // whether the button has been pressed
	private ArrayList<Color> answerKey; // the colours of the buttons
	private boolean puzzleComplete;
	private boolean failed;
	private String description;
	private Item descriptionItem;
	
	// all possible colours. More can be added, but then they must also be added to the descriptiongenerator
	private Color[] totalColours= {Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW};
	
	//String arrays containing various descriptions of the colours. Currently just placeholders
	//TODO: make riddles and stuff
	private ArrayList<String> blueDesc;
	private ArrayList<String> redDesc;
	private ArrayList<String> greenDesc;
	private ArrayList<String> yellowDesc;
	
	public PaintedButtonPuzzle(){
		// If you make a clue longer than ~40 characters you need a newline
		blueDesc = new ArrayList<String>(Arrays.asList(
			"The colour of wisdom and freedom",
			"I am in France, I am in Russia, but I am not in Poland or Indonesia",
			"I'm _ da ba dee da ba daa",
			"I do not control traffic",
			"I am the colour of our planet",
			"The colour of the couch"));
		redDesc = new ArrayList<String>(Arrays.asList(
			"Inside I am the carrier of air, yet let me breathe and I turn black.\n   What colour am I?",
			"I am hate, I am love",
			"Let's seize the means of production comrade!",
			"I eat, I live, I breathe, I live, I drink, I die. What colour am I?",
			"I am a fruit with seeds on the outside. What colour am I?"));
		greenDesc = new ArrayList<String>(Arrays.asList(
			"The colour of life, I provide the world with air", 
			"I am the face of envy and pestilence",
			"I am not a part of the others, but two of them are a part of me",
			"The colour of the basement pillars"));
		yellowDesc = new ArrayList<String>(Arrays.asList(
			"The giver of life and maker of shadows",
			"Beatles travel the sea within me. What colour am I?",
			"A box without hinges, key or lid, yet coloured treasure inside is hid",
			"Remove 6 letters from YSEIXLLETLTOERSW to find the answer"));
		
		puzzleComplete=false;
		currentStep=0;
		n=3;
		pressed = new ArrayList<Boolean>();
		answerKey = new ArrayList<Color>();
		for(int i=0; i<n;i++){
			answerKey.add(i,totalColours[rand.nextInt(totalColours.length)]);
			pressed.add(false);
		}
		description = generateDescription();
		descriptionItem = new Note(description);
	}
	
	// Generates a description of the solution of the puzzle.
	// To be used for generating a description for the "clue item"
	private String generateDescription(){
		String s="The clues seem to describe different colours.\n\n";
		for(int i=0; i<n; i++){
			s+=(i+1)+": ";
			Color c = answerKey.get(i);
			if(c==Color.BLUE){
				int index = rand.nextInt(blueDesc.size());
				s+= blueDesc.get(index)+"\n";
				blueDesc.remove(index);
			}
			if(c==Color.RED){
				int index = rand.nextInt(redDesc.size());
				s+= redDesc.get(index)+"\n";
				redDesc.remove(index);
			}
			if(c==Color.GREEN){
				int index = rand.nextInt(greenDesc.size());
				s+= greenDesc.get(index)+"\n";
				greenDesc.remove(index);
			}
			if(c==Color.YELLOW){
				int index = rand.nextInt(yellowDesc.size());
				s+= yellowDesc.get(index)+"\n";
				yellowDesc.remove(index);
			}
		}
		return s;
	}
	
	public void pressButton(Color c){
		if(currentStep==n || puzzleComplete)return; // return if all buttons have been pressed and/or game is complete
		if(answerKey.get(currentStep)==c){
			pressed.set(currentStep++,true);
			if(currentStep==n){
				puzzleComplete=true;
				Spooks.manager.get("sound/success1.wav", Sound.class).play();
				wonPuzzle();
			}
		}
		else lostPuzzle();
	}

	private void lostPuzzle() {
		// TODO do whatever we want to do if a player fails the puzzle
		// Currently: Prints message that player pressed wrong and resets puzzle
		failed=true;
	}
	private void wonPuzzle(){
		// TODO do whatever we want do do if a player completes the puzzle
		
	}
	public boolean getPuzzleComplete(){
		return puzzleComplete;
	}
	public String getDescription(){
		return description;
	}
	public Item getDescriptionItem(){
		return descriptionItem;
	}
	public boolean getFailed(){
		return failed;
	}
	
}

