package ru.roor.game.pool;

import ru.roor.game.base.SpritesPool;
import ru.roor.game.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
