package prasdud.shooter.com;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

public class PickupItem {
    private Sprite sprite;

    public PickupItem(Texture texture, float x, float y) {
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public boolean isPickedUp(Character character) {
        Circle pickupArea = new Circle(
            character.getBoundingRectangle().x + character.getBoundingRectangle().width / 2,
            character.getBoundingRectangle().y + character.getBoundingRectangle().height / 2,
            1 // Adjust this radius as needed
        );
        System.out.println("picked up gun");
        return pickupArea.contains(sprite.getBoundingRectangle().x + sprite.getBoundingRectangle().width / 2,
            sprite.getBoundingRectangle().y + sprite.getBoundingRectangle().height / 2);
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }
}
