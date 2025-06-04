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

    @Test
    void deleteSubTask() {
        Epic epic1 = new Epic("Epic1", "Epic1 description");
        taskManager.createEpic(epic1);
        SubTask subTask1 = new SubTask("SubTask1","description SubTask1 ep1", 1);
        taskManager.createSubTask(subTask1);
        SubTask subTask2 = new SubTask("SubTask2","description SubTask2 ep2", 1);
        taskManager.createSubTask(subTask2);
        Integer subTaskEpic1Count = epic1.getSubTasksList().size();
        assertEquals(2, subTaskEpic1Count, "Неверное количество сабтасок в эпике 1.");
        taskManager.removeTask(subTask2.getId());
        subTaskEpic1Count = epic1.getSubTasksList().size();
        assertEquals(1, subTaskEpic1Count, "Неверное количество сабтасок в эпике 1.");
         }

    @Test
    void deleteEpic() {
        Epic epic1 = new Epic("Epic1", "Epic1 description");
        taskManager.createEpic(epic1);
        SubTask subTask1 = new SubTask("SubTask1","description SubTask1 ep1", 1);
        taskManager.createSubTask(subTask1);
        SubTask subTask2 = new SubTask("SubTask2","description SubTask2 ep2", 1);
        taskManager.createSubTask(subTask2);
        Integer subTaskEpic1Count = epic1.getSubTasksList().size();
        assertEquals(2, subTaskEpic1Count, "Неверное количество сабтасок в эпике 1.");
        final HashMap<Integer, SubTask> subTasksBeforeRemoveEpic = taskManager.getSubTaskList();
        assertEquals(2, subTasksBeforeRemoveEpic.size(), "Неверное количество задач.");
        taskManager.removeTask(epic1.getId());
        final HashMap<Integer, SubTask> subTasksAfterRemoveEpic = taskManager.getSubTaskList();
        assertEquals(0, subTasksAfterRemoveEpic.size(), "Неверное количество задач.");
    }

}