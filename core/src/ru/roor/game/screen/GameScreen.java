package ru.roor.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.roor.game.base.BaseScreen;
import ru.roor.game.math.Rect;
import ru.roor.game.sprite.Background;
import ru.roor.game.sprite.MainShip;
import ru.roor.game.sprite.Star;

public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private MainShip mainShip;
    private Star[] stars;

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas(Gdx.files.internal("textures/mainAtlas.tpack"));
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        mainShip = MainShip.init(atlas);
        stars = new Star[256];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        mainShip.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        mainShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        mainShip.update(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        mainShip.draw(batch);
        batch.end();
    }
}
