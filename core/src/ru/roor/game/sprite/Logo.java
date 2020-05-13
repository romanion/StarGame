package ru.roor.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.roor.game.base.Sprite;
import ru.roor.game.math.Rect;

public class Logo extends Sprite {

    private static final float L_SPEED = 0.01f;
    private Vector2 speed;
    private Vector2 auxiliary;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        setLogoParams();
        speed = new Vector2();
        auxiliary = new Vector2();
    }

    public void setLogoParams() {
        setHeight(0.15f);
        setWidth(0.1f);
        pos.set(0, this.getHalfHeight() - 0.5f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(regions[frame].getRegionHeight());
    }

    public void draw(SpriteBatch batch, Vector2 touch) {
        if (touch != null && !isDestinationReached(touch)) {
            speed.set(auxiliary).setLength(L_SPEED);
            pos.add(speed);
        }

        draw(batch);
    }

    public boolean isDestinationReached(Vector2 dest) {
        if (isMe(dest)) return true;
        auxiliary.set(dest);

        return auxiliary.sub(pos).len() < 0.01f ? true : false;
    }
}