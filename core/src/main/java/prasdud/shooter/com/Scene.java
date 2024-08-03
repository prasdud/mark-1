package prasdud.shooter.com;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Scene implements Screen {
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private Character character;


    @Override
    public void show() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("background-image.jpg");
        backgroundSprite = new Sprite(backgroundTexture);
        Texture characterTexture = new Texture("character.png");
        character = new Character(characterTexture);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        character.update(delta);
        batch.begin();
        backgroundSprite.draw(batch);
        character.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {}

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
