package tests;

import managers.InMemoryHistoryManager;
import managers.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class UnitTaskDurationTests {
    static InMemoryTaskManager inMemoryTaskManager;

    LocalDateTime date1;
    Duration duration1;


    @BeforeEach
    public void setClassObjects() {
        inMemoryTaskManager = new InMemoryTaskManager();
        duration1 = Duration.ofMinutes(4);
        date1 = LocalDateTime.now();
    }

    @Test
    void checkGetPrioritizedTasks() {
        Task task1 = new Task("Task 1", "Нужно сделать", duration1, date1);
        inMemoryTaskManager.createTask(task1);

        Task task2 = new Task("Task 2", "Нужно сделать", duration1, date1.minusDays(2));
        inMemoryTaskManager.createTask(task2);

        Task task3 = new Task("Task 3", "Нужно сделать", duration1, date1.minusDays(1));
        inMemoryTaskManager.createTask(task3);

        Task task4 = new Task("Task 4", "Нужно сделать");
        inMemoryTaskManager.createTask(task4);

        Task task5 = new Task("Task 5", "Нужно сделать", duration1, date1.minusDays(3));
        inMemoryTaskManager.createTask(task5);
        List<Task> sortTasks = inMemoryTaskManager.getPrioritizedTasks();

        for (Task task : sortTasks) {
            task.toString();
        }

        assertEquals(sortTasks.get(sortTasks.size() - 1).getName(), task1.getName());
        assertEquals(sortTasks.get(0).getName(), task5.getName());
        assertEquals(sortTasks.size(), 4);
    }

    @Test
    void checkEpicDuration() {
        Epic epic = new Epic("epic 1", "Нужно сделать");
        inMemoryTaskManager.createEpic(epic);
        SubTask subtask1 = new SubTask("Subtask1",
                "Написать что то", epic.getId(), duration1.plusMinutes(3), date1.minusDays(3));
        inMemoryTaskManager.createSubTask(subtask1);
        SubTask subtask2 = new SubTask("Subtask2",
                "Написать что то", epic.getId(), duration1, date1.minusMinutes(1).plusHours(1));
        inMemoryTaskManager.createSubTask(subtask2);

        SubTask subtask5 = new SubTask("Subtask5",
                "Написать что то", epic.getId(), duration1, date1.minusDays(9));
        inMemoryTaskManager.createSubTask(subtask5);

        SubTask subtask6 = new SubTask("Subtask6",
                "Написать что то", epic.getId(), duration1.plusMinutes(2), date1.minusDays(8));
        inMemoryTaskManager.createSubTask(subtask6);

        SubTask subtask3 = new SubTask("Subtask3",
                "Написать что то", epic.getId());
        inMemoryTaskManager.createSubTask(subtask3);
        Duration allSubTaskDuration = Duration.ofMinutes(subtask1.getTaskDuration().toMinutes() + subtask2.getTaskDuration().toMinutes() +
                subtask3.getTaskDuration().toMinutes() + subtask5.getTaskDuration().toMinutes() + subtask6.getTaskDuration().toMinutes());
        assertEquals(epic.getTaskDuration().toMinutes(),allSubTaskDuration.toMinutes());
        assertEquals(epic.getStartTime(),subtask5.getStartTime());
        assertEquals(epic.getEndTime(),subtask2.getStartTime().plusMinutes(subtask2.getTaskDuration().toMinutes()));
    }

    @Test
    void checkCrossDate() {
        Task task1 = new Task("Task 1", "Нужно сделать", duration1, date1);
        inMemoryTaskManager.createTask(task1);

        Task task2 = new Task("Task 2", "Нужно сделать", duration1, date1.minusDays(2));
        inMemoryTaskManager.createTask(task2);

        Task task3 = new Task("Task 3", "Нужно сделать", duration1, date1.minusDays(1));
        inMemoryTaskManager.createTask(task3);

        Task task4 = new Task("Task 4", "Нужно сделать");
        inMemoryTaskManager.createTask(task4);

        Task task5 = new Task("Task 5", "Нужно сделать", duration1, date1.minusDays(3));
        inMemoryTaskManager.createTask(task5);

        Epic epic = new Epic("epic 1", "Нужно сделать");
        inMemoryTaskManager.createEpic(epic);
        SubTask subtask1 = new SubTask("Subtask1",
                "Написать что то", epic.getId(), duration1.plusMinutes(3), date1.minusDays(3));
        inMemoryTaskManager.createSubTask(subtask1);
        SubTask subtask2 = new SubTask("Subtask2",
                "Написать что то", epic.getId(), duration1, date1.minusMinutes(1).plusHours(1));
        inMemoryTaskManager.createSubTask(subtask2);

        SubTask subtask5 = new SubTask("Subtask5",
                "Написать что то", epic.getId(), duration1, date1.minusDays(9));
        inMemoryTaskManager.createSubTask(subtask5);

        SubTask subtask6 = new SubTask("Subtask6",
                "Написать что то", epic.getId(), duration1.plusMinutes(2), date1.minusDays(8));
        inMemoryTaskManager.createSubTask(subtask6);

        SubTask subtask3 = new SubTask("Subtask3",
                "Написать что то", epic.getId());
        inMemoryTaskManager.createSubTask(subtask3);

        List<Task> sortTasks = inMemoryTaskManager.getPrioritizedTasks();

        for (Task task : sortTasks) {
            System.out.println(task.toString());
        }
        assertEquals(sortTasks.size(),7);
    }

}