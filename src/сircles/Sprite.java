package сircles;

import java.awt.*;

public class Sprite { // олицетворяет некий двумерный объект, который будет уметь рисоваться и содержит какие-то сведения о себе
    /*
     * Мы делаем так, чтобы координаты 0 0 - это не левый верхний угол, а центр Sprite - это Х и У
     */
    public static int Count = 0;

    protected float x; // координаты по х
    protected float y; // координаты по у
    protected float halfWidth;
    protected float halfHeight;

    protected float getLeft() {
        return x - halfWidth;
    }
    protected void setLeft(float left) {
        x = left + halfWidth;
    }
    protected float getRight() {
        return x + halfWidth;
    }
    protected void setRight(float right) {
        x = right - halfWidth;
    }
    protected float getTop() {
        return y - halfHeight;
    }
    protected void setTop(float top) {
        y = top + halfHeight;
    }
    protected float getBottom() {
        return y + halfHeight;
    }
    protected void setBottom(float bottom) {
        y = bottom - halfHeight;
    }
    protected float getWidth() {
        return 2f * halfWidth;
    }
    protected float getHeight() {
        return 2f * halfHeight;
    }
    void update(GameCanvas canvas, float deltaTime) {} //Sprite умеет обновляет свои координаты
    void render(GameCanvas canvas, Graphics g) {} // Sprite умеет отрисовыватся


}

