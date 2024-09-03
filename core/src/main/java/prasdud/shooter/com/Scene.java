package prasdud.shooter.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.List;

public class Scene implements Screen {
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private Character character;
    private OrthographicCamera camera;
    private Obstacle[] obstacles;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private List<PickupItem> items;
    private Cursor customCursor;

    @Override
    public void show() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("background-image.jpg");
        backgroundSprite = new Sprite(backgroundTexture);
        Texture characterTexture = new Texture("character.png");

        //init box2d
        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();

        //create the character
        character = new Character(world, characterTexture);

        //items
        items = new ArrayList<>();
        Texture gunTexture = new Texture("pistol.png");
        items.add(new Gun("Pistol", 10f, 0.5f, gunTexture, 300, 300));

        // Initialize the camera
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // rotating camera for isometric view
        camera.rotate(45);
        camera.rotate(-30, 1, 0, 0);

        // Initialize obstacles
        Texture obstacleTexture = new Texture("obstacle.png");
        obstacles = new Obstacle[]{
            new Obstacle(world, obstacleTexture, 200, 200),
            new Obstacle(world, obstacleTexture, 400, 300)
        };

        //create cursor
        Pixmap pixmap = new Pixmap(Gdx.files.internal("target-16.png"));
        int xHotspot = 8;
        int yHotspot = 8;
        customCursor = Gdx.graphics.newCursor(pixmap, xHotspot, yHotspot);
        pixmap.dispose();
        Gdx.graphics.setCursor(customCursor);
    }

    @Override
    public void render(float delta) {

        //step physics simulation
        world.step(1 / 60f, 6, 2);

        // Update character movement based on input
        character.update(delta);

        // Move the camera to follow the character
        camera.position.set(character.getX(), character.getY(), 0);
        camera.update();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        // Draw the background, which moves with the camera
        batch.draw(backgroundTexture, -camera.viewportWidth / 2, -camera.viewportHeight / 2);

        // Draw the character, which is centered by the camera
        character.render(batch);

        // Draw obstacles in their fixed positions relative to the camera
        for (Obstacle obstacle : obstacles) {
            obstacle.render(batch);
        }

        for (PickupItem item : items) {
            item.render(batch);
            if (item.isPickedUp(character)) {
                item.dispose();
                // Handle the pickup (e.g., add to inventory or equip the weapon)
            }
        }

        batch.end();
        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        character.dispose();
        // Dispose obstacle textures
        for (Obstacle obstacle : obstacles) {
            obstacle.dispose();
        }
        world.dispose();
        debugRenderer.dispose();
        if (customCursor != null) {
            Gdx.graphics.setCursor(null); // Reset the cursor to default
            customCursor.dispose();
        }
    }
}
