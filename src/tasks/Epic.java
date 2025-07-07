package tasks;

import managers.TaskType;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Epic  extends Task {
    private ArrayList<Integer> subTasksList;
    LocalDateTime endTime;

    public Epic(String name, String description) {
        super(name, description);
        this.subTasksList = new ArrayList<>();
    }

    public Epic(Integer id, String name, TaskStatus taskStatus, String description) {
        super(id, name, taskStatus, description);
        this.subTasksList = new ArrayList<>();
    }

    public ArrayList<Integer> getSubTasksList() {
        return subTasksList;
    }

    @Override
    public String toString() {
        return id + "," + TaskType.EPIC + "," + name + "," + taskStatus + "," + description;
    }

    public String toStringDate() {
        return id + "," + TaskType.EPIC + "," + name + "," + taskStatus + "," + description+ "," + duration.toMinutes() + "," + startTime;
    }


    public void setSubTasksList(ArrayList<Integer> subTasksList) {
        this.subTasksList = subTasksList;
    }

    public LocalDateTime getEndTime(){
        return endTime;
    }

    public void endTime(LocalDateTime endTime){
        this.endTime= endTime;
    }
}
