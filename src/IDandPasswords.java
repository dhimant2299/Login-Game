import java.util.HashMap;

public class IDandPasswords {

    HashMap<String,String> logininfo = new HashMap<String, String>();

    IDandPasswords(){
        logininfo.put("Dhimant", "sand");
        logininfo.put("a", "s");
    }

    protected HashMap<String, String> getLogininfo() {
        return logininfo;
    }

        // Method to add a new user
        public void addUser(String username, String password) {
            logininfo.put(username, password);
        }

    
}


