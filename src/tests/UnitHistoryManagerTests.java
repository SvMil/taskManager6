package tests;

import managers.InMemoryHistoryManager;
import managers.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


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
        ArrayList<Task> history = new ArrayList<>();
        assertEquals(0, history.size(), "История пустая.");
        historyManager.addTaskToHistory(task);
        history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }

    @Test
    void checkHistorySize() {
        Integer tasksCount = 0;
        ArrayList<Task> history = new ArrayList<>();
        while(tasksCount<=historyManager.getHistorySize()+1){
            Task task1 = new Task("task"+tasksCount, "task" + tasksCount + "description");
            historyManager.addTaskToHistory(task1);
            tasksCount++;
            history = historyManager.getHistory();
            if (tasksCount<=historyManager.getHistorySize()) {
                assertEquals(history.size(), tasksCount,  tasksCount + " столько элементов должно быть в списке просмотров.");
            }else {
                assertEquals(history.size(), historyManager.getHistorySize(), tasksCount + " столько элементов должно быть в списке, это лимит.");

            }
        }
         }


}