import javax.swing.JOptionPane;

public class Task {

    // This method returns true if the task description has less than 51 characters
    // and returns false otherwise
    public static boolean checkTaskDescription(String description) {
        if (description.length() <= 50) {
            return true;
        } else {
            return false;
        }
    }

    // Creation of the task ID using the task name, the developer first name and the
    // task number
    public static String createTaskID(String developer_names, String task_name, int task_number) {

        String lastThreeLetters = "";
        String ID; // This variable will contain the task ID
        char ch; // This variable will contain each and every letter of the programmer's name as
                 // it is getting iterated through

        for (int i = 0; i < developer_names.length(); i++) {

            ch = developer_names.charAt(i);

            if (Character.isWhitespace(ch)) {
                lastThreeLetters = developer_names.substring(i - 3, i);
            }
        }

        // This will ensure automatically generated taskID will be stored in the id
        // variable

        ID = task_name.substring(0, 2) + ":" + task_number +
                ":" + lastThreeLetters;

        // Task ID is then converted to upercase as per the question paper and returned
        // by the createTaskID method
        return ID.toUpperCase();
    }

    /*
     * The following method returns all the datails of a particular task.
     */
    public static String printTaskDetails(String taskStatus, String developerNames, int currentTask, String taskName,
            String taskDescription, int duration) {
        return taskStatus + "\n" + developerNames + "\n" +
                currentTask + "\n" + taskName + "\n" +
                taskDescription + "\n" + "\n" + duration;
    }

    // this method returns the number of hours needed to complete all the tasks
    public static int returnTotalHours(int totalHours) {

        return totalHours;
    }

    public static void main(String[] args) {

        Task mainTask = new Task();
        // Example of checkTaskDescription
        String description = "This is a sample task description";
        boolean isValid = Task.checkTaskDescription(description);
        System.out.println("Is description valid? " + isValid);

        // Example of createTaskID
        String developerNames;
        developerNames = JOptionPane.showInputDialog(null, "Enter developer Name");
        String taskName;
        taskName = JOptionPane.showInputDialog(null, "Enter task Name");
        int taskNumber;
        String taskNumberString = JOptionPane.showInputDialog(null, "Enter task number.");
        taskNumber = Integer.parseInt(taskNumberString);
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
