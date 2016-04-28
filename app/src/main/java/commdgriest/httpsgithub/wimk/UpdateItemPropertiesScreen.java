package commdgriest.httpsgithub.wimk;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

/*
    Update Item Properties Screen
*/

public class UpdateItemPropertiesScreen extends VisualInventoryScreen implements android.view.View.OnClickListener{
    private String tempName;
    private int tempColor;
    private int tempQuantity;
    private int thisIconID;

    Button btnSave;
    Button btnDelete;
    Button btnCancelProp;
    EditText itemNameText;
    SeekBar quantitySeekBar;

    String nameOfSelectedItem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_properties_screen);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCancelProp = (Button) findViewById(R.id.btnCancel);
        itemNameText = (EditText) findViewById(R.id.itemNameText);
        quantitySeekBar = (SeekBar) findViewById(R.id.quantitySeekBar);

        String white = "#FFFFFF";
        String dark_gray = "#424242";

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.updateItemPropertiesRelativeLayout);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCancelProp.setOnClickListener(this);

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
        this.tempName = nameOfSelectedItem;
        this.tempColor = item.getColor();
        this.tempQuantity = item.getQuantity();
        this.thisIconID = item.getIconID();

        /* Set the icon according to iconID */
        int icon = imageId[item.getIconID()];

        quantitySeekBar.setProgress(tempQuantity);

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

        /* If color button white or pale blue is selected, set progress color dark for contrast */
        if(tempColor == 0 || tempColor == 2){
            setProgressColor(dark_gray);
        }
        /* Otherwise, set it to white */
        else{
            setProgressColor(white);
        }

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

//        addListenerOnRatingBar();

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

                String white = "#FFFFFF";
                String dark_gray = "#424242";

                /* If color button white or pale blue is selected, set progress color dark for contrast */
                if(tempColor == 0 || tempColor == 2){
                    setProgressColor(dark_gray);
                }
                /* Otherwise, set it to white */
                else{
                    setProgressColor(white);
                }

                /*set background color*/
                rl.setBackground(colors[index]);
            }
        });

        quantitySeekBar = (SeekBar) findViewById(R.id.quantitySeekBar);

        quantitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                tempQuantity = progressChanged;
            }
        });
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        /* Save */
        if (view == findViewById(R.id.btnSave)){
            /* Update this item's attributes and save the changes in the database */

            /* Get the item's new name from the edit text */
            String newName = itemNameText.getText().toString();

            /* First create a new item with the attributes we want to save */
            Item updatedItem = new Item();
            updatedItem.setName(newName);
            updatedItem.setColor(this.tempColor);
            updatedItem.setQuantity(quantitySeekBar.getProgress());
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

            /* And return to the Visual Inventory Screen */
            startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
        }

        /* If the user clicks DELETE */
        if (view == findViewById(R.id.btnDelete)){
            /* Prompt for confirmation */
            new AlertDialog.Builder(this)
                    .setTitle("Delete entry")
                    .setMessage("Are you sure you want to delete this item?")
                    /* If the user confirms, DELETE the item */
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            db.deleteItem(nameOfSelectedItem);
                            startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
                        }
                    })
                    /* Otherwise, do dismiss the dialog and do nothing */
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        /* Cancel */
        if (view == findViewById(R.id.btnCancel)){
            startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
        }
    }

    public void setProgressColor(String color){
        quantitySeekBar.getProgressDrawable().setColorFilter(new
                PorterDuffColorFilter(Color.parseColor(color), PorterDuff.Mode.MULTIPLY));
    }
}

