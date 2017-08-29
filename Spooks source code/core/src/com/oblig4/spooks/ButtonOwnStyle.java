package com.oblig4.spooks;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 *Extension for a ButtonStyle class
 * 
 * @author Emilia Przybysz
 *
 */

public class ButtonOwnStyle extends TextButtonStyle {
	
	public ButtonOwnStyle(Drawable up, Drawable down, Drawable checked, Drawable over) {
		super.up = up;
		super.down = down;
		super.checked = checked;
		super.over = over;	
	}
	
	public ButtonOwnStyle(Drawable up, Drawable over) {
		super.up = up;
		super.over = over;	
	}

}
