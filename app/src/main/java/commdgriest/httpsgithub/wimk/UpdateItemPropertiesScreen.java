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
    private Color tempColor;
    private String tempName;
    private int tempIconId;
    private int tempQuantity;
    private String TempItemName;
    private int item_ID = 0;

    Button btnSave;
    Button btnDelete;
    Button btnCancelProp;
    Button btnChangeIcon;
    Button btnIcon;
    Button btnSetItemName;
    EditText itemNameText;

    //TEMPORARY, TODO REMOVE AFTER FINISHED TESTING

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_properties_screen);

        /* Item Quantity Graph */
//        // See http://www.android-graphview.org/documentation/category/bar-graph
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


        item_ID = 0;
        Intent intent = getIntent();
        item_ID = intent.getIntExtra("item_Id", 0);
        DatabaseDML db = new DatabaseDML(this);
        Item item = new Item();
        item = db.getItemById(item_ID);

        itemNameText.setText(String.valueOf(item.name));

    }
    public void onClick(View view) {


        if (view == findViewById(R.id.btnSave)){
            DatabaseDML db = new DatabaseDML(this);
            Item itemToUpdate = new Item();
            itemToUpdate.name = itemNameText.getText().toString();
            itemToUpdate.id= item_ID;
            //TODO adding Default values for the rest of the poperties to Test, should change in future
            itemToUpdate.iconId = 0;
            itemToUpdate.quantity = 0;
            itemToUpdate.color = 0;
            itemToUpdate.shouldShow = true;
            //meaning it was a new item
            if (item_ID==0){
                item_ID = db.insert(itemToUpdate);

                Toast.makeText(this,"New Item Inserted",Toast.LENGTH_SHORT).show();
            }else{

                db.update(itemToUpdate);
                Toast.makeText(this,"Item updated",Toast.LENGTH_SHORT).show();
            }
            finish();
        }else if (view== findViewById(R.id.btnDelete)){
            DatabaseDML db = new DatabaseDML(this);
            db.delete(item_ID);

            //TODO add functionality to ask are you sure? yes or no
            Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnCancel)){
            finish();
        }else if (view== findViewById(R.id.btnSelectIcon)){
            startActivity(new Intent(UpdateItemPropertiesScreen.this, UpdateIconScreen.class));
        }else if (view== findViewById(R.id.btnSetQuantity)){
            startActivity(new Intent(UpdateItemPropertiesScreen.this, UpdateItemQuantityScreen.class));
        }
        else if (view== findViewById(R.id.btnSetItemName)) {
         /* Make the textView visible so the user can enter a query */
            itemNameText.setVisibility(View.VISIBLE);
                /* Shift focus to the textView to avoid making the user manually click on it */
            itemNameText.requestFocus();
                /* Launch the keyboard for user input */
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(itemNameText, InputMethodManager.SHOW_IMPLICIT);
        }

//      TODO : need to add items in itemsList to the VI
        //http://stackoverflow.com/questions/10899335/adding-user-input-from-edit-text-into-list-view
    }

//
//
//    /* When Save button is clicked, go back to Visual Inventory activity with new changes */
//
//        Button btnSave = (Button) findViewById(R.id.btnSave);
//        btnSave.requestFocus();
//        //need functionality to actually update the item selected!
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
//
//                //JENNA
//                //once save is clicked, overwrite temp variables
//                //except instead of creating a new item, need to find the item id of the item we want to update
//                //I think the way we do that is grab the id of the item that is clicked on VI screen, pass that to View Item Screen
//                //then pass that to update Item properties??
//                //Item x = new Item();
//                // x.setName(tempName);
//
//            }
//        });
//
//        /* When Delete button is clicked, go back to Visual Inventory activity with new changes */
//        Button btnDelete = (Button) findViewById(R.id.btnDelete);
//        btnDelete.requestFocus();
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
//
//
//            }
//        });
//
//        /* When Cancel button is clicked, go back to Visual Inventory activity with no changes made */
//        Button btnCancelProp = (Button) findViewById(R.id.btnCancel);
//        btnCancelProp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
//            }
//        });
//
//        /* When Change Icon button is clicked, goes to Change Icon activity */
//        Button btnChangeIcon = (Button) findViewById(R.id.btnSelectIcon);
//        btnChangeIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(UpdateItemPropertiesScreen.this, UpdateIconScreen.class));
//            }
//        });
//
//        /* When Icon is clicked, goes to Update Item Quantity activity */
//        Button btnIcon = (Button) findViewById(R.id.btnSetQuantity);
//        btnIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(UpdateItemPropertiesScreen.this, UpdateItemQuantityScreen.class));
//            }
//        });
//
//        /* Handle color radio button changes */
//        RadioGroup radioColorGroup = (RadioGroup) findViewById(R.id.radioColorGroup);
//
//        radioColorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            RelativeLayout rl = (RelativeLayout) findViewById(R.id.updateItemPropertiesRelativeLayout);
//
//            public void onCheckedChanged(RadioGroup rGroup, int checkedId) {
//
//                /* Get the radioButton that has changed in its check state */
//                RadioButton checkedRadioButton = (RadioButton) rGroup.findViewById(checkedId);
//
//                /* Put the value (true/false) into the variable */
//                boolean isChecked = checkedRadioButton.isChecked();
//
//                /* If the radioButton that has changed in check state is now checked */
//                if (isChecked) {
//                    /* Change the layout background color (temporary, we will change something else in the future but I am happy this works) */
////                    rl.setBackground(checkedRadioButton.getBackground());
//                }
//            }
//        });
//
//        /* When Set Item Name button is clicked, launch the keyboard to type */
//        Button btnSetItemName = (Button) findViewById(R.id.btnSetItemName);
//        btnSetItemName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText itemNameText = (EditText) findViewById(R.id.itemNameText);
//                /* Make the textView visible so the user can enter a query */
//                itemNameText.setVisibility(View.VISIBLE);
//                /* Shift focus to the textView to avoid making the user manually click on it */
//                itemNameText.requestFocus();
//                /* Launch the keyboard for user input */
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(itemNameText, InputMethodManager.SHOW_IMPLICIT);
//
//                /* Capture the content typed into that input field */
//                Log.v("EditText", itemNameText.getText().toString());
//
//                /* Store the name as part of the "item object" to be added to itemsList */
//                tempName = itemNameText.getText().toString();
//            }
//        });
//


}

