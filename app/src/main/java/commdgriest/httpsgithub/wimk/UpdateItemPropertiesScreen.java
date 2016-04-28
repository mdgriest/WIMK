package commdgriest.httpsgithub.wimk;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class UpdateItemPropertiesScreen extends VisualInventoryScreen implements android.view.View.OnClickListener{
    private int item_ID;
    private String tempName;
    private int tempColor;
    private int tempIconId;
    private float tempQuantity;
    private Item thisItem;

    Button btnSave;
    Button btnDelete;
    Button btnCancelProp;
    Button btnChangeIcon;
    ImageView imgViewIcon;
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
        btnChangeIcon = (Button) findViewById(R.id.btnSelectIcon);
        imgViewIcon = (ImageView) findViewById(R.id.imgViewIcon);
        itemNameText = (EditText) findViewById(R.id.itemNameText);
        quantityRatingBar = (RatingBar) findViewById(R.id.quantityRatingBar);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCancelProp.setOnClickListener(this);
        btnChangeIcon.setOnClickListener(this);
        imgViewIcon.setOnClickListener(this);

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

        /* Get the item that was clicked in VI Screen */
        Item item = db.getItem(nameOfSelectedItem);

        /* Upon opening the screen, set all temporary values to the item's current values */
        this.tempName = item.getName();
        this.tempColor = item.getColor();
        this.tempIconId = item.getIconID();
        this.tempQuantity = item.getQuantity();

        /* Set the icon according to iconID */
        int icon = imageId[ item.getIconID() ];
        imgViewIcon.setImageResource( icon );

        quantityRatingBar.setRating(tempQuantity);

        addListenerOnRatingBar();
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
            this.tempName = newName;
//            Toast.makeText(this, "newName: " + newName, Toast.LENGTH_SHORT).show();

            /* First create a new item with the attributes we want to save */
            Item updatedItem = new Item();
            updatedItem.setName(this.tempName.toString());
            updatedItem.setColor(this.tempColor);
            updatedItem.setQuantity(quantityRatingBar.getRating());
            updatedItem.setIconID(this.tempIconId);

            Toast.makeText(this, "temQuantity: " + tempQuantity, Toast.LENGTH_SHORT).show();

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
            startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
        }

        /* Select Icon */
        else if (view == findViewById(R.id.btnSelectIcon)){
            /* Send the name of the item to Update Item Properties Screen */
            Intent intent = new Intent(UpdateItemPropertiesScreen.this, UpdateIconScreen.class);
            intent.putExtra("NAME_OF_SELECTED_ITEM", nameOfSelectedItem);

            /* And launch the Update Item Properties screen */
            startActivity(intent);
        }
    }
}

