package com.oblig4.spooks.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.oblig4.spooks.Spooks;

public class DesktopLauncher {

	public static void main(String[] arg) {
		System.setProperty("user.name", "Geir"); // Hvis ÆØÅ i brukersti
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Spooks";
		config.resizable = false;
		// sets screen size automatically depending on users screen resolution
		config.width = 1280;
		config.height = 768;

		config.addIcon("SpooksIcon.png", FileType.Internal);

		config.fullscreen = false;


		new LwjglApplication(new Spooks(), config);
	}
}
