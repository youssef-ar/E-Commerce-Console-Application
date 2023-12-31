import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private int orderId;

    private int customerId;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String orderStatus; //"pending", "processing", "shipped", "delivered", "cancelled"
    private ArrayList<Product> orderItems;
    private HashMap<Integer,Integer> itemQuantity;
    private float totalPrice;
    private String shippingAddress;
    private String shippingMethod;
    private float shippingCost;

    public Order(Customer customer, int orderId ,ArrayList<Product> orderItems,HashMap<Integer,Integer> itemQuantity, float totalPrice, int shippingMethod){
        this.orderId=orderId;
        customerId=customer.getCustomerID();
        orderStatus="pending";
        orderDate = LocalDate.now();
        this.orderItems=orderItems;
        this.totalPrice=totalPrice;
        this.itemQuantity=itemQuantity;
        shippingAddress=customer.Address;
        if(shippingMethod==1){
            this.shippingMethod="Express";
            shippingCost=25;
            deliveryDate=orderDate.plusDays(2);
        }else if(shippingMethod==2){
            this.shippingMethod="Expedited";
            shippingCost=20;
            deliveryDate=orderDate.plusDays(3);
        }else{
            this.shippingMethod="Standard";
            shippingMethod=10;
            deliveryDate=orderDate.plusDays(5);
        }


    }

    public LocalDate getDeliveryDate(){
        return deliveryDate;
    }
    public int getOrderId(){
        return orderId;
    }
    public int getNumItems(){
        return orderItems.size();
    }

    public LocalDate getOrderDate(){
        return orderDate;
    }

    public String getOrderStatus(){
        return orderStatus;
    }
    public float getTotalPrice(){
        return totalPrice;
    }
    public String getShippingMethod(){
        return shippingMethod;
    }

    public ArrayList<Product> getOrderItems(){
        return orderItems;
    }
    public HashMap<Integer,Integer> getItemQuantity(){
        return itemQuantity;
    }
    public Product productIsOrdered(int productID){
        for(Product prod:orderItems){
            if(prod.getProductID()==productID){
                return prod;
            }
        }
        return null;
    }
}
