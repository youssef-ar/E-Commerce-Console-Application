import java.util.ArrayList;
public class AccountManager {
    private final ArrayList<Customer> CustomerList = new ArrayList<Customer>();
    private final ArrayList<Admin> AdminList = new ArrayList<Admin>();
    public void addCustomer(){
        int id = (int)(Math.random() * 900) + 100;
        while(!uniqueCustomerID(id)){
            id = (int)(Math.random() * 900) + 100;
        }
        Customer c = new Customer(id);
        CustomerList.add(c);

    }
    public void addAdmin(){
        int id = (int)(Math.random() * 900) + 100;
        while(!uniqueAdminID(id)){
            id = (int)(Math.random() * 900) + 100;
        }
        Admin a = new Admin(id);
        AdminList.add(a);
    }

    public void removeCustomer(int id){
        Customer c = null;
        for(Customer customer:CustomerList){
            if(customer.getCustomerID()==id){
                c=customer;
            }
        }
        if(c!=null){
            CustomerList.remove(c);
        }
    }

    public Customer findCustomerAccount(String un, String pw){
        for(Customer customer:CustomerList){
            if(customer.getPassword().equals(pw) && customer.getUserName().equals(un)){
                return customer;
            }
        }
        return null;
    }

    public Admin findAdminAccount(String un, String pw){
        for(Admin admin:AdminList){
            if(admin.getPassword().equals(pw) && admin.getUserName().equals(un)){
                return admin;
            }
        }
        return null;
    }

    private boolean uniqueCustomerID(int id){
        for(Customer customer:CustomerList){
            if(customer.getCustomerID()==id){
                return false;
            }
        }
        return true;
    }
    private boolean uniqueAdminID(int id){
        for(Admin admin:AdminList){
            if(admin.getAdminID()==id){
                return false;
            }
        }
        return true;
    }

}
