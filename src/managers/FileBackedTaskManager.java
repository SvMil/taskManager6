package managers;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.TaskStatus;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.*;
import java.util.ArrayList;

public class FileBackedTaskManager extends InMemoryTaskManager {
    protected String fileName;

    public static void main(String[] args) {

        FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager("filewriterall.csv");
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

        FileBackedTaskManager fileBackedTaskManager2 = fileBackedTaskManager.loadFromFile(fileBackedTaskManager.fileName,"newManagerFile.csv");
        fileBackedTaskManager2.getAllTasks();
    }

    public FileBackedTaskManager(String fileName) {
        super();
        this.fileName = fileName;
    }

    @Override
    public void createTask(Task newTask) {
        super.createTask(newTask);
        saveToFile();
    }

    @Override
    public void createEpic(Epic newEpic) {
        super.createTask(newEpic);
        saveToFile();
    }
    public String getFileName(){
        return fileName;
    }

    @Override
    public SubTask getSubTaskById(Integer id) {
        return super.getSubTaskById(id);
    }

    @Override
    public void createSubTask(SubTask newSubTask) {
        super.createTask(newSubTask);
        saveToFile();
    }

    @Override
    public void updateTask(Task newTask) {
        super.updateTask(newTask);
        saveToFile();
    }

    @Override
    public void updateEpic(Epic newEpic) {
        super.updateEpic(newEpic);
        saveToFile();
    }

    @Override
    public void updateSubTask(SubTask newSubTask) {
        super.updateSubTask(newSubTask);
        saveToFile();
    }

    @Override
    public void removeTask(Integer id) {
        super.removeTask(id);
        saveToFile();
    }

    @Override
    public void removeAllTasks() {
        super.removeAllTasks();
        saveToFile();
    }

    public static void copySourceFile(String sourceFile, String destFile){

        Path sourcePath = Paths.get(sourceFile);
        Path destPath = Paths.get(destFile);

        try {
            Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File is copied successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readFromFile(String fileName) {
        System.out.println("чтение из файла");
        ArrayList<String> readAllFromFile = new ArrayList<String>();
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader);

            while (br.ready()) {
                String line = br.readLine();
                readAllFromFile.add(line);
            }
            br.close();
            for (String str : readAllFromFile) {
                System.out.println(str);
            }
            System.out.println("окончания чтения из файла");
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла.");
        }
        return readAllFromFile;
    }

    public static FileBackedTaskManager loadFromFile(String sourceFile, String newManagerFile) {
        System.out.println("создать FileBackedTaskManager загрузкой из файла");
        copySourceFile(sourceFile, newManagerFile);
        ArrayList<String> readAllFromFile = readFromFile(sourceFile);
        FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(newManagerFile);
        for (String str : readAllFromFile) {
            String[] split = str.split(",");
            if (split[1].equals(TaskType.EPIC)) {
                Epic epic = new Epic(Integer.parseInt(split[0]), split[2], TaskStatus.valueOf(split[3]), split[4]);
                fileBackedTaskManager.createEpic(epic);
            } else if (split[1].equals(TaskType.TASK)) {
                Task task = new Task(Integer.parseInt(split[0]), split[2], TaskStatus.valueOf(split[3]), split[4]);
                fileBackedTaskManager.createTask(task);
            } else if (split[1].equals(TaskType.SUBTASK)) {
                SubTask subTask = new SubTask(Integer.parseInt(split[0]), split[2], TaskStatus.valueOf(split[3]), split[4], Integer.parseInt(split[5]));
                fileBackedTaskManager.createSubTask(subTask);
            }
        }
        return fileBackedTaskManager;
    }


    private void saveToFile() {
        try (Writer fileWriter = new FileWriter(fileName)) {
            if (taskList != null && !taskList.isEmpty()) {
                for (Task task : taskList.values()) {
                    fileWriter.write(task.toString() + "\n");
                }
            } else if (epicList != null && !epicList.isEmpty()) {
                System.out.println("Все задачи с типом Tasks.Epic:");
                for (Epic epic : epicList.values()) {
                    fileWriter.write(epic.toString() + "\n");
                }
            } else if (subTaskList != null && !subTaskList.isEmpty()) {
                System.out.println("Все задачи с типом Tasks.SubTask:");
                for (SubTask subTask : subTaskList.values()) {
                    fileWriter.write(subTask.toString() + "\n");
                }
            } else {
                System.out.println("В системе нет зарегистрированных задач");
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время записи файла.");
        }
    }
}
