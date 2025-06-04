package managers;

import tasks.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int HISTORY_LIST_SIZE = 10;

    private ArrayList <Task> historyList = new ArrayList<>();


    @Override
    public void addTaskToHistory(Task task) {
        if(historyList.size()==HISTORY_LIST_SIZE){
            System.out.println("historyList.size()" + historyList.size());
            ArrayList<Task> bufferHistoryList = new ArrayList<>();
            for(int i=0; i<HISTORY_LIST_SIZE-1; i++){
                bufferHistoryList.add(i,historyList.get(i+1));
            }
            historyList.clear();
            historyList = bufferHistoryList;
        }
        historyList.add(task);
    }

    @Override
    public ArrayList <Task>  getHistory() {

        return historyList;
    }

    public Integer  getHistorySize() {

        return HISTORY_LIST_SIZE;
    }

}
