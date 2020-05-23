//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.roor.game.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.roor.game.math.Rect;

public class BaseShip extends Sprite {
    protected Rect worldBounds;

    public BaseShip(TextureRegion region) {
        super(region);
    }

    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        this.setHeightProportion(0.2F);
    }
}