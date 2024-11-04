import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WelcomePage {
    
JFrame frame = new JFrame();
JLabel welcomeLabel = new JLabel("Hello!");

WelcomePage(String userID){
    // Set the frame to full screen but keep window decorations
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    // frame.setUndecorated(true); 
    frame.setLayout(new BorderLayout());

    welcomeLabel.setFont(new Font(null, Font.PLAIN, 50));
    welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    welcomeLabel.setText("Hello " + userID + "! Congatulations on your successful login.");

    frame.add(welcomeLabel, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setVisible(true);

}

}


