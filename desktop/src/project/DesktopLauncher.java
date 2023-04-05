package project;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import project.ScreenManagement.ScreenManagerImpl;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(Constants.screenWidth, Constants.screenHeight);
		config.useVsync(true);
		config.setForegroundFPS(Constants.fps);
		config.setTitle("TowerDefense");
		config.setResizable(false);
		new Lwjgl3Application(new ScreenManagerImpl(), config);
	}
}