package com.kilobolt.zombiebird.zbhelpers;

import com.badlogic.gdx.InputProcessor;
import com.kilobolt.zombiebird.gameobjects.Bird;
import com.kilobolt.zombiebird.gameworld.GameWorld;

public class InputHandler implements InputProcessor {

    private GameWorld world;
    private Bird bird;

    public InputHandler(GameWorld world) {
        this.bird = world.getBird();
        this.world = world;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (world.isReady()) {
            world.start();
        }

        bird.onClick();

        if (world.isGameOver()) {
            // Обнулим все перменные, перейдем в GameState.READ
            world.restart();
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
