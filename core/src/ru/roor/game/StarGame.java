package ru.roor.game;

import com.badlogic.gdx.Game;

import ru.roor.game.screen.MenuScreen;

public class StarGame extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}
}
