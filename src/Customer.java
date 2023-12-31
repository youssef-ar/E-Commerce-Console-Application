import java.util.*;
import java.util.regex.Pattern;


public class Customer extends Person {
    private String password;
    private int customerID;
    private ShoppingCart shoppingCart;
    private ArrayList<Order> orderHistory;
    Scanner sc=new Scanner(System.in);
    public Customer(int id){
        shoppingCart=new ShoppingCart();
        orderHistory = new ArrayList<Order>();
        System.out.println("*** Fill the following details ***");
        UserInformation();
        this.customerID=id;
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

    public int getCustomerID(){
        return customerID;
    }

    public String getPassword(){
        return password;
    }
    public String getUserName(){
        return First_Name;
    }

    public void searchProduct(Inventory inventory){
        ArrayList<Product> searchresult = inventory.getProductList();
        System.out.print("--> ");
        //sc.nextLine();
        String search = sc.nextLine();
        sc.nextLine();
        searchresult=inventory.searchProduct(search);
        System.out.print("Filter results? [Y][N] : ");
        String filter = sc.next();
        if(filter.toLowerCase().equals("y")) {
            while (true){
                boolean exitFilter = false;
                System.out.print("\t************************************* Filter Menu ********************************************\n");
                System.out.print("\t*                                                                                            *\n");
                System.out.print("\t*                  1. PRICE                                                                  *\n");
                System.out.print("\t*                  2. CATEGORY                                                               *\n");
                System.out.print("\t*                  3. NOT OUT OF STOCK                                                       *\n");
                System.out.print("\t*                  4. RATING                                                                 *\n");
                System.out.print("\t*                  5. EXIT FILTER MENU                                                       *\n");
                System.out.print("\t*                                                                                            *\n");
                System.out.print("\t**********************************************************************************************\n");
                System.out.print("--> ");
                int ch = sc.nextInt();
                switch (ch)
                {
                    case 1:
                    {
                        System.out.println("MINIMUM PRICE ---> ");
                        float min = sc.nextFloat();
                        System.out.println("MAXIMUM PRICE ---> ");
                        float max = sc.nextFloat();
                        searchresult=filterPrice(min,max,searchresult);
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Category --->");
                        sc.nextLine();
                        String category = sc.nextLine();
                        searchresult=filterCategory(category,searchresult);
                        break;
                    }
                    case 3:
                    {
                        System.out.print("Minimum Rating out of 10 ---> ");
                        double rating = sc.nextDouble();
                        while(rating>10){
                            System.out.print("Minimum Rating out of 10 ---> ");
                            rating = sc.nextDouble();
                        }
                        searchresult=filterRating(searchresult,rating);

                    }
                    case 4:
                    {
                        searchresult=filterOutOfStock(searchresult);

                        break;
                    }
                    case 5:
                    {
                        exitFilter=true;
                        break;
                    }
                    default:
                    {
                        System.out.println("Please Choose An Appropriate Option!!!");
                    }
                }
                if(exitFilter){
                    break;
                }
            }
        }
        printProducts(searchresult);
        }

    public void addProductToCart(Inventory inventory){
        int productId;
        int quantity;
        System.out.print("PRODUCT ID --> ");
        productId=sc.nextInt();
        System.out.print("QUANTITY --> ");
        quantity=sc.nextInt();
        Product p = inventory.getProduct(productId);
        if(p!=null){
            shoppingCart.addProduct(p,quantity);
        }
    }
    public void ProductList(Inventory inventory){
        inventory.printProducts();
    }

    public void removeProductFromCart(){
        int productId;
        System.out.print("PRODUCT ID --> ");
        productId=sc.nextInt();
        shoppingCart.removeProduct(productId);
    }
    public void printCart(){
        if(shoppingCart.getShoppingCart().size()>0){
            shoppingCart.printCart();
            System.out.println("Total price before tax: "+shoppingCart.getTotalPrice()+" $");
            System.out.println("Total price after tax: "+(shoppingCart.getTotalPrice()+(shoppingCart.getTotalPrice()*shoppingCart.getTaxRate()/100))+" $");
        }
        else{
            System.out.println("CART IS EMPTY");
        }
    }

    public void updateCart(){
        int productId;
        int quantity;
        System.out.print("PRODUCT ID --> ");
        productId=sc.nextInt();
        System.out.print("NEW QUANTITY --> ");
        quantity=sc.nextInt();
        shoppingCart.updateCart(productId,quantity);
    }
    public void clearCart(){
        shoppingCart.removeAllProducts();
        System.out.println("CART CLEARED");
    }

    public void checkout(){
        if(shoppingCart.getTotalPrice()!=0) {
            int id = (int) (Math.random() * 900) + 100;
            while (!uniqueOrderID(id)) {
                id = (int) (Math.random() * 900) + 100;
            }
            String ch;
            System.out.println("******** SHIPPING ADDRESS ********");
            System.out.println("Current shipping address: " + Address);
            System.out.println("Change shipping address? [Y][N]");

            while (true) {
                ch = sc.next();
                boolean exit = false;
                if (ch.equals("y") || ch.equals("Y")) {
                    System.out.print("New shipping address --> ");
                    String adr = sc.nextLine();
                    sc.nextLine();
                    Address = adr;
                    exit = true;
                } else if (ch.equals("n") || ch.equals("N")) {
                    exit = true;
                } else {
                    System.out.println("Change shipping address? [Y][N]");
                }
                if (exit) {
                    break;
                }
            }
            int shippingMethod;
            float shippingCost;
            System.out.println("******** SHIPPING METHOD ********");
            System.out.println("Please choose your preferred shipping method:");
            System.out.println("1-Express:Delivery within 1-2 business days");
            System.out.println("    Cost: 25$");
            System.out.println("2-Expedited:Delivery within 2-3 business days");
            System.out.println("    Cost: 20$");
            System.out.println("3-Standard:delivery within 3-5 business days");
            System.out.println("    Cost: 10$$");
            System.out.print("--> ");
            shippingMethod = sc.nextInt();
            while (shippingMethod != 1 && shippingMethod != 2 && shippingMethod != 3) {
                System.out.print("Pick a valid option");
                System.out.print("--> ");
                shippingMethod = sc.nextInt();
            }
            if (shippingMethod == 1) {
                shippingCost = 25;
            } else if (shippingMethod == 2) {
                shippingCost = 20;
            } else {
                shippingCost = 10;
            }

            System.out.println("******** PAYMENT INFORMATION ********");
            System.out.println("Please choose your preferred payment method:");
            System.out.println("1-Credit/Debit Card");
            System.out.println("2-PayPal");

            int paymentMethod = sc.nextInt();
            while (paymentMethod != 1 && paymentMethod != 2) {

                System.out.println("Please choose a valid payment method:");
                paymentMethod = sc.nextInt();

            }

            if (paymentMethod == 1) {
                String cardNumber;
                String expiryDate;
                String cvv;
                sc.nextLine();
                System.out.print("Enter your card number (16 digits): ");
                cardNumber = sc.nextLine();

                System.out.print("Enter your card's expiry date (MM/YY): ");
                expiryDate = sc.next();
                System.out.print("Enter the CVV code on the back of your card (3 digits): ");
                cvv = sc.next();
                String pattern = "^(0[1-9]|1[0-2])/(?:[0-9]{2})$";

                while (!(cardNumber.replaceAll("\\s", "").length() == 16 && cvv.length() == 3 && expiryDate.matches(pattern))) {

                    System.out.print("Please enter valid payment information ");
                    System.out.print("Enter your card number: ");
                    sc.nextLine();
                    cardNumber = sc.nextLine();
                    System.out.print("Enter your card's expiry date (MM/YY): ");
                    expiryDate = sc.next();
                    System.out.print("Enter the CVV code on the back of your card: ");
                    cvv = sc.next();
                }

            } else {
                String email;
                String password;
                System.out.println("PayPal Email : ");
                sc.nextLine();
                email = sc.nextLine();
                System.out.println("PayPal Password : ");
                password = sc.nextLine();

                String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$";
                while (!(isValidPassword(password) && email.matches(pattern))) {
                    System.out.print("Please enter valid payment information ");
                    System.out.print("PayPal Email : ");
                    email = sc.nextLine();

                    System.out.print("PayPal Password : ");
                    password = sc.nextLine();

                }

            }
            System.out.println("******** ORDER SUMMARY ********");
            shoppingCart.printCart();
            System.out.println("Total price without shipping : " + shoppingCart.getTotalPrice() + " $");
            System.out.println("Total price with shipping : " + (shoppingCart.getTotalPrice() + shippingCost) + " $");
            float totalPrice = (shoppingCart.getTotalPrice() + shippingCost);
            totalPrice=totalPrice + (totalPrice*10/100);
            System.out.println("Total price after tax: " + totalPrice+ " $");

            System.out.println("******** CONFIRM PAYMENT ********");
            String confirmPayment;
            System.out.print("Confirm payment [Y][N] --> ");

            while (true) {
                confirmPayment = sc.next();
                boolean exit = false;
                if (confirmPayment.equals("Y") || confirmPayment.equals("y")) {
                    ArrayList<Product> orderItems = new ArrayList<Product>();
                    orderItems.addAll(shoppingCart.getShoppingCart());
                    HashMap<Integer,Integer> itemQuantity = new HashMap<Integer,Integer>();
                    for (Map.Entry<Integer, Integer> entry : shoppingCart.getProductQuantity().entrySet()) {
                        itemQuantity.put(entry.getKey(), entry.getValue());
                    }

                    if(shoppingCart.checkout()){
                        System.out.println("Your payment has been successfully processed! Your order is now confirmed");
                        System.out.println("Thank you for shopping with us");
                        Order order = new Order(this,id,orderItems,itemQuantity,totalPrice,shippingMethod);
                        orderHistory.add(order);
                        exit=true;

                    }else{
                        System.out.println("Your payment has not been successfully processed! There are items that are out of stock");
                        exit=true;
                    }


                }else if(confirmPayment.equals("n") || confirmPayment.equals("N")){
                    System.out.println("You cancelled the payment . If you'd like to revisit your order later, it will be saved in your cart");
                    exit=true;
                }else{
                    System.out.println("Please choose a valid option");
                }
                if(exit){
                    break;
                }
            }


        }else{
            System.out.println("Cart is empty");
        }

    }

    public void printOrderHistory(){
        if(orderHistory.size()>0) {
            for (Order order : orderHistory) {
                System.out.print("\t**********************************************************************************************\n");
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Order status: " + order.getOrderStatus());
                System.out.println("Number of items ordered : " + order.getNumItems());
                System.out.println("Order date: " + order.getOrderDate());
                System.out.println("Expected delivery date : " + order.getDeliveryDate());
                System.out.println("Shipping method: " + order.getShippingMethod());
                System.out.println("Total Price: " + order.getTotalPrice()+" $");
                System.out.print("\t**********************************************************************************************\n");
            }
        }else {
            System.out.println("No Order History");
        }
    }

    public void addRating(int productID){
        if(productIsOrdered(productID)!=null){
            System.out.print("Rate this product out of 10 --> ");
            double rating = sc.nextDouble();
            while (rating>10){
                System.out.print("The Rate needs to be out of 10 --> ");
                rating = sc.nextDouble();
            }
            productIsOrdered(productID).addRating(rating);
            sc.nextLine();
            System.out.println("Add a feedback: ");
            String feedback = sc.nextLine();
            productIsOrdered(productID).addFeedBack(feedback);
            System.out.println("THANK YOU FOR YOUR FEEDBACK");

        }else{
            System.out.println("You can only add a feedback if you bought the product");

        }
    }

    public void cancelOrder(){
        System.out.print("Order ID --> ");

        int orderID= sc.nextInt();
        Order deleteOrder=null;
        for(Order order:orderHistory){
            if(order.getOrderId()==orderID){
                if(!order.getOrderStatus().equals("delivered")){
                    deleteOrder=order;
                }
                break;

            }
        }
        if(deleteOrder!=null){
            ArrayList<Product> orderItems = deleteOrder.getOrderItems();
            HashMap<Integer,Integer> itemQuantity=deleteOrder.getItemQuantity();
            for(Product prod:orderItems){
                int newQuantity = prod.getQuantityInStock()+itemQuantity.get(prod.getProductID());
                prod.setQuantityInStock(newQuantity);
            }
            orderHistory.remove(deleteOrder);
            System.out.println("The order was canceled successfully");
        }else{
            System.out.println("Order not found");
        }
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

    private ArrayList<Product> filterPrice(float min, float max, ArrayList<Product> productList) {
        ArrayList<Product> newProductList = new ArrayList<Product>();
        for(Product prod:productList) {
            if(prod.getPrice()>=min && prod.getPrice()<=max){
                newProductList.add(prod);
            }
        }
        return newProductList;
    }
    private ArrayList<Product> filterCategory(String category, ArrayList<Product> productList) {
        ArrayList<Product> newProductList = new ArrayList<Product>();
        for(Product prod:productList) {
            if(prod.getCategory().toLowerCase().equals(category.toLowerCase())){
                newProductList.add(prod);
            }
        }
        return newProductList;
    }
    private ArrayList<Product> filterOutOfStock(ArrayList<Product> productList) {
        ArrayList<Product> newProductList = new ArrayList<Product>();
        for(Product prod:productList) {
            if(prod.getOutOfStock()==false){
                newProductList.add(prod);
            }
        }
        return newProductList;
    }

    private ArrayList<Product> filterRating(ArrayList<Product> productList,double rating){
        ArrayList<Product> newProductList = new ArrayList<Product>();
        for(Product prod:productList) {
            if(prod.getRating()>=rating){
                newProductList.add(prod);
            }
        }
        return newProductList;
    }

    private void printProducts(ArrayList<Product> ProductList){
        for(Product prod:ProductList){
            System.out.print("\t**********************************************************************************************\n");
            System.out.println("Product ID: "+ prod.getProductID());
            System.out.println("Product Category: "+ prod.getCategory());
            System.out.println("Product Name: "+ prod.getName());
            System.out.println("Product Description: "+ prod.getDescription());
            System.out.println("Product Price: "+ prod.getPrice()+"$");
            if(prod.getOutOfStock()){
                System.out.println("!! OUT OF STOCK !!");

            }
            System.out.print("\t**********************************************************************************************\n");

        }
    }
    private boolean uniqueOrderID(int id){
        for(Order order:orderHistory){
            if(order.getOrderId()==id){
                return false;
            }
        }
        return true;
    }
    private Product productIsOrdered(int productID){
        for(Order order:orderHistory){
            if(order.productIsOrdered(productID)!=null){
                return order.productIsOrdered(productID);
            }
        }
        return null;
    }
}

