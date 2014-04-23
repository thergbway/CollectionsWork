package task;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class Task {
    private static final int FRAME_POSITION_X = 600;
    private static final int FRAME_POSITION_Y = 200;
    private static int MIN_WEIGHT = 800;
    private static int MIN_HEIGHT = 500;
    private final Object readLineMonitor = new Object();
    private JFrame frame;
    private JTextArea textArea;
    private JTextField textField;

    /**
     * @param taskTitle Название задачи
     */
    public Task(String taskTitle, String taskInfo) {
        frame = new JFrame(taskTitle);
        frame.setBounds(FRAME_POSITION_X, FRAME_POSITION_Y, frame.getWidth(), frame.getHeight());
        GridBagLayout gbl = new GridBagLayout();
        frame.setLayout(gbl);

        textArea = new JTextArea();
        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (readLineMonitor) {
                    readLineMonitor.notifyAll();
                }
            }
        });

        JScrollPane jScrollPane = new JScrollPane(textArea);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbl.setConstraints(jScrollPane, gbc);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.weightx = 1;
        gbc.weighty = 0;
        gbl.setConstraints(textField, gbc);

        textField.setEnabled(false);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setText(taskInfo);

        frame.add(jScrollPane);
        frame.add(textField);

        frame.setSize(MIN_WEIGHT, MIN_HEIGHT);
        frame.setMinimumSize(new Dimension(MIN_WEIGHT, MIN_HEIGHT));

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        execute();
    }

    /**
     * Метод, выполняющий запуск задания
     */
    public abstract void execute();

    /**
     * Выводит информацию о возникшем исключении в textArea
     *
     * @param e исключение
     */
    protected void writeExceptionInfo(Exception e) {
        appendText("Exception, work will not be continued. Try to run the task again.\n");
        appendText(e.getMessage() + "\n");
        StackTraceElement[] traceElements = e.getStackTrace();
        for (StackTraceElement traceElement : traceElements) {
            appendText(traceElement + "\n");
        }
    }

    /**
     * Sets text to textArea component
     *
     * @param str text
     */
    protected void setText(String str) {
        textArea.setText(str);
    }

    /**
     * appends text to textArea component
     *
     * @param str text
     */
    protected void appendText(String str) {
        textArea.append(str);
    }

    /**
     * Чтение строки с ожиданием ввода
     *
     * @return прочитанная строка
     */
    protected String readLine() {
        textField.setEnabled(true);
        //get focus
        textField.requestFocusInWindow();

        synchronized (readLineMonitor) {
            try {
                readLineMonitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        textField.setEnabled(false);
        String result = textField.getText();
        textField.setText("");
        return result;
    }

    /**
     * Ввод числа в проверкой корректности и ожиданием ввода
     *
     * @return положительное число
     */
    protected int readInt() {
        boolean correctInput = false;
        int value = -1;
        while (!correctInput) {
            try {
                value = Integer.parseInt(readLine());
            } catch (NumberFormatException ex) {

            }
            if (value >= 1)
                correctInput = true;
            else
                appendText("Введено неправильное число, попробуйте ещё раз\n");
        }
        return value;
    }
}


