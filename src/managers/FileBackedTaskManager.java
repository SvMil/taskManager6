package managers;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.TaskStatus;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileBackedTaskManager extends InMemoryTaskManager {
    private String fileName;
    private HashMap<Integer, TaskType> generalList = new HashMap<>();
    private Integer generalCounter  = 1;


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

        FileBackedTaskManager fileBackedTaskManager2 = fileBackedTaskManager.loadFromFile(fileBackedTaskManager.fileName);
        fileBackedTaskManager.getAllTasks();

    }

    public FileBackedTaskManager(String fileName) {
        super();
        this.fileName = fileName;
    }

    @Override
    public void createTask(Task newTask) {
        super.createTask(newTask);
        generalList.put(generateGeneralListId(),TaskType.TASK);
        saveToFile();
    }

    private  Integer generateGeneralListId() { return generalCounter++; }

    @Override
    public void createEpic(Epic newEpic) {
        super.createEpic(newEpic);
        generalList.put(generateGeneralListId(),TaskType.EPIC);
        saveToFile();
    }
    public String getFileName(){
        return fileName;
    }


    @Override
    public void createSubTask(SubTask newSubTask) {
        super.createSubTask(newSubTask);
        generalList.put(generateGeneralListId(),TaskType.SUBTASK);
        saveToFile();
    }

    @Override
    protected void epicStatusControl(Integer epicId) {
        super.epicStatusControl(epicId);
        saveToFile();
    }

    @Override
    protected void addSubTaskToEpic(SubTask newSubTask) {
        super.addSubTaskToEpic(newSubTask);
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

    public static FileBackedTaskManager loadFromFile(String sourceFile) {
        System.out.println("создать FileBackedTaskManager загрузкой из файла");
        ArrayList<String> readAllFromFile = readFromFile(sourceFile);
        FileBackedTaskManager fileBackedTaskManager2 = new FileBackedTaskManager(sourceFile);
        for (String str : readAllFromFile) {
            String[] split = str.split(",");
            if (split[1].equals("EPIC")) {
                Epic epic = new Epic(Integer.parseInt(split[0]), split[2], TaskStatus.valueOf(split[3]), split[4]);
                fileBackedTaskManager2.createEpic(epic);
            } if (split[1].equals("TASK")) {
                Task task = new Task(Integer.parseInt(split[0]), split[2], TaskStatus.valueOf(split[3]), split[4]);
                fileBackedTaskManager2.createTask(task);
            } if (split[1].equals("SUBTASK")) {
                SubTask subTask = new SubTask(Integer.parseInt(split[0]), split[2], TaskStatus.valueOf(split[3]), split[4], Integer.parseInt(split[5]));
                fileBackedTaskManager2.createSubTask(subTask);
            }
        }
        return fileBackedTaskManager2;
    }

    private void saveToFile() {
        try (Writer fileWriter = new FileWriter(fileName)) {
            fileWriter.write("id,type,name,description,status,epic\n");
            for (Map.Entry<Integer,TaskType> entry : generalList.entrySet()) {
                Integer id = entry.getKey();
                TaskType taskType = entry.getValue();

                switch (taskType.toString()){
                    case "TASK":
                        fileWriter.write(getTaskById(id).toString() + "\n");
                        break;
                    case "EPIC":
                        fileWriter.write(getEpicById(id).toString() + "\n");
                        break;
                    case "SUBTASK":
                        fileWriter.write(getSubTaskById(id).toString() + "\n");
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время записи файла.");
        }

    }
}
