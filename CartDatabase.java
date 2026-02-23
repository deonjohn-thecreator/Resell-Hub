import java.io.*;
import java.util.*;

public class CartDatabase {

    private static HashMap<String,
            ArrayList<ProductDatabase.Product>> cart;

    private static final String FILE = "cart.dat";

    static { load(); }

    public static void addToCart(String user,
                                 ProductDatabase.Product p){

        cart.putIfAbsent(user,new ArrayList<>());
        cart.get(user).add(p);
        save();
    }

    public static ArrayList<ProductDatabase.Product> getCart(String user){

        return new ArrayList<>(
                cart.getOrDefault(user,new ArrayList<>()));
    }

    public static void removeItem(String user,int index){

        if(cart.containsKey(user)){

            ArrayList<ProductDatabase.Product> list =
                    cart.get(user);

            if(index >= 0 && index < list.size()){
                list.remove(index);
                save();
            }
        }
    }

    public static void clearCart(String user){

        if(cart.containsKey(user)){
            cart.remove(user);
            save();
        }
    }

    private static void save(){

        try(ObjectOutputStream o =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE))) {

            o.writeObject(cart);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void load(){

        try(ObjectInputStream i =
                    new ObjectInputStream(
                            new FileInputStream(FILE))) {

            cart = (HashMap<String,
                    ArrayList<ProductDatabase.Product>>) i.readObject();

        } catch(Exception e){

            cart = new HashMap<>();
        }
    }
}