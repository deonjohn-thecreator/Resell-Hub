import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class AdminPage extends JFrame {

    public AdminPage(String admin){

        setTitle("Admin Panel");
        setSize(450,350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JLabel title = new JLabel("Admin Dashboard",
                SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,20));

        add(title,BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(3,1,15,15));
        center.setBorder(
                BorderFactory.createEmptyBorder(20,50,20,50));

        JButton viewUsers=new JButton("View Users");
        JButton logout=new JButton("Logout");

        center.add(viewUsers);
        center.add(logout);

        add(center,BorderLayout.CENTER);

        viewUsers.addActionListener(e->{

            HashMap<String, UserDatabase.User> users =
                    UserDatabase.getAllUsers();

            StringBuilder sb = new StringBuilder("Users:\n\n");

            for(String id : users.keySet()){
                sb.append("ID: ")
                        .append(id)
                        .append(" | Name: ")
                        .append(users.get(id).name)
                        .append(" | Role: ")
                        .append(users.get(id).role)
                        .append("\n");
            }

            JOptionPane.showMessageDialog(this,sb.toString());
        });

        logout.addActionListener(e->{ dispose(); new Login(); });

        setVisible(true);
    }
}
