
import javax.swing.JOptionPane;

public class poe_taskarray {
    static String done;// This variable will hold the task with the status of done
    static String longest; // This variable will hold the details of the longest task
    static String taskFound; // This variable will store the details of the searched task
    static String dev_tasks; // This variable will hold the details of the tasks assigned to a particular
                             // developer
    static String deleted; // This variable will hold a deletion message
    static String devNames; // This variable will hold the name of the developer whose tasks needs to be
                            // searched
    static String search; // This variable will hold the name of the task to be searched
    static String t_name; // This variable will hold the name of the task to be deleted
    static String report;

    static int option = 0; // This variable will hold the user's choice to manipulate the arrays
    static String choice; //

    static int size = TaskDetails.getNumberOfTasks();

    static String[] developer_names = new String[size];
    static String[] task_names = new String[size];
    static String[] task_ids = new String[size];
    static String[] task_statuses = new String[size];
    static int[] task_durations = new int[size];

    /*
     * In the following method, the user is given options to perform a number of
     * different functions
     * on the task details
     */
    public static void manipulateArrays(String developerName[], String taskName[], String taskID[], String[] status,
            int duration[]) {
        // the following lines copy the local arrays into the global arrays so the
        // Information can be accessed anywhere
        System.arraycopy(developerName, 0, developer_names, 0, size);
        System.arraycopy(taskName, 0, task_names, 0, size);
        System.arraycopy(taskID, 0, task_ids, 0, size);
        System.arraycopy(status, 0, task_statuses, 0, size);
        System.arraycopy(duration, 0, task_durations, 0, size);

        choice = JOptionPane.showInputDialog("Enter\n"
                + "a. >> To view the list of all tasks with the status of done\n"
                + "b. >> To view the name of the developer whose task takes the longest to complete\n"
                + "c. >> To search for a Task\n"
                + "d. >> To view the list of tasks assigned to a developer\n"
                + "e. >> To delete a task\n"
                + "f. >> To get a report of all the captured Tasks");

        switch (choice) {
            // The user gets the list of done tasks if they select a
            case "a":
                doneTasks();
                break;
            case "b":
                longestTask();
                break;
            case "c":
                search = JOptionPane.showInputDialog("Enter the name of the task to be searched");
                searchTask(search);
                break;
            case "d":
                devNames = JOptionPane.showInputDialog("Enter developer full names");
                developerTasks(devNames);
                break;
            case "e":
                t_name = JOptionPane.showInputDialog("Enter the name of the task to be deleted");
                deleteTask(t_name);
                break;
            case "f":
                displayReport();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid input");
        }

    }

    /*
     * These methods searche for the tasks with the status Done and if found, the
     * task together
     * with the Task name and durations are stored in the done variable and later
     * diplayed
     */
    public static void doneTasks() {
        // Initialize as empty
        done = "";
        for (int i = 0; i < size; i++) {
            if (taskStatuses[i].equals("Done")) {
                done += developer_names[i] + ", " + task_names[i] + ", " + task_durations[i] + "\n";
            }
        }
        if (done.isEmpty()) {
            done = "No tasks with status 'Done' found.";
        }
        JOptionPane.showMessageDialog(null, done);
    }

    /*
     * This method searches for the largest number in the taskDuration array and
     * displays it together with the developerNames
     * assigned to the task.
     */
    public static void longestTask() {
        int maximum;
        maximum = task_durations[0];
        // this variable will hold the location of the largest number in the
        // taskDurations array

        int position = 0;

        // this loop finds the largest value in the taskDurations array and stores it in
        // the maximum variable

        for (int i = 0; i < size; i++) {

            if (taskDurations[i] > maximum) {
                maximum = task_durations[i];
                position = i;
            }
        }

        longest = (developer_names[position] + ", " + maximum);
        JOptionPane.showMessageDialog(null, longest);

    }

    /*
     * This method allows a user to search for a Task using the task name
     */
    public static String searchTask(String searching) {
        searching = search;
        for (int i = 0; i < size; i++) {
            if (task_names[i].equals(searching)) {
                taskFound = (task_names[i] + ", " + developer_names[i] + ", " + task_statuses[i]);
                i = size; // this line will terminate the loop when a developer name is found

            } else {
                taskFound = "Task not found";
            }
        }
        JOptionPane.showMessageDialog(null, taskFound);
        return searching;

    }

    /*
     * This method allows the user to search for all the tasks assigned to
     * particular developer
     */
    public static String developerTasks(String developer) {
        dev_tasks = "";
        for (int i = 0; i < size; i++) {
            if (developer_names[i].equals(devNames)) {
                dev_tasks += task_names[i] + ", " + task_statuses[i] + "\n";
            }
        }
        if (dev_tasks.isEmpty()) {
            dev_tasks = "No tasks found for developer " + devNames;
        }
        JOptionPane.showMessageDialog(null, dev_tasks);
        return dev_tasks;
    }

    /*
     * Under condition the user enters a task that is in the array, the task will be
     * deleted
     * along with all the details related to it.
     */
    public static String deleteTask(String task) {
        task = t_name;
        int deleteIndex; // this variable will hold the name of the task to be deleted
        /*
         * The loop finds the task to be deleted, and if the task is found, its index is
         * noted and everything at that
         * index is removed iin the parallel arrays.
         */
        for (int i = 0; i < size; i++) {

            if (task_names[i].equals(task)) {
                deleteIndex = i;

                deleted = ("Task " + task_names[i] + " successfully deleted.");

                task_names[deleteIndex] = "";

                developer_names[deleteIndex] = "";

                task_ids[deleteIndex] = "";

                task_statuses[deleteIndex] = "";

                task_durations[deleteIndex] = 0;

                i = size;
            }

        }
        JOptionPane.showMessageDialog(null, deleted);
        return deleted;
    }

    public static void displayReport() {
        report = ""; // Initialize as empty
        for (int i = 0; i < size; i++) {
            if (!task_names[i].isEmpty() && task_durations[i] != 0) {
                report += task_names[i] + "\n" + developer_names[i] + "\n" + task_ids[i] + "\n"
                        + task_statuses[i] + "\n" + task_durations[i] + "\n\n";
            }
        }
        if (report.isEmpty()) {
            report = "No tasks to display.";
        }
        JOptionPane.showMessageDialog(null, report);
    }

}
