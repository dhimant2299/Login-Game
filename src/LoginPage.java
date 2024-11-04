import java.util.HashMap;
import java.util.Random;
import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("User ID:");
    JLabel userPasswordLabel = new JLabel("Password:");
    JLabel messageLabel = new JLabel();

    HashMap<String, String> logininfo = new HashMap<String, String>();

    public void showCustomDialog(Component parentComponent, String message, String title, int messageType, int x, int y) {
        JOptionPane optionPane = new JOptionPane(message, messageType);
        JDialog dialog = optionPane.createDialog(parentComponent, title);
        dialog.setLocation(x, y); // Set the custom position
        dialog.setVisible(true);
    }
    

    // Declare isLoginButtonFrozen as an instance variable
    boolean isLoginButtonFrozen = false;
    MouseAdapter loginButtonMouseListener;

    LoginPage(HashMap<String,String> loginInfoOriginal ){
        logininfo = loginInfoOriginal;

     
    // Set the frame to full screen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);

    
    // Adjust the positions based on full screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
    

        // Increase label size
        userIDLabel.setBounds(screenWidth / 2 - 150, screenHeight / 2 - 75, 150, 35); // Increased width and height
        userIDLabel.setFont(new Font(null, Font.PLAIN, 20)); // Increased font size

        userPasswordLabel.setBounds(screenWidth / 2 - 150, screenHeight / 2 - 25, 150, 35); // Increased width and height
        userPasswordLabel.setFont(new Font(null, Font.PLAIN, 20)); // Increased font size

        // Increase message label size
        // Position the messageLabel higher above the buttons
        messageLabel.setBounds(screenWidth / 2 - 150, screenHeight / 2 + 100, 500, 45); // Moved y position down by increasing y value
        messageLabel.setFont(new Font(null, Font.PLAIN, 30)); // Font size remains the same


        // Increase text field size
        userIDField.setBounds(screenWidth / 2 - 50, screenHeight / 2 - 75, 300, 35); // Increased width and height
        userPasswordField.setBounds(screenWidth / 2 - 50, screenHeight / 2 - 25, 300, 35); // Increased width and height

        // Increase button size
        loginButton.setBounds(screenWidth / 2 - 50, screenHeight / 2 + 50, 150, 40); // Increased width and height
        loginButton.setFont(new Font(null, Font.PLAIN, 18)); // Increased font size

        resetButton.setBounds(screenWidth / 2 + 110, screenHeight / 2 + 50, 150, 40); // Increased width and height
        resetButton.setFont(new Font(null, Font.PLAIN, 18)); // Increased font size

        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setFocusable(false);

        

        // Define the MouseListener for the login button
        loginButtonMouseListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isLoginButtonFrozen) {
                    moveLoginButton();
                }
            }
        };

        // Add the MouseListener to the login button
        loginButton.addMouseListener(loginButtonMouseListener);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                freezeLoginButton();
            }
        });


        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);

        frame.add(messageLabel);
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
}

        // Method to move the loginButton to a random location
        public void moveLoginButton() {
            Random random = new Random();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            // Ensure the button stays within the visible area of the screen
            int maxX = screenSize.width - loginButton.getWidth();
            int maxY = screenSize.height - loginButton.getHeight();
            int x = random.nextInt(maxX);
            int y = random.nextInt(maxY);
            loginButton.setLocation(x, y);
        }

    // Method to freeze the loginButton for 3 seconds and reset the fields
    public void freezeLoginButton() {
        // Clear the username and password fields
        userIDField.setText("");
        userPasswordField.setText("");

        // Freeze the Login button by removing the MouseListener
        isLoginButtonFrozen = true;
        loginButton.removeMouseListener(loginButtonMouseListener);

        //JOptionPane.showMessageDialog(frame, "Login Button Frozen for 10 seconds!");
        //showCustomDialog(frame, "Login Button Frozen for 10 seconds!", "HURRRAYYY", JOptionPane.INFORMATION_MESSAGE, 00, 00);
        
        
        Timer freezeTimer = new Timer(15000, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginButtonFrozen = false;
                loginButton.addMouseListener(loginButtonMouseListener); // Re-add the MouseListener
                messageLabel.setText("");
            }
        });
        freezeTimer.setRepeats(false);
        freezeTimer.start();
        //showCustomDialog(frame, "Login Button Frozen for 10 seconds!", "HURRRAYYY", JOptionPane.INFORMATION_MESSAGE, 00, 00);
        messageLabel.setForeground(Color.red);
        messageLabel.setText("LOGIN FREEZE 15 seconds!!");
        //showCustomDialog(frame, "Login Button Frozen for 10 seconds!", "HURRRAYYY", JOptionPane.INFORMATION_MESSAGE, 2250, 00);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

       /*  if (e.getSource()==resetButton){
            userIDField.setText("");
            userPasswordField.setText("");
        }*/

        if (e.getSource()==loginButton){
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());
            
            if (logininfo.containsKey(userID)){
                if(logininfo.get(userID).equals(password)){
                    showCustomDialog(frame, "User successfully logged in!", "Success", JOptionPane.INFORMATION_MESSAGE, 500, 100);
                    frame.dispose();
                    WelcomePage welcomePage = new WelcomePage(userID);
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Wrong Password!");
                }
            

            }
            else {
                showCustomDialog(frame, "Username not found.", "Error", JOptionPane.ERROR_MESSAGE, 1100, 500);

            }
            
        }
       
    }

    
}
 