package prasdud.shooter.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


public class Character {
    private Sprite sprite;
    private Body body;
    private float speed = 5000f;

    public Character(World world, Texture texture) {
        sprite = new Sprite(texture);
        // Initial position set to center of the screen
        sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2, Gdx.graphics.getHeight() / 2 - sprite.getHeight() / 2);


        // Create Box2D body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(sprite.getX(), sprite.getY());
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);

        Fixture fixture = body.createFixture(shape, 1.0f);
        fixture.setFriction(0.5f); // Add friction
        fixture.setRestitution(0.0f); // No bounce
        shape.dispose();
    }

    public void update(float delta) {
        float sprintSpeedMultiplier = 5f;
        Vector2 velocity = body.getLinearVelocity();

        velocity.set(0,0);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocity.x = -speed * delta;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocity.x = speed * delta;
        } else {
            velocity.x = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            velocity.y = speed * delta;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            velocity.y = -speed * delta;
        } else {
            velocity.y = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            velocity.scl(sprintSpeedMultiplier); // Scale the velocity for sprinting
        }

        body.setLinearVelocity(velocity);

        Vector2 position = body.getPosition();
        sprite.setPosition(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2);
    }


    public float getX() {
        return sprite.getX() + sprite.getWidth() / 2;
    }

    public float getY() {
        return sprite.getY() + sprite.getHeight() / 2;
    }


    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }
    public Rectangle getBoundingRectangle() {
        return sprite.getBoundingRectangle();
    }
}
