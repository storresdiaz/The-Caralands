package com.caralands.core;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.caralands.assets.Sounds;
import com.caralands.world.World;

public class ScreenGame implements Screen {

    public Core core;

    public World world;

    public ScreenGame(Core core){
        this.core = core;

        world = new World();

        Sounds.loadSounds();


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(195f/255f, 186f/255f, 105f/255f, 1f);

        world.render();
        world.update();

    }

    @Override
    public void resize(int width, int height) {
        world.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
