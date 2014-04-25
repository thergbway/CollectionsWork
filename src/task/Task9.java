package task;

import java.util.Stack;

public class Task9 extends Task {
    private static final String TASK_INFO = "Задана строка, содержащая символы '(', ')', '[', ']', '{', '}'. Проверить правильность расстановки скобок. Использовать стек.\n\n";

    public Task9() {
        super("#9", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Пожалуйста, введите строку\n");
                String str = readLine();
                appendText("Введено: " + str + "\n");

                boolean isInputCorrect = true;
                char[] chars = str.toCharArray();
                Stack<Character> openingBrackets = new Stack<Character>();
                for (int i = 0; i < chars.length; i++) {
                    char currChar = chars[i];
                    if(currChar == '(' || currChar == '[' || currChar == '{')
                        openingBrackets.push(currChar);
                    if(currChar == ')' || currChar == ']' || currChar == '}'){
                        Character openingLiteral = openingBrackets.pop();
                        Character closingLiteral = null;
                        if(openingLiteral == '(')
                            closingLiteral = ')';
                        if(openingLiteral == '[')
                            closingLiteral = ']';
                        if(openingLiteral == '{')
                            closingLiteral = '}';

                        if(closingLiteral != currChar){
                            isInputCorrect = false;
                            break;
                        }
                    }
                }

                if(isInputCorrect)
                    appendText("Скобки расставлены правильно");
                else
                    appendText("Скобки расставлены неправильно");
            }
        }).start();
    }
}