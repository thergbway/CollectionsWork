import task.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс - графическое окно, в котором находятся кнопки для запуска заданий
 */
class MainFrame extends JFrame {
    private static final int FRAME_POSITION_X = 200;
    private static final int FRAME_POSITION_Y = 200;
    private static final String[] TASK_NAMES = new String[]{
            "1 Сохранение строк из файла в другой файл в обратном порядке",
            "2 Отображение элементов каталога и его подкаталогов",
            "3 Сортировка строк стихотворения",
            "4 Определение пересечения и объединения множеств",
            "5 Сопротивление методом наименьших квадратов",
            "6 Попарное суммирование произвольного ряда чисел",
            "7 Сложение многочленов",
            "8 Умножение многочленов",
            "9 Правильность расстановки скобок",
            "10 Выделить различные слова в английском тексте",
            "11 Частота встречаемости слов в тексте",
            "12 Вычеркивание каждого второго человека",
            "13 Упорядочить числа относительно указанного числа",
            "14 Неориентированный граф",
            "15 Структура для хранения чисел",
            "16 Два списка из двух наборов чисел",
            "17 Прямые, проходящие через заданные точки",
            "18 Клетки, полностью лежащие в круге",
            "19 Точка пересечения отрезков с мин. абсциссой",
            "20 Поиск подматрицы из одинаковых элемиентов",
            "21 Структура \"черный ящик\"",
            "22 Нахождение количеств обгонов на трассе",
            "23 Первые К обгонов на трассе",
    };

    /**
     * @param s frame title
     */
    public MainFrame(String s) {
        super(s);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(FRAME_POSITION_X, FRAME_POSITION_Y, getWidth(), getHeight());

        setLayout(new GridLayout(0, 1));
        JButton[] startTaskButtons = new JButton[Task.values().length];

        int i = 0;
        for (Task t : Task.values()) {
            startTaskButtons[i] = new JButton(TASK_NAMES[i]);
            startTaskButtons[i].addActionListener(getActionListenerByTaskIndex(t));
            add(startTaskButtons[i]);
            ++i;
        }

    }

    /**
     * Функция, возвращающая объект - слушатель кнопок в соответствии с заданием
     *
     * @param task номер задания
     * @return соответствующий слушатель кнопки
     */
    private ActionListener getActionListenerByTaskIndex(Task task) {
        switch (task) {
            case TASK1:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task1();
                    }
                };
            case TASK2:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task2();
                    }
                };
            case TASK3:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task3();
                    }
                };
            case TASK4:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task4();
                    }
                };
            case TASK5:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task5();
                    }
                };
            case TASK6:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task6();
                    }
                };
            case TASK7:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task7();
                    }
                };
            case TASK8:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task8();
                    }
                };
            case TASK9:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task9();
                    }
                };
            case TASK10:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task10();
                    }
                };
            case TASK11:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task11();
                    }
                };
            case TASK12:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task12();
                    }
                };
            case TASK13:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task13();
                    }
                };
            case TASK14:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task14();
                    }
                };
            case TASK15:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task15();
                    }
                };
            case TASK16:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task16();
                    }
                };
            case TASK17:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task17();
                    }
                };
            case TASK18:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task18();
                    }
                };
            case TASK19:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task19();
                    }
                };
            case TASK20:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task20();
                    }
                };
            case TASK21:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task21();
                    }
                };
            case TASK22:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task22();
                    }
                };
            case TASK23:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Task23();
                    }
                };
            default:
                throw new IllegalArgumentException();
        }
    }

    private enum Task {
        TASK1, TASK2, TASK3, TASK4, TASK5, TASK6, TASK7, TASK8, TASK9, TASK10,
        TASK11, TASK12, TASK13, TASK14, TASK15, TASK16, TASK17, TASK18, TASK19, TASK20,
        TASK21, TASK22, TASK23
    }
}
