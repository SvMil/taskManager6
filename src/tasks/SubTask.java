package tasks;

import managers.TaskType;

import java.util.ArrayList;

public class SubTask extends Task {
    private Integer parentEpicId;
    public SubTask(String name, String description, Integer parentEpicId) {
        super(name, description);
        this.parentEpicId = parentEpicId;
    }

    public SubTask(Integer id, String name, TaskStatus taskStatus, String description, Integer parentEpicId) {
        super(id, name, taskStatus, description);
        this.parentEpicId = parentEpicId;
    }

    public Integer getParentEpicId() {
        return parentEpicId;
    }

    public void setParentEpicId(Integer parentEpicId) {
        this.parentEpicId = parentEpicId;
    }


    @Override
    public String toString() {
        return id + "," + TaskType.SUBTASK + "," + name + "," + taskStatus + "," + description + "," + parentEpicId;
    }

    public void setStatus(TaskStatus status){
        this.taskStatus = status;
    }


}
