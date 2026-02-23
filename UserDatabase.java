import java.io.*;
import java.util.HashMap;

public class UserDatabase {

    private static HashMap<String, User> users;
    private static final String FILE_NAME = "users.dat";

    public static class User implements Serializable {
        private static final long serialVersionUID = 1L;

        public String name;
        public String password;
        public String role;

        public User(String name, String password, String role) {
            this.name = name;
            this.password = password;
            this.role = role;
        }
    }

    static { load(); }

    public static boolean registerUser(String id, String name, String password) {

        if(id == null || id.isEmpty()) return false;
        if(users.containsKey(id)) return false;

        users.put(id, new User(name, password, "USER"));
        save();
        return true;
    }

    public static boolean checkLogin(String id, String pass) {

        User u = users.get(id);
        return u != null && u.password.equals(pass);
    }

    public static String getName(String id) {

        User u = users.get(id);
        return (u != null) ? u.name : null;
    }

    public static String getRole(String id) {

        User u = users.get(id);
        return (u != null) ? u.role : null;
    }

    public static HashMap<String, User> getAllUsers() {

        return new HashMap<>(users); // return copy (safe)
    }

    private static void save() {

        try(ObjectOutputStream o =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE_NAME))) {

            o.writeObject(users);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void load() {

        try(ObjectInputStream i =
                    new ObjectInputStream(
                            new FileInputStream(FILE_NAME))) {

            users = (HashMap<String,User>) i.readObject();

        } catch(Exception e) {

            users = new HashMap<>();

            // Default Admin
            users.put("admin",
                    new User("Administrator","1234","ADMIN"));
        }
    }
}
