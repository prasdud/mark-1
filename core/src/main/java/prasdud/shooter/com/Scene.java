package prasdud.shooter.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Scene implements Screen {
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private Character character;
    private OrthographicCamera camera;
    private Obstacle[] obstacles;

    @Override
    public void show() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("background-image.jpg");
        backgroundSprite = new Sprite(backgroundTexture);
        Texture characterTexture = new Texture("character.png");
        character = new Character(characterTexture);

        // Initialize the camera
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Initialize obstacles
        Texture obstacleTexture = new Texture("obstacle.png");
        obstacles = new Obstacle[] {
            new Obstacle(obstacleTexture, 200, 200),
            new Obstacle(obstacleTexture, 400, 300)
        };
    }

    @Override
    public void render(float delta) {
        // Update character movement based on input
        character.update(delta);

        // Move the camera to follow the character
        camera.position.set(character.getX(), character.getY(), 0);
        camera.update();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        // Draw the background, which moves with the camera
        batch.draw(backgroundTexture, -camera.position.x, -camera.position.y);
        // Draw the character, which is centered by the camera
        character.render(batch);
        // Draw obstacles in their fixed positions relative to the camera
        for (Obstacle obstacle : obstacles) {
            // Convert obstacle position to camera coordinates
            float obstacleX = obstacle.getX() - camera.position.x;
            float obstacleY = obstacle.getY() - camera.position.y;
            batch.draw(obstacle.getTexture(), obstacleX, obstacleY);
        }
        batch.end();
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
    }
}
