package com.caralands.core;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class Core extends Game {

	public Screen screenGame;

	@Override
	public void create () {
		screenGame = new ScreenGame(this);
		this.setScreen(screenGame);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		super.render();

		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}

	}
	
	@Override
	public void dispose () {

	}
}
