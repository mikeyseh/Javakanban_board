import javax.swing.JOptionPane;

public class TaskDetails {
    Task task = new Task();
    // These variables will contain the task details provided by the user.
    private static String taskName;
    private static int chosenOption;
    private static int numberOfTasks;
    private static int taskDuration;
    private static int taskNumber;
    private static String taskDescription;
    private static String developerNames;
    private static String taskStatus;
    static int totalHours = 0;
    static String taskDetails;

    // Getter and setter the name of the task
    public void setTaskName(String name) {
        taskName = name;
    }

    public String getTaskName() {
        return taskName;
    }

    // get the number selected by the user from the numeric options
    public int getChosenOPtion() {
        return chosenOption;
    }

    // set and get the number of tasks the user wishes to enter
    public void setNumberOfTasks(int number) {
        numberOfTasks = number;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    // set and get the estimated duration of each task
    public void setTaskDuration(int duration) {
        taskDuration = duration;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    // Setter and getter description of each chapter
    public void setTaskDescription(String description) {
        taskDescription = description;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    // The user will be given a warning and a chance to re-enter the task
    // description if it is longer than 50 words.
    public void DescriptionStatus() {
        while (!(task.checkTaskDescription(getTaskDescription()))) {
            JOptionPane.showMessageDialog(null, "Please enter a task description of not more than 50 characters");
            setTaskDescription(JOptionPane.showInputDialog("Please enter a task description"));
        }

        JOptionPane.showMessageDialog(null, "Task Successfully captured.");
    }

    // set and return the full names of the developer assigned to a task
    public void setDeveloperNames(String names) {
        developerNames = names;
    }

    public String getDeveloperNames() {
        return developerNames;
    }

    public int welcomeAndChooseOption(boolean login) {
        /*
         * The user is only welcomed to KanBan if they logged in successfully.
         */
        if (login) {
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban\n");

            // the do-while loop will only stop if the user selects three from the numeric
            // menu
            do {
                chosenOption = Integer.parseInt(JOptionPane.showInputDialog(

                        "PLEASE SELECT AN OPTION\n"
                                + "Option 1) Add tasks\n"
                                + "Option 2) Show Report\n"
                                + "Option 3) Quit"));

                switch (chosenOption) {

                    case 1: // if the user chooses 1, they will be allowed to add tasks.

                        break;
                    case 2: // this option tells the user the feature is still in development
                        JOptionPane.showMessageDialog(null, "Coming soon");
                        break;
                    case 3: // this is the option that terminates the loop
                        chosenOption = 3;
                        break;

                }
            } while (chosenOption != 3);

        }
        return chosenOption;
    }

    // set and return the number of the current task
    public void setNumberOfCurrentTask(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getNumberOfCurrentTask() {
        return taskNumber;
    }

    /*
     * Allow the user to choose the status of their task
     */
    public void setTaskStatus() {
        int option = Integer
                .parseInt(JOptionPane.showInputDialog("Please choose the Status of this task from the three options.\n"
                        + "1) To Do\n"
                        + "2) Doing\n"
                        + "3) Done"));
        switch (option) {
            case 1:
                taskStatus = "To Do";
                break;
            case 2:
                taskStatus = "Doing";
                break;
            case 3:
                taskStatus = "Done";
                break;
            default:
                taskStatus = "Invalid selection!! Please pick an option from the number from 1 to 3";
        }
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void addTasks() {

        /*
         * get the number of tasks from the user and use it to control the loop that
         * allows the user to add tasks
         */

        setNumberOfTasks(Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks you want to add")));

        for (int i = 0; i < getNumberOfTasks(); i++) {
            // Call all the othe setters

            setTaskStatus();
            setNumberOfCurrentTask(i);
            setTaskName(JOptionPane.showInputDialog("Enter task name "));
            setTaskDuration(Integer.parseInt(JOptionPane.showInputDialog("Enter the duration of this task")));

            setDeveloperNames(JOptionPane.showInputDialog("Please enter the developer's first name and surname"));
            setTaskDescription(JOptionPane.showInputDialog("Please enter a task description"));
            DescriptionStatus();

            // Print out the Details of the task
            JOptionPane.showMessageDialog(null, task.printTaskDetails(getTaskStatus(), getDeveloperNames(),
                    getNumberOfCurrentTask(), getTaskName(), getTaskDescription(), getTaskDuration()));

            // Calculate the total number of hours needed to complete tasks.
            totalHours = totalHours + getTaskDuration();

        }
        // Print out the total number of hours needed to complete all tasks.
        JOptionPane.showMessageDialog(null, "You need " + totalHours + " hours to complete these tasks.");

    }

    public static void main(String[] args) {
        TaskDetails taskDetails = new TaskDetails();

        // Assuming the user is sucessfully logged in , passing tur to the method
        boolean login = true;

        // Calling the method that welcomes the user and prompts them to choose an
        // option
        int option = taskDetails.welcomeAndChooseOption(login);

        // if the user selects the option to add tasks(option 1), call the addTasks
        // method
        if (option == 1) {
            taskDetails.addTasks();
        }
    }
}
