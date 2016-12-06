import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by melmo on 12/1/16.
 */
public class Item {
    private SimpleDateFormat DF = new SimpleDateFormat("dd/M/yyyy");
    private String name;
    private int quantity;
    private double price;
    String foodGroup;
    private Date saleStart = getValidDate("01/01/2000");
    private Date saleEnd = getValidDate("01/01/2000");
    private double salePrice;

    public static Item createItem(String name, int quantity, double price, String foodGroup) throws Exception {
        switch (foodGroup.toLowerCase()){
            case "fruit":
                return new Fruit(name, quantity, price);
            case "vegetable":
                return new Vegetable(name, quantity, price);
            case "meat":
                return new Meat(name, quantity, price);
            case "dairy":
                return new Dairy(name, quantity, price);
            case "grain":
                return new Grain(name, quantity, price);
            default:
                throw new Exception("You entered an invalid food group.");
        }
    }

    public Item(String name, int quantity, double price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString(){
        String str = String.format("%-15s %5s $%.2f", "<"+this.name+">", this.quantity+" @", this.getPrice());
        return str;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int qty){
        this.quantity = qty;
    }

    public boolean isOnSale(){
        try {
            Date today = DF.parse(DF.format(new Date()));
            return today.compareTo(this.saleStart) >= 0 && today.compareTo(this.saleEnd) <= 0;
        }
        catch (ParseException e){
            return false;
        }
    }

    public double getPrice() {
        if (this.isOnSale()){
            return this.salePrice;
        }
        else {
            return this.price;
        }
    }

    public void setPrice(double price){
        this.price = price;
    }

    public Date getSaleStart() {
        return this.saleStart;
    }

    public void setSaleStart(Date saleStart) {
        this.saleStart = saleStart;
    }

    public Date getSaleEnd() {
        return this.saleEnd;
    }

    public void setSaleEnd(Date saleEnd) {
        this.saleEnd = saleEnd;
    }

    public double getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    private Date getValidDate(String str){
        try {
            Date date = DF.parse(str);
            return date;
        }
        catch (ParseException e){
            return null;
        }

    }
}
