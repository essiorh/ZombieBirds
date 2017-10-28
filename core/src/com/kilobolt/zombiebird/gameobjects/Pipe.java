package com.kilobolt.zombiebird.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Pipe extends Scrollable {

    public static final int VERTICAL_GAP = 45;
    public static final int SKULL_WIDTH = 24;
    public static final int SKULL_HEIGHT = 11;

    private Random r;

    private float groundY;

    private Rectangle skullUp, skullDown, barUp, barDown;

    private  boolean isScored;

    // Когда констуктор Pipe вызван – вызовите конструтор родителя (Scrollable)
    public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);
        // Иницилизируйте объект типа Random для генерации случайных чисел
        this.r = new Random();

        this.skullUp = new Rectangle();
        this.skullDown = new Rectangle();
        this.barUp = new Rectangle();
        this.barDown = new Rectangle();
        this.groundY = groundY;
    }

    @Override
    public void reset(float newX) {
        // вызовите reset метод в родительском классе (Scrollable)
        super.reset(newX);
        // Измените высоту на случайное значение
        height = r.nextInt(90) + 15;
        isScored = false;
    }

    @Override
    public void update(float delta) {
        // вызываем update метод в родительском классе (Scrollable)
        super.update(delta);

        // Метод set() позволяет выставить координаты верзнего лего угла - x, y
        // вместе с width и height прямоугольника

        barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
                groundY - (position.y + height + VERTICAL_GAP));

        // Ширина черепа 24 пикселя. Ширина трубы всего 22 пикселя. Так что череп
        // должен быть смещен на 1 пиксель влево (так что череп будет отцентрирован
        // относительно трубы).

        // Смещение равнозначно: (SKULL_WIDTH - width) / 2
        skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height
                - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y,
                SKULL_WIDTH, SKULL_HEIGHT);

    }

    public Rectangle getSkullUp() {
        return skullUp;
    }

    public Rectangle getSkullDown() {
        return skullDown;
    }

    public Rectangle getBarUp() {
        return barUp;
    }

    public Rectangle getBarDown() {
        return barDown;
    }


    public boolean collides(Bird bird) {
        if (position.x < bird.getX() + bird.getWidth()) {
            return (Intersector.overlaps(bird.getBoundingCircle(), barUp)
                    || Intersector.overlaps(bird.getBoundingCircle(), barDown)
                    || Intersector.overlaps(bird.getBoundingCircle(), skullUp) || Intersector
                    .overlaps(bird.getBoundingCircle(), skullDown));
        }
        return false;
    }

    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean scored) {
        isScored = scored;
    }

    public void onRestart(float x, float scrollSpeed) {
        velocity.x = scrollSpeed;
        reset(x);
    }
}