import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by melmo on 12/1/16.
 */
public class Inventory {
    private int CURRENT_ITEM_ID = 1000;
    private String CURRENT_USER = null;
    private HashMap<Integer, Item> inventory = new HashMap<>();
    private HashMap<String, String> users = new HashMap<>();
    private Scanner input = new Scanner(System.in);
    private SimpleDateFormat DF = new SimpleDateFormat("dd/M/yyyy");

    public void login(){
        System.out.println("What is your username?");
        String name = getValidStr(this.input.nextLine());
        String pass = users.get(name);
        while (pass == null){
            System.out.println("\nThat username does not exist. Please enter a valid username.");
            name = getValidStr(this.input.nextLine());
            pass = users.get(name);
        }
        System.out.println("\nWhat is your password?");
        String password = getValidStr(this.input.nextLine());
        while (!pass.equals(password)){
            System.out.println("\nPlease enter the correct password.");
            password = getValidStr(this.input.nextLine());
        }
        CURRENT_USER = name;
        System.out.format("\n<%s> is logged in.", CURRENT_USER);
        whatYouWant();
    }
    
    public void addUser(String username, String password){
        users.put(username, password);
    }

    private void whatYouWant(){

        String str = "\n\nWhat would you like to do? < 1, 2, 3, 4, 5, 6, 7, 8 >\n\n" +
                "1 : Print current inventory\n" +
                "2 : Create a new inventory item\n" +
                "3 : Delete an item from inventory\n" +
                "4 : Sell an item from inventory\n" +
                "5 : Put item on sale\n" +
                "6 : Change the price of an item\n" +
                "7 : Change the quantity in stock of an item\n" +
                "8 : Exit the program";

        System.out.println(str);

        int input = getValidInt(this.input.nextLine());

        while (input < 1 || input > 8){
            System.out.println("Please enter a valid option.");
            input = getValidInt(this.input.nextLine());
        }

        switch(input) {
            case 1:
                printAll();
                break;
            case 2:
                newItem();
                break;
            case 3:
                deleteItem();
                break;
            case 4:
                sellItem();
                break;
            case 5:
                itemOnSale();
                break;
            case 6:
                updatePrice();
                break;
            case 7:
                updateQuantity();
                break;
            case 8:
                exit();
                break;
        }


    }


    private void newItem() {
        
        System.out.println("\nWhat is the name of the item?");
        String name = getValidStr(this.input.nextLine());
        
        System.out.println("\nWhat is the quantity?");
        int qty = getValidQty(this.input.nextLine());
        
        System.out.println("\nWhat is the price?");
        double price = getValidPrice(this.input.nextLine());
        
        Item item  = new Item(name, qty, price);
        this.inventory.put(CURRENT_ITEM_ID, item);
        CURRENT_ITEM_ID++;

        System.out.format("\n%d <%s> @ %.2f has been added to inventory.\n", item.getQuantity(), item.getName(), item.getPrice());

        whatYouWant();
    }

    public void newItem(Item item){
        this.inventory.put(CURRENT_ITEM_ID, item);
        CURRENT_ITEM_ID++;
    }

    private void deleteItem(){

        int id =0;
        while (id == 0) {
            System.out.println("\nWhat is the ID of the item you would like to delete?");
            id = getValidInt(this.input.nextLine());
            if (this.inventory.get(id) == null){
                System.out.println("\nNo such item exists at that ID.\n");
                id = 0;
            }
        }

        this.inventory.remove(id);
        whatYouWant();
    }

    private void sellItem(){
        Item item = getItem();
        int itemQty = item.getQuantity();
        System.out.println("\nWhat is the quantity to sell?");
        int qty = getValidQty(this.input.nextLine());

        while (qty > itemQty) {
            System.out.println("\nThere is not enough in stock!");
            System.out.println("\nWhat is the quantity to sell?");
            qty = getValidQty(this.input.nextLine());
        }

        item.setQuantity(itemQty-qty);
        System.out.format("\n%d <%s> have been removed from inventory.", qty, item.getName());

        whatYouWant();
    }

    private void itemOnSale(){
        Item item = this.getItem();
        System.out.println("\nWhat is the sale start date? <dd/mm/yyyy>");
        item.setSaleStart(getValidDate());
        System.out.println("\nWhat is the sale end date? <dd/mm/yyyy>");
        item.setSaleEnd(getValidDate());
        System.out.println("\nWhat is the sale price?");
        item.setSalePrice(getValidPrice(this.input.nextLine()));

        whatYouWant();
    }

    private void print(int id){
        String str = String.format("ID#%d %s\n", id, inventory.get(id).toString());

        System.out.format(str);
        for (int i=0; i<str.length(); i++){
            System.out.print('-');
        }
        System.out.print("\n");
    }

    private void printAll(){
        if (inventory.isEmpty()){
            System.out.println("\nThere are no items in the inventory.");
        }
        System.out.println("");
        for(Integer id : inventory.keySet()){
            print(id);
        }
        whatYouWant();
    }

    private void updatePrice(){
        Item item = getItem();
        System.out.println("\nWhat is the new price?");
        double price = getValidPrice(this.input.nextLine());
        item.setPrice(price);
        whatYouWant();
    }

    private void updateQuantity(){
        Item item = getItem();
        System.out.println("\nWhat is the new quantity?");
        int qty = getValidQty(this.input.nextLine());
        item.setQuantity(qty);
        whatYouWant();
    }

    private void exit(){
        System.exit(0);
    }

//  Helper Functions

    private Item getItem(){
        int id = -1;
        Item item = null;
        while (id < 0) {
            System.out.println("\nWhat is the ID of the item?");
            id = getValidInt(this.input.nextLine());
            item = this.inventory.get(id);
            if (item == null){
                System.out.println("\nNo such item exists at that ID.");
                id = -1;
            }
        }
        return item;
    }

    private double getValidPrice(String str){
        double dub = getValidDouble(str);
        while (dub < 0){
            System.out.println("\nPlease enter a price greater than or equal to 0.");
            dub = getValidDouble(this.input.nextLine());
        }
        return dub;
    }

    private int getValidQty(String str){
        int qty = getValidInt(str);
        while (qty < 0){
            System.out.println("\nPlease enter a quantity greater than or equal to 0.");
            qty = getValidInt(this.input.nextLine());
        }
        return qty;
    }
    
    private String getValidStr(String str){
        while (str.isEmpty()){
            System.out.println("\nPlease enter a value?");
            str = this.input.nextLine();
        }
        return str;
    }

    private int getValidInt(String str) {
        try {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException e){
            System.out.println("\nPlease enter a valid number.");
            return -1;
        }
    }

    private double getValidDouble(String str){
        try {
            return Double.parseDouble(str);
        }
        catch (NumberFormatException e){
            System.out.println("\nPlease enter a valid number.");
            return -1;
        }
    }

    private Date getValidDate(){
        Date date = null;
        while(date == null){
            try {
                date = DF.parse(this.input.nextLine());
            }
            catch (ParseException e){
                System.out.println("\nPlease enter a date in the format <dd/mm/yyyy>");
                date = null;
            }
        }
        return date;
    }

}
