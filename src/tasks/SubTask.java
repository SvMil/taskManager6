package tasks;

import managers.TaskType;

import java.time.Duration;
import java.time.LocalDateTime;
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

    public SubTask(Integer id, String name, TaskStatus taskStatus, String description, Integer parentEpicId, Duration duration, LocalDateTime startTime) {
        super(id, name, taskStatus, description, duration, startTime);
        this.parentEpicId = parentEpicId;
    }

    public SubTask(String name, String description, Integer parentEpicId,Duration duration, LocalDateTime startTime ) {
        super(name, description, duration, startTime);
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
        if(startTime!=null){
            return id + "," + TaskType.SUBTASK + "," + name + "," + taskStatus + "," + description + "," + parentEpicId + "," + duration.toMinutes() + "," + startTime.format(taskDateformatter);

        }else {
            return id + "," + TaskType.SUBTASK + "," + name + "," + taskStatus + "," + description + "," + parentEpicId;
        }
    }

    @Override
    public String toStringDate() {
        if(startTime!=null){  return id + "," + TaskType.SUBTASK + "," + name + "," + taskStatus + "," + description + "," + parentEpicId + "," + duration.toMinutes() + "," + startTime.format(taskDateformatter);
        }else {
            return id + "," + TaskType.SUBTASK + "," + name + "," + taskStatus + "," + description + "," + parentEpicId;

        }
        }

    public void setStatus(TaskStatus status){
        this.taskStatus = status;
    }


}
