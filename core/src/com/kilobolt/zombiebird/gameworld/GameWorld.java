package com.kilobolt.zombiebird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.kilobolt.zombiebird.gameobjects.Bird;
import com.kilobolt.zombiebird.zbhelpers.AssetLoader;
import com.kilobolt.zombiebird.zbhelpers.ScrollHandler;

public class GameWorld {

    private Circle rect = new Circle(0, 50, 17);
    private Bird bird;
    private ScrollHandler scroller;

    private boolean isAlive = true;

    public GameWorld(int midPointY) {
        this.bird = new Bird(33, midPointY - 5, 17, 12);
        this.scroller = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta) {
        Gdx.app.log("GameWorld", "update");
        bird.update(delta);
        scroller.update(delta);
        if (isAlive && scroller.collides(bird)) {
            // Clean up on game over
            scroller.stop();
            AssetLoader.dead.play();
            isAlive = false;
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
}
