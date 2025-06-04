import managers.InMemoryTaskManager;

import managers.Node;
import tasks.*;

import java.util.Map;
import java.util.Scanner;

public class Main {
    static InMemoryTaskManager inMemoryTaskManager;
    static Scanner scanner;

    public static void main(String[] args) {
        inMemoryTaskManager = new InMemoryTaskManager();
        scanner = new Scanner(System.in);

        Epic epic1 = new Epic("Эпик 1", "Нужно сделать");
        inMemoryTaskManager.createEpic(epic1);

        SubTask subtask1 = new SubTask("Subtask1 создания ",
                "Написать что то ", epic1.getId());
        inMemoryTaskManager.createSubTask(subtask1);
        SubTask subtask2 = new SubTask("Subtask2 создания ",
                "Написать что то ", epic1.getId());
        inMemoryTaskManager.createSubTask(subtask2);
        System.out.println(epic1);

        Epic epic2 = new Epic("Эпик 2", "Нужно сделать");
        inMemoryTaskManager.createEpic(epic2);


        subtask1.setStatus(TaskStatus.IN_PROGRESS);
        inMemoryTaskManager.updateSubTask(subtask1);
        System.out.println(epic1);


        subtask2.setStatus(TaskStatus.DONE);
        inMemoryTaskManager.updateSubTask(subtask2);

        System.out.println(epic1);
        subtask1.setStatus(TaskStatus.DONE);
        inMemoryTaskManager.updateSubTask(subtask1);
        System.out.println(epic1);

        inMemoryTaskManager.getAnyTaskById(1);
        inMemoryTaskManager.getAnyTaskById(2);
        inMemoryTaskManager.getAnyTaskById(3);
        inMemoryTaskManager.getAnyTaskById(1);
        inMemoryTaskManager.getAnyTaskById(1);
        inMemoryTaskManager.getAnyTaskById(2);
        inMemoryTaskManager.getAnyTaskById(4);
        inMemoryTaskManager.getAnyTaskById(2);

        System.out.println("Получить историю просмотров");

        Map<Integer, Node<Task>> fastHistoryList = inMemoryTaskManager.inMemoryHistoryManager.getHistory();
        for (Integer key : fastHistoryList.keySet()) {
            Node<Task> value = fastHistoryList.get(key);
            System.out.println("Id: " + key + ", TaskInfo: " + value.data.toString());
        }
        System.out.println("Удалить эпик 1");
        inMemoryTaskManager.removeTask(epic1.getId());

        System.out.println("Повторно вывести историю просмотров");
        fastHistoryList = inMemoryTaskManager.inMemoryHistoryManager.getHistory();
        for (Integer key : fastHistoryList.keySet()) {
            Node<Task> value = fastHistoryList.get(key);
            System.out.println("Id: " + key + ", TaskInfo: " + value.data.toString());
        }
    }
}