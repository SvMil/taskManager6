package managers;

import tasks.Task;

import java.util.Map;

public interface HistoryManager {
    void  addTaskToHistory(Task task);

    void removeTaskFromHistory(int id);

    Map<Integer, Node<Task>> getHistory();
}
