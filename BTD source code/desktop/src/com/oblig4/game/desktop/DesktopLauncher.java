package com.oblig4.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.oblig4.game.TowerDefence;

public class DesktopLauncher {
	public static void main(String[] arg) {
		System.setProperty("user.name", "Geir"); // Hvis ÆØÅ i brukersti
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.title = "Towerdefense";
		config.width = 1280;
		config.height = 736;
	//	config.addIcon("image\\jagermeister.png", FileType.Internal);
		config.fullscreen = false;
		new LwjglApplication(new TowerDefence(), config);
	}
}
