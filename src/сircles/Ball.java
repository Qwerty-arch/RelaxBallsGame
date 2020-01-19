package сircles;

import java.awt.*;

public class Ball extends Sprite { //наш мячик
    private final Color color = new Color (
            /*
             * Red/Green/Blue
             */
            (int)(Math.random() * 255),
            (int)(Math.random() * 255),
            (int)(Math.random() * 255)
    );
    private float vX = (float)(100f + (Math.random() * 200f)); // скорость по Х
    private float vY = (float)(100f + (Math.random() * 200f)); // скорость по У мячика

    Ball() { // конструктор, который будет создавать наш мячик, задаем размеры
        halfHeight = 20 + (float)(Math.random() * 50f); // высота
        halfWidth = halfHeight;
    }

    @Override
    void update(GameCanvas canvas, float deltaTime) {
        x += vX * deltaTime; // координата по Х должна прибавить скорость по Х * deltaTime
        y += vY * deltaTime; // координата по Y должна прибавить скорость по Y * deltaTime
        if (getLeft() < canvas.getLeft()) { // если текущая левая граница < канвовской getLeft
            setLeft(canvas.getLeft()); // то устанавливаем свою левую границу в canvas левую границу
            vX = -vX; // меняем скорость по Х
        }
        if (getRight() > canvas.getRight()) { // если текущая правая больше ...
            setRight(canvas.getRight());
            vX = -vX;
        }
        if (getTop() < canvas.getTop()) { // если верхняя меньше
            setTop(canvas.getTop());
            vY = -vY;
        }
        if (getBottom() > canvas.getBottom()) { // если нижняя больше
            setBottom(canvas.getBottom());
            vY = -vY;
        }
    }

    @Override
    void render(GameCanvas canvas, Graphics g) {
        g.setColor(color); // объект графики устанавливает наш цвет (рандомный)
        g.fillOval((int) getLeft(), (int) getTop(), // объект графики рисует овал, заполненный по координатам ...
                (int) getWidth(), (int) getHeight());
    }
}


