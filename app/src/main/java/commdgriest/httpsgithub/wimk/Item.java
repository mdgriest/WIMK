package commdgriest.httpsgithub.wimk;

import java.util.Comparator;

public class Item {
    String name;
    int quantity;
    int iconId;
    int id;
    boolean shouldShow;
    Color color;

    /* Getters and Setters*/
    public String getName() {
        return this.name;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int newQuantity){
        this.quantity = newQuantity;
    }
    public int getIconId(){
        return this.iconId;
    }
    public void setIconId(int newIconID){
        this.iconId = newIconID;
    }
    public int getId(){
        return this.id;
    }

    //TODO this one should probably not be public and maybe should not even exist
    public void setId(int newID){ this.id = newID; }

    //TODO possibly remove (along with Color class)
    public Color getColor(){
        return this.color;
    }
    public void setColor(Color c){
        this.color = c;
    }

    //sorting by quantity ascending order
    //using Comparator interface for Java ArrayLists
    //Reference: http://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
    public Comparator<Item> ItemQuantityComparatorLowtoHigh = new Comparator<Item>()

    {
        public int compare (Item item1, Item item2){
            int itemQuantity1 = item1.getQuantity();
            int itemQuantity2 = item2.getQuantity();
            return itemQuantity1 - itemQuantity2;
        }

    };

    //sorting by quantity descending order
    //using Comparator interface for Java ArrayLists
    //Reference: http://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
    public Comparator<Item> ItemQuantityComparatorHightoLow = new Comparator<Item>()

    {
        public int compare (Item item1, Item item2){
            int itemQuantity1 = item1.getQuantity();
            int itemQuantity2 = item2.getQuantity();
            return itemQuantity2 - itemQuantity1;
        }

    };


    //sorting Alphabetically A to Z
    //using Comparator interface for Java ArrayLists
    //Reference: http://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
    public Comparator<Item> ItemNameComparatorAtoZ = new Comparator<Item>()

    {
        public int compare (Item item1, Item item2){
            String itemName1 = item1.getName().toUpperCase();
            String itemName2 = item2.getName().toUpperCase();
            return itemName1.compareTo(itemName2);
        }

    };

    //sorting Alphabetically Z to A
    //using Comparator interface for Java ArrayLists
    //Reference: http://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
    public Comparator<Item> ItemNameComparatorZtoA = new Comparator<Item>()

    {
        public int compare (Item item1, Item item2){
            String itemName1 = item1.getName().toUpperCase();
            String itemName2 = item2.getName().toUpperCase();
            return itemName2.compareTo(itemName1);
        }

    };
}
