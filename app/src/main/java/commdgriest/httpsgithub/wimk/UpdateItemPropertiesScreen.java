package commdgriest.httpsgithub.wimk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

/**
 * Created by Alex on 3/22/16.
 */
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_properties_screen);

        /* Item Quantity Graph */
//      See http://www.android-graphview.org/documentation/category/bar-graph
        GraphView graphItemQuantity = (GraphView) findViewById(R.id.graphItemQuantity);
        GridLabelRenderer glr = (GridLabelRenderer) graphItemQuantity.getGridLabelRenderer();
        BarGraphSeries<DataPoint> seriesItemQuantity = new BarGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 5),
        });

        /* Do not show axis labels */
        glr.setHorizontalLabelsVisible(false);
        glr.setVerticalLabelsVisible(false);

        /* Do not show the grid */
        glr.setGridStyle(GridLabelRenderer.GridStyle.NONE);

        /* Set the color of the bar for now */
        seriesItemQuantity.setColor(getResources().getColor(R.color.avocado));
        graphItemQuantity.addSeries(seriesItemQuantity);
        //TODO fix the bar so that it occupies the full width of the icon
        //TODO get the series color to update based on which radio button from the group below is checked

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCancelProp = (Button) findViewById(R.id.btnCancel);
        btnChangeIcon = (Button) findViewById(R.id.btnSelectIcon);
        btnIcon = (Button) findViewById(R.id.btnSetQuantity);
        btnSetItemName = (Button) findViewById(R.id.btnSetItemName);
        itemNameText = (EditText) findViewById(R.id.itemNameText);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCancelProp.setOnClickListener(this);
        btnChangeIcon.setOnClickListener(this);
        btnIcon.setOnClickListener(this);
        btnSetItemName.setOnClickListener(this);

        /* Receive itemID */
        Bundle extras = getIntent().getExtras();
        long item_ID = extras.getLong("item_Id");

        Toast.makeText(this, "item_ID: " + item_ID, Toast.LENGTH_SHORT).show();

        /* Find the item we are updating in the database */
        Item item = db.getItem(item_ID);

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
            Toast.makeText(this, "newName: " + newName, Toast.LENGTH_SHORT).show();

            /* First create a new item with the attributes we want to save */
            Item updatedItem = new Item();
            updatedItem.setName(this.tempName.toString());
            updatedItem.setColor(this.tempColor);
            updatedItem.setQuantity(this.tempQuantity);
            updatedItem.setIconID(this.tempIconId);

            /* Update the item in the database */
            db.updateItem(updatedItem);

            /* And return to the Visual Inventory Screen */
            startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
        }

        /* Delete */

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

        /* Set Item Name */
        else if (view == findViewById(R.id.btnSetItemName)) {
         /* Make the textView visible so the user can enter a query */
            itemNameText.setVisibility(View.VISIBLE);
                /* Shift focus to the textView to avoid making the user manually click on it */
            itemNameText.requestFocus();
                /* Launch the keyboard for user input */
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(itemNameText, InputMethodManager.SHOW_IMPLICIT);
        }
//      TODO : need to add items in itemsList to the VI
//      http://stackoverflow.com/questions/10899335/adding-user-input-from-edit-text-into-list-view
    }

}

