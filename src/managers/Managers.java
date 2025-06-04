package managers;

public class Managers{
    HistoryManager historyManager;
    TaskManager taskManager;

    public static InMemoryTaskManager getDefault(){
        return new InMemoryTaskManager();
    }

    public static InMemoryHistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}
