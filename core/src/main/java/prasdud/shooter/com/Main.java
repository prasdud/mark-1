package prasdud.shooter.com;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Main extends Game {
    @Override
    public void create() {
        this.setScreen(new Scene()); // Set the scene as the initial screen
    }
    World world = new World(new Vector2(0, -10), true);
}
