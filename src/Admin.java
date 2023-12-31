
import java.util.regex.Pattern;

public class Admin extends Person{
    private int adminID;
    private String password;
    //grants access to inventory
    public static final class InventoryAccess{
        private InventoryAccess(){}
    }
    private static final InventoryAccess inventoryAccess = new InventoryAccess();

    public Admin(int id){
        System.out.println("*** Fill the following details ***");
        UserInformation();
        this.adminID=id;
        System.out.println("Password Requirements :");
        System.out.println("    1-Minimum length of 8 characters");
        System.out.println("    2-At least one uppercase letter");
        System.out.println("    3-At least one lowercase letter");
        System.out.println("    4-At least one digit");
        System.out.println("    5-At least one special character");
        System.out.println("    6-No whitespace allowed");
        System.out.println("Password: ");
        password = sc.nextLine();
        //Check if the password is valid
        while(!isValidPassword(password)){
            System.out.println("Password not valid!!");
            System.out.println("new Password:");
            password = sc.next();
        }

    }

    public void ProductList(Inventory inventory){
        inventory.printProducts();
    }
    public void addProduct(Inventory inventory){
        int id = (int)(Math.random() * 900) + 100;
        while(!inventory.uniqueProductID(id)){
            id = (int)(Math.random() * 900) + 100;
        }
        Product p = new Product(id);
        inventory.addProduct(p,inventoryAccess);
    }
    public void removeProduct(int id,Inventory inventory){
        inventory.removeProduct(id,inventoryAccess);
    }

    public int getAdminID(){
        return adminID;
    }

    public String getPassword(){
        return password;
    }

    public String getUserName(){
        return First_Name;
    }
    private boolean isValidPassword(String password) {
        // Minimum length of 8 characters
        if (password.length() < 8) {
            return false;
        }

        // At least one uppercase letter
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            return false;
        }

        // At least one lowercase letter
        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            return false;
        }

        // At least one digit
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            return false;
        }

        // At least one special character
        if (!Pattern.compile("[^a-zA-Z0-9]").matcher(password).find()) {
            return false;
        }

        // No whitespace allowed
        if (Pattern.compile("\\s").matcher(password).find()) {
            return false;
        }

        return true;
    }

}





