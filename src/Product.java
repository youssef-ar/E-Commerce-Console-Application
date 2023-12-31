import java.util.ArrayList;
import java.util.Scanner;

public class Product {
    protected int productID;
    protected String name;
    protected String description;
    protected float price;
    protected int quantityInStock;
    protected String category;

    protected int numberOfReviews;
    protected double rating;
    protected ArrayList<String> feedBack;

    protected boolean outOfStock;



    Scanner sc=new Scanner(System.in);
    public Product(int id){
        productID=id;

        System.out.println("Product Name:");
        name=sc.nextLine();
        System.out.println("Product Description:");
        description=sc.nextLine();
        System.out.println("Product Price:");
        price=sc.nextFloat();
        System.out.println("Quantity In Stock:");
        quantityInStock=sc.nextInt();
        sc.nextLine();
        System.out.println("Product Category:");
        category=sc.nextLine();


        if(quantityInStock==0){
            outOfStock=true;
        }else{
            outOfStock=false;
        }
        numberOfReviews=0;
        rating=0;
        feedBack=new ArrayList<String>();


    }

    public int getProductID(){

        return productID;
    }
    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }
    public void setPrice(float price){
        this.price=price;
    }

    public void setQuantity(int quantity){
        quantityInStock+=quantity;
    }

    public float getPrice(){
        return price;
    }

    public int getQuantityInStock(){
        return quantityInStock;
    }

    public String getCategory(){
        return category;
    }

    public boolean getOutOfStock(){
        return outOfStock;
    }


    public void setQuantityInStock(int quantity){
        quantityInStock=quantity;
    }

    public void addRating(double rating){
        numberOfReviews++;
        this.rating+=rating;
    }

    public double getRating(){
        if(numberOfReviews!=0){
            return rating/numberOfReviews;
        }else{
            return 0;
        }

    }

    public void addFeedBack(String feedback){
        this.feedBack.add(feedback);
    }

    public void printFeedBack(){
        System.out.println("\t*********************************** Product FeedBack ***************************************************\n");
        if(feedBack.size()>0){
            for(String feedback:feedBack){
                System.out.println("\t**********************************************************************************************\n");
                System.out.println(feedback);
                System.out.println("\t**********************************************************************************************\n");

            }
        }else{
            System.out.println("The product has no reviews");
        }
    }






}
