package tasks;

import managers.TaskType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.Duration.ofMinutes;

public class Task {
    protected String name;
    protected String description;
    protected Integer id;
    Duration duration = Duration.ZERO;
    //LocalDateTime startTime = LocalDateTime.of(2222, 2, 2, 2, 2);
    LocalDateTime startTime;



    protected TaskStatus taskStatus;

    public Integer getId() {
        return id;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.taskStatus = TaskStatus.NEW;
    }

    public LocalDateTime getEndTime(){
        return startTime.plusMinutes(duration.toMinutes());
    }

    public Task(Integer id, String name, TaskStatus taskStatus, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.taskStatus = taskStatus;
    }



    public Task(String name, String description, Duration duration, LocalDateTime startTime) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return id + "," + TaskType.TASK + "," + name + "," + taskStatus + "," + description;
    }

    public String toStringDate() {
        return id + "," + TaskType.TASK + "," + name + "," + taskStatus + "," + description + "," + duration.toMinutes() + "," + startTime;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTaskDurationMinutes(){
        return duration.toMinutes();
    }

    public Duration getTaskDuration(){
        return duration;
    }

    public void setTaskDuration(Duration duration){
        this.duration= duration;
    }

    public LocalDateTime getStartTime(){
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime){
        this.startTime= startTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getId(), task.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
