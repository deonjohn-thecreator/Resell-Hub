import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    public Login(){

        setTitle("Industry Marketplace Login");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15,15));

        getContentPane().setBackground(new Color(240,240,240));

        JLabel title = new JLabel("Marketplace Login",SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,22));
        add(title,BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(2,2,10,10));
        center.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
        center.setBackground(new Color(240,240,240));

        JTextField id = new JTextField();
        JPasswordField pass = new JPasswordField();

        center.add(new JLabel("User ID:"));
        center.add(id);
        center.add(new JLabel("Password:"));
        center.add(pass);

        add(center,BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(240,240,240));

        JButton login = createButton("Login");
        JButton register = createButton("Register");

        bottom.add(login);
        bottom.add(register);

        add(bottom,BorderLayout.SOUTH);

        login.addActionListener(e -> {

            String user = id.getText();
            String password = new String(pass.getPassword());

            if(user.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(this,"Fill all fields");
                return;
            }

            if(UserDatabase.checkLogin(user,password)){
                String role = UserDatabase.getRole(user);
                dispose();
                if("ADMIN".equals(role))
                    new AdminPage(user);
                else
                    new HomePage(user);
            } else {
                JOptionPane.showMessageDialog(this,"Invalid Login");
            }
        });

        register.addActionListener(e -> {

            String uid = JOptionPane.showInputDialog("User ID:");
            if(uid == null || uid.isEmpty()) return;

            String name = JOptionPane.showInputDialog("Name:");
            if(name == null || name.isEmpty()) return;

            JPasswordField pf = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(null,pf,
                    "Enter Password",JOptionPane.OK_CANCEL_OPTION);

            if(option == JOptionPane.OK_OPTION){
                String pw = new String(pf.getPassword());
                if(UserDatabase.registerUser(uid,name,pw))
                    JOptionPane.showMessageDialog(this,"Registered!");
                else
                    JOptionPane.showMessageDialog(this,"User Exists!");
            }
        });

        setVisible(true);
    }

    private JButton createButton(String text){
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setBackground(new Color(41,128,185));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI",Font.BOLD,14));
        return b;
    }

    public static void main(String[] a){
        new Login();
    }
}