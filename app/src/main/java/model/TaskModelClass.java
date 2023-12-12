package model;

public class TaskModelClass {
    private final String category;
    private final String taskName;
    private final String durationMinutes;

    public TaskModelClass(String category, String taskName, String durationMinutes) {
        this.category = category;
        this.taskName = taskName;
        this.durationMinutes = durationMinutes;
    }

    public String getCategory() {
        return category;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDurationMinutes() {
        return durationMinutes;
    }
}
