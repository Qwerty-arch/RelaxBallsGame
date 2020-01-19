package сircles;

import javax.swing.*;
import java.awt.*;


public class GameCanvas extends JPanel { // панельку, чтобы на ней рисовать

    MainCircles gameController; // сохраняем MainCircles, принимаемый из конструктора ниже
    long lastFrameTime; //предыдущее время(время последнего обновления)


    GameCanvas(MainCircles gameController) { // принимаем MainCircles для "GameCanvas canvas = new GameCanvas(this);"
        this.gameController = gameController;
        lastFrameTime = System.nanoTime();
    }

    @Override
    protected void paintComponent(Graphics g) { // каждый раз, когда у нас будет добавлятся компонет, мы сюда будем добавлять какое-то свое поведение
        // конва должна только показывать, сама она даже не должна знать, что на ней рисуют
        super.paintComponent(g);

        long currentTime = System.nanoTime(); // текущее время, а предыдущее время выше + в конструкторе; System.nanoTime() возвращает нам long, там сколько-то нано секунд, в штуках
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f; //задали deltaTime (текущее время - предыдущее время). 0.000000001f - позволяет наносекунды привести к секундам
        gameController.onDrawFrame(this, g, deltaTime); //вызываем onDrawFrame(ООП), передали Canvas(this), объект графики и deltaTime нужна, которую мы сформировали 2 строчками выше
        lastFrameTime = currentTime; // обновляем предыдущее время, меня его на текущее время
        try {
            Thread.sleep(16); //60fps - плавно и красиво, чтобы было на поспать 16,666...
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        repaint(); // наша Canvas должна постоянно обновлятся -> бесконечный цикл, чтобы было комфорт. глазу мы сделали выше 60 перерисовок в сек(60 FPS)(делаем с помощью sleep)
    }

    /*
     * getter для размеров нашей GameCanvas
     */

    public int getLeft() { return 0; }  // левая граница GameCanvas
    public int getRight() { return getWidth() - 1; } // правая граница GameCanvas (ширина - 1)
    public int getTop() { return 0; } // верхняя граница = 0 по Y GameCanvas
    public int getBottom() { return getHeight() - 1; } // Нижняя граница GameCanvas (высота - 1)
}

