package commdgriest.httpsgithub.wimk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class UpdateItemPropertiesScreen extends VisualInventoryScreen implements android.view.View.OnClickListener{
    private int item_ID;
    private String tempName;
    private int tempColor;
    private int tempIconId;
    private int tempQuantity;
    private Item thisItem;

    Button btnSave;
    Button btnDelete;
    Button btnCancelProp;
    Button btnChangeIcon;
    Button btnIcon;
    Button btnSetItemName;
    EditText itemNameText;

    Bundle savedInstanceState;
    String nameOfSelectedItem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_properties_screen);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCancelProp = (Button) findViewById(R.id.btnCancel);
        btnChangeIcon = (Button) findViewById(R.id.btnSelectIcon);
        btnIcon = (Button) findViewById(R.id.btnSetQuantity);
        itemNameText = (EditText) findViewById(R.id.itemNameText);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCancelProp.setOnClickListener(this);
        btnChangeIcon.setOnClickListener(this);
        btnIcon.setOnClickListener(this);

        /* Receive name of item that was clicked in VI Screen */
        Bundle extras = getIntent().getExtras();
        nameOfSelectedItem = extras.getString("NAME_OF_SELECTED_ITEM");

        /* Set the editText to hold the received item's name */
        if( nameOfSelectedItem != null){
            itemNameText.setText(nameOfSelectedItem);
        }
        else{
            itemNameText.setText("New Item");
        }

        //TODO using default values when opening the screen for now, need to get the item we are updating in the future
        Item item = new Item();

        /* Upon opening the screen, set all temporary values to the item's current values */
        this.tempName = item.getName();
        this.tempColor = item.getColor();
        this.tempQuantity = item.getQuantity();
        this.tempIconId = item.getIconID();
    }

    public void onClick(View view) {
        /* Save */
        if (view == findViewById(R.id.btnSave)){
            /* Update this item's attributes and save the changes in the database */

            /* Get the item's new name from the edit text */
            String newName = itemNameText.getText().toString();
            this.tempName = newName;
//            Toast.makeText(this, "newName: " + newName, Toast.LENGTH_SHORT).show();

            /* First create a new item with the attributes we want to save */
            Item updatedItem = new Item();
            updatedItem.setName(this.tempName.toString());
            updatedItem.setColor(this.tempColor);
            updatedItem.setQuantity(this.tempQuantity);
            updatedItem.setIconID(this.tempIconId);

            /* If the item was already in inventory */
            if(this.nameOfSelectedItem != null){
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

            //TODO if the item is not actually new, UPDATE item in the databse

            /* And return to the Visual Inventory Screen */
            startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
        }

        /* Delete */
        if (view == findViewById(R.id.btnDelete)){
            /* Delete the item from the database */
            db.deleteItem(nameOfSelectedItem);
            /* And return to the VI Screen */
            startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
        }

        /* Cancel */
        if (view == findViewById(R.id.btnCancel)){
            finish();
        }

        /* Select Icon */
        else if (view == findViewById(R.id.btnSelectIcon)){
            startActivity(new Intent(UpdateItemPropertiesScreen.this, UpdateIconScreen.class));
        }

        /* Set Quantity */
        else if (view == findViewById(R.id.btnSetQuantity)){
            startActivity(new Intent(UpdateItemPropertiesScreen.this, UpdateItemQuantityScreen.class));
        }

//      http://stackoverflow.com/questions/10899335/adding-user-input-from-edit-text-into-list-view
    }

}

