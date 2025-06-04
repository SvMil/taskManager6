package tasks;

import java.util.*;

public class Epic  extends Task {
    private ArrayList<Integer> subTasksList;

    public Epic(String name, String description) {
        super(name, description);
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

    public void setSubTasksList(ArrayList<Integer> subTasksList) {
        this.subTasksList = subTasksList;
    }
}
