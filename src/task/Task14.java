package task;

import sun.util.resources.LocaleNames_ja;

import java.util.Random;

public class Task14 extends Task {
    private static final String TASK_INFO = "Реализовать класс Graph, представляющий собой неориентированный граф. " +
            "В конструкторе класса передается количество вершин в графе. " +
            "Методы должны поддерживать быстрое добавление и удаление ребер.\n\n";

    public Task14() {
        super("#14", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Пожалуйста, введите количество точек графа > 0\n");
                int graphSize = readInt();
                appendText("Введено " + graphSize + "\n");

                Graph graph = new Graph(graphSize);
                graph.randomize();
                
                appendText("Матрица смежности сгенерированного графа: \n");
                appendText(graph.toString());

                appendText("\nДобавление ребра:\n");
                appendText("Введите номер первой точки:\n");

                boolean isFirstTime = true;
                int indexOfFirstPoint = -1;
                while(indexOfFirstPoint < 0 || indexOfFirstPoint >= graphSize){
                    if(!isFirstTime)
                        appendText("Введено неправильное число, попробуйте ещё раз\n");
                    indexOfFirstPoint = readInt() - 1;
                    isFirstTime = false;
                }

                appendText("Введите номер второй точки:\n");

                isFirstTime = true;
                int indexOfSecondPoint = -1;
                while(indexOfSecondPoint < 0 || indexOfSecondPoint >= graphSize){
                    if(!isFirstTime)
                        appendText("Введено неправильное число, попробуйте ещё раз\n");
                    indexOfSecondPoint = readInt() - 1;
                    isFirstTime = false;
                }

                //add edge
                boolean isAdded = graph.addEdge(indexOfFirstPoint, indexOfSecondPoint);
                if (isAdded) {
                    appendText("После добавления ребра: \n");
                    appendText(graph.toString());
                }
                else
                    appendText("Добавление не было произведено: ребро уже присутствует в графе\n");


                appendText("\nУдаление ребра:\n");
                appendText("Введите номер первой точки:\n");

                isFirstTime = true;
                indexOfFirstPoint = -1;
                while(indexOfFirstPoint < 0 || indexOfFirstPoint >= graphSize){
                    if(!isFirstTime)
                        appendText("Введено неправильное число, попробуйте ещё раз\n");
                    indexOfFirstPoint = readInt() - 1;
                    isFirstTime = false;
                }

                appendText("Введите номер второй точки:\n");

                isFirstTime = true;
                indexOfSecondPoint = -1;
                while(indexOfSecondPoint < 0 || indexOfSecondPoint >= graphSize){
                    if(!isFirstTime)
                        appendText("Введено неправильное число, попробуйте ещё раз\n");
                    indexOfSecondPoint = readInt() - 1;
                    isFirstTime = false;
                }

                //add edge
                isAdded = graph.removeEdge(indexOfFirstPoint, indexOfSecondPoint);
                if (isAdded) {
                    appendText("После удаления ребра: \n");
                    appendText(graph.toString());
                }
                else
                    appendText("Удаление не было произведено: ребро уже присутствует в графе\n");
            }
        }).start();
    }
}

class Graph{
    private final int size;
    private boolean[][] adjacencyMatrix;//матрица смежности
    private static final double PERCENTAGE_OF_RANDOM_EDGES = 0.4;//Рандомизация добавляет 40% от возможного числа ребер

    public Graph(int size){
        if(size <= 0)
            throw new IllegalArgumentException("Size of Graph <= 0");

        this.size = size;
        adjacencyMatrix = new boolean[size][size];
    }

    public boolean addEdge(int indexOfFirstPoint, int indexOfSecondPoint){
        if(indexOfFirstPoint < 0 || indexOfFirstPoint >= size
                ||indexOfSecondPoint < 0 || indexOfSecondPoint >= size)
            throw new IllegalArgumentException("Illegal point coordinate");

        if(adjacencyMatrix[indexOfFirstPoint][indexOfSecondPoint])
            return false;
        else{
            adjacencyMatrix[indexOfFirstPoint][indexOfSecondPoint] = true;
            adjacencyMatrix[indexOfSecondPoint][indexOfFirstPoint] = true;
        }
        return true;
    }

    public boolean removeEdge(int indexOfFirstPoint, int indexOfSecondPoint){
        if(indexOfFirstPoint < 0 || indexOfFirstPoint >= size
                ||indexOfSecondPoint < 0 || indexOfSecondPoint >= size)
            throw new IllegalArgumentException("Illegal point coordinate");

        if(!adjacencyMatrix[indexOfFirstPoint][indexOfSecondPoint])
            return false;
        else{
            adjacencyMatrix[indexOfFirstPoint][indexOfSecondPoint] = false;
            adjacencyMatrix[indexOfSecondPoint][indexOfFirstPoint] = false;
        }
        return true;
    }

    public void randomize(){
        int edgesToCreate = (int)((size*size - size)/2 * PERCENTAGE_OF_RANDOM_EDGES);
        Random r = new Random();

        for (int i = 0; i < edgesToCreate; i++) {
            int indexOfFirstPoint = r.nextInt(size);
            int indexOfSecondPoint = r.nextInt(size);

            addEdge(indexOfFirstPoint, indexOfSecondPoint);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < size + 1 + 1; i++) {
            if(i == 1){
                sb.append("  X ");
                continue;
            }

            sb.append(String.format("%3d", i - 1) + " ");
        }
        sb.append("\n");

        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size + 1 + 1; j++) {
                if(j == 1) {
                    sb.append(String.format("%3d", i + 1) + " ");
                    continue;
                }
                if(adjacencyMatrix[i][j-1-1])
                    sb.append(String.format("%3d", 1) + " ");
                else
                    sb.append(String.format("%3d", 0) + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}