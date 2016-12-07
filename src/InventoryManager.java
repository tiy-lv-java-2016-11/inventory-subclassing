/**
 * Created by robertcampos on 12/1/16.
 */
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InventoryManager {
    // class scoped map for storing invetory items
    private Map<String, Inventory> itemMap = new HashMap<>();

    // default constructor
    public InventoryManager() {

    }

    // how we run this object
    public static void main(String[] args) {
        InventoryManager im = new InventoryManager();
        im.execute();
    }

    // this where all the work gets done
    private void execute() {
        try {
            // variables reading user input from the command line
            InputStreamReader cin = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(cin);

            // main processing loop
            String option = "0";
            while(!option.equals("6")) {

                // printing the user options to the command line
                System.out.println("Inventory Types:");
                if(itemMap.size() > 0) {
                    Iterator iter = itemMap.keySet().iterator();
                    int i = 1;
                    while(iter.hasNext()) {
                        String key = (String) iter.next();
                        Inventory iv = itemMap.get(key);
                        System.out.println("   " + i + ". name[" + iv.getName() + "] category[" + iv.getCategory() + "]");
                        i++;
                    }
                }

                System.out.println("Options:" );
                System.out.println("   [1] Create a new item");
                System.out.println("   [2] Sell an item(s)");
                System.out.println("   [3] Remove an item(s)");
                System.out.println("   [4] Update item quantity");
                System.out.println("   [5] Update item price");
                System.out.println("   [6] Quit");
                System.out.print("Enter option: ");

                // read user option from command line
                option = br.readLine();
                if((option == null) || (option.length() <= 0)) {
                  System.out.println("Invalid option");
                    option = "0";
                    continue;
               }

                // this runs the create item functionality
                if(option.equals("1")) {
                   System.out.println("Enter comma delimiited name,price,quantity,category");
                    String line = br.readLine();
                    String[] parts = line.split(",");
                    if((parts == null) || (parts.length != 4)) {
                        System.out.println("Invalid inventory");
                        continue;
                    }
                    String name = parts[0];
                    String price = parts[1];
                    String quantity = parts[2];
                    String category = parts[3];

                    if(!isInteger(quantity)) {
                        System.out.println("Invalid quantity");
                        continue;
                    }
                    if(!isDouble(price)) {
                        System.out.println("Invalid price");
                        continue;
                    }

                    Inventory inventory = createItem(name, Double.parseDouble(price), Integer.parseInt(quantity), category);
                    if(itemMap.containsKey(name)) {
                        System.out.println(name + " Already created.");
                        continue;

                    }
                    else {
                        itemMap.put(name, inventory);

                    }

                }

                // sell an item thus reducing the value of quatity
                else if(option.equals("2")) {
                    System.out.println("Enter comma delimiited name,quantity");
                    String line = br.readLine();
                    String[] parts = line.split(",");
                    if((parts == null) || (parts.length != 2)) {
                        System.out.println("Invalid inventory");
                        continue;
                    }
                    String name = parts[0];
                    String quantity = parts[1];

                    if(!isInteger(quantity)) {
                        System.out.println("Invalid quantity");
                        continue;
                    }
                    if(!itemMap.containsKey(name)) {
                        System.out.println(name + " Does not exist.");
                        continue;
                    }
                    Inventory inventory = itemMap.get(name);
                    int i = inventory.getQuantity();
                    if(Integer.parseInt(quantity) > i) {
                        throw new Exception("Inventory Quantity less than " + quantity);
                    }
                    else {
                        inventory.setQuantity(inventory.getQuantity() - Integer.parseInt(quantity));
                    }
                }

                // remove item from map
                else if(option.equals("3")) {
                    System.out.println("Enter name");
                    String line = br.readLine();
                    String[] parts = line.split(",");
                    if((parts == null) || (parts.length != 1)) {
                        System.out.println("Invalid name");
                        continue;
                    }
                    String name = parts[0];

                    if(!itemMap.containsKey(name)) {
                        System.out.println(name + " Does not exist.");
                        continue;
                    }
                    itemMap.remove(name);
                }

                // update item quanity
                else if(option.equals("4")) {
                    if(itemMap.size() <= 0) {
                        System.out.println("No items found.");
                        continue;
                    }
                    System.out.println("Choose from items below by name");
                    Iterator iter = itemMap.keySet().iterator();
                    while(iter.hasNext()) {
                        System.out.println((String)iter.next());
                    }
                    String name = br.readLine();
                    if(!itemMap.containsKey(name)) {
                        System.out.println("Invalid item " + name);
                        continue;
                    }
                    System.out.println("Enter new quantity: ");
                    String quantity = br.readLine();
                    if(!isInteger(quantity)) {
                        System.out.println("Invalid Quantity " + quantity);
                        continue;
                    }
                    Inventory inventory = itemMap.get(name);
                    inventory.setQuantity(Integer.parseInt(quantity));
                }

                // update item price
                else if(option.equals("5")) {
                    if(itemMap.size() <= 0) {
                        System.out.println("No items found.");
                        continue;
                    }
                    System.out.println("Choose from items below by name");
                    Iterator iter = itemMap.keySet().iterator();
                    while(iter.hasNext()) {
                        System.out.println((String)iter.next());
                    }
                    String name = br.readLine();
                    if(!itemMap.containsKey(name)) {
                        System.out.println("Invalid item " + name);
                        continue;
                    }
                    System.out.println("Enter new price: ");
                    String price = br.readLine();
                    if(!isDouble(price)) {
                        System.out.println("Invalid price " + price);
                        continue;
                    }
                    Inventory inventory = itemMap.get(name);
                    inventory.setPrice(Double.parseDouble(price));

                }

                // quit processing while loop
                else if(option.equals("6")) {
                    System.out.println("Quit");
                }
                else {
                    System.out.println("value = " + option);
                }
            }
        }
        catch(Exception e ) {
            e.printStackTrace();
        }
    }

    // helper to validate String is an integer
    private boolean isInteger(String option) {
        boolean test = true;
        try {
            int i = Integer.parseInt(option);
        }
        catch(NumberFormatException nfe) {
            test = false;
        }
        return test;
    }

    // helper method that validates String is a double
    private boolean isDouble(String option) {
        boolean test = true;
        try {
            double i = Double.parseDouble(option);
        }
        catch(NumberFormatException nfe) {
            test = false;
        }
        return test;
    }

    // Create item method based on category passed in during create item option #1.
    private Inventory createItem(String name, double price, int quantity, String category) {
        Inventory inventory = null;
        if(category.equals("belt")) {
            inventory = new Belt(name, price, quantity);
        }
        else if(category.equals("scarf")) {
            inventory = new Scarf(name, price, quantity);
        }
        else if(category.equals("sunglass")) {
            inventory = new Sunglass(name, price, quantity);
        }
        else if(category.equals("wallet")) {
            inventory = new Wallet(name, price, quantity);
        }
        else if(category.equals("watch")) {
            inventory = new Watch(name, price, quantity);
        }
        else {
            System.out.printf("Invalid Category " + category);
        }
        return inventory;

    }
}
