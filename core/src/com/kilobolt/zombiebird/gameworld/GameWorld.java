package com.kilobolt.zombiebird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.kilobolt.zombiebird.gameobjects.Bird;
import com.kilobolt.zombiebird.zbhelpers.AssetLoader;
import com.kilobolt.zombiebird.zbhelpers.ScrollHandler;

public class GameWorld {

    private Circle rect = new Circle(0, 50, 17);
    private Bird bird;
    private ScrollHandler scroller;

    private Rectangle ground;

    private int score = 0;

//    private boolean isAlive = true;

    public GameWorld(int midPointY) {
        this.bird = new Bird(33, midPointY - 5, 17, 12);
        this.scroller = new ScrollHandler(this, midPointY + 66);
        this.ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta) {
        Gdx.app.log("GameWorld", "update");
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
        }

        rect.x++;
        if (rect.x > 137) {
            rect.x = 0;
        }
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
}
