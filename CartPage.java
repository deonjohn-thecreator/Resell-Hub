import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CartPage extends JFrame {

    public CartPage(String user){

        setTitle("My Cart");
        setSize(650,400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        ArrayList<ProductDatabase.Product> list =
                CartDatabase.getCart(user);

        String[] cols = {"Name","Price","Seller"};
        DefaultTableModel model = new DefaultTableModel(cols,0);

        double total = 0;

        for(ProductDatabase.Product p : list){
            model.addRow(new Object[]{p.name,p.price,p.seller});
            total += p.price;
        }

        JTable table = new JTable(model);
        table.setRowHeight(28);

        JScrollPane sp = new JScrollPane(table);

        JLabel totalLabel = new JLabel("Total: ₹" + total);

        JPanel bottom = new JPanel();
        JButton remove = new JButton("Remove Selected");
        JButton clear = new JButton("Clear Cart");
        JButton back = new JButton("Back");

        bottom.add(remove);
        bottom.add(clear);
        bottom.add(back);

        remove.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(this,"Select item");
                return;
            }
            CartDatabase.removeItem(user,row);
            dispose();
            new CartPage(user);
        });

        clear.addActionListener(e -> {
            CartDatabase.clearCart(user);
            dispose();
            new CartPage(user);
        });

        back.addActionListener(e -> {
            dispose();
            new HomePage(user);
        });

        add(totalLabel,BorderLayout.NORTH);
        add(sp,BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);

        setVisible(true);
    }
}