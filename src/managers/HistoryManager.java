package managers;

import tasks.Task;

import java.util.List;
import java.util.Map;

public interface HistoryManager {
    void  addTaskToHistory(Task task);

    void removeTaskFromHistory(int id);

    List<Node<Task>> getHistory();
}
