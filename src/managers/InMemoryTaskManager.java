package managers;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    protected Integer taskCounter = 1;
    protected HashMap <Integer, Task> taskList = new HashMap<>();
    protected HashMap <Integer, Epic> epicList = new HashMap<>();
    protected HashMap <Integer, SubTask> subTaskList = new HashMap<>();
    protected InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
    private  Integer generateId() { return taskCounter++; }

    ////////////// create task/////////////
    @Override
    public void createTask(Task newTask) {
            newTask.setId(generateId());
        if (taskList == null) {
            taskList = new HashMap<>();
        }
        taskList.put(newTask.getId(), newTask);
    }

    @Override
    public void createEpic(Epic newEpic) {
        newEpic.setId(generateId());
        if (epicList == null) {
            epicList = new HashMap<>();
        }
        epicList.put(newEpic.getId(), newEpic);
    }

    @Override
    public void createSubTask(SubTask newSubTask) {
        newSubTask.setId(generateId());
        if (subTaskList == null) {
            subTaskList = new HashMap<>();
        }
        subTaskList.put(newSubTask.getId(), newSubTask);
        addSubTaskToEpic(newSubTask);
        epicStatusControl(newSubTask.getParentEpicId());
    }

    ///////////////update task//////////////////
    @Override
    public void updateTask(Task newTask) {
         if (taskList != null && taskList.get(newTask.getId())!=null) {
             Task task = taskList.get(newTask.getId());
             task.setName(newTask.getName());
             task.setDescription(newTask.getDescription());
             task.setTaskStatus(newTask.getTaskStatus());
         }else{
             System.out.println("Задачи с таким id не существует");
         }
    }


    @Override
    public void updateEpic(Epic newEpic) {
        if (epicList != null && epicList.get(newEpic.getId())!=null) {
            Epic epic = epicList.get(newEpic.getId());
            epic.setName(newEpic.getName());
            epic.setDescription(newEpic.getDescription());
            epic.setTaskStatus(newEpic.getTaskStatus());
        }else{
            System.out.println("Эпика с таким id не существует");
        }
    }

    @Override
    public void updateSubTask(SubTask newSubTask) {
        if (subTaskList != null && subTaskList.get(newSubTask.getId())!=null) {
            SubTask subTask = subTaskList.get(newSubTask.getId());
            subTask.setName(newSubTask.getName());
            subTask.setDescription(newSubTask.getDescription());
            subTask.setTaskStatus(newSubTask.getTaskStatus());
            Integer exParentEpicId=subTask.getParentEpicId();
            if(exParentEpicId!=newSubTask.getParentEpicId()){
                removeSubTaskFromEpic(subTask);
                subTask.setParentEpicId(newSubTask.getParentEpicId());
                addSubTaskToEpic(subTask);
                epicStatusControl(exParentEpicId);
            }
            epicStatusControl(subTask.getParentEpicId());
        }
    else{
                System.out.println("Подзадачи с таким id не существует");
            }
    }

    protected void addSubTaskToEpic(SubTask newSubTask) {
        ArrayList <Integer> epicSubtaskList;
        if (epicList.get(newSubTask.getParentEpicId()).getSubTasksList() != null&& epicList.get(newSubTask.getParentEpicId()).getSubTasksList().isEmpty() != true) {
            epicSubtaskList = epicList.get(newSubTask.getParentEpicId()).getSubTasksList();

        }else {
            epicSubtaskList = new ArrayList<>();
        }
        epicSubtaskList.add(newSubTask.getId());
        epicList.get(newSubTask.getParentEpicId()).setSubTasksList(epicSubtaskList);
        epicStatusControl(newSubTask.getParentEpicId());
    }

    ////////////// epicStatusControl ///////////

    protected void epicStatusControl(Integer epicId) {
       Integer newTaskCount = 0;
       Integer doneTaskCount = 0;
        Epic epic= epicList.get(epicId);
        ArrayList<Integer> tasks = epic.getSubTasksList();
        if(!tasks.isEmpty()){
            for(Integer subTaskId: tasks){
               TaskStatus status = subTaskList.get(subTaskId).getTaskStatus();
               if(status==TaskStatus.NEW){
                   newTaskCount++;
               }else if (status==TaskStatus.DONE){
                   doneTaskCount++;
                }

        }
        }
        if(newTaskCount==epic.getSubTasksList().size()){
            epic.setTaskStatus(TaskStatus.NEW);
        }else if(doneTaskCount==epic.getSubTasksList().size()){
            epic.setTaskStatus(TaskStatus.DONE);
        }else{
            epic.setTaskStatus(TaskStatus.IN_PROGRESS);
        }

    }


   //////////// remove ////////////
   @Override
   public void removeTask(Integer id){
        System.out.println("removeTask");
        if (taskList != null && taskList.get(id)!=null){
            taskList.remove(id);
        }if (subTaskList != null && subTaskList.get(id)!=null){
            if(subTaskList.get(id) != null)
            {
                Integer subTaskParentId = subTaskList.get(id).getParentEpicId();
                removeSubTaskFromEpic(subTaskList.get(id));
                subTaskList.remove(id);
                epicStatusControl(subTaskParentId);
            }
        }if (epicList != null && epicList.get(id)!=null){
            if(epicList.get(id) != null)
            {
                ArrayList<Integer> epicSubtaskList= epicList.get(id).getSubTasksList();
                for (Integer sabtaskId:epicSubtaskList){
                    subTaskList.remove(sabtaskId);
                    inMemoryHistoryManager.removeTaskFromHistory(sabtaskId);
                }
                epicList.remove(id);
            }
        }else {
            System.out.println("Задачи с таким id не существует");
        }
        inMemoryHistoryManager.removeTaskFromHistory(id);
    }

    protected    void removeSubTaskFromEpic(SubTask newSubTask) {
        ArrayList <Integer> epicSubtaskList;
        if (epicList.get(newSubTask.getParentEpicId()).getSubTasksList() != null) {
            epicSubtaskList = epicList.get(newSubTask.getParentEpicId()).getSubTasksList();
        }else {
            epicSubtaskList = new ArrayList<>();
        }
        epicSubtaskList.remove(newSubTask.getId());
        epicList.get(newSubTask.getParentEpicId()).setSubTasksList(epicSubtaskList);
    }

    @Override
    public void removeAllTasks(){
        if (taskList != null){
            taskList.clear();
        }
        if (epicList != null){
            epicList.clear();
        }
        if (subTaskList != null){
            subTaskList.clear();
        }
        taskCounter = 1;
    }

    //////////// get ////////////

    @Override
    public void getAllTasks(){
        if (taskList != null&&!taskList.isEmpty()){
            for(Task task: taskList.values()){
                System.out.println(task.toString());
            }
        }
        if (epicList != null&&!epicList.isEmpty()){
            System.out.println("Все задачи с типом Tasks.Epic:");
            for(Epic epic: epicList.values()){
                System.out.println(epic.toString());
            }
        }
        if (subTaskList != null&&!subTaskList.isEmpty()){
            System.out.println("Все задачи с типом Tasks.SubTask:");
            for(SubTask subTask: subTaskList.values()){
                System.out.println(subTask.toString());
            }
        }
        else{
            System.out.println("В системе нет зарегистрированных задач");
        }
    }

    @Override
    public Task getTaskById(Integer id){
        if (taskList != null && taskList.get(id)!=null){
            inMemoryHistoryManager.addTaskToHistory(taskList.get(id));
            return taskList.get(id);
        }else {
            System.out.println("Задачи с таким id не существует");
            return null;
        }
    }

    @Override
    public Epic getEpicById(Integer id){
        if (epicList != null && epicList.get(id)!=null){
            inMemoryHistoryManager.addTaskToHistory(epicList.get(id));
            return epicList.get(id);

        }else {
            System.out.println("Эпика с таким id не существует");
            return null;
        }
    }

    @Override
    public SubTask getSubTaskById(Integer id){
        if (subTaskList != null && subTaskList.get(id)!=null){
            inMemoryHistoryManager.addTaskToHistory(subTaskList.get(id));
            return subTaskList.get(id);
        }else {
            System.out.println("Сабтаски с таким id не существует");
            return null;
        }
    }

    @Override
    public boolean getAnyTaskById(Integer id){
        if (taskList != null && taskList.get(id)!=null){
            System.out.println(taskList.get(id).toString());
            inMemoryHistoryManager.addTaskToHistory(taskList.get(id));
            return true;
        }else if (subTaskList != null && subTaskList.get(id)!=null){
            System.out.println(subTaskList.get(id).toString());
            inMemoryHistoryManager.addTaskToHistory(subTaskList.get(id));
            return true;
        }else if (epicList != null && epicList.get(id)!=null){
            System.out.println(epicList.get(id).toString());
            inMemoryHistoryManager.addTaskToHistory(epicList.get(id));
            return true;
        }else {
            System.out.println("Задачи с таким id не существует");
            return false;
        }
    }

    public HashMap<Integer, Task> getTaskHashMap() {
        return taskList;
    }

    public Task getTask(Integer id) {

            if(taskList.containsKey(id)){
                return taskList.get(id);
            }else{
                return null;
            }
    }

    public HashMap<Integer, Epic> getEpicHashMap() {
        return epicList;
    }

    public HashMap<Integer, SubTask> getSubTaskList() {
        return subTaskList;
    }

    @Override
    public void getEpicSubTasks(Integer id){
        ArrayList <Integer> epicSubtaskList;
        if (epicList.get(id).getSubTasksList().isEmpty() != true) {
            epicSubtaskList = epicList.get(id).getSubTasksList();
            for (Integer sabtaskId:epicSubtaskList){
                subTaskList.get(sabtaskId).toString();
            }
        }else {
            System.out.println("У эпика " + id + " нет сабтасок");
        }
    }

    @Override
    public List getHistory(){
        return inMemoryHistoryManager.getHistory();

    }
}
