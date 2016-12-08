import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;


/**
 * Created by darionmoore on 12/2/16.
 */
public class Item {
    private String name;
    private double price;
    private int stock;
    private int backStock = 5;
    private int itemId;
    private String category;
    private static ArrayList<String> categories = new ArrayList<>(Arrays.asList("Weapon", "Buff", "Debuff", "Accessory", "Shield"));
    private Scanner userInput = new Scanner(System.in);




    public Item(String name, double price, int stock, String category ) {
        this.itemId = itemId++;
        this.name = getName();
        this.price = getPrice();
        this.stock = stock;
        this.category = category;


    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public int getBackStock() {
        return backStock;
    }
    public void setBackStock() {
        this.backStock = userInput.nextInt();
    }
    public int getId(){
        return itemId;
    }
    public String getCategory(){
        return category;
    }


     public static Item createItem(String name, double price, int stock, String category)throws Exception{
         if(category.equalsIgnoreCase("Weapon")){
             return new Weapon(name, price, stock, category);
         } else if (category.equalsIgnoreCase("Buff")){
             return new Buff(name, price, stock, category);
         }else if (category.equalsIgnoreCase("Debuff")){
             return new Debuff(name, price, stock, category);
         }else if(category.equalsIgnoreCase("Accessory")){
             return new Debuff(name, price, stock, category);
         }else if(category.equalsIgnoreCase("Shield")){
             return new Shield(name, price, stock, category);
         }else {
             throw new Exception("Invalid category");
         }
     }







//    public void chooseId(){
//
//    }
//    public void sellItem() throws Exception {
//        if (this.getStock() > this.getBackStock())  {
//            throw new Exception("You don't have enough in stock.");
//        } else if (this.getStock() <= this.getBackStock()){
//            this.stock -= this.backStock;
//        }
//    }





}
