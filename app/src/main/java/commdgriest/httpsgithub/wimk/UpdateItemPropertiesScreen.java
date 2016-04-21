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

/**
 * Created by Alex on 3/22/16.
 */
public class UpdateItemPropertiesScreen extends VisualInventoryScreen{
    private Color tempColor;
    private String tempName;
    private int tempIconId;
    private int tempQuantity;
    private String TempItemName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_properties_screen);


        /* When Save button is clicked, go back to Visual Inventory activity with new changes */
        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.requestFocus();
        //need functionality to actually update the item selected!
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overwriteItemAttributes();
                startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));

                //JENNA
                //once save is clicked, overwrite temp variables
                //except instead of creating a new item, need to find the item id of the item we want to update
                //I think the way we do that is grab the id of the item that is clicked on VI screen, pass that to View Item Screen
                //then pass that to update Item properties??
                //Item x = new Item();
                // x.setName(tempName);

            }
        });

        /* When Cancel button is clicked, go back to Visual Inventory activity with no changes made */
        Button btnCancelProp = (Button) findViewById(R.id.btnCancel);
        btnCancelProp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
            }
        });

        /* When Change Icon button is clicked, goes to Change Icon activity */
        Button btnChangeIcon = (Button) findViewById(R.id.btnSelectIcon);
        btnChangeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateItemPropertiesScreen.this, UpdateIconScreen.class));
            }
        });

        /* When Icon is clicked, goes to Update Item Quantity activity */
        Button btnIcon = (Button) findViewById(R.id.btnSetQuantity);
        btnIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateItemPropertiesScreen.this, UpdateItemQuantityScreen.class));
            }
        });

        /* Handle color radio button changes */
        RadioGroup radioColorGroup = (RadioGroup) findViewById(R.id.radioColorGroup);

        radioColorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.updateItemPropertiesRelativeLayout);

            public void onCheckedChanged(RadioGroup rGroup, int checkedId) {

                /* Get the radioButton that has changed in its check state */
                RadioButton checkedRadioButton = (RadioButton) rGroup.findViewById(checkedId);

                /* Put the value (true/false) into the variable */
                boolean isChecked = checkedRadioButton.isChecked();

                /* If the radioButton that has changed in check state is now checked */
                if (isChecked) {
                    /* Change the layout background color (temporary, we will change something else in the future but I am happy this works) */
                    rl.setBackground(checkedRadioButton.getBackground());
                }
            }
        });

        /* When Set Item Name button is clicked, launch the keyboard to type */
        Button btnSetItemName = (Button) findViewById(R.id.btnSetItemName);
        btnSetItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText itemNameText = (EditText) findViewById(R.id.itemNameText);
                /* Make the textView visible so the user can enter a query */
                itemNameText.setVisibility(View.VISIBLE);
                /* Shift focus to the textView to avoid making the user manually click on it */
                itemNameText.requestFocus();
                /* Launch the keyboard for user input */
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(itemNameText, InputMethodManager.SHOW_IMPLICIT);

                /* Capture the content typed into that input field */
                Log.v("EditText", itemNameText.getText().toString());

                /* Store the name as part of the "item object" to be added to itemsList */
                tempName = itemNameText.getText().toString();
            }
        });
            // TODO : need to add items in itemsList to the VI
            //http://stackoverflow.com/questions/10899335/adding-user-input-from-edit-text-into-list-view
    }

    /* Set color based on radio group selection */
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.updateItemPropertiesRelativeLayout);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rdbtnColor00:
                if (checked)
                    rl.setBackgroundColor(getResources().getColor(R.color.red));
                    //set tempColor to red
                break;
        }
    }

    //when save is clicked, save temp values
    public void overwriteItemAttributes(){


    }

}