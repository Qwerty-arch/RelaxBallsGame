package сircles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainCircles extends JFrame { //наше основное окошко, которое будет запускать прогу...окно-> extends JFrame

    /*
     * Константы - размеры нашего окошка
     */
    private int startCount = 10;
    private static final int WINDOW_WIDTH = 800; // ширина
    private static final int WINDOW_HEIGHT = 600; // высота

    Sprite[] sprites = new Sprite[1000]; // у нас будет массив Sprite, 10 мячиков у нас будет

    public static void main(String[] args) {
        /*
         * Запускаем наше окно (Удобный и правильный способ)
         */
        SwingUtilities.invokeLater(new Runnable() { //SwingUtilities - класс, метод invokeLater принимает на вход Runnable
            @Override //наш поток
            public void run() {
                new MainCircles(); //создаем наше новое окошко
            }
        });
    }

    private MainCircles() { // конструктор
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width/2 - 400, dimension.height/2 - 300, WINDOW_WIDTH, WINDOW_HEIGHT);


        initApplication(); // инициализирующий игру, создаем шарики


        GameCanvas canvas = new GameCanvas(this); //положили нашу canvas т.к. чтобы вызвать метод onDrawFrame у canvas. По факту передали в canvas MainCircles(ссылку на него), чтобы канвас в последствии мог вызывать метод onDrawFrame
        add(canvas, BorderLayout.CENTER); // положили нашу панельку-canvas и указали менеджер размещения

        int delay = 1500;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BackGround backGround = new BackGround();
                canvas.setBackground(backGround.ColorTime());
            }
        };

        new Timer(delay, taskPerformer).start();

        JButton jButton = new JButton("New Ball");
        JButton jButton2 = new JButton("Delete Ball");

        canvas.add(jButton);
        canvas.add(jButton2);

        add(jButton, BorderLayout.NORTH);
        add(jButton2, BorderLayout.SOUTH);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sprites[Sprite.Count] = new Ball();
                Sprite.Count++;


            }
        });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sprites[Sprite.Count] = new Ball();
                Sprite.Count--;
                if (Sprite.Count == 0) {
                    JOptionPane.showMessageDialog(canvas, "We're out of Balls, what a pity", "Dangerous !!!", JOptionPane.ERROR_MESSAGE);
                }
                if (Sprite.Count < 0) {
                    JOptionPane.showMessageDialog(canvas, "Good Bye", "Noooooooooooooo", JOptionPane.OK_OPTION);
                }
            }
        });


        setTitle("Circles");
        setVisible(true);
    }

    private void initApplication() { // метод, инициализирующий игру
        for (int i = 0; i < startCount; i++) { // идём циклом по массиву Sprite и заполняем мячиками
            sprites[i] = new Ball();
            Sprite.Count++;
        }
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) { // "поФактуОтрисовкиФрейма" frame=canvas, что должно происходить -> нам нужна canvas(чтобы знать, какая canvas отрисовалась) в аргументах и объект графики(чтобы смочь по факту отрисовки frame на этой canvas что-то рисовать) deltaTime(сколько времени прошло с предыдущей отрисовки frame, чтобы реагировать если n растояние, то прошло столько-то времени и тд). Нам нужно из canvas этот метод вызвать
        update(canvas, deltaTime); // obnovlenie(сместить) // S = v * t // нам надо понять, какая canvas обновилась и сколько времени прошло
        render(canvas, g); // otrisovka // надо понять, на какой canvas рисовать и объект графики(то, чем рисовать)
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < Sprite.Count; i++) {
            sprites[i].update(canvas, deltaTime); // берем все Sprite и говорим, что пришло время update
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < Sprite.Count; i++) {
            sprites[i].render(canvas, g); // Все Sprite должны отрисоватся
        }
    }

}


