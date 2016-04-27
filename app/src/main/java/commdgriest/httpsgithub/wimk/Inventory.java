package commdgriest.httpsgithub.wimk;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

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
                itemsList.get(i).setShouldShow( 1 );
            }
            /* Otherwise, do not show the item */
            else{ itemsList.get(i).setShouldShow( 0 ); }
        }
        /* Now call showItemsFromSearch to view the results of the search */
        VisualInventoryScreen vis = new VisualInventoryScreen();
        //vis.showItemsFromSearch();
    }

    //add a new item to itemsList with default values
    public void addItem(){
        nextId++;
        //Color newColor = new Color();
        //newColor.darkColorHexVal = 000000; //black
        //newColor.lightColorHexVal = 908888; //gray
        Item newItem = new Item();
        newItem.setName("Item");
//        newItem.setID(nextId);
        newItem.setQuantity( 0 );
        newItem.setIconID( R.drawable.visual_inventory_icon_flat_avocado_00 );
        newItem.setShouldShow( 1 );
        newItem.setColor( 0 );

        //add item to list
        itemsList.add(newItem);
    }

    //remove item from itemsList
    public void deleteItem(Item itemToDelete){
        itemsList.remove(itemToDelete);
    }

    public ArrayList<Item> getItemsList(){ return this.itemsList; }
}
