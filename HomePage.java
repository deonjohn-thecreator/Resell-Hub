import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    public HomePage(String user){

        setTitle("User Dashboard");
        setSize(700,450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15,15));

        getContentPane().setBackground(new Color(240,240,240));

        JLabel welcome = new JLabel(
                "Welcome " + UserDatabase.getName(user),
                SwingConstants.CENTER);
        welcome.setFont(new Font("Segoe UI",Font.BOLD,22));

        add(welcome,BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(1,3,20,20));
        center.setBackground(new Color(240,240,240));
        center.setBorder(
                BorderFactory.createEmptyBorder(20,40,20,40));

        JButton cars = createButton("CARS");
        JButton bikes = createButton("BIKES");
        JButton others = createButton("OTHERS");

        center.add(cars);
        center.add(bikes);
        center.add(others);

        add(center,BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(240,240,240));

        JButton add = createButton("Add Product");
        JButton cart = createButton("View Cart");
        JButton logout = createButton("Logout");

        bottom.add(add);
        bottom.add(cart);
        bottom.add(logout);

        add(bottom,BorderLayout.SOUTH);

        cars.addActionListener(e -> { dispose(); new ProductPage("CARS",user); });
        bikes.addActionListener(e -> { dispose(); new ProductPage("BIKES",user); });
        others.addActionListener(e -> { dispose(); new ProductPage("OTHERS",user); });

        cart.addActionListener(e -> { dispose(); new CartPage(user); });

        add.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Product Name:");
            if(name == null || name.isEmpty()) return;

            String[] cat = {"CARS","BIKES","OTHERS"};
            String c = (String) JOptionPane.showInputDialog(
                    this,"Category","Select",
                    JOptionPane.QUESTION_MESSAGE,null,cat,cat[0]);

            if(c == null) return;

            try{
                double price = Double.parseDouble(
                        JOptionPane.showInputDialog("Price:"));
                ProductDatabase.addProduct(name,c,price,user);
                JOptionPane.showMessageDialog(this,"Product Added!");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,"Invalid Price");
            }
        });

        logout.addActionListener(e -> { dispose(); new Login(); });

        setVisible(true);
    }

    private JButton createButton(String text){
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setBackground(new Color(41,128,185));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI",Font.BOLD,16));
        return b;
    }
}
