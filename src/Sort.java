import managers.InMemoryTaskManager;
import managers.Node;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.TaskStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Sort {
    static InMemoryTaskManager inMemoryTaskManager;
    static Scanner scanner;

    public static void main(String[] args) {
        inMemoryTaskManager = new InMemoryTaskManager();
        scanner = new Scanner(System.in);

        LocalDateTime date1 = LocalDateTime.now();
        Duration duration1 = Duration.ofMinutes(4);

        Task task1 = new Task("Task 1", "Нужно сделать", duration1, date1);
        inMemoryTaskManager.createTask(task1);

        Task task2 = new Task("Task 2", "Нужно сделать", duration1, date1.minusDays(2));
        inMemoryTaskManager.createTask(task2);

        Task task3 = new Task("Task 3", "Нужно сделать", duration1, date1.minusDays(1));
        inMemoryTaskManager.createTask(task3);

        Epic epic = new Epic("epic 1", "Нужно сделать");
        inMemoryTaskManager.createEpic(epic);



       inMemoryTaskManager.getPrioritizedTasks();
        SubTask subtask1 = new SubTask("Subtask1",
                "Написать что то", epic.getId(), duration1.plusHours(3),date1.minusDays(3));
        inMemoryTaskManager.createSubTask(subtask1);
        SubTask subtask2 = new SubTask("Subtask2",
                "Написать что то", epic.getId(), duration1,date1.minusDays(3).plusHours(1));
        inMemoryTaskManager.createSubTask(subtask2);

        SubTask subtask5 = new SubTask("Subtask5",
                "Написать что то", epic.getId(), duration1,date1.minusDays(8));
        inMemoryTaskManager.createSubTask(subtask5);

        SubTask subtask6 = new SubTask("Subtask6",
                "Написать что то", epic.getId(), duration1.plusDays(2),date1.minusDays(8));
        inMemoryTaskManager.createSubTask(subtask6);

        SubTask subtask3 = new SubTask("Subtask3",
                "Написать что то", epic.getId());
        inMemoryTaskManager.createSubTask(subtask3);
        inMemoryTaskManager.getPrioritizedTasks();

      // inMemoryTaskManager.getSubTasks();
     //   inMemoryTaskManager.getEpics();
        inMemoryTaskManager.epicStartTimeControl(epic);
        System.out.println("-----------------------------");
        inMemoryTaskManager.epicEndTimeControl(epic);

        Task task5 = new Task("Task 5", "Нужно сделать", duration1.plusDays(1), date1);
        inMemoryTaskManager.newCreateTask(task5);

        Task task6 = new Task("Task 6", "Нужно сделать", duration1, date1.minusDays(12));
        inMemoryTaskManager.newCreateTask(task6);


    }
}