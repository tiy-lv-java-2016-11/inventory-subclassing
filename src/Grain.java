/**
 * Created by melmo on 12/6/16.
 */
public class Grain extends Item {
    public Grain(String name, int quantity, double price) {
        super(name, quantity, price);
        this.foodGroup = "Grain";
    }
}
