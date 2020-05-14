package ru.roor.game.pool;

import ru.roor.game.base.SpritePool;
import ru.roor.game.sprite.Bullet;

public class BulletPool extends SpritePool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
