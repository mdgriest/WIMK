package commdgriest.httpsgithub.wimk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

public class UpdateIconScreen extends Activity {
    ListView listIconChoices;
    Database db = new Database(this);
    String nameOfSelectedItem;

    String[] names = {
        "Apples",
        "Bananas",
        "Milk",
        "Cheese",
    };

    Integer[] imageId = {
        R.drawable.apple_by_creative_stall,
        R.drawable.bananas_by_fernando_affonso,
        R.drawable.milk_by_norbert_kucsera,
        R.drawable.cheese_by_edward_boatman,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_icon_screen);

        CustomList adapter = new  CustomList(UpdateIconScreen.this, names, imageId);
//        listIconChoices=(ListView)findViewById(R.id.listIconChoices);
//        listIconChoices.setAdapter(adapter);

        ListView lv = (ListView) findViewById(R.id.listIconChoices);

        lv.setAdapter(adapter);

        /* Receive name of item that was originally clicked in VI Screen */
        Bundle extras = getIntent().getExtras();
        nameOfSelectedItem = extras.getString("NAME_OF_SELECTED_ITEM");

        /* When the user clicks on an icon in the update icon screen */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedIconID = position;

                Item itemFromDB = db.getItem(nameOfSelectedItem);

                /* Update the database entry for this item's iconID */

                /* First create a new item with the attributes we want to save */
                Item updatedItem = new Item();
                updatedItem.setName(itemFromDB.getName());
                updatedItem.setColor(itemFromDB.getColor());
                updatedItem.setQuantity(itemFromDB.getQuantity());
                /* And the iconID for the newly selected icon */
                updatedItem.setIconID(selectedIconID);

                /* If the item was already in inventory */
                if(nameOfSelectedItem != null){
                    /* UPDATE in database */

                    // 1. Delete the old version of the item from the db
                    db.deleteItem(nameOfSelectedItem);

                    // 2. Add the updated item to inventory
                    db.addItem(updatedItem);
                }

                /* If the item is a brand new item */
                else{
                    /* INSERT the updated Item into the database */
                    db.addItem(updatedItem);
                }

                /* And return to the Update Item Properties */
                /* Send the name of the item to Update Item Properties Screen */
                String nameOfSelectedItem = (String)parent.getItemAtPosition(position);
                Intent intent = new Intent(UpdateIconScreen.this, UpdateItemPropertiesScreen.class);
                intent.putExtra("NAME_OF_SELECTED_ITEM", nameOfSelectedItem);

                /* And launch the Update Item Properties screen */
                startActivity(intent);
            }
        });
    }
}
