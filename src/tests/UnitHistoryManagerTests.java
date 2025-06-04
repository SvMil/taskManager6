package tests;

import managers.InMemoryHistoryManager;
import managers.InMemoryTaskManager;
import managers.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class UnitHistoryManagerTests {

    static InMemoryTaskManager taskManager;
    static InMemoryHistoryManager historyManager;


    @BeforeEach
    public void setClassObjects(){
        taskManager=new InMemoryTaskManager();
        historyManager = new InMemoryHistoryManager();
    }



    @Test
    void addTaskToHistory() {
        Task task = new Task("Test2", "Test2 addNewTask description");
        Map<Integer, Node<Task>> history = new LinkedHashMap<>();
        assertEquals(0, history.size(), "История пустая.");
        historyManager.addTaskToHistory(task);
        history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }

    @Test
    void checkDoubleHistory() {
        Task task1 = new Task("task1", "task1 description1");
        taskManager.createTask(task1);
        Task task2 = new Task("task2", "task2 description2");
        taskManager.createTask(task2);
        Task task3 = new Task("task3", "task3 description3");
        taskManager.createTask(task3);
        Task task4 = new Task("task4", "task4 description4");
        taskManager.createTask(task4);
        historyManager.addTaskToHistory(task1);
        historyManager.addTaskToHistory(task2);
        historyManager.addTaskToHistory(task1);
        assertEquals(historyManager.getHistorySize(), 2, " 2 столько элементов должно быть в списке просмотров без повторов.");
    }

    @Test
    void checkRemoveTaskFromHistory() {
        Task task1 = new Task("task1", "task1 description1");
        taskManager.createTask(task1);
        Task task2 = new Task("task2", "task2 description2");
        taskManager.createTask(task2);
        Task task3 = new Task("task3", "task3 description3");
        taskManager.createTask(task3);
        Task task4 = new Task("task4", "task4 description4");
        taskManager.createTask(task4);
       historyManager.addTaskToHistory(task1);
        historyManager.addTaskToHistory(task2);
        historyManager.addTaskToHistory(task3);
        historyManager.addTaskToHistory(task4);
        assertEquals(historyManager.getHistorySize(), 4, " 4 элемента должно быть в истории  до удаления");
        historyManager.removeTaskFromHistory(task3.getId());
        assertEquals(historyManager.getHistorySize(), 3, " 3 элемента должно быть в истории  до удаления");
    }

    @Test
    void checkRemoveEpicSubtaskFromHistory() {
        Epic epic1 = new Epic("Эпик 1","Нужно сделать");
        taskManager.createEpic(epic1);
        SubTask subtask1 = new SubTask("Subtask1 создания ",
                "Написать что то ", epic1.getId());
        taskManager.createSubTask(subtask1);
        SubTask subtask2 = new SubTask("Subtask2 создания ",
                "Написать что то ", epic1.getId());
        taskManager.createSubTask(subtask2);
        Epic epic2 = new Epic("Эпик 2","Нужно сделать");
        taskManager.createEpic(epic2);

        taskManager.inMemoryHistoryManager.addTaskToHistory(epic1);
        taskManager.inMemoryHistoryManager.addTaskToHistory(subtask1);
        taskManager.inMemoryHistoryManager.addTaskToHistory(subtask2);
        taskManager.inMemoryHistoryManager.addTaskToHistory(epic1);
        taskManager.inMemoryHistoryManager.addTaskToHistory(epic1);
        taskManager.inMemoryHistoryManager.addTaskToHistory(subtask1);
        taskManager.inMemoryHistoryManager.addTaskToHistory(epic2);
        taskManager.inMemoryHistoryManager.addTaskToHistory(subtask1);

        assertEquals(taskManager.inMemoryHistoryManager.getHistorySize(), 4, " 4 элемента должно быть в истории  до удаления");
        taskManager.removeTask(epic1.getId());
        assertEquals(taskManager.inMemoryHistoryManager.getHistorySize(), 1, " 3 элемента должно быть в истории  до удаления");
    }
}