package prasdud.shooter.com;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PickupItem {
    private Sprite sprite;
    private boolean pickedUp;

    public PickupItem(Texture texture, float x, float y) {
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
        pickedUp = false;
    }

    public void render(SpriteBatch batch) {
        if (!pickedUp) {
            sprite.draw(batch);
        }
    }

    public boolean isPickedUp(Character character) {
        if (character.getBoundingRectangle().overlaps(sprite.getBoundingRectangle())) {
            pickedUp = true;  // Mark the item as picked up
            System.out.println("Item picked up");
            return true;
        }
        return false;
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }
}
