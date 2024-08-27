package prasdud.shooter.com;

import com.badlogic.gdx.graphics.Texture;

public class Gun extends PickupItem {
    private String name;
    private float damage;
    private float fireRate;

    public Gun(String name, float damage, float fireRate, Texture texture, float x, float y) {
        super(texture, x, y);
        this.name = name;
        this.damage = damage;
        this.fireRate = fireRate;
    }

    // Getters for the gun attributes
    public String getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }

    public float getFireRate() {
        return fireRate;
    }


}
