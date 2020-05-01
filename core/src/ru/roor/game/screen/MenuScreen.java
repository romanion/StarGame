package ru.roor.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.roor.game.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    Texture img;

    private Vector2 pos;
    private Vector2 dest;
    private Vector2 speed;

    @Override
    public void show() {
        super.show();
        img = new Texture("spaceship.jpg");
        pos = new Vector2();
        speed = new Vector2();
        dest = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(isDestinationReached()) speed.set(0, 0);
        pos.add(speed);

        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
    }

    public boolean isDestinationReached(){
        return pos.cpy().sub(dest).len() < 0.5f ? true : false;
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        dest.set(screenX, Gdx.graphics.getHeight() - screenY);
        speed = dest.cpy().sub(pos).nor();
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
