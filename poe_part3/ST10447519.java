import java.util.Scanner;

public class ST10447519 {
     private static String userName;
     private static String firstName;
     private static String surname ;
     private static String password;
     
      // Set and get methods
     public static void  setFirstName(String name){
        firstName = name;
    }
     
     public static String getFirstName(){
        return firstName;
     
     }
     
        public static void setUserName(String usname){
        userName = usname;
         
     
     }
     
     public String getUserName(){
        return this.userName;
     
     }
      public static void setSurname(String  srname){
        surname = srname;
        
     
     }
     
     public static String getSurname(){
        return surname;
     
     }
     
     
        public static void setPasswordName(String pass){
         password = pass;
        
     
     }
     
     public String getPasswordName(){
        
         return this.password;
     
     }
    
     public static void main(String[] args) {
        
        Login mike = new Login();
        Scanner input = new Scanner(System.in);
        
        // getting user details
        System.out.println("****Registration****");
        
        
        System.out.println("Enter your first name >>");
         firstName =  input.nextLine();

        System.out.println("Enter your surname >>>");
         surname = input.nextLine();

        System.out.println("Enter your password");
         password = input.nextLine();

        System.out.println("Enter your user name. Note: User name must be less than 5 characters long and contain an underscore");
         userName = input.nextLine();
        
         // The registration process
         if (mike.loginUser(userName, password)){
            System.out.println(mike.returnLoginStatus(true,getFirstName(),getSurname()));
          } else{ 
             System.out.println(mike.returnLoginStatus(false,getFirstName(),getSurname()));
         }
         System.out.println("\n****Login****");
         System.out.println("Please enter the username you provided earlier >>");
         String inputUsername = input.nextLine();
         System.out.println("Please enter the password you entered earlier >>");
         String inputPassword = input.nextLine();
         
         boolean loginResult = mike.loginUser(inputUsername, inputPassword);
         System.out.println(mike.returnLoginStatus(loginResult, getFirstName(), getSurname()));
    }
    
    
}
