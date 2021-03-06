package com.kilobolt.zombiebird.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.kilobolt.zombiebird.gameworld.GameRenderer;
import com.kilobolt.zombiebird.gameworld.GameWorld;
import com.kilobolt.zombiebird.zbhelpers.InputHandler;

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;

    private float runTime = 0;

    public GameScreen() {
        Gdx.app.log("GameScreen", "Attached");
        float screenWidth = Gdx.graphics.getWidth();//400
        float screenHeight = Gdx.graphics.getHeight();//800
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world, (int) gameHeight, midPointY);

        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void render(float delta) {
        Gdx.app.log("GameScreen FPS", (1/delta) + "");
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);

    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
