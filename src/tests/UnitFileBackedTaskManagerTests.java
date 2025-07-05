package tests;

import managers.FileBackedTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class UnitFileBackedTaskManagerTests {

    static FileBackedTaskManager fileBackedTaskManager;

    @BeforeEach
    public void setClassObjects(){
        fileBackedTaskManager = new FileBackedTaskManager("filewriterall.csv");
        Epic epic1 = new Epic("Эпик 1", "Нужно сделать");
        fileBackedTaskManager.createEpic(epic1);

        Task task1 = new Task("Задача 1", "Нужно сделать");
        fileBackedTaskManager.createTask(task1);

        SubTask subtask1 = new SubTask("Subtask1 создания",
                "Написать что то", epic1.getId());
        fileBackedTaskManager.createSubTask(subtask1);
        SubTask subtask2 = new SubTask("Subtask2 создания",
                "Написать что то", epic1.getId());
        fileBackedTaskManager.createSubTask(subtask2);

        Epic epic2 = new Epic("Эпик 2", "Нужно сделать");
        fileBackedTaskManager.createEpic(epic2);
    }

    @Test
    void loadFromFile() {
        ArrayList<String> firstManagerList = FileBackedTaskManager.readFromFile(fileBackedTaskManager.getFileName());

        FileBackedTaskManager fileBackedTaskManager2 = fileBackedTaskManager.loadFromFile(fileBackedTaskManager.getFileName(),"newManagerFile.csv");
        fileBackedTaskManager2.getAllTasks();
        ArrayList<String> secondManagerList = FileBackedTaskManager.readFromFile(fileBackedTaskManager2.getFileName());
        assertTrue(firstManagerList.equals(secondManagerList));
    }

    @Test
    void addTaskToSourseFile() {
        ArrayList<String> managerTasksList1 = FileBackedTaskManager.readFromFile(fileBackedTaskManager.getFileName());
        Task task2 = new Task("Задача 2", "Нужно сделать");
        fileBackedTaskManager.createTask(task2);
        ArrayList<String> managerTasksList2 = FileBackedTaskManager.readFromFile(fileBackedTaskManager.getFileName());
        assertFalse(managerTasksList1.equals(managerTasksList2));
        fileBackedTaskManager.getAllTasks();
    }

    @Test
    void addTaskToSourseFileCheskDest() {
        FileBackedTaskManager fileBackedTaskManager2 = fileBackedTaskManager.loadFromFile(fileBackedTaskManager.getFileName(),"newManagerFile.csv");
        fileBackedTaskManager2.getAllTasks();
        Task task2 = new Task("Задача 2", "Нужно сделать");
        fileBackedTaskManager.createTask(task2);
        ArrayList<String> firstManagerList = FileBackedTaskManager.readFromFile(fileBackedTaskManager.getFileName());
        ArrayList<String> secondManagerList = FileBackedTaskManager.readFromFile(fileBackedTaskManager2.getFileName());
        assertFalse(firstManagerList.equals(secondManagerList));
    }

    @Test
    void addTaskToDestFile() {
        FileBackedTaskManager fileBackedTaskManager2 = fileBackedTaskManager.loadFromFile(fileBackedTaskManager.getFileName(),"newManagerFile.csv");
        fileBackedTaskManager2.getAllTasks();
        ArrayList<String> firstManagerList = FileBackedTaskManager.readFromFile(fileBackedTaskManager.getFileName());
        ArrayList<String> secondManagerList = FileBackedTaskManager.readFromFile(fileBackedTaskManager2.getFileName());
        assertTrue(firstManagerList.equals(secondManagerList));
        Task task2 = new Task("Задача 2", "Нужно сделать");
        fileBackedTaskManager2.createTask(task2);
        ArrayList<String> firstManagerList2 = FileBackedTaskManager.readFromFile(fileBackedTaskManager.getFileName());
        ArrayList<String> secondManagerList2 = FileBackedTaskManager.readFromFile(fileBackedTaskManager2.getFileName());
        assertFalse(firstManagerList2.equals(secondManagerList2));
        assertTrue(firstManagerList.equals(firstManagerList2));
        assertFalse(secondManagerList.equals(secondManagerList2));
        fileBackedTaskManager2.getAllTasks();
    }
}