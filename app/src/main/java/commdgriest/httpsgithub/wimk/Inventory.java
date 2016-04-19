package commdgriest.httpsgithub.wimk;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jlisicky on 4/18/16.
 */
public class Inventory {

    static ArrayList<Item> itemsList = new ArrayList<Item>();
    static int sortingRule;
    //each item gets own unique Id - incremented each time addItem is clicked
    static int nextId = 0;


    //searching for item name in itemsList
    public static void searchByName(String query){
        for(int i =0; i<itemsList.size(); i++){
            if(itemsList.get(i).getName().contains(query)) {
                //success - shouldShow set to true
                itemsList.get(i).shouldShow = true;
            }

            //failure - shouldShow set to false
            else{
                itemsList.get(i).shouldShow = false;
            }

        }
        //now call showItemsFromSearch in Visual Inventory class
        VisualInventoryScreen.showItemsFromSearch();
    }

    //sort only if sorting rule has been changed
    //*** Might have to change case value based on radio button ID's
    public static void sort(){
        if(SortingSettingsScreen.hasChanged(sortingRule) == true){
            //sorting rule should now be set to new sorting rule
            switch(sortingRule){
                case 0:
                    sortLowtoHigh();
                case 1:
                    sortHightoLow();
                case 2:
                    sortAtoZ();

                case 3:
                    sortAtoZ();
            }
        }

    }

    //sorting by Quantity High to Low
    public static void sortHightoLow(){
        Collections.sort(itemsList, Item.ItemQuantityComparatorHightoLow);
    }

    //sorting by Quantity Low to High
    public static void sortLowtoHigh(){
        Collections.sort(itemsList, Item.ItemQuantityComparatorLowtoHigh);
    }

    //sorting Alphabetically Z to A
    public static void sortZtoA(){
        Collections.sort(itemsList, Item.ItemNameComparatorZtoA);
    }

    //sorting Alphabetically A to Z
    public static void sortAtoZ(){
        Collections.sort(itemsList, Item.ItemNameComparatorAtoZ);
    }


    //add a new item to itemsList with default values
    public static void addItem(){
        nextId++;
        Color newColor = new Color();
        newColor.darkColorHexVal = 000000; //black
        newColor.lightColorHexVal = 908888; //gray
        Item newItem = new Item();
        newItem.name = "Item";
        newItem.id = nextId;
        newItem.quantity = 0;
        newItem.iconId = R.drawable.visual_inventory_icon_flat_avocado_00;
        newItem.shouldShow = true;
        newItem.color = newColor;
        //add item to list
        itemsList.add(newItem);
    }

    //remove item from itemsList
    public static void deleteItem(Item itemToDelete){
        itemsList.remove(itemToDelete);
    }
}
