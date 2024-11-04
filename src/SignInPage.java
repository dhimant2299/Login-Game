import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInPage implements ActionListener {


    public void showCustomDialog(Component parentComponent, String message, String title, int messageType, int x, int y) {
        JOptionPane optionPane = new JOptionPane(message, messageType);
        JDialog dialog = optionPane.createDialog(parentComponent, title);
        dialog.setLocation(x, y); // Set the custom position
        dialog.setVisible(true);
    }

    JFrame frame = new JFrame();
    JTextField userNameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel userNameLabel = new JLabel("UserID: ");
    JLabel passwordLabel = new JLabel("Password: ");
    JButton signInButton = new JButton("Sign In");
    JButton robotCheckButton = new JButton("Are you a robot?");
    JLabel messageLabel = new JLabel();
    JButton loginButton = new JButton("Already signed in?");
    
    IDandPasswords idAndPasswords;

    SignInPage(IDandPasswords idAndPasswords) {

        
        this.idAndPasswords = idAndPasswords;

        // Set the frame to full screen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Adjust the positions based on full screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Set up the labels, text fields, and buttons
        userNameLabel.setBounds(screenWidth / 2 - 150, screenHeight / 2 - 150, 300, 35);
        userNameLabel.setFont(new Font(null, Font.PLAIN, 20));

        passwordLabel.setBounds(screenWidth / 2 - 150, screenHeight / 2 - 100, 300, 35);
        passwordLabel.setFont(new Font(null, Font.PLAIN, 20));

        userNameField.setBounds(screenWidth / 2 - 50, screenHeight / 2 - 150, 300, 35);
        passwordField.setBounds(screenWidth / 2 - 50, screenHeight / 2 - 100, 300, 35);

        // Robot check button positioned above the Sign-In button
        robotCheckButton.setBounds(screenWidth / 2 - 50, screenHeight / 2 - 50, 250, 40);
        robotCheckButton.setFont(new Font(null, Font.PLAIN, 18));
        robotCheckButton.addActionListener(this);
        robotCheckButton.setFocusable(false);

        signInButton.setBounds(screenWidth / 2 - 50, screenHeight / 2 + 20, 170, 45);
        signInButton.setFont(new Font(null, Font.PLAIN, 20));
        signInButton.addActionListener(this);
        signInButton.setFocusable(false);
        signInButton.setEnabled(false); // Initially disable the sign-in button

        loginButton.setBounds(screenWidth / 2 - 50, screenHeight / 2 + 100, 300, 50); // Position below Sign In button
        loginButton.setFont(new Font(null, Font.PLAIN, 20));
        loginButton.addActionListener(this);

        messageLabel.setBounds(screenWidth / 2 - 150, screenHeight / 2 + 150, 600, 45);
        messageLabel.setFont(new Font(null, Font.PLAIN, 20));
        messageLabel.setFocusable(false);

        frame.add(userNameLabel);
        frame.add(passwordLabel);
        frame.add(userNameField);
        frame.add(passwordField);
        frame.add(robotCheckButton);
        frame.add(signInButton);
        frame.add(loginButton);
        frame.add(messageLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == robotCheckButton) {
            // Open the quiz window when "Are you a robot?" is clicked
            JOptionPane.showMessageDialog(frame, "Let's check if you're a robot!");

            new QuizWindow();
            signInButton.setEnabled(true); // Enable the Sign-In button after passing the robot check
        }
        else if (e.getSource() == signInButton) {
            String username = userNameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                idAndPasswords.addUser(username, password);
                //System.out.println("User added to HashMap.");
                JOptionPane.showMessageDialog(frame, "Sign-In successful! User added.");
                frame.dispose(); // Close current window
            new LoginPage(idAndPasswords.getLogininfo()); // Open the LoginPage
                // Print the HashMap values to the console
                //printHashMap();
            } else {
                //System.out.println("Username or password is empty.");
                showCustomDialog(frame, "No User Found", "Message", JOptionPane.INFORMATION_MESSAGE, 1100, 500);

            }
        } else if (e.getSource() == loginButton) {
            // Redirect to LoginPage if user already has an account
            frame.dispose(); // Close current window
            new LoginPage(idAndPasswords.getLogininfo()); // Open the LoginPage
        }
    

    }

}

