package prasdud.shooter.com;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class Obstacle {
    private Sprite sprite;
    private Body body;

    public Obstacle(Texture texture, float x, float y) {
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
    }

    public Obstacle(World world, Texture texture, float x, float y) {
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);

        //create box2d body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        body =  world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);


        // Create a fixture definition for the obstacle
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.0f;  // No density needed for static bodies
        fixtureDef.friction = 0.5f; // Friction for sliding interactions
        fixtureDef.restitution = 0.0f; // No bounce

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public Texture getTexture() {
        return sprite.getTexture();
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }
}
