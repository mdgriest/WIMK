package commdgriest.httpsgithub.wimk;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jlisicky on 4/18/16.
 */
public class Inventory {


    ArrayList<Item> itemsList = new ArrayList<Item>();
    int sortingRule;

    /* Used to assign a unique ID to each item; Incremented each time an item is added to inventory */
    int nextId = 0;


    /* searching for item name in itemsList */
    public void searchByName(String query){
        /* For each item in the inventory */
        for(int i =0; i<itemsList.size(); i++){
            /* If the item's name contains the user's query as a substring */
            if(itemsList.get(i).getName().contains(query)) {
                /* Include the item in the subset of inventory to be shown */
                itemsList.get(i).shouldShow = true;
            }
            /* Otherwise, do not show the item */
            else{ itemsList.get(i).shouldShow = false; }
        }
        /* Now call showItemsFromSearch to view the results of the search */
        VisualInventoryScreen vis = new VisualInventoryScreen();
        //vis.showItemsFromSearch();
    }

    /* Sort only if sorting rule has been changed */
    //*** Might have to change case value based on radio button ID's
    public void sort(){
        SortingSettingsScreen settings = new SortingSettingsScreen();
        if(settings.hasChanged(sortingRule) == true){
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
    public void sortHightoLow(){
        Item newItem = new Item();
        Collections.sort(itemsList, newItem.ItemQuantityComparatorHightoLow);
    }

    //sorting by Quantity Low to High
    public void sortLowtoHigh(){
        Item newItem = new Item();
        Collections.sort(itemsList, newItem.ItemQuantityComparatorLowtoHigh);
    }

    //sorting Alphabetically Z to A
    public void sortZtoA(){

        Item newItem = new Item();
        Collections.sort(itemsList, newItem.ItemNameComparatorZtoA);
    }

    //sorting Alphabetically A to Z
    public void sortAtoZ(){

        Item newItem = new Item();
        Collections.sort(itemsList, newItem.ItemNameComparatorAtoZ);
    }


    //add a new item to itemsList with default values
    public void addItem(){
        nextId++;
        //Color newColor = new Color();
        //newColor.darkColorHexVal = 000000; //black
        //newColor.lightColorHexVal = 908888; //gray
        Item newItem = new Item();
        newItem.name = "Item";
        newItem.id = nextId;
        newItem.quantity = 0;
        newItem.iconId = R.drawable.visual_inventory_icon_flat_avocado_00;
        newItem.shouldShow = true;
        newItem.color = 0;

        //add item to list
        itemsList.add(newItem);
    }

    //remove item from itemsList
    public void deleteItem(Item itemToDelete){
        itemsList.remove(itemToDelete);
    }

    public ArrayList<Item> getItemsList(){ return this.itemsList; }
}
