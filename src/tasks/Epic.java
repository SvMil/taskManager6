package tasks;

import java.util.ArrayList;

public class Epic  extends Task {
    private ArrayList<Integer> subTasksList;

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
        return "Epic{" +
                "subTasksList=" + subTasksList +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", taskStatus=" + taskStatus +
                '}';
    }

    public String writeToString() {
        return id + "," + this.getClass().toString() + "," + name + "," + taskStatus + "," + description;
    }

    public void setSubTasksList(ArrayList<Integer> subTasksList) {
        this.subTasksList = subTasksList;
    }
}
