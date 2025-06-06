package managers;

import tasks.Task;

import java.util.LinkedHashMap;
import java.util.Map;


public class InMemoryHistoryManager implements HistoryManager {

    private Map<Integer, Node<Task> > fastHistoryList = new LinkedHashMap<>();

    private Node<Task> head;
    private Node<Task> tail;
    private int linkedListSize = 0;

    public int linkedListSize() {
        return this.linkedListSize;
    }

    public Node<Task> linkLast(Task element) {
        final Node<Task> oldTail = tail;
        final Node<Task> newNode = new Node<>(oldTail, element, null);
        tail = newNode;
        if (oldTail == null)
            tail = newNode;
        else
            oldTail.next = newNode;
        linkedListSize++;
        return newNode;
    }

    public void removeLinkedNode(Node <Task> node) {
            if (node.prev != null) {
                final Node<Task> prev = node.prev;
                prev.next = node.next;
            }
            if (node.next != null) {
                final Node<Task> next = node.next;
                next.prev = node.prev;
            }

            linkedListSize--;
    }

    @Override
    public void addTaskToHistory(Task task) {
        if(fastHistoryList.containsKey(task.getId())){
            removeTaskFromHistory(task.getId());
        }
        fastHistoryList.put(task.getId(),linkLast(task));
    }

    @Override
    public Map<Integer, Node<Task>>  getHistory() {

        return fastHistoryList;
    }

    @Override
    public void removeTaskFromHistory(int id) {
        removeLinkedNode(fastHistoryList.get(id));
        fastHistoryList.remove(id);
    }

    public Integer  getHistorySize() {

        return linkedListSize;
    }
}
