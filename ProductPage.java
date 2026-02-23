import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ProductPage extends JFrame {

    public ProductPage(String category,String user){

        setTitle(category+" Products");
        setSize(650,400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        ArrayList<ProductDatabase.Product> list =
                ProductDatabase.getProducts(category);

        String[] col={"Name","Price","Seller"};
        DefaultTableModel model=new DefaultTableModel(col,0);

        for(ProductDatabase.Product p:list)
            model.addRow(new Object[]{p.name,p.price,p.seller});

        JTable table=new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI",Font.PLAIN,14));
        table.getTableHeader().setFont(
                new Font("Segoe UI",Font.BOLD,14));

        JScrollPane sp=new JScrollPane(table);

        JPanel bottom=new JPanel();
        JButton cart=new JButton("Add to Cart");
        JButton back=new JButton("Back");

        bottom.add(cart);
        bottom.add(back);

        cart.addActionListener(e->{
            int row=table.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(this,"Select product");
                return;
            }
            CartDatabase.addToCart(user,list.get(row));
            JOptionPane.showMessageDialog(this,"Added to Cart");
        });

        back.addActionListener(e->{ dispose(); new HomePage(user); });

        add(sp,BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);

        setVisible(true);
    }
}
