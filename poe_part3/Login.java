import java.util.Scanner;


public class Login {
    
    ST10447519 registration = new ST10447519();
    String  passwordMessage = "Password is not correctly formatted, " + "please ensure the password contains at least 8 characters, a capital letter, a number and a special character";
    String nameMessage = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more" + "than 5 characters in length";
    String passMessage;
    
    // checking the username validation with a funtion/method
   public boolean checkUserName(String userName){
        if(userName.length() <= 5 && userName.contains("_")){
           return true;   
    } else {
     return false;
   }
}
    // method to check password complexity

    public boolean checkPasswordComplexity(String password){
       boolean passwordOkay =false;
       boolean hasNumber =false;
       boolean hasCap =false;
       boolean hasChar = false;
       char current;
       
       if (password.length() >= 8){
           for(int i = 0; i <password.length();i++){
               current = password.charAt(i);
               
               if(Character.isUpperCase(current)){
                   hasCap = true;
               }
               
               if(Character.isDigit(current)){
                   hasNumber = true;
               
               }
               if(!(Character.isLetterOrDigit(current)));
                  hasChar = true;
           }
           if(hasNumber && hasCap && hasChar){
             passwordOkay= true;
           }
          
       }

      return passwordOkay;
    
    }
        // method to register the user and validate username and password
        public String registerUser(String name, String password){   
            
            if(checkPasswordComplexity(password)){
           passwordMessage = "Password sucessfully captured";
       } else {
            passwordMessage = "Password is not correctly formatted ,please ensure that the password contains at least 8 cahracters, a capital letter , a number";
       }        
       if (checkUserName(name)) {
        nameMessage = "Username successfully captured";
       } else {
       nameMessage = "Username is not correctly formatted, please ensure that username contains an underscore and no more5 characters "; 
       }
          return (passwordMessage + "/n" + nameMessage);
       }

        // Method to log in user 
        public boolean loginUser (String userName,String password){
              boolean logged = false;
             
              // keep track of user login activity
             
              if(userName.equals(registration.getUserName()) && password.equals(registration.getPasswordName())){
              logged =true;
        
        }

            return logged;
        }

        // Method to return login status message

        public String returnLoginStatus (boolean regStatus,String name,String surname){
                  String message = "Username or password incorrect, please try again";
                  
                  if(regStatus){
                      message = "Welcome " + name + ", " + "it is great to see you";
                  }
                   return message;   
                 
              }
                  
       }

    

    
   
