import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            System.out.println("\n\t******************************E-Commerce-Console-Application***********************************\n");
            Scanner sc = new Scanner(System.in);
            AccountManager accountManager = new AccountManager();
            Inventory inventory = new Inventory();
            while (true) {
                boolean exitMainMenu = false;
                System.out.print("\t**********************************************************************************************\n");
                System.out.print("\t*                                                                                            *\n");
                System.out.print("\t*                  1. ADMIN - LOGIN                                                          *\n");
                System.out.print("\t*                  2. CUSTOMER - LOGIN                                                       *\n");
                System.out.print("\t*                  3. ADMIN - SIGN-UP                                                          *\n");
                System.out.print("\t*                  4. CUSTOMER -SIGN-UP                                                      *\n");
                System.out.print("\t*                  5. EXIT                                                                   *\n");
                System.out.print("\t*                                                                                            *\n");
                System.out.print("\t**********************************************************************************************\n");
                System.out.print("-->");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: //Admins portal
                    {
                        boolean allowAccess = true;
                        System.out.println("*****************Welcome to Admins portal***********************");

                        String un;
                        String pd;
                        System.out.print("FIRST NAME--> ");
                        un = sc.next();
                        System.out.print("Password--> ");
                        pd = sc.next();
                        Admin adminAccount = accountManager.findAdminAccount(un, pd);

                        if (adminAccount != null) {
                            while (true) {

                                System.out.print("\t**********************************************************************************************\n");
                                System.out.print("\t*                                                                                            *\n");
                                System.out.print("\t*                  1. SHOW INVENTORY                                                         *\n");
                                System.out.print("\t*                  2. ADD PRODUCT                                                            *\n");
                                System.out.print("\t*                  3. REMOVE PRODUCT                                                         *\n");
                                System.out.print("\t*                  4. UPDATE PRICE                                                           *\n");
                                System.out.print("\t*                  5. UPDATE QUANTITY IN STOCK                                               *\n");
                                System.out.print("\t*                  6. LOGOUT                                                                 *\n");
                                System.out.print("\t*                                                                                            *\n");
                                System.out.print("\t**********************************************************************************************\n");
                                System.out.print("--> ");
                                int ch = sc.nextInt();
                                switch (ch) {
                                    case 1: {
                                        adminAccount.ProductList(inventory);
                                        break;
                                    }
                                    case 2: {
                                        adminAccount.addProduct(inventory);
                                        break;
                                    }
                                    case 3: {
                                        System.out.print("Product ID: ");
                                        int id = sc.nextInt();
                                        adminAccount.removeProduct(id, inventory);
                                        break;
                                    }
                                    case 4:
                                    {
                                        System.out.print("Product ID: ");
                                        int id = sc.nextInt();
                                        inventory.updatePrice(id);
                                        break;
                                    }
                                    case 5:
                                    {
                                        System.out.print("Product ID: ");
                                        int id = sc.nextInt();
                                        inventory.updateQuantity(id);
                                        break;
                                    }
                                    case 6: {
                                        allowAccess = false;
                                        break;
                                    }
                                    default: {
                                        System.out.println("Please pick a valid option");
                                    }
                                }
                                if (!allowAccess) {
                                    break;
                                }
                            }

                        } else {
                            System.out.println("Invalid Username or Password");
                        }

                        break;

                    }

                    case 2: {
                        System.out.println("*****************Welcome to Customers portal***********************");

                        String un;
                        String pd;
                        System.out.print("FIRST NAME--> ");
                        un = sc.next();
                        System.out.print("Password--> ");
                        pd = sc.next();
                        Customer customerAccount = accountManager.findCustomerAccount(un, pd);
                        if (customerAccount != null) {
                            while (true) {
                                boolean exit = false;
                                System.out.print("\t**********************************************************************************************\n");
                                System.out.print("\t*                                                                                            *\n");
                                System.out.print("\t*                  1. SHOW ALL PRODUCTS                                                      *\n");
                                System.out.print("\t*                  2. SEARCH PRODUCTS                                                        *\n");
                                System.out.print("\t*                  3. ADD TO CART                                                            *\n");
                                System.out.print("\t*                  4. REMOVE FROM CART                                                       *\n");
                                System.out.print("\t*                  5. UPDATE CART                                                            *\n");
                                System.out.print("\t*                  6. CLEAR CART                                                             *\n");
                                System.out.print("\t*                  7. VIEW CART                                                              *\n");
                                System.out.print("\t*                  8. ADD FEEDBACK                                                           *\n");
                                System.out.print("\t*                  9. CHECKOUT                                                               *\n");
                                System.out.print("\t*                  10. VIEW ORDER HISTORY                                                     *\n");
                                System.out.print("\t*                  11. CANCEL ORDER                                                          *\n");
                                System.out.print("\t*                  12. LOGOUT                                                                *\n");
                                System.out.print("\t*                                                                                            *\n");
                                System.out.print("\t**********************************************************************************************\n");
                                System.out.print("--> ");
                                int ch = sc.nextInt();
                                switch (ch) {
                                    case 1:
                                    {
                                        customerAccount.ProductList(inventory);
                                        break;
                                    }
                                    case 2: {
                                        customerAccount.searchProduct(inventory);
                                        break;

                                    }
                                    case 3: {

                                        customerAccount.addProductToCart(inventory);
                                        break;
                                    }
                                    case 4: {
                                        customerAccount.removeProductFromCart();
                                        break;
                                    }
                                    case 5: {
                                        customerAccount.updateCart();
                                        break;
                                    }
                                    case 6: {
                                        customerAccount.clearCart();
                                        break;
                                    }
                                    case 7: {
                                        customerAccount.printCart();
                                        break;
                                    }
                                    case 8:
                                    {
                                        System.out.print("Product ID --> ");
                                        int productID=sc.nextInt();
                                        if(inventory.getProduct(productID)!=null){
                                            customerAccount.addRating(productID);
                                        }

                                        break;
                                    }
                                    case 9: {
                                        System.out.println("*********************** Checkout ***********************");
                                        customerAccount.checkout();
                                        break;
                                    }
                                    case 10: {
                                        System.out.println("*********************** Order History ***********************");
                                        customerAccount.printOrderHistory();
                                        break;
                                    }
                                    case 11: {
                                        customerAccount.cancelOrder();
                                        break;
                                    }
                                    case 12: {
                                        exit = true;
                                        break;
                                    }
                                    default: {
                                        System.out.println("Please pick a valid option");
                                    }
                                }
                                if (exit) {
                                    break;
                                }


                            }
                        } else {
                            System.out.println("Invalid Username or Password");
                        }
                        break;


                    }
                    case 3: {
                        accountManager.addAdmin();
                        break;
                    }
                    case 4: {
                        accountManager.addCustomer();

                        break;
                    }
                    case 5: {
                        exitMainMenu = true;
                        break;
                    }
                    default: {
                        System.out.println("Please pick a valid option");
                    }

                }
                if (exitMainMenu) {
                    break;
                }


            }

        }catch (Exception e){
            System.out.println("Something went Wrong");
        }
        }

}

