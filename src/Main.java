/**
 * Класс, где определена точка входа в приложение
 */
public class Main {
    /**
     * Точка входа в приложение
     *
     * @param args аргументы, передаваемые при запуске
     */
    public static void main(String... args) {
        MainFrame mainFrame = new MainFrame("Java@EPAM task.Task 2");
        mainFrame.setMinimumSize(mainFrame.getPreferredSize());
        mainFrame.setSize(mainFrame.getPreferredSize());
        mainFrame.setVisible(true);
    }
}
