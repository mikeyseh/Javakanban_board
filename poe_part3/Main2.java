public class Main2 {
    public static void main(String[] args) {

        Task task = new Task();
        // Example of checkTaskDescription
        String description = "This is a sample task description";
        boolean isValid = Task.checkTaskDescription(description);
        System.out.println("Is description valid? " + isValid);

        // Example of createTaskID
        String developerNames = "John Doe";
        String taskName = "Development";
        int taskNumber = 1;
        String taskID = Task.createTaskID(developerNames, taskName, taskNumber);
        System.out.println("Task ID: " + taskID);

        // Example of printTaskDetails
        String taskStatus = "Doing";
        String taskDescription = "This is a task description.";
        int duration = 5;
        String details = Task.printTaskDetails(taskStatus, developerNames, taskNumber, taskName, taskDescription,
                duration);
        System.out.println("Task Details:\n" + details);

        // Example of returnTotalHours
        int totalHours = 10;
        int hours = Task.returnTotalHours(totalHours);
        System.out.println("Total hours: " + hours);
    }
}
