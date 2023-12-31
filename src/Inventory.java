import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Inventory {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Product> ProductList = new ArrayList<Product>();
    public void addProduct(Product p,Admin.InventoryAccess inventoryAccess){
        Objects.requireNonNull(inventoryAccess);
        for(Product item:ProductList){
            if(p.getProductID()==item.getProductID()){
                System.out.println("Product already exists in inventory");
                return;
            }
        }
        ProductList.add(p);

    }

    public void removeProduct(int id,Admin.InventoryAccess inventoryAccess){
        Objects.requireNonNull(inventoryAccess);
        Product p=null;
        for(Product prod:ProductList){
            if(prod.getProductID()==id){
                p=prod;
                break;
            }
        }
        if(p!=null){
            ProductList.remove(p);
            System.out.println("Product removed");
        }else{
            System.out.println("Product does not exist in inventory");
        }

    }

    public void printProducts(){
        if(ProductList.size()>0){

            for(Product prod:ProductList){
                System.out.print("\t**********************************************************************************************\n");
                System.out.println("Product ID: "+ prod.getProductID());
                System.out.println("Product Category: "+ prod.getCategory());
                System.out.println("Product Name: "+ prod.getName());
                System.out.println("Product Description: "+ prod.getDescription());
                System.out.println("Product Price: "+ prod.getPrice()+"$");
                System.out.println("Product Rating out of 10: "+ prod.getRating());
                if(prod.getOutOfStock()){
                    System.out.println("!! OUT OF STOCK !!");

                }
                prod.printFeedBack();
                System.out.print("\t**********************************************************************************************\n");

            }
        }else{
            System.out.println("The Inventory is Empty");
        }

    }

    public ArrayList<Product> getProductList(){
        return ProductList;
    }


    public  ArrayList<Product> searchProduct(String search){
        ArrayList<Product> searchResult = new ArrayList<Product>();
        for(Product prod:ProductList){
            if(prod.getName().toLowerCase().contains(search.toLowerCase()) || prod.getCategory().toLowerCase().contains(search.toLowerCase()) ||prod.getDescription().toLowerCase().contains(search.toLowerCase())){
                searchResult.add(prod);
            }
        }
        return searchResult;
    }
    public Product getProduct(int id){
        for(Product prod:ProductList){
            if(prod.getProductID()==id){
                return prod;
            }
        }
        System.out.println("PRODUCT DOES NOT EXIST");
        return null;
    }

    public void updatePrice(int productID){
        if(getProduct(productID)!=null){
            System.out.print("New Price --> ");
            float price=sc.nextFloat();
            getProduct(productID).setPrice(price);
            System.out.println("PRICE UPDATED");
        }
    }

    public void updateQuantity(int productID){
        if(getProduct(productID)!=null){
            System.out.print("Product quantity to add to inventory --> ");
            int quantity = sc.nextInt();
            getProduct(productID).setQuantity(quantity);
            System.out.println("QUANTITY UPDATED");
        }
    }



    public boolean uniqueProductID(int id){
        for(Product prod:ProductList){
            if(prod.getProductID()==id){
                return false;
            }
        }
        return true;
    }
}
