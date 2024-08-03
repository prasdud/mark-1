package prasdud.shooter.com.lwjgl3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import prasdud.shooter.com.Scene; // Import your Scene class

public class Lwjgl3Launcher extends Game {

    @Override
    public void create() {
        setScreen(new Scene()); // Set the initial screen
    }

    public static void main (String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Survivor Shooter");
        config.setWindowedMode(1920, 1080);
        new Lwjgl3Application(new Lwjgl3Launcher(), config); // Use Lwjgl3Launcher here
    }
}
