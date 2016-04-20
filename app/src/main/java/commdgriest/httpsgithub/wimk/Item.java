package commdgriest.httpsgithub.wimk;

/**
 * Created by jlisicky on 4/18/16.
 */
import java.util.Comparator;

public class Item {
    String name;
    int quantity;
    int iconId;
    int id;
    boolean shouldShow;
    Color color;

    /* Getters and setters
     */


    //name
    public String getName() {
        return name;
    }

    public void setName(String n){
        this.name = n;
    }

    //quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int q){
        this.quantity = q;
    }

    //iconId
    public int getIconId(){
        return iconId;
    }

    public void setIconId(int i){
        this.iconId = i;
    }

    //id
    public int getId(){
        return id;
    }

    public void setId(int d){
        this.id = d;
    }

    //color
    public Color getColor(){
        return color;
    }

    public void setColor(Color c){
        this.color = c;
    }

    //sorting by quantity ascending order
    //using Comparator interface for Java ArrayLists
    //Reference: http://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
    public static Comparator<Item> ItemQuantityComparatorLowtoHigh = new Comparator<Item>()

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
    public static Comparator<Item> ItemQuantityComparatorHightoLow = new Comparator<Item>()

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
    public static Comparator<Item> ItemNameComparatorAtoZ = new Comparator<Item>()

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
    public static Comparator<Item> ItemNameComparatorZtoA = new Comparator<Item>()

    {
        public int compare (Item item1, Item item2){
            String itemName1 = item1.getName().toUpperCase();
            String itemName2 = item2.getName().toUpperCase();

            return itemName2.compareTo(itemName1);

        }

    };
}
