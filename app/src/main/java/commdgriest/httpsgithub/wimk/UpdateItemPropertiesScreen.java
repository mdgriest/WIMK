package commdgriest.httpsgithub.wimk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Alex on 3/22/16.
 */
public class UpdateItemPropertiesScreen extends VisualInventoryScreen{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_properties_screen);

        /* When Save button is clicked, go back to Visual Inventory activity with new changes */
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnSaveProp = (Button) findViewById(R.id.btnSaveProp);
        //need functionality to actually update the item selected!
        btnSaveProp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
            }
        });

        /* When Cancel button is clicked, go back to Visual Inventory activity with no changes made */
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnCancelProp = (Button) findViewById(R.id.btnCancelProp);
        btnCancelProp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateItemPropertiesScreen.this, VisualInventoryScreen.class));
            }
        });

        /* When Change Icon button is clicked, goes to Change Icon activity */
        //Based on: http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
        Button btnChangeIcon = (Button) findViewById(R.id.btnChangeIcon);
        btnChangeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateItemPropertiesScreen.this, UpdateIconScreen.class));
            }
        });

        //needed functionality: whatever is typed into the plaintext field should be saved as the new item name

    }

}