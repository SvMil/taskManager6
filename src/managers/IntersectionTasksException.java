package managers;

import tasks.Task;

public class IntersectionTasksException extends RuntimeException{
    private final Task task1;
    private final Task task2;

    public  IntersectionTasksException(final String message, final Task task1, final Task task2) {
        super(message);
        this.task1 = task1;
        this.task2 = task2;
    }

    public String getDetailMessage() {
        return getMessage() + ": " + task1.toStringDate() + "пересекается с " + task2.toStringDate();
    }



    public Task getTask1() {
        return task1;
    }

    public Task getTask2() {
        return task2;
    }


}
