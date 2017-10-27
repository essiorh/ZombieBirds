package com.kilobolt.zombiebird.gameobjects;

public class Grass extends Scrollable {

    // Когда констуктор Grass вызван – вызовите конструтор родителя (Scrollable)
    public Grass(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);

    }

}