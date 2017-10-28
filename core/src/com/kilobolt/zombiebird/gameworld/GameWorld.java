package com.kilobolt.zombiebird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.kilobolt.zombiebird.gameobjects.Bird;
import com.kilobolt.zombiebird.zbhelpers.AssetLoader;
import com.kilobolt.zombiebird.zbhelpers.ScrollHandler;

public class GameWorld {

    private GameState currentState;

    private Circle rect = new Circle(0, 50, 17);
    private Bird bird;
    private ScrollHandler scroller;

    private Rectangle ground;

    private int score = 0;

    private int midPointY;

//    private boolean isAlive = true;

    public GameWorld(int midPointY) {
        this.midPointY = midPointY;
        this.currentState = GameState.READY;

        this.bird = new Bird(33, midPointY - 5, 17, 12);
        this.scroller = new ScrollHandler(this, midPointY + 66);
        this.ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta) {

        switch (currentState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;

            default:
                break;
        }

    }

    private void updateReady(float delta) {
        // Пока что ничего не делаем
    }

    public void updateRunning(float delta) {
        Gdx.app.log("GameWorld", "updateRunning");
        // Добавим лимит для нашей delta, так что если игра начнет тормозить
        // при обновлении, мы не нарушим нашу логику определения колизии

        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scroller.update(delta);
        if (/*isAlive*/bird.isAlive() && scroller.collides(bird)) {
            // Clean up on game over
            scroller.stop();
            bird.die();
            AssetLoader.dead.play();
//            isAlive = false;
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;

            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }

        rect.x++;
        if (rect.x > 137) {
            rect.x = 0;
        }
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public Circle getRect() {
        return rect;
    }

    public Bird getBird() {
        return bird;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }
}
