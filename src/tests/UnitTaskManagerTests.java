package tests;

import static org.junit.jupiter.api.Assertions.*;

import managers.InMemoryHistoryManager;
import managers.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.HashMap;


class UnitTaskManagerTests {

    static InMemoryTaskManager taskManager;
    static InMemoryHistoryManager historyManager;


    @BeforeEach
    public void setClassObjects(){
        taskManager=new InMemoryTaskManager();
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void addNewTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description");
        taskManager.createTask(task);
        task.toString();
        final Integer taskId = task.getId();
        final Task savedTask = taskManager.getTaskById(taskId);
        savedTask.toString();

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task.getId(), savedTask.getId(), "Задачи не совпадают.");

        final HashMap<Integer, Task> tasks = taskManager.getTaskList();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task.getId(), tasks.get(1).getId(), "Задачи не совпадают.");
    }

    @Test
    void addNewEpic() {
        Epic epic = new Epic("Epic addNewEpic", "Epic addNewEpic description");
        taskManager.createEpic(epic);
        epic.toString();
        final Integer epicId = epic.getId();
        final Epic savedEpic = taskManager.getEpicById(epicId);
        savedEpic.toString();

        assertNotNull(savedEpic, "Задача не найдена.");
        assertEquals(epic.getId(), savedEpic.getId(), "Задачи не совпадают.");

        final HashMap<Integer, Epic> epics = taskManager.getEpicList();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic.getId(), epics.get(1).getId(), "Задачи не совпадают.");
    }


}