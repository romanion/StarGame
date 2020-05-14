package ru.roor.game.sprite;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.roor.game.base.BaseShip;
import ru.roor.game.math.Rect;
import ru.roor.game.pool.BulletPool;

public class MainShip extends BaseShip {
    private static final int INVALID_POINTER = -1;

    private int leftPointer;
    private int rightPointer;

    private BulletPool bulletPool;
    private TextureRegion bulletRegion;
    private Vector2 bulletV;

    private TextureRegion injuredShip;
    private TextureRegion ship;
    private Vector2 touch;

    private final Vector2 v0;
    private final Vector2 v;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public MainShip(TextureRegion [][] textureRegions, BulletPool bulletPool, TextureRegion bulletRegion) {
        super(textureRegions[0][0]);
        injuredShip = textureRegions[0][1];
        ship = textureRegions[0][0];

        v0 = new Vector2(0.5f, 0);
        v = new Vector2();
        leftPointer = INVALID_POINTER;
        rightPointer = INVALID_POINTER;
        this.bulletPool = bulletPool;
        this.bulletRegion = bulletRegion;
        bulletV = new Vector2(0, 0.5f);
    }

    public static MainShip init(TextureAtlas atlas, BulletPool bulletPool){
        TextureRegion textureRegion = atlas.findRegion("main_ship");
        TextureRegion bulletRegion = atlas.findRegion("bulletMainShip");

        return new MainShip(textureRegion.
                split(textureRegion.getRegionWidth()/2,
                        textureRegion.getRegionHeight()),
                bulletPool,
                bulletRegion);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom());
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkBounds();
    }

    public void checkBounds(){
        if(getLeft() < worldBounds.getLeft() ){
            stop();
            setLeft(worldBounds.getLeft());
        }

        if(getRight() > worldBounds.getRight()){
            stop();
            setRight(worldBounds.getRight());
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.A:
            case Input.Keys.LEFT:
                leftPressed = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                rightPressed = true;
                moveRight();
                break;

        }

        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.A:
            case Input.Keys.LEFT:
                leftPressed = false;
                if(rightPressed){
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                rightPressed = false;
                if(leftPressed){
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
        stop();
        return false;
    }

    public void moveRight(){
        v.set(v0);
    }

    public void moveLeft(){
        v.set(v0).rotate(180);
    }

    public void stop(){
        v.setZero();
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, 0.01f, worldBounds, 1);
    }

}
