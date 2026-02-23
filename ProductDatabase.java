import java.io.*;
import java.util.*;

public class ProductDatabase {

    public static class Product implements Serializable {
        private static final long serialVersionUID = 1L;

        public String name;
        public String category;
        public String seller;
        public double price;

        public Product(String n,String c,double p,String s){
            name = n;
            category = c;
            price = p;
            seller = s;
        }
    }

    private static HashMap<String, ArrayList<Product>> products;
    private static final String FILE = "products.dat";

    static { load(); }

    public static void addProduct(String n,String c,double p,String s){

        if(!products.containsKey(c)){
            products.put(c,new ArrayList<>());
        }

        products.get(c).add(new Product(n,c,p,s));
        save();
    }

    public static ArrayList<Product> getProducts(String c){

        if(products.containsKey(c))
            return new ArrayList<>(products.get(c)); // safe copy

        return new ArrayList<>();
    }

    public static void removeProduct(String c,int index){

        if(products.containsKey(c)){
            ArrayList<Product> list = products.get(c);

            if(index >= 0 && index < list.size()){
                list.remove(index);
                save();
            }
        }
    }

    private static void save(){

        try(ObjectOutputStream o =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE))) {

            o.writeObject(products);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void load(){

        try(ObjectInputStream i =
                    new ObjectInputStream(
                            new FileInputStream(FILE))) {

            products = (HashMap<String,
                    ArrayList<Product>>) i.readObject();

        } catch(Exception e){

            products = new HashMap<>();

            products.put("CARS", new ArrayList<>());
            products.put("BIKES", new ArrayList<>());
            products.put("OTHERS", new ArrayList<>());
        }
    }
}