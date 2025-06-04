package managers;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

public interface TaskManager {
    ////////////// create task/////////////
    void createTask(Task newTask);

    void createEpic(Epic newEpic);

    void createSubTask(SubTask newSubTask);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubTask(SubTask subTask);

    //////////// remove ////////////
    void removeTask(Integer id);

    void removeAllTasks();

    /////////////// get /////////////

    void getAllTasks();

    Task getTaskById(Integer id);

    Epic getEpicById(Integer id);

    SubTask getSubTaskById(Integer id);

    boolean getAnyTaskById(Integer id);

    void getEpicSubTasks(Integer id);

}
