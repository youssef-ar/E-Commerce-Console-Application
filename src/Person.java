import java.util.Scanner;

abstract public class Person {
    protected String First_Name;
    protected String Last_Name;
    protected String Email_Address;
    protected int age;
    protected String city;
    protected String state;
    protected String Country;
    protected String Address;
    Scanner sc=new Scanner(System.in);
    protected void UserInformation()
    {

        System.out.println("First Name:");
        First_Name=sc.next();
        System.out.println("Last Name:");
        Last_Name=sc.next();
        System.out.println("Email Address:");
        Email_Address=sc.next();
        String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$";
        while(!Email_Address.matches(pattern)){
            System.out.println("Please provide a valid Email Address");
            System.out.println("Email Address:");
            Email_Address=sc.next();
        }
        System.out.println("Age:");
        age=sc.nextInt();
        System.out.println("City:");
        city=sc.next();
        System.out.println("State:");
        state=sc.next();
        System.out.println("Country:");
        Country=sc.next();
        sc.nextLine();
        System.out.println("Address:");
        Address=sc.nextLine();
    }

}
