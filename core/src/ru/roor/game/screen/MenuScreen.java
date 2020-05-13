package ru.roor.game.screen;


import com.badlogic.gdx.graphics.Texture;

import ru.roor.game.base.BaseScreen;
import ru.roor.game.math.Rect;
import ru.roor.game.sprite.Background;
import ru.roor.game.sprite.Logo;


public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Texture lg;

    private Background background;
    private Logo logo;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        lg = new Texture("spaceship.jpg");
        background = new Background(bg);
        logo = new Logo(lg);
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        logo.draw(batch, touch);
        batch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        lg.dispose();
        super.dispose();
    }

}
