
public class Item {
    private String name;
    private Integer price;
    private Integer stock;
    String category;

    public Item(String name, Integer price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public static Item createAxe(String name, Integer price, Integer stock, String category) throws Exception {
        if (category.equalsIgnoreCase("weapon")) {
            return new Weapon(name, price, stock);
        }
        else if(category.equalsIgnoreCase("armor")){
            return new Armor(name, price, stock);
        }
        else if(category.equalsIgnoreCase("Potion")){
            return new Potion(name, price, stock);
        }
        else if(category.equalsIgnoreCase("gem")) {
            return new Gem(name, price, stock);
        }
        else if(category.equalsIgnoreCase("material")){
            return new Material(name, price, stock);
        }
        else{
            throw new Exception("that is not a category");
        }
    }
}
