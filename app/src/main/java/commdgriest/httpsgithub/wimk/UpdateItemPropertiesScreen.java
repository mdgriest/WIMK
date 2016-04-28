package commdgriest.httpsgithub.wimk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UpdateItemPropertiesScreen extends VisualInventoryScreen implements android.view.View.OnClickListener{
    private int item_ID;
    private String tempName;
    private int tempColor;
    private float tempQuantity;
    private Item thisItem;
    private int thisIconID;

    Button btnSave;
    Button btnDelete;
    Button btnCancelProp;
    Button btnChangeIcon;
//    ImageView imgViewIcon;
    EditText itemNameText;
    RatingBar quantityRatingBar;

    Bundle savedInstanceState;
    String nameOfSelectedItem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_properties_screen);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCancelProp = (Button) findViewById(R.id.btnCancel);
//        btnChangeIcon = (Button) findViewById(R.id.btnSelectIcon);
//        imgViewIcon = (ImageView) findViewById(R.id.imgViewIcon);
        itemNameText = (EditText) findViewById(R.id.itemNameText);
        quantityRatingBar = (RatingBar) findViewById(R.id.quantityRatingBar);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.updateItemPropertiesRelativeLayout);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCancelProp.setOnClickListener(this);
//        btnChangeIcon.setOnClickListener(this);
//        imgViewIcon.setOnClickListener(this);

        String[] names = {
                "Apples2",
                "Bananas2",
                "Milk2",
                "Cheese2",
        };

        Integer[] imageId = {
                R.drawable.apple_by_creative_stall,
                R.drawable.bananas_by_fernando_affonso,
                R.drawable.milk_by_norbert_kucsera,
                R.drawable.cheese_by_edward_boatman,
        };

        /* Receive name of item that was clicked in VI Screen */
        Bundle extras = getIntent().getExtras();
        nameOfSelectedItem = extras.getString("NAME_OF_SELECTED_ITEM");

        /* Set the editText to hold the received item's name */
        if (nameOfSelectedItem != null) {
            itemNameText.setText(nameOfSelectedItem);
        } else {
            itemNameText.setText("New Item");
        }

        /* Get the item that was clicked in VI Screen */
        Item item = db.getItem(nameOfSelectedItem);

        /* Upon opening the screen, set all temporary values to the item's current values */
//        this.tempName = item.getName();
        this.tempName = nameOfSelectedItem;
        this.tempColor = item.getColor();
        this.tempQuantity = item.getQuantity();
        this.thisIconID = item.getIconID();

        /* Set the icon according to iconID */
        int icon = imageId[item.getIconID()];
//        imgViewIcon.setImageResource( icon );

        quantityRatingBar.setRating(tempQuantity);


        /* Create an array holding the ids of our radio buttons */
        int[] radioButtons = {
            (R.id.rdbtnColor00),
            (R.id.rdbtnColor01),
            (R.id.rdbtnColor02),
            (R.id.rdbtnColor03),
            (R.id.rdbtnColor04),
            (R.id.rdbtnColor05),
            (R.id.rdbtnColor06),
        };

        final RadioGroup radioColorGroup = (RadioGroup) findViewById(R.id.radioColorGroup);

        /* And set the appropriate button to checked upon opening the screen */
        radioColorGroup.check(radioButtons[tempColor]);

        final Drawable[] colors = {
            getResources().getDrawable(R.color.color0),
            getResources().getDrawable(R.color.color1),
            getResources().getDrawable(R.color.color2),
            getResources().getDrawable(R.color.color3),
            getResources().getDrawable(R.color.color4),
            getResources().getDrawable(R.color.color5),
            getResources().getDrawable(R.color.color6)
        };

        rl.setBackground(colors[tempColor]);

        addListenerOnRatingBar();

        radioColorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup rGroup, int checkedId) {

                RelativeLayout rl = (RelativeLayout) findViewById(R.id.updateItemPropertiesRelativeLayout);

                /*Get checked radio button id*/
                RadioButton checkedRadioButton = (RadioButton) rGroup.findViewById(checkedId);

                View radioButton = radioColorGroup.findViewById(checkedId);
                /*Get checked radio button index */
                int index = radioColorGroup.indexOfChild(radioButton);
                //showToast("index of radio btn: " + index);
                tempColor = index;

                /*set background color*/
                rl.setBackground(colors[index]);
            }
        });
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void addListenerOnRatingBar(){
        quantityRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tempQuantity = quantityRatingBar.getRating();
            }
        });
    }





    public void onClick(View view) {
        /* Save */
        if (view == findViewById(R.id.btnSave)){
            /* Update this item's attributes and save the changes in the database */

            /* Get the item's new name from the edit text */
            String newName = itemNameText.getText().toString();
//            Toast.makeText(this, "newName: " + newName, Toast.LENGTH_SHORT).show();

            /* First create a new item with the attributes we want to save */
            Item updatedItem = new Item();
//            updatedItem.setName(newName);
            updatedItem.setName(newName);
            updatedItem.setColor(this.tempColor);
            updatedItem.setQuantity(quantityRatingBar.getRating());
            updatedItem.setIconID(this.thisIconID);

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
            new AlertDialog.Builder(this)
                    .setTitle("Delete entry")
                    .setMessage("Are you sure you want to delete this item?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            /* Delete the item from the database */
                            db.deleteItem(nameOfSelectedItem);
                            startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            /* Delete the item from the database */
           // db.deleteItem(nameOfSelectedItem);
            /* And return to the VI Screen */
            //startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
        }

        /* Cancel */
        if (view == findViewById(R.id.btnCancel)){
            startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
        }

        /* Select Icon */
//        else if (view == findViewById(R.id.btnSelectIcon)){
//            /* Send the name of the item to Update Item Properties Screen */
//            Intent intent = new Intent(UpdateItemPropertiesScreen.this, UpdateIconScreen.class);
//            intent.putExtra("NAME_OF_SELECTED_ITEM", nameOfSelectedItem);
//
//            /* And launch the Update Item Properties screen */
//            startActivity(intent);
//        }
    }
}

