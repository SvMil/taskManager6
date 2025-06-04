import managers.InMemoryTaskManager;
import tasks.*;

import java.util.Scanner;

public class Main {
    static InMemoryTaskManager inMemoryTaskManager;
    static Scanner scanner;

    public static void main(String[] args) {
        inMemoryTaskManager = new InMemoryTaskManager();
        scanner = new Scanner(System.in);

        //TaskManager taskManager = new TaskManager();


        Epic epic1 = new Epic("Эпик 1","Нужно сделать");
        inMemoryTaskManager.createEpic(epic1);


        SubTask subtask1 = new SubTask("Subtask1 создания ",
                "Написать что то ", epic1.getId());
        inMemoryTaskManager.createSubTask(subtask1);
        SubTask subtask2 = new SubTask("Subtask2 создания ",
                "Написать что то ", epic1.getId());
        inMemoryTaskManager.createSubTask(subtask2);


        System.out.println(epic1);

        subtask1.setStatus(TaskStatus.IN_PROGRESS);
        inMemoryTaskManager.updateSubTask(subtask1);


        System.out.println(epic1);


        subtask2.setStatus(TaskStatus.DONE);
        inMemoryTaskManager.updateSubTask(subtask2);


        System.out.println(epic1);
        subtask1.setStatus(TaskStatus.DONE);
        inMemoryTaskManager.updateSubTask(subtask1);


        System.out.println(epic1);
    }

    }