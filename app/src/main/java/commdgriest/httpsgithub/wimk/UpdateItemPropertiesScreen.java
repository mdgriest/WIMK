package commdgriest.httpsgithub.wimk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_properties_screen);


        /* When Save button is clicked, go back to Visual Inventory activity with new changes */
        Button btnSaveProp = (Button) findViewById(R.id.btnSave);
        //need functionality to actually update the item selected!
        btnSaveProp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overwriteItemAttributes();
                startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
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
                RadioButton checkedRadioButton = (RadioButton)rGroup.findViewById(checkedId);

                /* Put the value (true/false) into the variable */
                boolean isChecked = checkedRadioButton.isChecked();

                /* If the radioButton that has changed in check state is now checked */
                if(isChecked) {
                    /* Change the layout background color (temporary, we will change something else in the future but I am happy this works) */
                    rl.setBackground(checkedRadioButton.getBackground());
                }
            }
        });

        //TODO Whatever is typed into the plaintext field should be saved as the new item name
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
                break;
        }
    }

    //when save is clicked, save temp values
    public static void overwriteItemAttributes(){


    }

}