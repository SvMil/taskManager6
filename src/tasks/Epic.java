package tasks;

import managers.TaskType;

import java.time.Duration;
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

    public Epic(Integer id, String name, TaskStatus taskStatus, String description, Duration duration, LocalDateTime startTime) {
        super(id, name, taskStatus, description, duration, startTime);
        this.subTasksList = new ArrayList<>();
    }

    public Epic(Integer id, String name, TaskStatus taskStatus, String description, Duration duration, LocalDateTime startTime, LocalDateTime endTime) {
        super(id, name, taskStatus, description, duration, startTime);
        this.subTasksList = new ArrayList<>();
        this.endTime = endTime;
    }

    public ArrayList<Integer> getSubTasksList() {
        return subTasksList;
    }

    @Override
    public String toString() {
        if(startTime!=null){
            return id + "," + TaskType.EPIC + "," + name + "," + taskStatus + "," + description+ "," + duration.toMinutes() + "," + startTime.format(taskDateformatter) + "," + endTime.format(taskDateformatter);
        }else {
            return id + "," + TaskType.EPIC + "," + name + "," + taskStatus + "," + description;
        }
    }

    public String toStringDate() {
        if(startTime!=null){
            return id + "," + TaskType.EPIC + "," + name + "," + taskStatus + "," + description+ "," + duration.toMinutes() + "," + startTime.format(taskDateformatter) + "," + endTime.format(taskDateformatter);
        }else {
            return id + "," + TaskType.EPIC + "," + name + "," + taskStatus + "," + description+ "," + null + "," + null + "," + null;
        }
        }



    public void setSubTasksList(ArrayList<Integer> subTasksList) {
        this.subTasksList = subTasksList;
    }

    public LocalDateTime getEndTime(){
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime){
        this.endTime= endTime;
    }
}
