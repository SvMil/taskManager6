package tasks;

public class SubTask extends Task {
    private Integer parentEpicId;
    public SubTask(String name, String description, Integer parentEpicId) {
        super(name, description);
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
        return "SubTask{" +
                "parentEpicId=" + parentEpicId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", taskStatus=" + taskStatus +
                '}';
    }

    public void setStatus(TaskStatus status){
        this.taskStatus = status;
    }


}
