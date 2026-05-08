public class Main{
       public static void main(String[] args){
         TaskDetails taskDetails = new TaskDetails();
       
         //Assuming the user is sucessfully logged in , passing tur to the method
         boolean login = true;
         
         //Calling the method that welcomes the user and prompts them to choose an option
         int option = taskDetails.welcomeAndChooseOption(login);
         
         // if the user selects the option to add tasks(option 1), call the addTasks method
         if(option == 1) {
          taskDetails.addTasks();
         }
       
       }
    }
  



  