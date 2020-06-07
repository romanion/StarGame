package ru.roor.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.roor.game.base.ScaledButton;
import ru.roor.game.math.Rect;
import ru.roor.game.screen.GameScreen;

public class ButtonNewGame extends ScaledButton {

    private static final float ANIMATE_INTERVAL = 1f;

    private float animateTimer;
    private boolean scaleUp = true;
    private GameScreen gameScreen;

    public ButtonNewGame(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void update(float delta) {
        animateTimer += delta;
        if (animateTimer >= ANIMATE_INTERVAL) {
            animateTimer = 0f;
            scaleUp = !scaleUp;
        }
        if (scaleUp) {
            setScale(getScale() + 0.003f);
        } else {
            setScale(getScale() - 0.003f);
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.05f);
    }

    @Override
    public void action() {
        gameScreen.startNewGame();
    }
}