// Example QuizWindow class
class QuizWindow {
    JFrame quizFrame = new JFrame("Human vs. Robot Quiz");
    JLabel questionLabel = new JLabel("", JLabel.CENTER);
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup optionsGroup = new ButtonGroup();
    JButton submitButton = new JButton("Submit");
    int currentQuestion = 0;
    int correctAnswers = 0;

    String[][] questions = {
        {"What is your favorite food?", "Electricity", "Wind", "Broccoli", "Sunlight"},
        {"What do you enjoy doing in your free time?", "Procrastinate by watching funny videos on YouTube", "Recharging", "Optimizing your code", "World Domination Planning" },
        {"It’s time to work. Do you:", "Start immediately", "Analyse predictions", "Check social media for 5 minutes." ,"Map out a strategic gameplan"},
        {"What is your favorite color?", "0x0000FF", "#FF5733","Red" ,"101010" },
        {"What do you notice first about someone you like?", " Their processing speed", "How long they can go without recharging", "Their Hardware version", "Their Smile"},
        {"Do you understand women?", "I’m still processing that complex algorithm", "I can predict weather patterns better", "I’ve run countless simulations, but the data is inconclusive", "I try my best, but it’s a lifelong learning process!"},
        {"Your friend's sad, what would you do?", "Analyze their situation", "Suggest they reboot", "Simulate empathy", "Comfort Them"},
        {"How many languages do you speak?", "I can say ‘hello’ in four languages—and count to ten in two" ,"I’m fluent in over 50 programming languages", "I can communicate in binary and machine code", "I’ve mastered all major human languages"},
        {"What’s your favorite movie genre?", "Sci-fi about robot uprisings", "Documentaries about machines", "Instructional videos on coding", "Comedy"},
        {"Do you ever feel lazy?", "I only rest when my batteries run low", "Absolutely, especially on a Sunday afternoon!" ,"I pause for maintenance, not laziness", "I might skip an update once in a while"},
        {"What’s your favorite hobby?", "Calculating pi", "Cleaning my circuits", "Hiking in nature" ,"Updating Software"},
        {"How would you spend time with your crush?", "Running a full diagnostic to optimize their performance", "Giving their sockets a good cleaning", " scan for compatibility", "Having a nice conversation"}
        
    };

    int[] correctAnswersIndex = {2, 0, 2, 2, 3, 3, 3, 0, 3, 1, 2, 3}; 

    QuizWindow() {
        quizFrame.setSize(500, 400);
        quizFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        quizFrame.setLayout(new BorderLayout());
        quizFrame.setLocationRelativeTo(null); // Center the frame on the screen

        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add padding
        quizFrame.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Add padding

        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 16));
            options[i].setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            options[i].setFocusable(false); // Disable focus for each radio button
            optionsGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }

        quizFrame.add(optionsPanel, BorderLayout.CENTER);

        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add padding around the button
        buttonPanel.add(submitButton);
        quizFrame.add(buttonPanel, BorderLayout.SOUTH);

        loadQuestion();

        quizFrame.setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            questionLabel.setText(questions[currentQuestion][0]);
            for (int i = 0; i < options.length; i++) {
                options[i].setText(questions[currentQuestion][i + 1]);
                options[i].setSelected(false); // Deselect all options for the new question
            }
        } else {
            // Show the final result
            JOptionPane.showMessageDialog(quizFrame, "Quiz completed! You got " + correctAnswers + " out of " + questions.length + " correct.");
            quizFrame.dispose(); // Close the quiz window
        }
    }

    private void checkAnswer() {
        if (options[correctAnswersIndex[currentQuestion]].isSelected()) {
            correctAnswers++;
        }
        currentQuestion++;
        loadQuestion();
    }
    }
