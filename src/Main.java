
/**
 * Created by melmo on 12/1/16.
 */
public class Main {

    public static void main(String[] args) throws Exception {



        Inventory myFridge = new Inventory();
        myFridge.addUser("melmo", "teddy");
        myFridge.addUser("ginger", "tux1");

        myFridge.newItem(Item.createItem("apple", 8, .49, "fruit"));
        myFridge.newItem(Item.createItem("carrot", 5, .35, "vegetable"));
        myFridge.newItem(Item.createItem("chicken breast", 3, 2.54, "meat"));
        myFridge.newItem(Item.createItem("cheddar cheese", 4, 5.43, "dairy"));
        myFridge.newItem(Item.createItem("bread", 2, 4.32, "grain"));

        myFridge.login();


    }
}
