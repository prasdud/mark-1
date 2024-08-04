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
        // Adjust background position according to camera position
        batch.draw(backgroundTexture, -camera.position.x, -camera.position.y);
        character.render(batch);
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
    }
}
