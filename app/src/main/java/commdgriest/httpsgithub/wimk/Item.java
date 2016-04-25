package commdgriest.httpsgithub.wimk;

import java.util.Comparator;

public class Item {

    /* Item attributes */
    private String name;
    private int quantity;
    private int iconId;
    private int id;
    private int shouldShow;
    private int color;

    /* Constructor */
    public Item() {
        this.name = "Default_Item_Name";
        this.quantity = 50;
        this.iconId = 0;
        this.shouldShow = 1;
        this.color = 15;
    }

    public Item(String name, int quantity, int iconId, int shouldShow, int color){
        this.name = name;
        this.quantity = quantity;
        this.iconId = iconId;
        this.shouldShow = shouldShow;
        this.color = color;
    }

    /* Getters and Setters */
    public String getName() {
        return this.name;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public int getIconID(){
        return this.iconId;
    }
    public int getID(){
        return this.id;
    }
    public int getShouldShow(){
        return this.shouldShow;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public void setQuantity(int newQuantity){
        this.quantity = newQuantity;
    }
    public void setIconID(int newIconID){
        this.iconId = newIconID;
    }
    public void setShouldShow( int newVal ){
        this.shouldShow = newVal;
    }

    //TODO this one should probably not be public and maybe should not even exist
    public void setID(int newID){ this.id = newID; }

    //TODO possibly remove (along with Color class)
    public int getColor(){
        return color;
    }
    public void setColor(int c) { color = c;
    }

    /* Sort by quantity ascending order */
    // Uses Comparator interface for Java ArrayLists
    public Comparator<Item> ItemQuantityComparatorLowtoHigh = new Comparator<Item>() {
        public int compare (Item item1, Item item2){
            int itemQuantity1 = item1.getQuantity();
            int itemQuantity2 = item2.getQuantity();
            return itemQuantity1 - itemQuantity2;
        }
    };

    /* Sort by quantity descending order */
    public Comparator<Item> ItemQuantityComparatorHightoLow = new Comparator<Item>() {
        public int compare (Item item1, Item item2){
            int itemQuantity1 = item1.getQuantity();
            int itemQuantity2 = item2.getQuantity();
            return itemQuantity2 - itemQuantity1;
        }
    };

    /* Sort Alphabetically A to Z */
    public Comparator<Item> ItemNameComparatorAtoZ = new Comparator<Item>() {
        public int compare (Item item1, Item item2){
            String itemName1 = item1.getName().toUpperCase();
            String itemName2 = item2.getName().toUpperCase();
            return itemName1.compareTo(itemName2);
        }
    };

    /* Sort Alphabetically Z to A */
    public Comparator<Item> ItemNameComparatorZtoA = new Comparator<Item>() {
        public int compare (Item item1, Item item2){
            String itemName1 = item1.getName().toUpperCase();
            String itemName2 = item2.getName().toUpperCase();
            return itemName2.compareTo(itemName1);
        }
    };
}
