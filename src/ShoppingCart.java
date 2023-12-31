import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCart {
    private HashMap<Integer,Integer> productQuantity = new HashMap<Integer,Integer>();
    private ArrayList<Product> ShoppingCart;
    private float totalPrice;
    private int taxRate;
    public ShoppingCart(){

        ShoppingCart = new ArrayList<Product>();
        totalPrice=0;
        taxRate=10;
    }

    public void addProduct(Product p, int quantity){
        if(p.getQuantityInStock()>=quantity){
            productQuantity.merge(p.getProductID(),quantity,Integer::sum);
            totalPrice+=quantity*p.getPrice();
            //FIXME:after payment
            //p.setQuantityInStock(p.getQuantityInStock()-quantity);
            ShoppingCart.add(p);
            System.out.println("PRODUCT ADDED TO CART");
        }else{
            System.out.println("NOT ENOUGH IN STOCK");
        }
    }

    public void removeProduct(int productId){
        int quantity = productQuantity.get(productId);
        productQuantity.remove(productId);
        Product p=null;
        for(Product prod:ShoppingCart){
            if(prod.getProductID()==productId){
                p=prod;
                break;
            }
        }
        if(p!=null){
            ShoppingCart.remove(p);
            //FIXME:after payment
            //p.setQuantityInStock(p.getQuantityInStock()+quantity);
            totalPrice-=quantity*p.getPrice();
            System.out.println("PRODUCT REMOVED SUCCESSFULLY");
        }

    }

    public void printCart(){
        for(Product prod:ShoppingCart){
            System.out.print("\t**********************************************************************************************\n");
            System.out.println("Product ID: "+ prod.getProductID());
            System.out.println("Product Name: "+ prod.getName());
            System.out.println("Quantity: "+ productQuantity.get(prod.getProductID()));
            System.out.print("\t**********************************************************************************************\n");
        }



    }
    public void updateCart(int productID, int quantity){
        for(Product prod:ShoppingCart){
            if(prod.getProductID()==productID){
                if(prod.getQuantityInStock()>=quantity){
                    totalPrice=totalPrice-productQuantity.get(productID)*prod.getPrice()+quantity*prod.getPrice();
                    productQuantity.put(productID,quantity);
                    System.out.println("CART UPDATED");

                }else{
                    System.out.println("NOT ENOUGH IN STOCK");
                }
                return;
            }
        }
        System.out.println("PRODUCT NOT FOUND");
    }
    public void removeAllProducts(){
        ShoppingCart.clear();
        productQuantity.clear();
        totalPrice=0;
    }

    public boolean checkout(){
        for(Product prod:ShoppingCart){
            int quantity = productQuantity.get(prod.getProductID());
            if(prod.getQuantityInStock()<quantity){
                return false;
            }
        }
        for(Product prod:ShoppingCart){
            int quantity = productQuantity.get(prod.getProductID());
            prod.setQuantityInStock(prod.getQuantityInStock()-quantity);
        }
        removeAllProducts();
        return true;
    }

    public float getTotalPrice(){return totalPrice;}
    public int getTaxRate(){return taxRate;}
    public ArrayList<Product> getShoppingCart(){return ShoppingCart;}
    public HashMap<Integer,Integer> getProductQuantity(){
        return productQuantity;
    }


}


