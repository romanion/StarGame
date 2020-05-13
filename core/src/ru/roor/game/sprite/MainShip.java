package ru.roor.game.sprite;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.roor.game.base.BaseShip;
import ru.roor.game.math.Rect;

public class MainShip extends BaseShip {

    private TextureRegion injuredShip;
    private TextureRegion ship;
    private Vector2 touch;

    private static final float L_SPEED = 0.01f;
    private Vector2 speed;
    private Vector2 auxiliary;

    public MainShip(TextureRegion [][] textureRegions) {
        super(textureRegions[0][0]);
        injuredShip = textureRegions[0][1];
        ship = textureRegions[0][0];

        speed = new Vector2();
        auxiliary = new Vector2();

    }

    public static MainShip init(TextureAtlas atlas){
        TextureRegion textureRegion = atlas.findRegion("main_ship");
        return new MainShip(textureRegion.
                split(textureRegion.getRegionWidth()/2, textureRegion.getRegionHeight()));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom());
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch = touch;
        return false;
    }


    @Override
    public void update(float delta) {
        if (touch != null && !isDestinationReached(touch)) {
            speed.set(auxiliary.x, 0).setLength(L_SPEED);
                pos.add(speed);
        }
    }

    public boolean isDestinationReached(Vector2 dest) {
        if (isMe(dest)) {
            return true;
        }
        auxiliary.set(dest);
        return Math.abs(auxiliary.sub(pos).x) < 0.015f ? true : false;
    }


}
